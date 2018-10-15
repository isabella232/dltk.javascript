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

import java.util.ArrayList;
import java.util.List;

public class ObjectDeclaration extends StructureNode {

	private final List<IStructureNode> children = new ArrayList<IStructureNode>();

	public ObjectDeclaration(IParentNode parent) {
		super(parent);
	}

	public void addChild(PropertyDeclaration propertyDeclaration) {
		children.add(propertyDeclaration);
	}

	@Override
	public List<IStructureNode> getChildren() {
		return children;
	}

	public int start() {
		return !children.isEmpty() ? children.get(0).start() : 0;
	}

	@Override
	public boolean isManyChildren() {
		return true;
	}

	@Override
	public String toString() {
		return "<object>";
	}

	public void reportStructure(IStructureRequestor requestor,
			IStructureContext context) {
		reportChildrenStructure(requestor, context);
	}

}
