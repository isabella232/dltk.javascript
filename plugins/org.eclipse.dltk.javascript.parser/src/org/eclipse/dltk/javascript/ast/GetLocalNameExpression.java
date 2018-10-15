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

public class GetLocalNameExpression extends Expression implements
		IXMLExpression {

	private Expression namespace;
	private Expression localName;
	private int coloncolon = -1;

	public GetLocalNameExpression(JSNode parent) {
		super(parent);
	}

	/**
	 * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
	 */
	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (namespace != null)
				namespace.traverse(visitor);
			if (localName != null)
				localName.traverse(visitor);
			visitor.endvisit(this);
		}
	}

	public Expression getNamespace() {
		return this.namespace;
	}

	public void setNamespace(Expression object) {
		this.namespace = object;
	}

	public Expression getLocalName() {
		return this.localName;
	}

	public void setLocalName(Expression localName) {
		this.localName = localName;
	}

	public int getColonColonPosition() {
		return this.coloncolon;
	}

	public void setColonColonPosition(int coloncolon) {
		this.coloncolon = coloncolon;
	}

	@Override
	public String toSourceString(String indentationString) {
		final StringBuilder buffer = new StringBuilder();

		buffer.append(toSourceString(namespace, indentationString));
		buffer.append("::");
		buffer.append(toSourceString(localName, indentationString));

		return buffer.toString();
	}

}
