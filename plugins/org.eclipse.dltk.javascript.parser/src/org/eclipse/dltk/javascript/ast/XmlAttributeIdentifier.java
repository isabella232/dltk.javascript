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

public class XmlAttributeIdentifier extends Expression implements
		IXMLExpression {
	/**
	 * @see Identifier
	 * @see AsteriskExpression
	 */
	private Expression expression;

	public XmlAttributeIdentifier(JSNode parent) {
		super(parent);
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toSourceString(String indentationString) {
		if (expression != null) {
			return "@" + expression.toSourceString(indentationString);
		} else {
			return "@" + "?";
		}
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (expression != null) {
				expression.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

	public String getAttributeName() {
		if (expression instanceof Identifier) {
			return '@' + ((Identifier) expression).getName();
		}
		return null;
	}

}
