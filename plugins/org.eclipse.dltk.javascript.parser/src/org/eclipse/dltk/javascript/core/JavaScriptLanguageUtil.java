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
package org.eclipse.dltk.javascript.core;

import org.eclipse.dltk.javascript.ast.Keywords;

public class JavaScriptLanguageUtil {

	public static boolean isValidIdentifier(String str) {
		// TODO: add proper checking
		for (int i = 1; i < str.length(); i++)
			if (!Character.isJavaIdentifierPart(str.charAt(i)))
				return false;
		return str.length() > 0
				&& Character.isJavaIdentifierStart(str.charAt(0))
				&& !Keywords.isKeyword(str);
	}

}
