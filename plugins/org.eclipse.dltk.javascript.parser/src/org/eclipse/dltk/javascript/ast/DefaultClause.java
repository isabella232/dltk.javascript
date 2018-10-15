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

import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTVisitor;

public class DefaultClause extends SwitchComponent {

	private Keyword defaultKeyword;

	public DefaultClause(JSNode parent) {
		super(parent);
	}

	/**
	 * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
	 */
	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (defaultKeyword != null)
				defaultKeyword.traverse(visitor);

			List<Statement> statements = getStatements();
			if (statements != null) {
				for (Statement statement : statements) {
					statement.traverse(visitor);
				}
			}
			visitor.endvisit(this);
		}
	}

	public Keyword getDefaultKeyword() {
		return this.defaultKeyword;
	}

	public void setDefaultKeyword(Keyword keyword) {
		this.defaultKeyword = keyword;
	}

	@Override
	public String toSourceString(String indentationString) {

		Assert.isTrue(sourceStart() > 0);
		Assert.isTrue(sourceEnd() > 0);
		// Assert.isTrue(colon > 0);

		StringBuffer buffer = new StringBuffer();

		buffer.append(indentationString);

		buffer.append(Keywords.DEFAULT);
		buffer.append(":\n");

		toSourceString(getStatements(), buffer, indentationString
				+ Statement.INDENT);

		return buffer.toString();
	}

	@Override
	public Keyword getKeyword() {
		return getDefaultKeyword();
	}

}
