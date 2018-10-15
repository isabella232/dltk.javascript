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
package org.eclipse.dltk.javascript.internal.core;

import org.eclipse.dltk.internal.javascript.ti.TypeSystemImpl;
import org.eclipse.dltk.javascript.typeinfo.model.Type;
import org.eclipse.dltk.javascript.typeinfo.model.TypeInfoModelLoader;
import org.eclipse.emf.ecore.resource.Resource;

public class GlobalTypeSystem extends TypeSystemImpl {

	@Override
	public Type resolveType(Type type) {
		type = super.resolveType(type);
		if (type != null) {
			final Resource resource = type.eResource();
			if (resource == null
					|| !TypeInfoModelLoader.getInstance().hasResource(resource)) {
				throw new IllegalArgumentException(type.getName());
			}
		}
		return type;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
