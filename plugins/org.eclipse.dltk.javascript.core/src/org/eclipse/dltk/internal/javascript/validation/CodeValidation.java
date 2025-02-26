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
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.validation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.compiler.problem.ProblemSeverity;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.javascript.ast.AbstractNavigationVisitor;
import org.eclipse.dltk.javascript.ast.BinaryOperation;
import org.eclipse.dltk.javascript.ast.BreakStatement;
import org.eclipse.dltk.javascript.ast.ContinueStatement;
import org.eclipse.dltk.javascript.ast.Expression;
import org.eclipse.dltk.javascript.ast.FunctionStatement;
import org.eclipse.dltk.javascript.ast.GetArrayItemExpression;
import org.eclipse.dltk.javascript.ast.Identifier;
import org.eclipse.dltk.javascript.ast.Keywords;
import org.eclipse.dltk.javascript.ast.Label;
import org.eclipse.dltk.javascript.ast.LabelledStatement;
import org.eclipse.dltk.javascript.ast.LoopStatement;
import org.eclipse.dltk.javascript.ast.ObjectInitializer;
import org.eclipse.dltk.javascript.ast.ObjectInitializerPart;
import org.eclipse.dltk.javascript.ast.PropertyExpression;
import org.eclipse.dltk.javascript.ast.PropertyInitializer;
import org.eclipse.dltk.javascript.ast.Script;
import org.eclipse.dltk.javascript.ast.UnaryOperation;
import org.eclipse.dltk.javascript.core.JavaScriptProblems;
import org.eclipse.dltk.javascript.parser.JSParser;
import org.eclipse.dltk.javascript.parser.Reporter;
import org.eclipse.osgi.util.NLS;

public class CodeValidation extends AbstractNavigationVisitor<Object> implements
		IBuildParticipant {
	private Reporter reporter;
	private Scope scope;

	static class LabelInfo {
		final LabelledStatement statement;
		boolean finished;

		public LabelInfo(LabelledStatement statement) {
			this.statement = statement;
		}

	}

	static class Scope {

		private final Map<String, LabelInfo> labels = new HashMap<String, LabelInfo>();

		public boolean addLabel(LabelledStatement statement) {
			final String label = statement.getLabel().getText();
			if (labels.containsKey(label)) {
				return false;
			}
			labels.put(label, new LabelInfo(statement));
			return true;
		}

		public LabelInfo getLabel(String label) {
			return labels.get(label);
		}

		public void removeLabel(LabelledStatement statement) {
			LabelInfo info = labels.get(statement.getLabel().getText());
			if (info != null) {
				info.finished = true;
			}
		}
	}

	public void build(IBuildContext context) throws CoreException {
		final Script script = JavaScriptValidations.parse(context);
		if (script == null) {
			return;
		}
		reporter = JavaScriptValidations.createReporter(context);
		scope = new Scope();
		visit(script);
	}

	@Override
	public Object visitFunctionStatement(FunctionStatement node) {
		final Scope savedScope = scope;
		scope = new Scope();
		final Object result = super.visitFunctionStatement(node);
		scope = savedScope;
		return result;
	}

	@Override
	public Object visitLabelledStatement(LabelledStatement node) {
		boolean added = scope.addLabel(node);
		final Object result = super.visitLabelledStatement(node);
		if (added) {
			scope.removeLabel(node);
		}
		return result;
	}

	@Override
	public Object visitBreakStatement(BreakStatement node) {
		if (node.getLabel() != null) {
			validateLabel(node.getLabel(), node.sourceStart(), JSParser.BREAK);
		}
		return super.visitBreakStatement(node);
	}

	@Override
	public Object visitContinueStatement(ContinueStatement node) {
		if (node.getLabel() != null) {
			validateLabel(node.getLabel(), node.sourceStart(),
					JSParser.CONTINUE);
		}
		return super.visitContinueStatement(node);
	}

	private void validateLabel(Label label, int statementStart, int token) {
		final LabelInfo info = scope.getLabel(label.getText());
		if (info == null) {
			return;
		}
		if (info.finished) {
			reporter.setMessage(
					token == JSParser.BREAK ? JavaScriptProblems.BREAK_OUTSIDE_LABEL
							: JavaScriptProblems.CONTINUE_OUTSIDE_LABEL,
					Keywords.fromToken(token)
							+ " outside of labelled statement");
			reporter.setSeverity(ProblemSeverity.ERROR);
			reporter.setRange(statementStart, label.sourceEnd());
			reporter.report();
			return;
		}
		if (!info.finished
				&& info.statement.getStatement() instanceof LoopStatement) {
			return;
		}
		if (token == JSParser.BREAK) {
			return;
		}
		reporter.setMessage(JavaScriptProblems.CONTINUE_NON_LOOP_LABEL,
				"continue can only use labels of iteration statements");
		reporter.setSeverity(ProblemSeverity.ERROR);
		reporter.setRange(statementStart, label.sourceEnd());
		reporter.report();
	}

	@Override
	protected void visitCondition(Expression condition) {
		super.visitCondition(condition);
		if (condition instanceof BinaryOperation) {
			BinaryOperation operation = (BinaryOperation) condition;
			if (operation.getOperation() == JSParser.ASSIGN) {
				reporter.reportProblem(JavaScriptProblems.EQUAL_AS_ASSIGN,
						"Test for equality (==) mistyped as assignment (=)?",
						condition.sourceStart(), condition.sourceEnd());
			}
		}
	}

	@Override
	public Object visitBinaryOperation(BinaryOperation node) {
		if (node.getOperation() == JSParser.ASSIGN
				&& !canAssignTo(node.getLeftExpression())) {
			reporter.reportProblem(JavaScriptProblems.INVALID_ASSIGN_LEFT,
					"Invalid assignment left-hand side.", node.sourceStart(),
					node.sourceEnd());
		}
		return super.visitBinaryOperation(node);
	}

	@Override
	public Object visitUnaryOperation(UnaryOperation node) {
		if (isIncDec(node.getOperation()) && !canAssignTo(node.getExpression())) {
			reporter.reportProblem(JavaScriptProblems.INVALID_ASSIGN_LEFT,
					"Invalid assignment left-hand side.", node.sourceStart(),
					node.sourceEnd());
		}
		return super.visitUnaryOperation(node);
	}

	private boolean isIncDec(int operation) {
		return operation == JSParser.INC || operation == JSParser.DEC
				|| operation == JSParser.PINC || operation == JSParser.PDEC;
	}

	private boolean canAssignTo(Expression expression) {
		// TODO what else? XML?
		return expression instanceof Identifier
				|| expression instanceof PropertyExpression
				|| expression instanceof GetArrayItemExpression;
	}

	@Override
	public Object visitObjectInitializer(ObjectInitializer node) {
		final Set<String> processed = new HashSet<String>();
		for (ObjectInitializerPart part : node.getInitializers()) {
			if (part instanceof PropertyInitializer) {
				final PropertyInitializer property = (PropertyInitializer) part;
				final String propertyName = property.getNameAsString();
				if (propertyName != null && !processed.add(propertyName)) {
					reporter.reportProblem(
							JavaScriptProblems.DUPLICATE_PROPERTY_IN_LITERAL,
							NLS.bind(
									"Duplicate property {0} in object literal",
									propertyName), property.getName()
									.sourceStart(), property.getName()
									.sourceEnd());
				}
			}
		}
		return super.visitObjectInitializer(node);
	}
}
