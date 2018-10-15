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
package org.eclipse.dltk.javascript.internal.parser;

import java.util.List;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.eclipse.dltk.javascript.parser.JSTokenSource;
import org.eclipse.dltk.javascript.parser.JSTokenStream;
import org.eclipse.dltk.javascript.parser.JavaScriptLexer;
import org.eclipse.dltk.javascript.parser.Reporter;

public class JSCommonTokenStream extends CommonTokenStream implements
		JSTokenStream {

	public JSCommonTokenStream(JavaScriptLexer tokenSource) {
		super(tokenSource);
	}

	public int getMode() {
		return JSTokenSource.MODE_JS;
	}

	public void setMode(int value) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Token> getTokens() {
		return super.getTokens();
	}

	public void setReporter(Reporter reporter) {
		((JavaScriptLexer) tokenSource).setReporter(reporter);
	}

}
