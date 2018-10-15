/*******************************************************************************
 * Copyright (c) 2009 xored software, Inc.  
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0  
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Vladimir Belov)
 *******************************************************************************/

package org.eclipse.dltk.javascript.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.javascript.internal.parser.JSLiterals;

public class IfStatement extends Statement implements ISourceableBlock {

	private Keyword ifKeyword;
	private Keyword elseKeyword;
	private Expression condition;
	private Statement thenStatement;
	private Statement elseStatement = null;
	private int LP = -1;
	private int RP = -1;

	public IfStatement(JSNode parent) {
		super(parent);
	}

	/**
	 * @see org.eclipse.dltk.javascript.ast.Statement#traverse(org.eclipse.dltk.ast.ASTVisitor)
	 */
	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (ifKeyword != null)
				ifKeyword.traverse(visitor);
			if (elseKeyword != null)
				elseKeyword.traverse(visitor);
			if (condition != null)
				condition.traverse(visitor);
			if (thenStatement != null)
				thenStatement.traverse(visitor);
			if (elseStatement != null)
				elseStatement.traverse(visitor);
			visitor.endvisit(this);
		}
	}

	public Expression getCondition() {
		return this.condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public Statement getThenStatement() {
		return this.thenStatement;
	}

	public void setThenStatement(Statement thenStatement) {
		this.thenStatement = thenStatement;
	}

	public Statement getElseStatement() {
		return this.elseStatement;
	}

	public void setElseStatement(Statement elseStatement) {
		this.elseStatement = elseStatement;
	}

	public Keyword getIfKeyword() {
		return this.ifKeyword;
	}

	public void setIfKeyword(Keyword keyword) {
		this.ifKeyword = keyword;
	}

	public Keyword getElseKeyword() {
		return this.elseKeyword;
	}

	public void setElseKeyword(Keyword keyword) {
		this.elseKeyword = keyword;
	}

	public int getLP() {
		return this.LP;
	}

	public void setLP(int LP) {
		this.LP = LP;
	}

	public int getRP() {
		return this.RP;
	}

	public void setRP(int RP) {
		this.RP = RP;
	}

	@Override
	public String toSourceString(String indentationString) {
		final StringBuilder buffer = new StringBuilder();

		buffer.append(indentationString);
		buffer.append(Keywords.IF);
		buffer.append(" (");
		buffer.append(toSourceString(condition, indentationString));
		buffer.append(")\n");

		buffer.append(getThenStatement().toSourceString(
				isBlock(getThenStatement()) ? indentationString
						: indentationString + INDENT));

		if (getElseStatement() != null) {
			buffer.append(indentationString);
			buffer.append(Keywords.ELSE);
			buffer.append(JSLiterals.EOL);
			buffer.append(getElseStatement().toSourceString(
					isBlock(getElseStatement()) ? indentationString
							: indentationString + INDENT));
		}

		return buffer.toString();
	}

	private static boolean isBlock(ASTNode node) {
		return (node instanceof ISourceableBlock)
				&& ((ISourceableBlock) node).isBlock();
	}

	public boolean isBlock() {
		return true;
	}

}
