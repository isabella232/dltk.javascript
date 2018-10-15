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

import java.text.ParseException;

import org.eclipse.dltk.compiler.problem.IProblemIdentifier;
import org.eclipse.dltk.javascript.parser.JSProblemIdentifier;

@SuppressWarnings("serial")
public class JSDocParseException extends ParseException {

	public final IProblemIdentifier problemId;

	public JSDocParseException(JSProblemIdentifier problemId, Object... args) {
		super(problemId.formatMessage(args), -1);
		assert problemId != null;
		this.problemId = problemId;
	}

	public JSDocParseException(String message, IProblemIdentifier problemId) {
		super(message, -1);
		assert problemId != null;
		this.problemId = problemId;
	}

}
