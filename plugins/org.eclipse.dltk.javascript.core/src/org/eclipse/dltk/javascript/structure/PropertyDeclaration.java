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

import org.eclipse.dltk.compiler.IElementRequestor.FieldInfo;
import org.eclipse.dltk.core.ISourceNode;
import org.eclipse.dltk.javascript.ast.PropertyInitializer;
import org.eclipse.dltk.javascript.typeinference.ReferenceLocation;
import org.eclipse.dltk.javascript.typeinfo.model.JSType;

public class PropertyDeclaration extends ParentNode implements IDeclaration {

	private final String name;
	private final PropertyInitializer initializer;
	private final ReferenceLocation location;
	private IStructureNode value;

	public PropertyDeclaration(IParentNode parent, String name,
			PropertyInitializer initializer, ReferenceLocation location) {
		super(parent);
		this.name = name;
		this.initializer = initializer;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public JSType getType() {
		if (value instanceof IDeclaration) {
			return ((IDeclaration) value).getType();
		} else {
			return null;
		}
	}

	public ReferenceLocation getLocation() {
		return location;
	}

	public int start() {
		return location.getDeclarationStart();
	}

	public IStructureNode getValue() {
		return value;
	}

	public void setValue(IStructureNode value) {
		this.value = value;
	}

	public ISourceNode getNameNode() {
		return initializer.getName();
	}

	@Override
	public List<IStructureNode> getChildren() {
		return value != null ? Collections.singletonList(value) : Collections
				.<IStructureNode> emptyList();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(":");
		if (value != null) {
			sb.append(value);
		} else {
			sb.append("<property?>");
		}
		return sb.toString();
	}

	public void reportStructure(IStructureRequestor requestor,
			IStructureContext context) {
		final boolean allowed = context.allow(IStructureContext.FIELD);
		if (!allowed) {
			context.pushMask(IStructureContext.METHOD);
		}
		if (value instanceof FunctionNode) {
			value.reportStructure(requestor, context);
		} else {
			if (allowed) {
				final FieldInfo info = new FieldInfo();
				info.declarationStart = initializer.getName().start();
				info.name = getName();
				info.nameSourceStart = initializer.getName().start();
				info.nameSourceEnd = initializer.getName().end() - 1;
				// info.type = typeToModel(type);
				requestor.enterField(info, initializer.getName(), null, false);
			}
			reportChildrenStructure(requestor, context);
			if (allowed) {
				requestor.exitField(initializer.end() - 1);
			}
		}
		if (!allowed) {
			context.popMask();
		}
	}

}
