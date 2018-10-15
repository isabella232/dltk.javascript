/*******************************************************************************
 * Copyright (c) 2010 xored software, Inc.
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
package org.eclipse.dltk.internal.javascript.parser;

import org.eclipse.dltk.ast.Modifiers;

/**
 * @since 3.0
 */
public class JSModifiers {
	public static final int DEPRECATED = 1 << Modifiers.USER_MODIFIER;

	public static final int PUBLIC = Modifiers.AccPublic;
	public static final int PROTECTED = Modifiers.AccProtected;
	public static final int PRIVATE = Modifiers.AccPrivate;

	public static final int USER_MODIFIER = Modifiers.USER_MODIFIER + 2;
}
