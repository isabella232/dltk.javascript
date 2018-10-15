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

package org.eclipse.dltk.javascript.formatter.internal.nodes;

import org.eclipse.dltk.formatter.IFormatterDocument;
import org.eclipse.dltk.javascript.ast.JSNode;
import org.eclipse.dltk.javascript.formatter.JavaScriptFormatterConstants;

public class EmptyArrayBracketsConfiguration extends ArrayBracketsConfiguration {

	public EmptyArrayBracketsConfiguration(IFormatterDocument document,
			JSNode node) {
		super(document, node);
	}

	public boolean isBeginLineBreaking() {

		if (getDocument().getBoolean(
				JavaScriptFormatterConstants.BRACE_EMPTY_ARRAY))
			return false;

		return super.isBeginLineBreaking();
	}

}
