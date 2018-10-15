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
package org.eclipse.dltk.javascript.ui.tests.autoedit;

import org.eclipse.dltk.javascript.internal.ui.text.JavascriptAutoEditStrategy;
import org.eclipse.dltk.javascript.ui.text.IJavaScriptPartitions;

public class TestStrategy extends JavascriptAutoEditStrategy {

	public TestStrategy() {
		super(IJavaScriptPartitions.JS_PARTITIONING, null);
	}

	private boolean smartMode = true;

	@Override
	protected boolean computeSmartMode() {
		return smartMode;
	}

}
