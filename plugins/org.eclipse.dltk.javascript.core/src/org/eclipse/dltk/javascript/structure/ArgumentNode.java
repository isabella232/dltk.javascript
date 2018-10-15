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

import org.eclipse.dltk.javascript.typeinference.ReferenceLocation;
import org.eclipse.dltk.javascript.typeinfo.model.JSType;

public class ArgumentNode extends StructureNode implements IDeclaration {

	private final String name;
	private final JSType type;
	private final ReferenceLocation location;

	public ArgumentNode(IScope parent, String name, JSType type,
			ReferenceLocation location) {
		super(parent);
		this.name = name;
		this.type = type;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public JSType getType() {
		return type;
	}

	public ReferenceLocation getLocation() {
		return location;
	}

	public int start() {
		return location.getDeclarationStart();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(":argument");
		return sb.toString();
	}

	public void reportStructure(IStructureRequestor requestor,
			IStructureContext context) {
		// empty
	}

}
