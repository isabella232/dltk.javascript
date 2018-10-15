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

public abstract class AbstractPunctuationConfiguration implements
		IPunctuationConfiguration {

	public boolean insertSpaceAfter() {
		return true;
	}

	public boolean insertSpaceBefore() {
		return true;
	}

}
