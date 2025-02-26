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
package org.eclipse.dltk.javascript.internal.ui.text.folding;

import org.eclipse.dltk.ui.text.folding.IFoldingBlockKind;

public enum JavaScriptFoldingBlockKind implements IFoldingBlockKind {
	FUNCTION(false), XML(false), MULTILINESTRING(false), OBJECT_INITIALIZER(
			false), COMMENT(true), JSDOC(true);

	final boolean isComment;

	private JavaScriptFoldingBlockKind(boolean isComment) {
		this.isComment = isComment;
	}

	public boolean isComment() {
		return isComment;
	}

}
