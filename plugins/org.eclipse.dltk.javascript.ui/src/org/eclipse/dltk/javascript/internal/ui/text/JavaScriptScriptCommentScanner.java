/*******************************************************************************
 * Copyright (c) 2010 xored software, Inc.
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
package org.eclipse.dltk.javascript.internal.ui.text;

import org.eclipse.dltk.compiler.task.ITodoTaskPreferences;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptCommentScanner;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.preference.IPreferenceStore;

public class JavaScriptScriptCommentScanner extends ScriptCommentScanner {

	public JavaScriptScriptCommentScanner(IColorManager manager,
			IPreferenceStore store, String comment, String todoTag,
			ITodoTaskPreferences preferences) {
		super(manager, store, comment, todoTag, preferences);
	}

	public JavaScriptScriptCommentScanner(
			ScriptSourceViewerConfiguration configuration, String comment,
			String todoTag, ITodoTaskPreferences preferences) {
		super(configuration, comment, todoTag, preferences);
	}

	@Override
	protected int skipCommentChars() {
		int count = 0;
		int c = read();
		while (Character.isWhitespace(c)) {
			++count;
			c = read();
		}
		while (c == '/') {
			++count;
			c = read();
		}
		while (c == '*') {
			++count;
			c = read();
		}
		unread();
		return count;
	}

}
