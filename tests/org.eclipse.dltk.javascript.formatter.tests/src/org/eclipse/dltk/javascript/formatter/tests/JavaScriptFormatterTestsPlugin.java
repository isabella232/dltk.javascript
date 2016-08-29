/*******************************************************************************
 * Copyright (c) 2009, 2016 xored software, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html  
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Vladimir Belov)
 *******************************************************************************/

package org.eclipse.dltk.javascript.formatter.tests;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.compiler.util.Util;
import org.eclipse.dltk.formatter.tests.ScriptedTest.IScriptedTestContext;
import org.eclipse.dltk.javascript.formatter.JavaScriptFormatterConstants;
import org.eclipse.dltk.javascript.internal.formatter.tests.TestJavaScriptFormatter;
import org.eclipse.dltk.ui.formatter.IScriptFormatter;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class JavaScriptFormatterTestsPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.dltk.ruby.formatter.tests"; //$NON-NLS-1$

	// The shared instance
	private static JavaScriptFormatterTestsPlugin plugin;

	/**
	 * The constructor
	 */
	public JavaScriptFormatterTestsPlugin() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static JavaScriptFormatterTestsPlugin getDefault() {
		return plugin;
	}

	public static void warn(String message) {
		warn(message, null);
	}

	public static void warn(String message, Throwable throwable) {
		log(new Status(IStatus.WARNING, PLUGIN_ID, 0, message, throwable));
	}

	public static void error(String message) {
		error(message, null);
	}

	public static void error(String message, Throwable throwable) {
		log(new Status(IStatus.ERROR, PLUGIN_ID, 0, message, throwable));
	}

	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static final IScriptedTestContext CONTEXT = new IScriptedTestContext() {

		@Override
		public Bundle getResourceBundle() {
			return getDefault().getBundle();
		}

		@Override
		public String getCharset() {
			return "ISO-8859-1"; //$NON-NLS-1$
		}

		@Override
		public IScriptFormatter createFormatter(Map<String, Object> preferences) {
			final Map<String, Object> prefs = JavaScriptFormatterConstants
					.getDefaults();
			prefs.put(
					JavaScriptFormatterConstants.INSERT_SPACE_BEFORE_LP_FUNCTION_ARGUMENTS,
					true);
			prefs.put(JavaScriptFormatterConstants.INDENT_SWITCH, true);
			if (preferences != null) {
				prefs.putAll(preferences);
			}
			return new TestJavaScriptFormatter(Util.LINE_SEPARATOR, prefs);
		}

		@Override
		public String validateOptionName(String name) {
			if (JavaScriptFormatterConstants.isDefined(name))
				return name;

			return null;
		}

		@Override
		public String validateOptionValue(String name, String value) {
			if (!JavaScriptFormatterConstants.isDefined(name))
				return null;

			if (JavaScriptFormatterConstants.isBoolean(name)) {
				return isBoolean(value) ? value : null;
			}

			if (JavaScriptFormatterConstants.isInteger(name)) {
				return isInteger(value) ? value : null;
			}

			return value;
		}

	};

	private static boolean isBoolean(String value) {
		return "false".equals(value) || "true".equals(value); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
