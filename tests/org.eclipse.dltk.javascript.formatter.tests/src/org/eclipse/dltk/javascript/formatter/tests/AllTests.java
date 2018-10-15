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
 *     xored software, Inc. - initial API and Implementation (Vladimir Belov)
 *******************************************************************************/

package org.eclipse.dltk.javascript.formatter.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.dltk.javascript.parser.tests.ANTLRParserTestCase;
import org.eclipse.dltk.javascript.parser.tests.JavaScriptParserTestCase;
import org.eclipse.dltk.javascript.parser.tests.JavaScriptStreamTokenTestCase;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"org.eclipse.dltk.javascript.formatter.tests"); //$NON-NLS-1$
		// $JUnit-BEGIN$
		suite.addTest(ArraysTest.suite());
		suite.addTest(BracesTest.suite());
		suite.addTest(IfStatementTest.suite());
		suite.addTest(FunctionTest.suite());
		suite.addTest(DoWhileTest.suite());
		suite.addTest(OptionsTest.suite());
		suite.addTest(CommentsTest.suite());
		suite.addTest(SwitchTest.suite());
		suite.addTest(TryCatchTest.suite());
		suite.addTest(SpacesTest.suite());
		suite.addTest(NewLinesTest.suite());
		suite.addTest(StringsTest.suite());
		suite.addTest(BlockTest.suite());
		suite.addTest(ForStatementTest.suite());
		suite.addTest(WhileTest.suite());
		suite.addTest(RegExpTest.suite());
		suite.addTest(KeywordsTest.suite());
		suite.addTest(E4XTest.suite());
		suite.addTest(StatementTest.suite());
		suite.addTest(VarTest.suite());
		suite.addTest(ObjectInitializerTest.suite());
		suite.addTestSuite(JavaScriptStreamTokenTestCase.class);
		suite.addTestSuite(JavaScriptParserTestCase.class);
		suite.addTestSuite(ANTLRParserTestCase.class);
		// suite.addTest(JavaScriptLibTest.suite());
		// $JUnit-END$
		return suite;
	}
}
