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
package org.eclipse.dltk.javascript.parser.tests;

import org.eclipse.dltk.core.tests.util.StringList;

public class Bug20110503 extends AbstractJSParserTest {

	/**
	 * @see org.eclipse.dltk.javascript.parser.JavaScriptParser.JSBaseParser#combineFollows(boolean)
	 */
	public void testCombinedFollowsNPE() {
		final StringList code = new StringList();
		code.add("var x = test({");
		code.add("  a : {");
		code.add("	  b : function () {");
		code.add("	    var q = new Q(");
		code.add("	  }");
		code.add("	}");
		code.add("});");
		parse(code.toString());
	}

}
