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
package org.eclipse.dltk.javascript.core;

import org.eclipse.dltk.javascript.typeinfo.ITypeNames;
import org.eclipse.dltk.javascript.typeinfo.model.Type;
import org.eclipse.dltk.javascript.typeinfo.model.TypeInfoModelFactory;
import org.eclipse.dltk.javascript.typeinfo.model.TypeInfoModelLoader;
import org.eclipse.dltk.javascript.typeinfo.model.TypeKind;

/**
 * Constants for the predefined JavaScript types models.
 */
public class Types {

	public static final Type OBJECT = initType(ITypeNames.OBJECT);

	public static final Type STRING = initType(ITypeNames.STRING);

	public static final Type NUMBER = initType(ITypeNames.NUMBER);

	public static final Type BOOLEAN = initType(ITypeNames.BOOLEAN);

	public static final Type FUNCTION = initType(ITypeNames.FUNCTION);

	public static final Type ARRAY = initType(ITypeNames.ARRAY);

	public static final Type XML = initType(ITypeNames.XML);

	public static final Type REGEXP = initType(ITypeNames.REGEXP);

	public static final Type ERROR = initType(ITypeNames.ERROR);

	protected static Type initType(String name) {
		Type type = TypeInfoModelLoader.getInstance().getType(name);
		if (type == null) {
			JavaScriptPlugin.error("Created empty " + name
					+ " type (error loading typeinfo models?)");
			type = TypeInfoModelFactory.eINSTANCE.createType();
			type.setName(name);
			type.setKind(TypeKind.PREDEFINED);
		}
		return type;
	}

}
