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

package org.eclipse.dltk.javascript.formatter.internal;

import org.eclipse.dltk.formatter.FormatterWriter;
import org.eclipse.dltk.formatter.IFormatterContext;
import org.eclipse.dltk.formatter.IFormatterDocument;
import org.eclipse.dltk.formatter.IFormatterIndentGenerator;

public class JavaScriptFormatterWriter extends FormatterWriter {

	public JavaScriptFormatterWriter(IFormatterDocument document,
			String lineDelimiter, IFormatterIndentGenerator indentGenerator) {
		super(document, lineDelimiter, indentGenerator);
	}

	@Override
	protected void writeIndent(IFormatterContext context, StringBuilder buffer) {
		super.writeIndent(context, buffer);

		JavaScriptFormatterContext jscontext = (JavaScriptFormatterContext) context;

		if (jscontext.getAdditionalIndent() != null) {
			buffer.append(jscontext.getAdditionalIndent());
		}
	}

}
