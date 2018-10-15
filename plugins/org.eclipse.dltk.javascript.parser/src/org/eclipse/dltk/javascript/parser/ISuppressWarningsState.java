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
package org.eclipse.dltk.javascript.parser;

import org.eclipse.dltk.compiler.problem.IProblemCategory;

/**
 * Represents the current state of suppress warnings directives in the
 * {@link JSProblemReporter}.
 * 
 * It doesn't expose any methods and implementation is completely internal.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @since 4.0
 */
public interface ISuppressWarningsState {

	/**
	 * Creates snapshot of the current state.
	 */
	IProblemCategory asCategory();

}
