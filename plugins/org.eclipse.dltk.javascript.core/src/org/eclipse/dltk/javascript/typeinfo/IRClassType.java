/*******************************************************************************
 * Copyright (c) 2011 NumberFour AG
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

import org.eclipse.dltk.annotations.Nullable;
import org.eclipse.dltk.javascript.typeinfo.model.Type;

/**
 * Resolved type expression represented <em>class reference</em> - kind of
 * static reference to the type.
 */
public interface IRClassType extends IRType {

	@Nullable
	Type getTarget();

	@Nullable
	IRTypeDeclaration getDeclaration();

	/**
	 * Returns the item of this class.
	 */
	IRType toItemType();

	/**
	 * Returns the item type when <code>new</code> operator is applied to this
	 * class.
	 */
	IRType newItemType();

}
