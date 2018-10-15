/*******************************************************************************
 * Copyright (c) 2010 xored software, Inc.  
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0  
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.javascript.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.javascript.internal.parser.JSLiterals;

public class XmlExpressionFragment extends XmlFragment {
	// TODO curly braces

	private Expression expression;

	public XmlExpressionFragment(XmlLiteral parent) {
		super(parent);
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String toSourceString(String indentationString) {
		return "{" + expression.toSourceString(JSLiterals.EMPTY) + "}"; //$NON-NLS-1$ //$NON-NLS-2$
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

}
