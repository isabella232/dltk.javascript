/*******************************************************************************
 * Copyright (c) 2010 xored software, Inc.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Vladislav Kuzkokov)
 *******************************************************************************/
package org.eclipse.dltk.javascript.core.dom.rewrite;

import static org.eclipse.jface.text.TextUtilities.determineLineDelimiter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.dltk.compiler.util.Util;
import org.eclipse.dltk.javascript.ast.MultiLineComment;
import org.eclipse.dltk.javascript.core.dom.BinaryExpression;
import org.eclipse.dltk.javascript.core.dom.BinaryOperator;
import org.eclipse.dltk.javascript.core.dom.CatchClause;
import org.eclipse.dltk.javascript.core.dom.DomPackage;
import org.eclipse.dltk.javascript.core.dom.FunctionExpression;
import org.eclipse.dltk.javascript.core.dom.Node;
import org.eclipse.dltk.javascript.core.dom.Source;
import org.eclipse.dltk.javascript.core.dom.Statement;
import org.eclipse.dltk.javascript.core.dom.TryStatement;
import org.eclipse.dltk.javascript.core.dom.UnaryExpression;
import org.eclipse.dltk.javascript.core.dom.UnaryOperator;
import org.eclipse.dltk.javascript.core.dom.VariableDeclaration;
import org.eclipse.dltk.javascript.core.dom.util.DomSwitch;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.ChangeKind;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.text.edits.DeleteEdit;
import org.eclipse.text.edits.InsertEdit;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;

public class RewriteAnalyzer extends DomSwitch<Boolean> {
	private final ChangeDescription cd;
	private final Set<Node> generated = new HashSet<Node>();
	private final String text;
	protected final String lineDelimiter;
	private final TextEdit edit;
	private MultiLineComment[] comments;

	public RewriteAnalyzer(ChangeDescription cd, String text) {
		this(cd, text, determineLineDelimiter(text, Util.LINE_SEPARATOR));
	}

	public RewriteAnalyzer(ChangeDescription cd, String text,
			String lineDelimiter) {
		this.cd = cd;
		this.text = text;
		this.lineDelimiter = lineDelimiter;
		this.edit = new MultiTextEdit();
	}

	public void rewrite(Source source) {
		final CommentContainer container = (CommentContainer) EcoreUtil
				.getExistingAdapter(source, CommentContainer.class);
		this.comments = container != null ? container.comments : null;
		rewrite((Node) source);
	}

	void rewrite(Node node) {
		doSwitch(node);
		for (EObject obj : node.eContents())
			if (!generated.contains(obj))
				rewrite((Node) obj);
	}

	public TextEdit getEdit() {
		return edit;
	}

	protected void addEdit(TextEdit edit, Node node) {
		this.edit.addChild(edit);
	}

