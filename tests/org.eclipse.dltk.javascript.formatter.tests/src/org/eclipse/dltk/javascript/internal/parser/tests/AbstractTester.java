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

package org.eclipse.dltk.javascript.internal.parser.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class AbstractTester {

	protected static String getScriptContent(InputStream stream, String charset)
			throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				stream, charset));

		StringBuffer content = new StringBuffer();
		String s = null;

		while ((s = reader.readLine()) != null) {
			content.append(s);
			content.append("\r\n");
		}

		return content.toString();
	}

	protected static String getScriptContent(String resourceName, String charset)
			throws IOException {
		return getScriptContent(JavaScriptParserTester.class
				.getResourceAsStream("/scripts.parser/" + resourceName),
				charset);
	}

}
