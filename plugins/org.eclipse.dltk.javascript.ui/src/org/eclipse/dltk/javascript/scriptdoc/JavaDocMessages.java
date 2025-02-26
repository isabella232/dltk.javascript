/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.scriptdoc;

import org.eclipse.osgi.util.NLS;


final class JavaDocMessages extends NLS {

	private static final String BUNDLE_NAME= JavaDocMessages.class.getName();

	private JavaDocMessages() {
		// Do not instantiate
	}

//	public static String CompletionEvaluator_default_package;
	public static String JavaDoc2HTMLTextReader_parameters_section;
	public static String JavaDoc2HTMLTextReader_returns_section;
	public static String JavaDoc2HTMLTextReader_throws_section;
	public static String JavaDoc2HTMLTextReader_author_section;
	public static String JavaDoc2HTMLTextReader_see_section;
	public static String JavaDoc2HTMLTextReader_since_section;

	static {
		NLS.initializeMessages(BUNDLE_NAME, JavaDocMessages.class);
	}
}