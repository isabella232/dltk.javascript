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
package org.eclipse.dltk.javascript.internal.ui.text;

import static org.eclipse.dltk.ui.PreferenceConstants.EDITOR_SEMANTIC_HIGHLIGHTING_ENABLED_SUFFIX;

import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;

public class AbstractJavaScriptHighlighter {

	protected static boolean isSemanticHighlightingEnabled(String key) {
		return JavaScriptUI.getDefault().getPreferenceStore()
				.getBoolean(key + EDITOR_SEMANTIC_HIGHLIGHTING_ENABLED_SUFFIX);
	}

}
