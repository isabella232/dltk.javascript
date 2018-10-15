/*******************************************************************************
 * Copyright (c) 2011 xored software, Inc.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.javascript.core;

import org.eclipse.dltk.core.keyword.IKeywordCategory;

/**
 * JavaScript keyword categories.
 * 
 * @since 3.0
 */
public enum JSKeywordCategory implements IKeywordCategory {
	/**
	 * code keyword category
	 */
	CODE,

	/**
	 * JSDoc tag category
	 */
	JS_DOC_TAG
}
