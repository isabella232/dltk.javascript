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
package org.eclipse.dltk.javascript.typeinference;

public interface IFunctionValueCollection extends IValueCollection {
	/**
	 * Tests if this object represents code like the following:
	 * 
	 * <pre>
	 * (function() { .... })();
	 * </pre>
	 */
	boolean isInlineBlock();

	/**
	 * The name of the function
	 * 
	 * @return String the name of the function
	 */
	String getFunctionName();

}
