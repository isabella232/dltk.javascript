/*******************************************************************************
 * Copyright (c) 2008 xored software, Inc.
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
package org.eclipse.dltk.javascript.ui.actions;

import org.eclipse.dltk.internal.ui.editor.DLTKEditorMessages;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.actions.DLTKActionConstants;
import org.eclipse.dltk.ui.actions.GenerateActionGroup;
import org.eclipse.dltk.ui.actions.IScriptEditorActionDefinitionIds;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;

public class JavaScriptGenerateActionGroup extends GenerateActionGroup {

	private AddJavaDocStubAction fAddJavaDocStub;

	public JavaScriptGenerateActionGroup(ScriptEditor editor, String groupName) {
		super(editor, groupName);

		Action action = new AddBlockCommentAction(
				DLTKEditorMessages.getBundleForConstructedKeys(),
				"AddBlockComment.", editor); //$NON-NLS-1$
		action.setActionDefinitionId(IScriptEditorActionDefinitionIds.ADD_BLOCK_COMMENT);
		editor.setAction(DLTKActionConstants.ADD_BLOCK_COMMENT, action);
		editor.markAsStateDependentAction(
				DLTKActionConstants.ADD_BLOCK_COMMENT, true);
		editor.markAsSelectionDependentAction(
				DLTKActionConstants.ADD_BLOCK_COMMENT, true);

		action = new RemoveBlockCommentAction(
				DLTKEditorMessages.getBundleForConstructedKeys(),
				"RemoveBlockComment.", editor); //$NON-NLS-1$
		action.setActionDefinitionId(IScriptEditorActionDefinitionIds.REMOVE_BLOCK_COMMENT);
		editor.setAction(DLTKActionConstants.REMOVE_BLOCK_COMMENT, action);
		editor.markAsStateDependentAction(
				DLTKActionConstants.REMOVE_BLOCK_COMMENT, true);
		editor.markAsSelectionDependentAction(
				DLTKActionConstants.REMOVE_BLOCK_COMMENT, true);

		fAddJavaDocStub = new AddJavaDocStubAction(editor);
		fAddJavaDocStub
				.setActionDefinitionId(IScriptEditorActionDefinitionIds.ADD_JAVADOC_COMMENT);
		editor.setAction("AddJavadocComment", fAddJavaDocStub); //$NON-NLS-1$
	}

	@Override
	protected int fillEditorSubMenu(IMenuManager source) {
		return super.fillEditorSubMenu(source)
				+ addAction(source, GROUP_COMMENT, fAddJavaDocStub);
	}

	@Override
	protected void setGlobalActionHandlers(IActionBars bars) {
		bars.setGlobalActionHandler(DLTKActionConstants.ADD_JAVA_DOC_COMMENT,
				fAddJavaDocStub);
		super.setGlobalActionHandlers(bars);
	}

}
