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

import org.eclipse.dltk.javascript.formatter.JavaScriptFormatterConstants;
import org.eclipse.dltk.ui.formatter.FormatterModifyTabPage;
import org.eclipse.dltk.ui.formatter.IFormatterControlManager;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialog;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class JavaScriptNewLinesTabPage extends FormatterModifyTabPage {

	public JavaScriptNewLinesTabPage(IFormatterModifyDialog dialog) {
		super(dialog);
	}

	@Override
	protected void createOptions(IFormatterControlManager manager,
			Composite parent) {

		Group insertNewLinesGroup = SWTFactory.createGroup(parent,
				Messages.JavaScriptNewLinesTabPage_InsertNewLineGroup_name, 1,
				1, GridData.FILL_HORIZONTAL);

		manager.createCheckbox(insertNewLinesGroup,
				JavaScriptFormatterConstants.NEW_LINE_IN_EMPTY_METHOD,
				Messages.JavaScriptBracesTabPage_NewLineInEmptyMethodBody_name);

		manager.createCheckbox(insertNewLinesGroup,
				JavaScriptFormatterConstants.NEW_LINE_IN_EMPTY_BLOCK,
				Messages.JavaScriptBracesTabPage_NewLineInEmptyBlock_name);

		manager.createCheckbox(insertNewLinesGroup,
				JavaScriptFormatterConstants.NEW_LINE_AT_EOF,
				Messages.JavaScriptBracesTabPage_NewLineAtEndOfFile_name);

	}

	@Override
	protected URL getPreviewContent() {
		return getClass().getResource("new-lines-preview.js"); //$NON-NLS-1$
	}

}
