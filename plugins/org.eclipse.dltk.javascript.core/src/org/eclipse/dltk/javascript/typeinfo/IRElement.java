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

import java.util.Set;

import org.eclipse.dltk.compiler.problem.IProblemCategory;
import org.eclipse.dltk.compiler.problem.IProblemIdentifier;

public interface IRElement {

	String getName();

	Set<IProblemCategory> getSuppressedWarnings();

	boolean isSuppressed(IProblemIdentifier problemIdentifier);

	boolean isDeprecated();

	/**
	 * Returns the declaration object used to construct this "runtime/resolved"
	 * element.
	 */
	Object getSource();

}
