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
import org.eclipse.dltk.javascript.formatter.JavaScriptFormatterConstants;

public class CatchParensConfiguration extends AbstractParensConfiguration {

	public CatchParensConfiguration(IFormatterDocument document) {
		super(document);

		spaceBeforeLpName = JavaScriptFormatterConstants.INSERT_SPACE_BEFORE_LP_CATCH;
		spaceAfterLpName = JavaScriptFormatterConstants.INSERT_SPACE_AFTER_LP_CATCH;
		spaceBeforeRpName = JavaScriptFormatterConstants.INSERT_SPACE_BEFORE_RP_CATCH;
	}

}
