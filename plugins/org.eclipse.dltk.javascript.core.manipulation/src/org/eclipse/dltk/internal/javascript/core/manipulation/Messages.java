package org.eclipse.dltk.internal.javascript.core.manipulation;
/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/


import com.ibm.icu.text.MessageFormat;

/**
 * Helper class to format message strings.
 * 
	 *
 */
public class Messages {

	public static String format(String message, Object object) {
		return MessageFormat.format(message, new Object[] { object});
	}

	public static String format(String message, Object[] objects) {
		return MessageFormat.format(message, objects);
	}

	private Messages() {
		// Not for instantiation
	}
}
