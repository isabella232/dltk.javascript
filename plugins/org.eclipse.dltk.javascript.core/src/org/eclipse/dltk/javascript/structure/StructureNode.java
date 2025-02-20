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

import java.util.Collections;
import java.util.List;

import org.eclipse.dltk.javascript.typeinfo.model.JSType;

public abstract class StructureNode implements IStructureNode {
	protected final IParentNode parent;

	public StructureNode(IParentNode parent) {
		this.parent = parent;
	}

	public List<? extends IStructureNode> getChildren() {
		return Collections.emptyList();
	}

	public boolean isManyChildren() {
		return false;
	}

	public IParentNode getParent() {
		return parent;
	}

	public IScope getScope() {
		return parent.getScope();
	}

	protected String typeToModel(JSType type) {
		return type != null ? type.getName() : null;
	}

	protected void reportChildrenStructure(IStructureRequestor requestor,
			IStructureContext context) {
		for (IStructureNode child : getChildren()) {
			child.reportStructure(requestor, context);
		}
	}

}
