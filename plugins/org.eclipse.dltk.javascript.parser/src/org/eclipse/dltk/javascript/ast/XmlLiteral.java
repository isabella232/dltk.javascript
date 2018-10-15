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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.javascript.internal.parser.JSLiterals;

public class XmlLiteral extends Expression implements IXMLExpression {

	private List<XmlFragment> fragments = null;

	public XmlLiteral(JSNode parent) {
		super(parent);
	}

	public void addFragment(XmlFragment fragment) {
		fragments.add(fragment);
	}

	public List<XmlFragment> getFragments() {
		return fragments != null ? fragments : Collections
				.<XmlFragment> emptyList();
	}

	/**
	 * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
	 */
	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (fragments != null) {
				for (XmlFragment fragment : fragments) {
					fragment.traverse(visitor);
				}
			}
			visitor.endvisit(this);
		}
	}

	@Override
	public String toSourceString(String indentationString) {
		StringBuilder sb = new StringBuilder();
		for (XmlFragment fragment : fragments) {
			sb.append(fragment.toSourceString(JSLiterals.EMPTY));
		}
		return sb.toString();
	}

	/**
	 * @param fragments
	 */
	public void setFragments(List<XmlFragment> fragments) {
		if (fragments != null) {
			this.fragments = new ArrayList<XmlFragment>(fragments);
		} else {
			this.fragments = null;
		}
	}

}
