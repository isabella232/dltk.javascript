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

import org.eclipse.dltk.formatter.FormatterTextNodeWrapper;
import org.eclipse.dltk.formatter.IFormatterContext;
import org.eclipse.dltk.formatter.IFormatterTextNode;
import org.eclipse.dltk.formatter.IFormatterWriter;

public class OperationOrPunctuationNode extends FormatterTextNodeWrapper {

	private final IPunctuationConfiguration configuration;

	public OperationOrPunctuationNode(IFormatterTextNode node,
			IPunctuationConfiguration configuration) {
		super(node);
		this.configuration = configuration;
	}

	@Override
	public void accept(IFormatterContext context, IFormatterWriter visitor)
			throws Exception {

		if (configuration.insertSpaceBefore())
			visitor.writeText(context, JSLiterals.SPACE);

		super.accept(context, visitor);

		if (configuration.insertSpaceAfter())
			visitor.writeText(context, JSLiterals.SPACE);
	}

}
