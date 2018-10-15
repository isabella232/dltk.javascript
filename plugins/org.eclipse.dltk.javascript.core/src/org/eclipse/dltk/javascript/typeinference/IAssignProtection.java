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
package org.eclipse.dltk.javascript.typeinference;

import org.eclipse.dltk.compiler.problem.IProblemIdentifier;

/**
 * Value of the {@link IValueReference} attribute to mark read-only elements, it
 * should be retrieved calling {@link IValueReference#getAttribute(String)} with
 * {@link #ATTRIBUTE}.
 * <p>
 * There could be different reasons for the element being read-only, so there
 * are a few static instances of this interface to distinguish them.
 * </p>
 */
public interface IAssignProtection {

	/**
	 * Key of the attribute containing this value.
	 */
	static String ATTRIBUTE = IAssignProtection.class.getName();

	/**
	 * Problem identifier to be used when reporting the problem.
	 */
	IProblemIdentifier problemId();

	/**
	 * Problem message to be used when reporting the problem.
	 */
	String problemMessage();

}
