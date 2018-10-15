/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class JavaScriptPreferenceMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.dltk.javascript.internal.ui.preferences.JavaScriptPreferenceMessages";//$NON-NLS-1$

	public static String JavascriptEditorPreferencePage_general;
	public static String JavaScriptEditorPropertyPage_SaveActionLink_Text;

	public static String JavaScriptGlobalPreferencePage_description;
	public static String JavascriptSmartTypingConfigurationBlock_smartPaste;
	public static String JavascriptSmartTypingConfigurationBlock_typing_smartTab;
	public static String JavascriptSmartTypingConfigurationBlock_closeStrings;
	public static String JavascriptSmartTypingConfigurationBlock_closeBrackets;
	public static String JavascriptSmartTypingConfigurationBlock_typing_tabTitle;
	public static String TodoTaskDescription;
	public static String ErrorWarningDescription;
	public static String ErrorWarning_enableTypeInfo;

	public static String JavascriptFoldingPreferencePage_initiallyFoldFunctions;

	static {
		NLS.initializeMessages(BUNDLE_NAME, JavaScriptPreferenceMessages.class);
	}
}
