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
package org.eclipse.dltk.javascript.parser;

import org.antlr.runtime.TokenSource;

public interface JSTokenSource extends TokenSource {

	int MODE_JS = 0;
	int MODE_XML = 1;
	int MODE_EXPRESSION = 2;

	int getMode();

	void setMode(int mode);

	/**
	 * Set the input cursor to the character position indicated by index.
	 **/
	void seek(int index);
}
