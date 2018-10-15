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

import java.util.List;

import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public interface JSTokenStream extends TokenStream {

	List<Token> getTokens();

	int getMode();

	void setMode(int value);

	void setReporter(Reporter reporter);

}
