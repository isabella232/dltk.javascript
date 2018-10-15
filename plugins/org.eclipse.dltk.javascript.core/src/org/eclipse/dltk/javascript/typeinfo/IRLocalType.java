/*******************************************************************************
 * Copyright (c) 2012 Servoy
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Servoy - initial API and Implementation (Johan Compagner)
 *******************************************************************************/
package org.eclipse.dltk.javascript.typeinfo;

import java.util.Set;

import org.eclipse.dltk.annotations.Nullable;
import org.eclipse.dltk.javascript.typeinference.IValueReference;
import org.eclipse.dltk.javascript.typeinference.ReferenceLocation;

/**
 * @author jcompagner
 */
public interface IRLocalType extends IRType, IRTypeExtension {
	public static final String PROTOTYPE_PROPERTY = "prototype";

	public IValueReference getValue();

	@Nullable
	public IValueReference getDirectChild(String name);

	public ReferenceLocation getReferenceLocation();

	public Set<String> getDirectChildren();

}
