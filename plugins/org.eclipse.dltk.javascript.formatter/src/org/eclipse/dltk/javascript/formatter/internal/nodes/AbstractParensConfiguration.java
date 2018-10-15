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

public abstract class AbstractParensConfiguration implements
		IParensConfiguration {

	private IFormatterDocument document;

	protected String spaceBeforeLpName;
	protected String spaceAfterLpName;
	protected String spaceBeforeRpName;

	public AbstractParensConfiguration(IFormatterDocument document) {
		this.document = document;
	}

	public boolean getSpaceBeforeLeftParen() {
		if (spaceBeforeLpName == null || spaceBeforeLpName.length() == 0)
			return true;

		return document.getBoolean(spaceBeforeLpName);
	}

	public boolean getSpaceAfterLeftParen() {
		if (spaceAfterLpName == null || spaceAfterLpName.length() == 0)
			return false;

		return document.getBoolean(spaceAfterLpName);
	}

	public boolean getSpaceBeforeRightParen() {
		if (spaceBeforeRpName == null || spaceBeforeRpName.length() == 0)
			return false;

		return document.getBoolean(spaceBeforeRpName);
	}

}
