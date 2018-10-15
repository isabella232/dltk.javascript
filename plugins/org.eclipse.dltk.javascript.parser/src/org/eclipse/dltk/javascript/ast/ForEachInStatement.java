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

public class ForEachInStatement extends ForInStatement {

	private Keyword eachKeyword;

	public ForEachInStatement(JSNode parent) {
		super(parent);
	}

	/**
	 * @see org.eclipse.dltk.javascript.ast.Statement#traverse(org.eclipse.dltk.ast.ASTVisitor)
	 */
	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (eachKeyword != null)
				eachKeyword.traverse(visitor);
			if (getForKeyword() != null)
				getForKeyword().traverse(visitor);
			if (getInKeyword() != null)
				getInKeyword().traverse(visitor);
			if (getItem() != null)
				getItem().traverse(visitor);
			if (getIterator() != null)
				getIterator().traverse(visitor);

			Statement body = getBody();
			if (body != null) {
				body.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

	public Keyword getEachKeyword() {
		return this.eachKeyword;
	}

	public void setEachKeyword(Keyword keyword) {
		this.eachKeyword = keyword;
	}

	@Override
	public String toSourceString(String indentationString) {

		Assert.isTrue(sourceStart() >= 0);
		Assert.isTrue(sourceEnd() > 0);
		Assert.isTrue(getLP() > 0);
		Assert.isTrue(getRP() > 0);

		StringBuffer buffer = new StringBuffer();

		buffer.append(indentationString);
		buffer.append(Keywords.FOR);
		buffer.append(' ');
		buffer.append(Keywords.EACH);
		buffer.append(" (");
		buffer.append(getItem().toSourceString(indentationString));
		buffer.append(" ");
		buffer.append(Keywords.IN);
		buffer.append(" ");
		buffer.append(getIterator().toSourceString(indentationString));
		buffer.append(")");
		if (getBody() != null) {
			buffer.append("\n");
			buffer.append(getBody().toSourceString(indentationString));
		} else {
			buffer.append("\n");
		}

		return buffer.toString();
	}

}
