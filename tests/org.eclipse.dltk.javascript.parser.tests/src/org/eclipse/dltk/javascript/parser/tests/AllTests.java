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
package org.eclipse.dltk.javascript.parser.tests;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"org.eclipse.dltk.javascript.parser.tests");
		// $JUnit-BEGIN$
		suite.addTest(new JUnit4TestAdapter(DeclarationScopeTest.class));
		suite.addTestSuite(NewTests.class);
		suite.addTestSuite(VariableDeclarationTests.class);
		suite.addTestSuite(ArrayInitializerTests.class);
		suite.addTest(new JUnit4TestAdapter(ObjectInitializerTests.class));
		suite.addTestSuite(FunctionDeclarationTests.class);
		suite.addTestSuite(XmlQueryTests.class);
		suite.addTestSuite(XmlLiteralTests.class);
		suite.addTestSuite(XmlLiteralTokenTests.class);
		suite.addTestSuite(ErrorReportingTests.class);
		suite.addTestSuite(FunctionDocumentationTests.class);
		suite.addTestSuite(ParserValidationsTest.class);
		suite.addTest(new JUnit4TestAdapter(SimpleJSDocParserTests.class));
		suite.addTestSuite(Bug20110503.class);
		suite.addTestSuite(ForTests.class);
		suite.addTestSuite(StringPoolTest.class);
		suite.addTestSuite(KeywordTest.class);
		// $JUnit-END$
		return suite;
	}

}
