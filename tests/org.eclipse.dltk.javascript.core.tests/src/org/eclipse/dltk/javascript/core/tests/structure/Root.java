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
package org.eclipse.dltk.javascript.core.tests.structure;

public class Root extends Member {

	public Root() {
		super("");
	}

	public Root(Member... children) {
		this();
		for (Member child : children) {
			add(child);
		}
	}

	@Override
	protected String describeMember() {
		return getClass().getSimpleName();
	}

	@Override
	public String toString() {
		return children.toString();
	}

}
