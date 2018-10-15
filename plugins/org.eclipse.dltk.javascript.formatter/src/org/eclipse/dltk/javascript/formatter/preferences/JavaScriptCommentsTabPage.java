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

package org.eclipse.dltk.javascript.formatter.preferences;

import java.net.URL;

import org.eclipse.dltk.ui.formatter.FormatterModifyTabPage;
import org.eclipse.dltk.ui.formatter.IFormatterControlManager;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialog;
import org.eclipse.swt.widgets.Composite;

public class JavaScriptCommentsTabPage extends FormatterModifyTabPage {

	public JavaScriptCommentsTabPage(IFormatterModifyDialog dialog) {
		super(dialog);
	}

	@Override
	protected void createOptions(IFormatterControlManager manager,
			Composite parent) {
	}

	@Override
	protected URL getPreviewContent() {
		return getClass().getResource("comments-preview.js"); //$NON-NLS-1$
	}
}
