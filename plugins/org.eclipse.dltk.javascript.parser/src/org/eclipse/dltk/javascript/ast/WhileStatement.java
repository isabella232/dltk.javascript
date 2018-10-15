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

public class WhileStatement extends LoopStatement {

	private Keyword whileKeyword;
	private Expression condition;
	private int LP = -1;
	private int RP = -1;

	public WhileStatement(JSNode parent) {
		super(parent);
	}

	/**
	 * @see org.eclipse.dltk.javascript.ast.Statement#traverse(org.eclipse.dltk.ast.ASTVisitor)
	 */
	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (whileKeyword != null)
				whileKeyword.traverse(visitor);
			if (condition != null)
				condition.traverse(visitor);

			Statement body = getBody();
			if (body != null) {
				body.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

	public Expression getCondition() {
		return this.condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public Keyword getWhileKeyword() {
		return this.whileKeyword;
	}

	public void setWhileKeyword(Keyword keyword) {
		this.whileKeyword = keyword;
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
		buffer.append(Keywords.WHILE);
		buffer.append(" (");
		buffer.append(getCondition().toSourceString(indentationString));
		buffer.append(")");
		if (getBody() != null) {
			buffer.append("\n");
			buffer.append(getBody().toSourceString(indentationString));
		} else {
			buffer.append(";\n");
		}

		return buffer.toString();
	}

}
