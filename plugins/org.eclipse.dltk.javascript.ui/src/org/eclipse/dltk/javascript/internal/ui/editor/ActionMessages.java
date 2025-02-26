/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.editor;

import org.eclipse.osgi.util.NLS;

public final class ActionMessages extends NLS {

	private static final String BUNDLE_NAME= "org.eclipse.dltk.javascript.internal.ui.editor.ActionMessages";//$NON-NLS-1$

	private ActionMessages() {
		// Do not instantiate
	}

	public static String MemberFilterActionGroup_hide_variables_label;
	public static String MemberFilterActionGroup_hide_variables_tooltip;
	public static String MemberFilterActionGroup_hide_variables_description;
	public static String MemberFilterActionGroup_hide_functions_label;
	public static String MemberFilterActionGroup_hide_functions_tooltip;
	public static String MemberFilterActionGroup_hide_functions_description;
	public static String MemberFilterActionGroup_hide_classes_label;
	public static String MemberFilterActionGroup_hide_classes_tooltip;
	public static String MemberFilterActionGroup_hide_classes_description;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ActionMessages.class);
	}

}
