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

import java.util.Set;

import org.eclipse.dltk.javascript.typeinference.IValueCollection;
import org.eclipse.dltk.javascript.typeinference.IValueReference;

public class WithValueCollection implements IValueCollection {

	private final IValueCollection parent;

	private final IValueReference with;

	public WithValueCollection(IValueCollection parent, IValueReference with) {
		this.parent = parent;
		this.with = with;
	}

	public boolean isScope() {
		return false;
	}

	public IValueReference getReturnValue() {
		return parent.getReturnValue();
	}

	public IValueReference getThis() {
		return parent.getThis();
	}

	public Set<String> getDirectChildren() {
		return with.getDirectChildren();
	}

	public Set<String> getDirectChildren(int flags) {
		return with.getDirectChildren(flags);
	}

	public Set<String> getDeletedChildren() {
		return with.getDeletedChildren();
	}

	public boolean hasChild(String name) {
		return with.hasChild(name);
	}

	public IValueReference getChild(String name) {
		return with.getChild(name);
	}

	public IValueReference createChild(String name) {
		// TODO Auto-generated method stub
		return parent.createChild(name);
	}

	public IValueCollection getParent() {
		return parent;
	}

}
