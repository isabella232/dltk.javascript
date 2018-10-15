/*******************************************************************************
 * Copyright (c) 2011 xored software, Inc.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.javascript.parser;

import java.util.Collection;

import org.eclipse.dltk.compiler.problem.IProblemIdentifier;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public interface JSProblemReporter extends ProblemReporter, IProblemReporter {

	void pushSuppressWarnings(Collection<IProblemIdentifier> suppressed);

	void popSuppressWarnings();

	/**
	 * Returns the current state of suppress warnings or <code>null</code>.
	 */
	ISuppressWarningsState getSuppressWarnings();

	/**
	 * Replaces the current state of suppress warnings with the value returned
	 * from {@link #getSuppressWarnings()}
	 */
	void restoreSuppressWarnings(ISuppressWarningsState state);

}
