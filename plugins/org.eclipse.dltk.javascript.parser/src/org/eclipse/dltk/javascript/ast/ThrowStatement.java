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

public class ThrowStatement extends Statement implements ISemicolonStatement {

	private Keyword throwKeyword;
	private Expression exception;
	private int semic = -1;

	public ThrowStatement(JSNode parent) {
		super(parent);
	}

	/**
	 * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
	 */
	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (throwKeyword != null)
				throwKeyword.traverse(visitor);
			if (exception != null)
				exception.traverse(visitor);

			visitor.endvisit(this);
		}
	}

	public Expression getException() {
		return this.exception;
	}

	public void setException(Expression exception) {
		this.exception = exception;
	}

	public Keyword getThrowKeyword() {
		return this.throwKeyword;
	}

	public void setThrowKeyword(Keyword keyword) {
		this.throwKeyword = keyword;
	}

	public int getSemicolonPosition() {
		return this.semic;
	}

	public void setSemicolonPosition(int semic) {
		this.semic = semic;
	}

	@Override
	public String toSourceString(String indentationString) {

		Assert.isTrue(sourceStart() >= 0);
		Assert.isTrue(sourceEnd() > 0);
		// Assert.isTrue(semic > 0);

		StringBuffer buffer = new StringBuffer();

		buffer.append(indentationString);
		buffer.append(Keywords.THROW);

		if (this.exception != null) {
			buffer.append(' ');
			buffer.append(exception.toSourceString(indentationString));
		}
		if (semic > 0)
			buffer.append(";\n");

		return buffer.toString();
	}

}
