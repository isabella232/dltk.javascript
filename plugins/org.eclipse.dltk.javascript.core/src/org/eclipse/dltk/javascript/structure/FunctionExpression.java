/*******************************************************************************
 * Copyright (c) 2012 NumberFour AG
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     NumberFour AG - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.javascript.structure;

import org.eclipse.dltk.core.ISourceNode;
import org.eclipse.dltk.javascript.ast.FunctionStatement;
import org.eclipse.dltk.javascript.typeinfo.IModelBuilder.IMethod;

public class FunctionExpression extends FunctionNode {

	public FunctionExpression(IParentNode parent, FunctionStatement function,
			IMethod method) {
		super(parent, function, method);
	}

	@Override
	protected String getName() {
		if (parent instanceof PropertyDeclaration) {
			return ((PropertyDeclaration) parent).getName();
		} else {
			return "<anonymous>";
		}
	}

	@Override
	public ISourceNode getNameNode() {
		if (parent instanceof PropertyDeclaration) {
			return ((PropertyDeclaration) parent).getNameNode();
		} else {
			return function.getFunctionKeyword();
		}
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<function(");
		sb.append(argumentsToString());
		sb.append(")");
		if (getType() != null) {
			sb.append(':');
			sb.append(getType().getName());
		}
		sb.append('>');
		return sb.toString();
	}

}
