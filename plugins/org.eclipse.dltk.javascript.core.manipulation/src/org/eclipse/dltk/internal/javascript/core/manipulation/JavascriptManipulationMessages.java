/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.core.manipulation;

import org.eclipse.osgi.util.NLS;

public class JavascriptManipulationMessages extends NLS {

	private static final String BUNDLE_NAME= "org.eclipse.dltk.internal.javascript.core.manipulation.JavascriptManipulationMessages"; //$NON-NLS-1$

	private JavascriptManipulationMessages() {
	}

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, JavascriptManipulationMessages.class);
	}

	public static String JavascriptManipulationMessages_internalError;
	public static String UndoSourceModuleChange_no_file;
}
