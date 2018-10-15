/*******************************************************************************
 * Copyright (c) 2009, 2012 xored software, Inc., NumberFour AG  
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0  
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Vladimir Belov)
 *     NumberFour AG - new option to control space before parens of anonymous function (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.javascript.formatter.internal.nodes;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.formatter.IFormatterContext;
import org.eclipse.dltk.formatter.IFormatterDocument;
import org.eclipse.dltk.formatter.IFormatterNode;
import org.eclipse.dltk.formatter.IFormatterWriter;

public class ParensNode extends FormatterBlockWithBeginEndNode {

	private final IParensConfiguration configuration;
	private final boolean indenting;
	private final boolean insertSpaceBefore;

	public ParensNode(IFormatterDocument document,
			IParensConfiguration configuration) {
		this(document, configuration, false, false);
	}

	public ParensNode(IFormatterDocument document,
			IParensConfiguration configuration, boolean indenting) {
		this(document, configuration, indenting, false);
	}

	public ParensNode(IFormatterDocument document,
			IParensConfiguration configuration, boolean indenting,
			boolean insertSpaceBefore) {
		super(document);
		Assert.isNotNull(configuration);
		this.configuration = configuration;
		this.indenting = indenting;
		this.insertSpaceBefore = insertSpaceBefore;
	}

	@Override
	protected boolean isIndenting() {
		return indenting;
	}

	@Override
	public void accept(IFormatterContext context, IFormatterWriter visitor)
			throws Exception {

		writeSpace(context, visitor, configuration.getSpaceBeforeLeftParen()
				|| insertSpaceBefore);
		if (getBegin() != null) {
			IFormatterNode[] nodes = getBegin();
			for (int i = 0; i < nodes.length; i++) {
				nodes[i].accept(context, visitor);
			}
		}
		writeSpace(context, visitor, configuration.getSpaceAfterLeftParen());

		final boolean indenting = isIndenting();
		if (indenting) {
			context.incIndent();
		}
		acceptBody(context, visitor);
		if (indenting) {
			context.decIndent();
		}

		writeSpace(context, visitor, configuration.getSpaceBeforeRightParen());
		if (getEnd() != null) {
			visitor.write(context, getEnd().getStartOffset(), getEnd()
					.getEndOffset());
		}
	}

	private void writeSpace(IFormatterContext context,
			IFormatterWriter visitor, boolean flag) throws Exception {
		visitor.writeText(context, flag ? JSLiterals.SPACE : JSLiterals.EMPTY);
	}
}
