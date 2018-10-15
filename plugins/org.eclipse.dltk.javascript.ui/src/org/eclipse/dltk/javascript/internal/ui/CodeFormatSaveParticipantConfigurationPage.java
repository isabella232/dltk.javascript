/*******************************************************************************
 * Copyright (c) 2011 NumberFour AG
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     NumberFour AG - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class CodeFormatSaveParticipantConfigurationPage extends
		AbstractConfigurationBlockPropertyAndPreferencePage {

	public static final String PROPERTY_PAGE_ID = "org.eclipse.dltk.javascript.editor.saveActions.propertyPage";

	static final PreferenceKey[] KEYS = new PreferenceKey[] { new PreferenceKey(
			JavaScriptUI.PLUGIN_ID,
			CodeFormatSaveParticipant.EDITOR_SAVE_PARTICIPANT_PREFIX
					+ CodeFormatSaveParticipant.ID) };

	@Override
	protected AbstractOptionsBlock createOptionsBlock(
			IStatusChangeListener newStatusChangedListener, IProject project,
			IWorkbenchPreferenceContainer container) {
		return new CodeFormatSaveParticipantConfigurationBlock(
				newStatusChangedListener, project, KEYS, container);
	}

	@Override
	protected String getHelpId() {
		return null;
	}

	@Override
	protected String getProjectHelpId() {
		return null;
	}

	@Override
	protected void setDescription() {
	}

	@Override
	protected void setPreferenceStore() {
	}

	@Override
	protected String getPreferencePageId() {
		return null;
	}

	@Override
	protected String getPropertyPageId() {
		return "org.eclipse.dltk.javascript.propertyPage.codeFormatSaveParticipant";
	}

}
