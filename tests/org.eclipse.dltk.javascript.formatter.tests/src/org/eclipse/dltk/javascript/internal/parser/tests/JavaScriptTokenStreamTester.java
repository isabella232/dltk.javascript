/*******************************************************************************
 * Copyright (c) 2009, 2016 xored software, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html  
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Vladimir Belov)
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.parser.tests;

import java.io.IOException;
import java.util.List;

import org.antlr.runtime.Token;
import org.eclipse.dltk.compiler.env.ModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.javascript.ast.Script;
import org.eclipse.dltk.javascript.formatter.tests.JavaScriptFormatterTestsPlugin;
import org.eclipse.dltk.javascript.parser.JSTokenStream;
import org.eclipse.dltk.javascript.parser.JavaScriptParser;
import org.junit.Assert;

public class JavaScriptTokenStreamTester extends AbstractTester {

	public static void tokenize(String resourceName) throws IOException {
		tokenize(resourceName,
				JavaScriptFormatterTestsPlugin.CONTEXT.getCharset());
	}

	public static void tokenize(String resourceName, String charset)
			throws IOException {

		String source = getScriptContent(resourceName, charset);

		JSTokenStream stream = new JavaScriptParser().createTokenStream(source);

		List<Token> tokens = stream.getTokens();

		Assert.assertTrue(tokens.size() > 0);

		// for (int i = 0; i < tokens.size(); i++) {
		// Token tk = (Token) tokens.get(i);
		// System.out.println(i + ". " + tk.getText());
		// }
	}

	public static void parse(String resourceName) throws IOException {
		parse(resourceName, JavaScriptFormatterTestsPlugin.CONTEXT.getCharset());
	}

	public static void parse(String resourceName, String charset)
			throws IOException {
		String source = getScriptContent(resourceName, charset);

		JavaScriptParser parser = new JavaScriptParser();

		Script root = parser.parse(new ModuleSource(source),
				(IProblemReporter) problem -> System.out.println("Parser "
						+ (problem.isError() ? "error" : "warning")
						+ ": " + problem.getMessage()));

		Assert.assertNotNull(root);

		// System.out.println(((Script) root).toSourceString(""));
	}
}
