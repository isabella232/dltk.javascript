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
package org.eclipse.dltk.javascript.typeinference;

public enum ReferenceKind {
	UNKNOWN, METHOD, PROPERTY, THIS, ARGUMENT, LOCAL, GLOBAL, FIELD, FUNCTION, TYPE, PREDEFINED;

	public boolean isLocal() {
		return this == LOCAL || this == ARGUMENT;
	}

	public boolean isVariable() {
		return this == LOCAL || this == GLOBAL;
	}
}
