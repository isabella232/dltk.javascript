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

import org.eclipse.dltk.annotations.NonNull;
import org.eclipse.dltk.javascript.typeinfo.model.Type;

/**
 * Resolved type expression representing <em>simple type reference</em>.
 */
public interface IRSimpleType extends IRType {

	@NonNull
	Type getTarget();

	@NonNull
	IRTypeDeclaration getDeclaration();

}
