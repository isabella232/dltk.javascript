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
package org.eclipse.dltk.javascript.typeinfo;

import org.eclipse.dltk.javascript.core.Types;
import org.eclipse.dltk.javascript.internal.core.TypeSystems;
import org.eclipse.dltk.javascript.typeinfo.model.Type;
import org.eclipse.emf.ecore.resource.Resource;

public abstract class DefaultMetaType implements MetaType {

	public static final MetaType DEFAULT = new Instance();

	static class Instance extends DefaultMetaType {
		public String getId() {
			return MetaType.class.getName() + ".DEFAULT";
		}
	}

	public IRType toRType(ITypeSystem typeSystem, Type type) {
		return new RSimpleType(typeSystem, type);
	}

	public IRType toRType(IRTypeDeclaration declaration) {
		return new RSimpleType(declaration);
	}

	@Override
	public String toString() {
		return getId();
	}

	public ITypeSystem getPreferredTypeSystem(Type type) {
		final Resource resource = type.eResource();
		if (resource != null && resource == Types.OBJECT.eResource()) {
			return TypeSystems.GLOBAL;
		}
		return null;
	}

}
