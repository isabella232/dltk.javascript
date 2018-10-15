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

import org.eclipse.dltk.formatter.IFormatterContext;
import org.eclipse.dltk.formatter.IFormatterDocument;
import org.eclipse.dltk.formatter.IFormatterWriter;
import org.eclipse.dltk.javascript.formatter.JavaScriptFormatterConstants;

public class FormatterElseNode extends FormatterBlockWithBeginNode {

	private boolean lineBreak;

	public FormatterElseNode(IFormatterDocument document, boolean lineBreak) {
		super(document);
		this.lineBreak = lineBreak;
	}

	@Override
	public void accept(IFormatterContext context, IFormatterWriter visitor)
			throws Exception {

		if (isLineBreaking()) {
			visitor.writeLineBreak(context);
		} else {
			visitor.appendToPreviousLine(context, JSLiterals.SPACE);
		}

		super.accept(context, visitor);
	}
	
	@Override
	protected boolean isIndenting() {
		return false;
	}

	protected boolean isLineBreaking() {
		if (lineBreak)
			return true;

		return getDocument().getBoolean(
				JavaScriptFormatterConstants.NEW_LINE_BEFORE_ELSE_IN_IF);
	}

}