	// Processes EReference only
	private void processFeature(Node node, FeatureChange fc) {
		if (fc.getFeature() instanceof EAttribute) {
			final EAttribute attribute = (EAttribute) fc.getFeature();
			if (!attribute.isMany()
					&& attribute.getEAttributeType() == EcorePackage.Literals.ESTRING
					&& EcoreUtil.getAnnotation(attribute, null, "value") != null) {
				addEdit(new ReplaceEdit(node.getBegin(), node.getEnd()
						- node.getBegin(), (String) node.eGet(attribute)), node);
			}
			return;
		}
		if (!fc.getFeature().isMany()) {
			Node n = (Node) node.eGet(fc.getFeature());
			Node o = (Node) fc.getReferenceValue();
			int off = o == null ? calcOffset(node, fc.getFeature()) : o
					.getBegin();
			int len = o == null ? 0 : o.getEnd() - o.getBegin();
			String value = n == null ? "" : generate(n, node, o == null, off);
			addEdit(new ReplaceEdit(off, len, value), n);
			return;
		}
		@SuppressWarnings("unchecked")
		EList<? extends Node> dst = (EList<? extends Node>) node.eGet(fc
				.getFeature());
		EList<Object> src = new BasicEList<Object>();
		src.addAll(dst);
		Set<Node> deleted = new HashSet<Node>();
		Set<Node> generated = new HashSet<Node>();
		for (ListChange lc : fc.getListChanges()) {
			if (lc.getKind() != ChangeKind.ADD_LITERAL)
				generated.add((Node) src.get(lc.getIndex()));
			if (lc.getKind() == ChangeKind.MOVE_LITERAL)
				deleted.add((Node) src.get(lc.getIndex()));
			lc.apply(src);
			if (lc.getKind() == ChangeKind.ADD_LITERAL)
				deleted.add((Node) src.get(lc.getIndex()));
		}
		if (fc.getListChanges().isEmpty()) {
			src.clear();
			generated.addAll(dst);
		}
		List<Node> original = new ArrayList<Node>(src.size());
		for (Object obj : src)
			original.add((Node) obj);
		Node last = null;
		for (Node item : original)
			if (!deleted.contains(item))
				last = item;

		// DELETING ELEMENTS
		// 1) general case: a,(b,)c,(d,)(e,)f
		// 2) at the end: a,(b,)c,d(,e)(,f)
		// 3) all of it: (a)(,b)(,c)
		// we delete element with following separator so that this will work
		// with statements and line feeds/semicolons.
		boolean isLast = last == null;
		for (int i = 0; i < original.size(); i++) {
			Node item = original.get(i);
			if (deleted.contains(item)) {
				int off = isLast && i != 0 ? original.get(i - 1).getEnd()
						: getBegin(item);
				int end = isLast ? item.getEnd()
						: getBegin(original.get(i + 1));
				addEdit(new DeleteEdit(off, end - off), item);
			} else
				isLast = item == last;
		}

		// OFFSETS
		// each element is added at the beginning of the next element
		// or at the end
		List<Integer> offs = new ArrayList<Integer>();
		int cur = original.isEmpty() ? calcOffset(node, fc.getFeature())
				: original.get(original.size() - 1).getEnd();
		for (int i = dst.size() - 1; i >= 0; i--) {
			Node item = dst.get(i);
			if (!generated.contains(item)) {
				cur = item.getBegin();
			}
			offs.add(cur);
		}
		Collections.reverse(offs);

		// ADDING ELEMENTS
		// adding is done by the same rules as deleting
		// cases 1-3 are processed in generateElement
		isLast = last == null;
		for (int i = 0; i < dst.size(); i++) {
			Node item = dst.get(i);
			if (generated.contains(item)) {
				int off = offs.get(i);
				addEdit(new InsertEdit(off, generateElement(item, i == 0,
						isLast, off)), item);
			} else
				isLast = item == last;
		}
	}

	private int getBegin(Node node) {
		int value = node.getBegin();
		if (comments != null) {
			final MultiLineComment comment = findComment(value);
			if (comment != null) {
				for (int i = comment.end(); i < value; ++i) {
					if (!Character.isWhitespace(text.charAt(i))) {
						return value;
					}
				}
				return comment.start();
			}
		}
		return value;
	}

	private MultiLineComment findComment(int value) {
		int low = 0;
		int high = comments.length;
		while (low < high) {
			final int mid = (low + high) >>> 1;
			final MultiLineComment comment = comments[mid];
			final int end = comment.end();
			if (end > value) {
				high = mid;
			} else if (end < value) {
				low = mid + 1;
			} else {
				return comment;
			}
		}
		return low > 0 ? comments[low - 1] : null;
	}

	@Override
	public Boolean caseNode(Node node) {
		if (cd.getObjectChanges().get(node) != null)
			for (FeatureChange fc : cd.getObjectChanges().get(node))
				processFeature(node, fc);
		return true;
	}

	@Override
	public Boolean caseUnaryExpression(UnaryExpression node) {
		if (cd.getObjectChanges().get(node) != null)
			for (FeatureChange fc : cd.getObjectChanges().get(node))
				if (fc.getFeature() == DomPackage.Literals.UNARY_EXPRESSION__OPERATION) {
					UnaryOperator n = node.getOperation();
					UnaryOperator o = (UnaryOperator) fc.getValue();
					int len = o.toString().length();
					if (isPostfix(o))
						addEdit(new DeleteEdit(node.getEnd() - len, len), node);
					else
						addEdit(new DeleteEdit(node.getBegin(), len), node);
					if (isPostfix(n))
						addEdit(new InsertEdit(node.getEnd(), n.toString()),
								node);
					else {
						String r = n.toString();
						if (isTextUnary(n))
							r += ' ';
						addEdit(new InsertEdit(node.getBegin(), r), node);
					}
				} else
					processFeature(node, fc);
		return true;
	}

	static boolean isPostfix(Object op) {
		return op == UnaryOperator.POSTFIX_INC
				|| op == UnaryOperator.POSTFIX_DEC;
	}

	static boolean isTextUnary(Object op) {
		return op == UnaryOperator.DELETE || op == UnaryOperator.VOID
				|| op == UnaryOperator.TYPEOF || op == UnaryOperator.YIELD;
	}

