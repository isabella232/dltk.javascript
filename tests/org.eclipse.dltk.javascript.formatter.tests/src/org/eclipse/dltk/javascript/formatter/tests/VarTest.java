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

import junit.framework.TestSuite;

import org.eclipse.dltk.formatter.tests.ScriptedTest;

public class VarTest extends ScriptedTest {

	public static TestSuite suite() {
		return new VarTest().createScriptedSuite(
				JavaScriptFormatterTestsPlugin.CONTEXT, "scripts/var.js");
	}

}
