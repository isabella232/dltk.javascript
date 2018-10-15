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
package org.eclipse.dltk.javascript.parser.util;

import java.text.ParseException;

import org.antlr.runtime.CharStream;

public class CharStreamUtil {

	public static void match(CharStream input, char expected)
			throws ParseException {
		if (input.LT(1) == expected) {
			input.consume();
		} else {
			throw new ParseException(expected + " expected", input.index());
		}
	}

	public static void skipSpaces(CharStream input) {
		int ch;
		while ((ch = input.LT(1)) != CharStream.EOF
				&& Character.isWhitespace(ch)) {
			input.consume();
		}
	}

}
