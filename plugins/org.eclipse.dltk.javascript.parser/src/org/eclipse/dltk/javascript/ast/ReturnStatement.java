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

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.javascript.internal.parser.JSLiterals;

public class ReturnStatement extends Statement implements ISemicolonStatement {

	private Keyword returnKeyword;
	private Expression value;
	private int semic = -1;

	public ReturnStatement(JSNode parent) {
		super(parent);
	}

	/**
	 * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
	 */
	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (returnKeyword != null)
				returnKeyword.traverse(visitor);
			if (value != null)
				value.traverse(visitor);

			visitor.endvisit(this);
		}
	}

	public Expression getValue() {
		return this.value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}

	public Keyword getReturnKeyword() {
		return this.returnKeyword;
	}

	public void setReturnKeyword(Keyword keyword) {
		this.returnKeyword = keyword;
	}

	public int getSemicolonPosition() {
		return this.semic;
	}

	public void setSemicolonPosition(int semic) {
		this.semic = semic;
	}

	@Override
	public String toSourceString(String indentationString) {
		final StringBuilder buffer = new StringBuilder();

		buffer.append(indentationString);
		buffer.append(Keywords.RETURN);

		if (value != null) {
			buffer.append(' ');
			buffer.append(value.toSourceString(indentationString));
		}

		if (this.semic > -1)
			buffer.append(JSLiterals.SEMICOLON);
		buffer.append(JSLiterals.EOL);

		return buffer.toString();
	}

}
