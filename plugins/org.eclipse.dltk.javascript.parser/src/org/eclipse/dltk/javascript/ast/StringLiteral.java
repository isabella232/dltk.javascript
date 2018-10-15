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

public class StringLiteral extends Literal implements Documentable {

	private String text;

	public StringLiteral(JSNode parent) {
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
	@Override
	public String getText() {
		return this.text;
	}

	/**
	 * Returns the internal content of this string literal (excluding quotes).
	 * 
	 * @return
	 */
	public String getValue() {
		return text != null ? valueOf(text) : null;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toSourceString(String indentationString) {
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

	public static String valueOf(String text) {
		final int len = text.length();
		if (len >= 2) {
			final char ch0 = text.charAt(0);
			if (ch0 == '"' || ch0 == '\'') {
				if (text.charAt(len - 1) == ch0) {
					return text.substring(1, len - 1);
				}
			}
		}
		return text;
	}

}
