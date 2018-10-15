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

package org.eclipse.dltk.javascript.formatter.tests;

import org.eclipse.dltk.formatter.tests.ScriptedTest;

import junit.framework.TestSuite;

public class StatementTest extends ScriptedTest {

	public static TestSuite suite() {
		return new StatementTest()
				.createScriptedSuite(JavaScriptFormatterTestsPlugin.CONTEXT,
						"scripts/statements.js");
	}

}
