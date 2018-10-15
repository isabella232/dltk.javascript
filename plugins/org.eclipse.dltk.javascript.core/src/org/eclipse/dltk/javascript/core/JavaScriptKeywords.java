/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.core;

public class JavaScriptKeywords {

	private static final String[] fgKeywords = { "break", "else", "new",
			"const", "var", "case", "finally", "return", "void", "catch",
			"for", "switch", "while", "continue", "function", "this", "with",
			"default", "if", "throw", "delete", "try", "do", "instanceof",
			"typeof", "null", "true", "false" };

	private static final String[] keywords = { "in", "undefined" };

	public static String[] getJavaScriptKeywords() {
		return fgKeywords;
	}

	public static String[] getJavaScriptExpressionKeywords() {
		return new String[] { "new", "function", "this", "delete",
				"instanceof", "typeof", "null", "true", "false" };
	}

	public static String[] getJavaScriptValueKeywords() {
		return new String[] { "new", "function", "this", "delete", "null",
				"true", "false" };
	}

	public static String[] getHighLightOnlyKeywords() {
		return keywords;
	}

}
