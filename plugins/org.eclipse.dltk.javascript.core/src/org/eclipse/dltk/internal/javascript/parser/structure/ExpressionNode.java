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

import org.eclipse.dltk.javascript.ast.Identifier;
import org.eclipse.dltk.javascript.structure.IDeclaration;
import org.eclipse.dltk.javascript.structure.IParentNode;
import org.eclipse.dltk.javascript.structure.IStructureContext;
import org.eclipse.dltk.javascript.structure.IStructureNode;
import org.eclipse.dltk.javascript.structure.IStructureRequestor;
import org.eclipse.dltk.javascript.structure.ObjectDeclaration;
import org.eclipse.dltk.javascript.structure.StructureNode;

public class ExpressionNode extends StructureNode implements IParentNode {

	public ExpressionNode(IParentNode parent) {
		super(parent);
	}

	public int start() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void reportStructure(IStructureRequestor requestor,
			IStructureContext context) {
	}

	public void addLocalReference(Identifier node, IDeclaration resolved) {
		parent.addLocalReference(node, resolved);
	}

	public void addMethodReference(Identifier identifier, int argCount) {
		parent.addMethodReference(identifier, argCount);
	}

	public void addFieldReference(Identifier identifier) {
		parent.addFieldReference(identifier);
	}

	public void addToScope(IStructureNode child) {
		if (child instanceof ObjectDeclaration) {
			return;
		}
		parent.addToScope(child);
	}
}
