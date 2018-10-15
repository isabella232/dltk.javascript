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

public class JSParserState {
	final JSParserState parent;
	final JSParserRule rule;
	private int errorCount = 0;

	public JSParserState(JSParserState parent, JSParserRule rule) {
		this.parent = parent;
		this.rule = rule;
	}

	public boolean hasErrors() {
		return errorCount != 0;
	}

	public void incrementErrorCount() {
		JSParserState state = this;
		for (;;) {
			++state.errorCount;
			state = state.parent;
			if (state == null) {
				break;
			}
		}
	}

}
