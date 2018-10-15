/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.dltk.javascript.internal.debug.ui.launchConfigurations;

import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.dltk.debug.ui.launchConfigurations.MainLaunchConfigurationTab;
import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.javascript.internal.debug.JavaScriptDebugPlugin;

/**
 * Main launch configuration tab for javascript scripts
 */
public class JavaScriptMainLaunchConfigurationTab extends
		MainLaunchConfigurationTab {

	public JavaScriptMainLaunchConfigurationTab(String mode) {
		super(mode);
	}

	/*
	 * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#breakOnFirstLinePrefEnabled(org.eclipse.dltk.core.PreferencesLookupDelegate)
	 */
	protected boolean breakOnFirstLinePrefEnabled(
			PreferencesLookupDelegate delegate) {
		return delegate.getBoolean(JavaScriptDebugPlugin.PLUGIN_ID,
				DLTKDebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE);
	}

	/*
	 * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#dbpgLoggingPrefEnabled(org.eclipse.dltk.core.PreferencesLookupDelegate)
	 */
	protected boolean dbpgLoggingPrefEnabled(PreferencesLookupDelegate delegate) {
		return delegate.getBoolean(JavaScriptDebugPlugin.PLUGIN_ID,
				DLTKDebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING);
	}

	/**
	 * @since 2.0
	 */
	public String getNatureID() {
		return JavaScriptNature.NATURE_ID;
	}
}
