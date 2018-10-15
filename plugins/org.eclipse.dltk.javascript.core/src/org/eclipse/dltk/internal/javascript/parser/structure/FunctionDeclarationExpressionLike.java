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
package org.eclipse.dltk.internal.javascript.parser.structure;

import org.eclipse.dltk.core.ISourceNode;
import org.eclipse.dltk.javascript.ast.Expression;
import org.eclipse.dltk.javascript.ast.FunctionStatement;
import org.eclipse.dltk.javascript.structure.FunctionDeclaration;
import org.eclipse.dltk.javascript.structure.IParentNode;
import org.eclipse.dltk.javascript.typeinfo.IModelBuilder.IMethod;

class FunctionDeclarationExpressionLike extends FunctionDeclaration {

	private final String name;
	private final ISourceNode nameNode;

	public FunctionDeclarationExpressionLike(IParentNode parent,
			FunctionStatement function, IMethod method, String name,
			ISourceNode nameNode) {
		super(parent, function, method);
		this.name = name;
		this.nameNode = nameNode;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ISourceNode getNameNode() {
		return nameNode;
	}

	@Override
	protected Expression getStructureNameNode() {
		if (nameNode instanceof Expression) {
			return (Expression) nameNode;
		}
		return super.getStructureNameNode();
	}

}
