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

import org.eclipse.dltk.compiler.problem.IProblemIdentifier;

public interface JSProblemIdentifier extends IProblemIdentifier {

	String getMessage();

	/**
	 * Formats the message for this problem using the specified arguments.
	 * 
	 * @param args
	 * @return
	 */
	String formatMessage(Object... args);

}
