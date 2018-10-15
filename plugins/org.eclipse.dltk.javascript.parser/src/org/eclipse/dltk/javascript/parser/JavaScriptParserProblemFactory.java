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
import org.eclipse.dltk.compiler.problem.IProblemIdentifierFactory;

public class JavaScriptParserProblemFactory implements
		IProblemIdentifierFactory {

	public IProblemIdentifier valueOf(String localName)
			throws IllegalArgumentException {
		return JavaScriptParserProblems.valueOf(localName);
	}

	public IProblemIdentifier[] values() {
		return JavaScriptParserProblems.values();
	}

}
