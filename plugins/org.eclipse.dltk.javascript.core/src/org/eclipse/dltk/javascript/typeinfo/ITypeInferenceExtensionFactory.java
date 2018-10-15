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

import org.eclipse.core.runtime.IAdaptable;

/**
 * This interface could be optionally implemented by
 * {@link ITypeInferenceHandlerFactory}.
 */
public interface ITypeInferenceExtensionFactory {

	/**
	 * Creates extension of the specified type for the specified context.
	 * Returns the extension created or null.
	 * 
	 * @param context
	 *            can be adapted to {@link ITypeInfoContext} or
	 *            {@link ReferenceSource}
	 * @param extensionClass
	 *            e.g.
	 *            {@link org.eclipse.dltk.javascript.validation.IValidatorExtension}
	 *            ,
	 *            {@link org.eclipse.dltk.javascript.structure.IStructureHandler}
	 *            ,
	 *            {@link org.eclipse.dltk.javascript.search.IMatchLocatorVisitor}
	 * @param arg
	 *            extension specific parameter, can be <code>null</code>
	 * @return
	 */
	Object createExtension(IAdaptable context, Class<?> extensionClass,
			Object arg);

}
