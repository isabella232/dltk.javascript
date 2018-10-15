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

import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.formatter.IFormatterContext;
import org.eclipse.dltk.formatter.IFormatterDocument;
import org.eclipse.dltk.formatter.IFormatterNode;
import org.eclipse.dltk.formatter.IFormatterWriter;

public class BracketsNode extends FormatterBlockWithBeginEndNode {

	private IBracketsConfiguration configuration;

	public BracketsNode(IFormatterDocument document,
			IBracketsConfiguration configuration) {
		super(document);

		Assert.isNotNull(configuration);

		this.configuration = configuration;
	}

	public void accept(IFormatterContext context, IFormatterWriter visitor)
			throws Exception {

		if (configuration.isBeginLineBreaking()) {
			visitor.writeLineBreak(context);
		}

		if (configuration.isBracketsIndenting())
			context.incIndent();

		if (getBegin() != null) {
			IFormatterNode[] nodes = getBegin();
			for (int i = 0; i < nodes.length; i++) {
				nodes[i].accept(context, visitor);
			}
		}

		acceptBody(context, visitor);

		if (configuration.isEndLineBreaking()) {
			visitor.writeLineBreak(context);
		}

		if (getEnd() != null) {
			visitor.write(context, getEnd().getStartOffset(), getEnd()
					.getEndOffset());
		}

		if (configuration.isBracketsIndenting())
			context.decIndent();

	}

	protected void acceptNodes(final List<IFormatterNode> nodes,
			IFormatterContext context, IFormatterWriter visitor)
			throws Exception {

		if (isIndenting())
			context.incIndent();

		super.acceptNodes(nodes, context, visitor);

		if (isIndenting())
			context.decIndent();

	}

	@Override
	protected boolean isIndenting() {
		return configuration.isIndenting();
	}

}
