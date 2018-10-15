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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class JavaScriptBracesTabPage extends FormatterModifyTabPage {

	public JavaScriptBracesTabPage(IFormatterModifyDialog dialog) {
		super(dialog);
	}

	private final String[] bracePositionKeys4 = new String[] {
			JavaScriptFormatterConstants.BRACE_SAME_LINE,
			JavaScriptFormatterConstants.BRACE_NEXT_LINE,
			JavaScriptFormatterConstants.BRACE_NEXT_LINE_INDENTED,
			JavaScriptFormatterConstants.BRACE_NEXT_LINE_ON_WRAP };

	private final String[] bracePositionKeys3 = new String[] {
			JavaScriptFormatterConstants.BRACE_SAME_LINE,
			JavaScriptFormatterConstants.BRACE_NEXT_LINE,
			JavaScriptFormatterConstants.BRACE_NEXT_LINE_INDENTED };

	private final String[] bracePositionItems3 = new String[] {
			Messages.JavaScriptBracesTabPage_BraceSameLine,
			Messages.JavaScriptBracesTabPage_BraceNextLine,
			Messages.JavaScriptBracesTabPage_BraceNextLineIndented };

	private class InitializeListener implements
			IFormatterControlManager.IInitializeListener {

		private final IFormatterControlManager manager;

		public InitializeListener(IFormatterControlManager manager) {
			this.manager = manager;
		}

		public void initialize() {
			keepEmptyArray.setEnabled(!manager.getString(
					JavaScriptFormatterConstants.BRACE_ARRAY).equals(
					JavaScriptFormatterConstants.BRACE_SAME_LINE));
		}
	}

	Combo arrayInitializer;
	Button keepEmptyArray;

	@Override
	protected void createOptions(IFormatterControlManager manager,
			Composite parent) {
		Group positionGroup = SWTFactory.createGroup(parent,
				Messages.JavaScriptBracesTabPage_BracePositionGroup_name, 2, 1,
				GridData.FILL_HORIZONTAL);

		manager.createCombo(positionGroup,
				JavaScriptFormatterConstants.BRACE_METHOD,
				Messages.JavaScriptBracesTabPage_FunctionDeclaration_name,
				bracePositionKeys3, bracePositionItems3);

		manager.createCombo(positionGroup,
				JavaScriptFormatterConstants.BRACE_BLOCK,
				Messages.JavaScriptBracesTabPage_Blocks_name,
				bracePositionKeys3, bracePositionItems3);

		manager.createCombo(positionGroup,
				JavaScriptFormatterConstants.BRACE_CASE,
				Messages.JavaScriptBracesTabPage_BlocksInCaseStatement_name,
				bracePositionKeys3, bracePositionItems3);

		manager.createCombo(positionGroup,
				JavaScriptFormatterConstants.BRACE_SWITCH,
				Messages.JavaScriptBracesTabPage_SwitchStatement_name,
				bracePositionKeys3, bracePositionItems3);

		arrayInitializer = manager.createCombo(positionGroup,
				JavaScriptFormatterConstants.BRACE_ARRAY,
				Messages.JavaScriptBracesTabPage_ArrayInitializer_name,
				bracePositionKeys4, bracePositionItems3);

		keepEmptyArray = manager.createCheckbox(positionGroup,
				JavaScriptFormatterConstants.BRACE_EMPTY_ARRAY,
				Messages.JavaScriptBracesTabPage_KeepEmptyArrayOnOneLine_name,
				2);

		manager.createCheckbox(positionGroup,
				JavaScriptFormatterConstants.BRACE_EMPTY_FUNCTION,
				Messages.JavaScriptBracesTabPage_EmptyFunctionBraces_name, 2);

		manager.createCheckbox(positionGroup,
				JavaScriptFormatterConstants.BRACE_EMPTY_OBJECT,
				Messages.JavaScriptBracesTabPage_EmptyObjectBraces_name, 2);

		manager.addInitializeListener(new InitializeListener(manager));

		arrayInitializer.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				keepEmptyArray.setEnabled(arrayInitializer.getSelectionIndex() > 0);
			}
		});

	}

	@Override
	protected URL getPreviewContent() {
		return getClass().getResource("braces-preview.js"); //$NON-NLS-1$
	}

}
