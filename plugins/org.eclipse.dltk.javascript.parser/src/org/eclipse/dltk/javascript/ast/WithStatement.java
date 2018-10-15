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

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTVisitor;

public class WithStatement extends Statement implements ISourceableBlock {

	private Keyword withKeyword;
	private Expression expression;
	private Statement statement;
	private int LP = -1;
	private int RP = -1;

	public WithStatement(JSNode parent) {
		super(parent);
	}

	/**
	 * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
	 */
	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (withKeyword != null)
				withKeyword.traverse(visitor);
			if (expression != null)
				expression.traverse(visitor);
			if (statement != null)
				statement.traverse(visitor);
			visitor.endvisit(this);
		}
	}

	public Expression getExpression() {
		return this.expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public Statement getStatement() {
		return this.statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public Keyword getWithKeyword() {
		return this.withKeyword;
	}

	public void setWithKeyword(Keyword keyword) {
		this.withKeyword = keyword;
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

		Assert.isTrue(sourceStart() >= 0);
		Assert.isTrue(sourceEnd() > 0);
		Assert.isTrue(LP > 0);
		Assert.isTrue(RP > 0);

		StringBuffer buffer = new StringBuffer();

		buffer.append(indentationString);
		buffer.append(Keywords.WITH);
		buffer.append(" (");
		buffer.append(getExpression().toSourceString(indentationString));
		buffer.append(")\n");
		buffer.append(getStatement().toSourceString(indentationString));

		return buffer.toString();
	}

	public boolean isBlock() {
		return true;
	}

}
