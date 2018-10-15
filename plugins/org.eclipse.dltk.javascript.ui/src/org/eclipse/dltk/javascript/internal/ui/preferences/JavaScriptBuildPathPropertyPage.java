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

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.javascript.core.JavaScriptLanguageToolkit;
import org.eclipse.dltk.ui.preferences.BuildPathsPropertyPage;
import org.eclipse.ui.IWorkbenchPropertyPage;

public class JavaScriptBuildPathPropertyPage extends BuildPathsPropertyPage
		implements IWorkbenchPropertyPage {
	public JavaScriptBuildPathPropertyPage() {
	}

	public IDLTKLanguageToolkit getLanguageToolkit() {
		return JavaScriptLanguageToolkit.getDefault();
	}
}
