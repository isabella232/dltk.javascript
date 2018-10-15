/*******************************************************************************
 * Copyright (c) 2009 xored software, Inc.  
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0  
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Vladimir Belov)
 *******************************************************************************/

package org.eclipse.dltk.javascript.ast;

public class MultiLineComment extends Comment {

	public static final String JSDOC_PREFIX = "/**"; //$NON-NLS-1$

	@Override
	public boolean isMultiLine() {
		return true;
	}

	@Override
	public boolean isDocumentation() {
		final String text = getText();
		return text != null && text.startsWith(JSDOC_PREFIX);
	}

}
