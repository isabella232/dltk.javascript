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
package org.eclipse.dltk.javascript.internal.ui.preferences;

import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;
import org.eclipse.dltk.ui.preferences.MarkOccurrencesPreferencePage;

public class JavaScriptMarkOccurrencesPreferencePage extends
		MarkOccurrencesPreferencePage {

	@Override
	protected void setPreferenceStore() {
		setPreferenceStore(JavaScriptUI.getDefault().getPreferenceStore());
	}

}
