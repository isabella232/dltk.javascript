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
package org.eclipse.dltk.internal.javascript.ti;

public interface IReferenceAttributes {

	String METHOD = "METHOD"; //$NON-NLS-1$
	String R_METHOD = "R_METHOD"; //$NON-NLS-1$
	String LOCATION = "LOCATION"; //$NON-NLS-1$
	String FUNCTION_SCOPE = "FUNCTION_SCOPE"; //$NON-NLS-1$
	String ELEMENT = "ELEMENT"; //$NON-NLS-1$
	String VARIABLE = "VARIABLE"; //$NON-NLS-1$s
	String R_VARIABLE = "R_VARIABLE"; //$NON-NLS-1$s
	String HIDE_ALLOWED = "HIDE_ALLOWED";

	String RESOLVING = "RESOLVING";
	String PHANTOM = "PHANTOM";
	String ACCESS = IReferenceAttributes.class.getName() + "#ACCESS";
	String LOCAL_TYPE_LOCATION = "LOCAL_TYPE_LOCATION";
}
