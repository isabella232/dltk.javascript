/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.text.folding;

import org.eclipse.dltk.javascript.internal.ui.preferences.JavaScriptPreferenceMessages;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.text.folding.SourceCodeFoldingPreferenceBlock;
import org.eclipse.jface.preference.PreferencePage;

/**
 * Javascript source code folding preferences.
 */
public class JavascriptFoldingPreferenceBlock extends
		SourceCodeFoldingPreferenceBlock {

	public JavascriptFoldingPreferenceBlock(OverlayPreferenceStore store,
			PreferencePage page) {
		super(store, page);
	}

	protected String getInitiallyFoldMethodsText() {
		return JavaScriptPreferenceMessages.JavascriptFoldingPreferencePage_initiallyFoldFunctions;
	}

	protected boolean supportsClassFolding() {
		return false;
	}
}
