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
import org.eclipse.dltk.javascript.ast.ReturnStatement;
import org.eclipse.dltk.javascript.formatter.JavaScriptFormatterConstants;

public class MultiLineObjectInitializerBracesConfiguration extends
		AbstractBracesConfiguration {

	private final JSNode node;

	public MultiLineObjectInitializerBracesConfiguration(
			IFormatterDocument document, JSNode node) {
		super(document);
		this.node = node;

		indentingSettingName = JavaScriptFormatterConstants.INDENT_BLOCK;
		bracesSettingName = JavaScriptFormatterConstants.BRACE_BLOCK;
	}

	@Override
	public int insertBeforeOpenBrace() {
		return IBracesConfiguration.EMPTY_SPACE;
	}

}
