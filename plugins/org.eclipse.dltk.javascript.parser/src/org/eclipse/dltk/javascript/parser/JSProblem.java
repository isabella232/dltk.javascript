/*******************************************************************************
 * Copyright (c) 2009 xored software, Inc.
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

import org.antlr.runtime.RecognitionException;
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.ProblemSeverity;

public class JSProblem extends DefaultProblem {

	private final Throwable cause;

	public JSProblem(Throwable cause) {
		super(
				cause.getClass().getSimpleName() + ": " + cause.getMessage(),
				JavaScriptParserProblems.INTERNAL_ERROR,
				null,
				ProblemSeverity.ERROR,
				-1,
				-1,
				cause instanceof RecognitionException ? ((RecognitionException) cause).line
						: -1);
		this.cause = cause;
	}

	public Throwable getCause() {
		return cause;
	}

}
