/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.templates;

import org.eclipse.dltk.internal.javascript.typeinference.CompletionString;
import org.eclipse.dltk.javascript.internal.core.codeassist.JavaScriptCompletionUtil;
import org.eclipse.dltk.javascript.internal.core.codeassist.JavaScriptCompletionUtil.ExpressionType;
import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.DocumentUtils;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;

/**
 * JavaScript template completion processor
 */
public class JavaScriptTemplateCompletionProcessor extends
		ScriptTemplateCompletionProcessor {

	private static final char[] IGNORE = new char[] { '.' };

	public JavaScriptTemplateCompletionProcessor(
			ScriptContentAssistInvocationContext context) {
		super(context);
	}

	@Override
	protected String extractPrefix(ITextViewer viewer, int offset) {
		int i = offset;
		IDocument document = viewer.getDocument();
		if (i > document.getLength())
			return "";
		return CompletionString.parse(document.get().substring(0, offset),
				false, true);
	}

	/*
	 * @see ScriptTemplateCompletionProcessor#getContextTypeId()
	 */
	@Override
	protected String getContextTypeId() {
		return JavaScriptUniversalTemplateContextType.CONTEXT_TYPE_ID;
	}

	/*
	 * @see ScriptTemplateCompletionProcessor#getIgnore()
	 */
	@Override
	protected char[] getIgnore() {
		return IGNORE;
	}

	/*
	 * @see ScriptTemplateCompletionProcessor#getTemplateAccess()
	 */
	@Override
	protected ScriptTemplateAccess getTemplateAccess() {
		return JavaScriptTemplateAccess.getInstance();
	}

	@Override
	protected boolean isValidPrefix(String prefix) {
		return true;
	}

	@Override
	protected ICompletionProposal createProposal(Template template,
			TemplateContext context, IRegion region, int relevance) {
		return new JavaScriptTemplateProposal(template, context, region,
				getImage(template), relevance);
	}

	@Override
	protected TemplateContextType getContextType(ITextViewer viewer,
			IRegion region) {
		if (isValidLocation(viewer, region)) {
			final ExpressionType type = JavaScriptCompletionUtil
					.evaluateExpressionType(getContext().getSourceModule(),
							DocumentUtils.asCharSequence(viewer.getDocument()),
							region.getOffset() + region.getLength());
			if (type == ExpressionType.OBJECT_INITIALIZER) {
				return null;
			}
			if (type == ExpressionType.PROPERTY_INITIALIZER_VALUE) {
				return getTemplateAccess()
						.getContextTypeRegistry()
						.getContextType(
								JavaScriptExpressionTemplateContextType.EXPRESSION_CONTEXT_TYPE_ID);
			}
		}
		return super.getContextType(viewer, region);
	}

}