	@Override
	public Boolean caseBinaryExpression(BinaryExpression node) {
		if (cd.getObjectChanges().get(node) != null)
			for (FeatureChange fc : cd.getObjectChanges().get(node))
				if (fc.getFeature() == DomPackage.Literals.BINARY_EXPRESSION__OPERATION) {
					String r = node.getOperation().toString();
					if (isTextBinary(fc.getValue()))
						r = ' ' + r + ' ';
					addEdit(new ReplaceEdit(node.getOperatorPosition(), fc
							.getValue().toString().length(), r), node);
				} else
					processFeature(node, fc);
		return true;
	}

	static boolean isTextBinary(Object op) {
		return op == BinaryOperator.IN || op == BinaryOperator.INSTANCEOF;
	}

	// calculates offset for null references and empty lists
	private int calcOffset(Node node, EStructuralFeature sf) {
		EReference ref = (EReference) sf;
		switch (ref.getEContainingClass().getClassifierID()) {
		case DomPackage.SOURCE:
			return node.getBegin();
		case DomPackage.BLOCK_STATEMENT:
			return node.getEnd() - 1; // skip right brace
		case DomPackage.VARIABLE_STATEMENT:
			return node.getBegin() + 3; // skip "var"
		case DomPackage.CONST_STATEMENT:
			return node.getBegin() + 5; // skip "const"
		case DomPackage.VARIABLE_DECLARATION:
		case DomPackage.IF_STATEMENT:
		case DomPackage.CONTINUE_STATEMENT:
		case DomPackage.BREAK_STATEMENT:
		case DomPackage.RETURN_STATEMENT:
		case DomPackage.SWITCH_ELEMENT:
		case DomPackage.PARAMETER:
			return node.getEnd();
		case DomPackage.CALL_EXPRESSION:
			return node.getEnd() - 1;
		case DomPackage.SWITCH_STATEMENT:
			throw new IllegalStateException("Empty switch statement");
		case DomPackage.CATCH_CLAUSE:
			CatchClause cc = (CatchClause) node;
			return cc.getException().getEnd();
		case DomPackage.TRY_STATEMENT:
			TryStatement ts = (TryStatement) node;
			if (ts.getFinallyClause() != null)
				return ts.getFinallyClause().getBegin();
			return node.getEnd();
		case DomPackage.FUNCTION_EXPRESSION:
			FunctionExpression expr = (FunctionExpression) node;
			if (ref == DomPackage.Literals.FUNCTION_EXPRESSION__IDENTIFIER)
				return expr.getParametersPosition() - 1;
			else
				return expr.getParametersPosition();
		}
		return -1;
	}

	static abstract class NodeSeparator {
		public abstract void appendTo(Generator gen);
	}

	static final NodeSeparator NEWLINE = new NodeSeparator() {
		@Override
		public void appendTo(Generator gen) {
			gen.newLine();
		}
	};

	static final NodeSeparator COMMA = new NodeSeparator() {
		@Override
		public void appendTo(Generator gen) {
			gen.append(",");
		}
	};

	static final NodeSeparator COMMA_NEWLINE = new NodeSeparator() {
		@Override
		public void appendTo(Generator gen) {
			gen.append(",");
			gen.newLine();
		}
	};

	public String generateElement(Node node, boolean first, boolean last,
			int pos) {
		Generator gen = new Generator(cd, text, pos, lineDelimiter);
		final NodeSeparator separator;
		if (node instanceof Statement) {
			separator = NEWLINE;
		} else if (node instanceof VariableDeclaration
				&& ((VariableDeclaration) node).getInitializer() != null) {
			separator = COMMA_NEWLINE;
		} else {
			separator = COMMA;
		}
		if (!first && last)
			separator.appendTo(gen);
		gen.generate(node);
		if (!last)
			separator.appendTo(gen);
		generated.add(node);
		return gen.toString();
	}

	public String generate(Node node, Node parent, boolean wasNull, int pos) {
		Generator gen = new Generator(cd, text, pos, lineDelimiter);
		if (wasNull
				&& parent.eClass() == DomPackage.Literals.VARIABLE_DECLARATION)
			gen.append("=");
		if (wasNull
				&& parent.eClass() == DomPackage.Literals.FUNCTION_EXPRESSION)
			gen.append(" ");
		if (wasNull
				&& node.eContainmentFeature() == DomPackage.Literals.CATCH_CLAUSE__FILTER)
			gen.append(" if ");
		gen.generate(node);
		generated.add(node);
		return gen.toString();
	}
}
