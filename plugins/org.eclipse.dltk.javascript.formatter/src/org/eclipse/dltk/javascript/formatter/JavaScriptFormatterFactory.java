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

package org.eclipse.dltk.javascript.formatter;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.formatter.AbstractScriptFormatterFactory;
import org.eclipse.dltk.javascript.formatter.preferences.JavaScriptFormatterModifyDialog;
import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialog;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialogOwner;
import org.eclipse.dltk.ui.formatter.IScriptFormatter;
import org.eclipse.dltk.ui.preferences.PreferenceKey;

public class JavaScriptFormatterFactory extends AbstractScriptFormatterFactory {

	public IScriptFormatter createFormatter(String lineDelimiter,
			Map<String, String> preferences) {
		return new JavaScriptFormatter(lineDelimiter, preferences);
	}

	@Override
	public Map<String, String> changeToIndentingOnly(
			Map<String, String> preferences) {
		final Map<String, String> copy = new HashMap<String, String>(
				preferences);
		copy.put(JavaScriptFormatterConstants.STATEMENT_NEW_LINE, String
				.valueOf(false));
		copy.put(JavaScriptFormatterConstants.WRAP_COMMENTS, String
				.valueOf(false));
		copy.put(JavaScriptFormatterConstants.LINES_PRESERVE, String
				.valueOf(-1));
		copy.put(JavaScriptFormatterConstants.KEEP_LINES, String.valueOf(true));
		return copy;
	}

	@Override
	public PreferenceKey getProfilesKey() {
		return new PreferenceKey(JavaScriptFormatterPlugin.PLUGIN_ID,
				JavaScriptFormatterConstants.FORMATTER_PROFILES);
	}

	public PreferenceKey getActiveProfileKey() {
		return new PreferenceKey(JavaScriptFormatterPlugin.PLUGIN_ID,
				JavaScriptFormatterConstants.FORMATTER_ACTIVE_PROFILE);
	}

	public PreferenceKey[] getPreferenceKeys() {
		String[] options = JavaScriptFormatterConstants.getNames();

		final PreferenceKey[] result = new PreferenceKey[options.length];
		for (int i = 0; i < options.length; ++i) {
			final String key = options[i];
			final String qualifier;
			if (CodeFormatterConstants.FORMATTER_TAB_CHAR.equals(key)
					|| CodeFormatterConstants.FORMATTER_INDENTATION_SIZE
							.equals(key)
					|| CodeFormatterConstants.FORMATTER_TAB_SIZE.equals(key)) {
				qualifier = JavaScriptUI.PLUGIN_ID;
			} else {
				qualifier = JavaScriptFormatterPlugin.PLUGIN_ID;
			}
			result[i] = new PreferenceKey(qualifier, key);
		}
		return result;
	}

	@Override
	public URL getPreviewContent() {
		return getClass().getResource("FormatterPreview.js"); //$NON-NLS-1$
	}

	public IFormatterModifyDialog createDialog(
			IFormatterModifyDialogOwner dialogOwner) {
		return new JavaScriptFormatterModifyDialog(dialogOwner, this);
	}
}
