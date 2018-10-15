/*******************************************************************************
 * Copyright (c) 2012 NumberFour AG
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
package org.eclipse.dltk.internal.javascript.ti;

import java.util.Set;

import org.eclipse.dltk.javascript.typeinference.IValueReference;

/**
 * Value representing script global level "this" (which is window in browser
 * environment).
 */
public class TopValueThis extends ThisValue {

	private final TopValueCollection owner;

	public TopValueThis(TopValueCollection collection) {
		this.owner = collection;
	}

	@Override
	public Set<String> getDirectChildren(int flags) {
		return owner.getDirectChildren(flags);
	}

	@Override
	public Set<String> getDeletedChildren() {
		return owner.getDeletedChildren();
	}

	@Override
	public boolean hasChild(String name) {
		return owner.hasChild(name);
	}

	@Override
	public IValueReference getChild(String name) {
		return owner.getChild(name);
	}

}
