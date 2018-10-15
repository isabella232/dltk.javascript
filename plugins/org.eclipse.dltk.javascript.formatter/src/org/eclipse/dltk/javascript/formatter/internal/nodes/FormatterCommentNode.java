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

import org.eclipse.dltk.formatter.FormatterTextNode;
import org.eclipse.dltk.formatter.IFormatterContext;
import org.eclipse.dltk.formatter.IFormatterDocument;
import org.eclipse.dltk.formatter.IFormatterWriter;
import org.eclipse.dltk.javascript.formatter.internal.JavaScriptFormatterContext;

public class FormatterCommentNode extends FormatterTextNode {

	private final boolean multiLine;

	public FormatterCommentNode(IFormatterDocument document, int startOffset,
			int endOffset, boolean multiLine) {
		super(document, startOffset, endOffset);
		this.multiLine = multiLine;
	}

	@Override
	public void accept(IFormatterContext context, IFormatterWriter visitor)
			throws Exception {
		context.setComment(true);
		if (multiLine) {
			visitor.ensureLineStarted(context);
			JavaScriptFormatterContext copy = (JavaScriptFormatterContext) context
					.copy();
			copy.setAdditionalIndent(JSLiterals.SPACE);
			super.accept(copy, visitor);
		} else {
			super.accept(context, visitor);
			visitor.disableAppendToPreviousLine();
		}
		context.setComment(false);
	}

}
