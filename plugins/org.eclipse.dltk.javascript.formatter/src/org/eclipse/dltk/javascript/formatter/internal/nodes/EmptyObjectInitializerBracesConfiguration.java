/*******************************************************************************
 * Copyright (c) 2011 NumberFour AG
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     NumberFour AG - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.javascript.formatter.internal.nodes;

import org.eclipse.dltk.formatter.IFormatterDocument;
import org.eclipse.dltk.javascript.formatter.JavaScriptFormatterConstants;

public class EmptyObjectInitializerBracesConfiguration extends
		AbstractBracesConfiguration {

	public EmptyObjectInitializerBracesConfiguration(IFormatterDocument document) {
		super(document);
		indentingSettingName = JavaScriptFormatterConstants.BRACE_BLOCK;
	}

	public int insertBeforeOpenBrace() {
		return IBracesConfiguration.EMPTY_SPACE;
	}

	public int insertAfterOpenBrace() {
		return IBracesConfiguration.EMPTY_SPACE;
	}

	public int insertBeforeCloseBrace() {
		return document
				.getBoolean(JavaScriptFormatterConstants.BRACE_EMPTY_OBJECT) ? IBracesConfiguration.EMPTY_SPACE
				: IBracesConfiguration.ONE_SPACE;
	}

	public int insertAfterCloseBrace() {
		return IBracesConfiguration.UNDEFINED;
	}

}
