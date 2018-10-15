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

import org.eclipse.osgi.util.NLS;

public final class JavaScriptFoldingMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.dltk.javascript.internal.ui.text.folding.JavaScriptFoldingMessages";//$NON-NLS-1$

	private JavaScriptFoldingMessages() {
		// Do not instantiate
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, JavaScriptFoldingMessages.class);
	}

	public static String JavaScriptFoldingPreferenceBlock_initFoldDoc;
}
