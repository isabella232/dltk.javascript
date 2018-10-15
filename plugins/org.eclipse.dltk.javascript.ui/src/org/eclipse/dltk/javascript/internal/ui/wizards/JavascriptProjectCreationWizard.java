/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.wizards;

import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.javascript.ui.JavaScriptImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.ProjectWizard;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.dltk.ui.wizards.ProjectWizardSecondPage;

public class JavascriptProjectCreationWizard extends ProjectWizard {
	private ProjectWizardFirstPage fFirstPage;
	private ProjectWizardSecondPage fSecondPage;

	public JavascriptProjectCreationWizard() {
		setDefaultPageImageDescriptor(JavaScriptImages.DESC_WIZBAN_PROJECT_CREATION);
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
		setWindowTitle(JavascriptWizardMessages.ProjectCreationWizard_title);
	}

	@Override
	public String getScriptNature() {
		return JavaScriptNature.NATURE_ID;
	}

	@Override
	public void addPages() {
		super.addPages();
		fFirstPage = new ProjectWizardFirstPage() {

			@Override
			protected boolean interpeterRequired() {
				// TODO Auto-generated method stub
				return false;
			}
		};
		fFirstPage
				.setTitle(JavascriptWizardMessages.ProjectCreationWizardFirstPage_title);
		fFirstPage
				.setDescription(JavascriptWizardMessages.ProjectCreationWizardFirstPage_description);
		addPage(fFirstPage);
		fSecondPage = new ProjectWizardSecondPage(fFirstPage);
		addPage(fSecondPage);
	}

}
