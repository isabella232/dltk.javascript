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

package org.eclipse.dltk.javascript.internal.ui.refactoring.code;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.internal.javascript.corext.refactoring.code.ExtractMethodRefactoring;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.ui.refactoring.RefactoringWizard;

public class ExtractMethodWizard extends RefactoringWizard {

	/* package */static final String DIALOG_SETTING_SECTION = "ExtractMethodWizard"; //$NON-NLS-1$

	public ExtractMethodWizard(ExtractMethodRefactoring ref) {
		super(ref, DIALOG_BASED_USER_INTERFACE | PREVIEW_EXPAND_FIRST_NODE);
		setDefaultPageTitle(RefactoringMessages.ExtractMethodWizard_extract_method);
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
	}

	public Change createChange() {
		// creating the change is cheap. So we don't need to show progress.
		try {
			return getRefactoring().createChange(new NullProgressMonitor());
		} catch (CoreException e) {
			DLTKUIPlugin.log(e);
			return null;
		}
	}

	protected void addUserInputPages() {
		addPage(new ExtractMethodInputPage());
	}
}
