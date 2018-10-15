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
package org.eclipse.dltk.javascript.formatter.internal.nodes;

/**
 * [BracesIndenting] [BeforeOpenBrace] { [AfterOpenBrace]
 * 
 * [BracesIndenting] [Indenting] body...
 * 
 * [BracesIndenting] [BeforeCloseBrace] } [AfterCloseBrace]
 */
public interface IBracesConfiguration {

	/**
	 * Indents content between braces
	 * 
	 * @return
	 */
	boolean isIndenting();

	/**
	 * Indents braces and content
	 * 
	 * @return
	 */
	boolean isBracesIndenting();

	/**
	 * Keep original text
	 */
	public static final int UNDEFINED = 0;

	/**
	 * Replace white-spaces with one space character
	 */
	public static final int ONE_SPACE = 1;

	/**
	 * Start body with new line
	 */
	public static final int LINE_BREAK = 2;

	/**
	 * Delete all white-spaces
	 */
	public static final int EMPTY_SPACE = 3;

	/**
	 * 
	 * @return
	 */
	int insertBeforeOpenBrace();

	/**
	 * 
	 * @return
	 */
	int insertAfterOpenBrace();

	/**
	 * 
	 * @return
	 */
	int insertBeforeCloseBrace();

	/**
	 * 
	 * @return
	 */
	int insertAfterCloseBrace();
}
