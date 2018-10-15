/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.dltk.javascript.core.JSKeywordCategory;
import org.eclipse.dltk.javascript.core.JSKeywordManager;
import org.eclipse.dltk.javascript.core.JavaScriptKeywords;
import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.ui.coloring.ColoringPreferences;
import org.eclipse.dltk.ui.coloring.IKeywordColorProvider;
import org.eclipse.dltk.ui.coloring.ITextRuleProvider;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.rules.FloatNumberRule;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

public class JavascriptCodeScanner extends AbstractScriptScanner {

	private static final String fgReturnKeyword = "return";
	private static final String fgTokenProperties[] = new String[] {
			JavascriptColorConstants.JS_DEFAULT,
			JavascriptColorConstants.JS_KEYWORD,
			JavascriptColorConstants.JS_KEYWORD_RETURN,
			JavascriptColorConstants.JS_NUMBER,
			JavascriptColorConstants.JS_FUNCTION_DEFINITION };

	public JavascriptCodeScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		return fgTokenProperties;
	}

	private IKeywordColorProvider[] keywordColorProviders = null;

	private IToken getKeywordColor(String keyword, IToken defaultToken) {
		if (keywordColorProviders == null) {
			keywordColorProviders = ColoringPreferences
					.getKeywordColorProviders(JavaScriptNature.NATURE_ID);
		}
		for (IKeywordColorProvider provider : keywordColorProviders) {
			final String colorKey = provider.getColorKey(
					JSKeywordCategory.CODE, keyword);
			if (colorKey != null) {
				return getToken(colorKey);
			}
		}
		return defaultToken;
	}

	@Override
	protected List<IRule> createRules() {
		List<IRule> rules = new ArrayList<IRule>();
		IToken keyword = getToken(JavascriptColorConstants.JS_KEYWORD);
		IToken keywordReturn = getToken(JavascriptColorConstants.JS_KEYWORD_RETURN);
		IToken other = getToken(JavascriptColorConstants.JS_DEFAULT);
		IToken def = getToken(JavascriptColorConstants.JS_FUNCTION_DEFINITION);
		IToken number = getToken(JavascriptColorConstants.JS_NUMBER);
		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new JavascriptWhitespaceDetector()));
		// Add word rule for keywords, types, and constants.
		WordRule wordRule = new WordRule(new JavascriptWordDetector(), other);
		for (String word : JavaScriptKeywords.getJavaScriptKeywords()) {
			wordRule.addWord(word, getKeywordColor(word, keyword));
		}
		for (String word : JavaScriptKeywords.getHighLightOnlyKeywords()) {
			wordRule.addWord(word, getKeywordColor(word, keyword));
		}
		wordRule.addWord(fgReturnKeyword,
				getKeywordColor(fgReturnKeyword, keywordReturn));
		for (String word : JSKeywordManager.getInstance().getKeywords(
				JSKeywordCategory.CODE, null)) {
			wordRule.addWord(word, getKeywordColor(word, keyword));
		}

		rules.add(wordRule);
		rules.add(new FloatNumberRule(number));

		final ITextRuleProvider[] textRuleProviders = ColoringPreferences
				.getTextRules(JavaScriptNature.NATURE_ID);
		if (textRuleProviders != null) {
			for (ITextRuleProvider provider : textRuleProviders) {
				final IRule[] r = provider.getRules(
						IDocument.DEFAULT_CONTENT_TYPE, this);
				if (r != null) {
					Collections.addAll(rules, r);
				}
			}
		}

		setDefaultReturnToken(other);
		return rules;
	}
}
