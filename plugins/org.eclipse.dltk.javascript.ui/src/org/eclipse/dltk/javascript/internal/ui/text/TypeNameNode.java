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
package org.eclipse.dltk.javascript.internal.ui.text;

public class TypeNameNode {
	public final String type;
	public final int start;
	public final int end;

	public TypeNameNode(String type, int start, int end) {
		this.type = type;
		this.start = start;
		this.end = end;
	}

	public String value() {
		return type;
	}
}
