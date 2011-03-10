/*******************************************************************************
 * Copyright (c) 2009 xored software, Inc.  
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html  
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Vladimir Belov)
 *******************************************************************************/

package org.eclipse.dltk.javascript.ast;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;

public class StringLiteral extends Expression implements Documentable {

	private String text;

	public StringLiteral(ASTNode parent) {
		super(parent);
	}

	/**
	 * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
	 */
	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			visitor.endvisit(this);
		}
	}

	/**
	 * Returns the text of this literal (including quotes).
	 * 
	 * @return
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Returns the internal content of this string literal (excluding quotes).
	 * 
	 * @return
	 */
	public String getValue() {
		if (text != null) {
			final int len = text.length();
			if (len >= 2) {
				final char ch0 = text.charAt(0);
				if (ch0 == '"' || ch0 == '\'') {
					if (text.charAt(len - 1) == ch0) {
						return text.substring(1, len - 1);
					}
				}
			}
		}
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toSourceString(String indentationString) {
		Assert.isTrue(sourceStart() >= 0);
		Assert.isTrue(sourceEnd() > 0);

		return text;
	}

	private Comment documentation;

	@Override
	public Comment getDocumentation() {
		return documentation;
	}

	public void setDocumentation(Comment documentation) {
		this.documentation = documentation;
	}

}
