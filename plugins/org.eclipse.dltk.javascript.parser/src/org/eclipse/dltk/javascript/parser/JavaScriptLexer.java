/*******************************************************************************
 * Copyright (c) 2010 xored software, Inc.
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

import org.antlr.runtime.BitSet;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.RecognitionException;
import org.eclipse.dltk.compiler.problem.ProblemSeverity;

public class JavaScriptLexer extends JSLexer {

	public JavaScriptLexer(CharStream input) {
		super(input);
	}

	private Reporter reporter;

	public void setReporter(Reporter reporter) {
		this.reporter = reporter;
	}

	private int lastRecoveryIndex = -1;

	@Override
	public void recover(RecognitionException re) {
		/*
		 * recover() is called TWICE! first in match(), then in nextToken().
		 */
		if (re instanceof MismatchedTokenException) {
			if (re.index <= lastRecoveryIndex) {
				return;
			}
			lastRecoveryIndex = re.index;
		}
		super.recover(re);
	}

	@Override
	public void displayRecognitionError(String[] tokenNames,
			RecognitionException e) {
		if (reporter == null)
			return;
		int start;
		int end;
		if (e instanceof NoIdentifierException) {
			e.c = input.LA(1);
			start = input.index();
			end = start + 1;
		} else {
			start = lastToken != null ? reporter.getOffset(lastToken)
					+ reporter.length(lastToken) : 0;
			end = reporter.getOffset(e.line, e.charPositionInLine);
			if (end < start) {
				end = start + 1;
			}
		}
		final String msg = getErrorMessage(e, tokenNames);
		reporter.setMessage(JavaScriptParserProblems.LEXER_ERROR, msg);
		reporter.setSeverity(ProblemSeverity.ERROR);
		reporter.setRange(start, end);
		reporter.setLine(e.line - 1);
		reporter.report();
	}

	@Override
	public String getCharErrorDisplay(int c) {
		final String s = super.getCharErrorDisplay(c);
		if (c >= 32 && c < 127) {
			return s; // ASCII
		} else {
			return s + " (0x" + Integer.toHexString(c).toUpperCase() + ")";
		}
	}

	@Override
	public void recoverFromMismatchedToken(IntStream input,
			RecognitionException e, int ttype, BitSet follow)
			throws RecognitionException {
		// if next token is what we are looking for then "delete" this token
		if (input.LA(2) == ttype) {
			reportError(e);
			beginResync();
			input.consume(); // simply delete extra token
			endResync();
			input.consume(); // move past ttype token as if all were ok
			return;
		}
		if (!recoverFromMismatchedElement(input, e, follow)) {
			throw e;
		}
	}

}
