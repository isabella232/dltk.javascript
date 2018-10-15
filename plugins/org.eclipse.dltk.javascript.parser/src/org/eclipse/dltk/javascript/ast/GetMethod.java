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

public class GetMethod extends Method {

	private Keyword getKeyword;

	public GetMethod(JSNode parent) {
		super(parent);
	}

	/**
	 * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
	 */
	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (getKeyword != null)
				getKeyword.traverse(visitor);
			if (getName() != null)
				getName().traverse(visitor);
			if (getBody() != null)
				getBody().traverse(visitor);
			visitor.endvisit(this);
		}
	}

	public Keyword getGetKeyword() {
		return this.getKeyword;
	}

	public void setGetKeyword(Keyword keyword) {
		this.getKeyword = keyword;
	}

	@Override
	public String toSourceString(String indentationString) {
		final StringBuilder buffer = new StringBuilder();

		buffer.append(Keywords.GET);
		buffer.append(" ");
		buffer.append(toSourceString(getName(), indentationString));
		buffer.append(" ()");
		buffer.append(toSourceString(getBody(), indentationString));

		return buffer.toString();
	}

}
