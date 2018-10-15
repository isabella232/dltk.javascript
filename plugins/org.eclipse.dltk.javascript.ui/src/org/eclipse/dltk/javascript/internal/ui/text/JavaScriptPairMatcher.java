/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.text;

import org.eclipse.dltk.javascript.ui.text.IJavaScriptPartitions;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;

/**
 * Helper class for match pairs of characters.
 */
public final class JavaScriptPairMatcher extends DefaultCharacterPairMatcher {

	public JavaScriptPairMatcher(char[] pairs) {
		super(pairs, IJavaScriptPartitions.JS_PARTITIONING);
	}

}
