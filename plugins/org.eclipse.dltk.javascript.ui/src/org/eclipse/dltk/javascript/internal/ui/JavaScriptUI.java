/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.javascript.internal.ui.text.JavascriptTextTools;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 */
public class JavaScriptUI extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.dltk.javascript.ui";

	// The shared instance
	private static JavaScriptUI plugin;
	
	private JavascriptTextTools fJavascriptTextTools;
	
	/**
	 * The constructor
	 */
	public JavaScriptUI() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static JavaScriptUI getDefault() {
		return plugin;
	}
	
	public synchronized JavascriptTextTools getTextTools() {
		if (fJavascriptTextTools == null)
			fJavascriptTextTools= new JavascriptTextTools(true);
		return fJavascriptTextTools;
	}

	public static void log(Throwable t) {
		final Plugin p = plugin;
		if (p != null) {
			p.getLog().log(
					new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, t
							.getLocalizedMessage(), t));
		} else {
			t.printStackTrace();
		}
	}
}
