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

import org.eclipse.dltk.javascript.typeinference.IValueReference;

public class AnonymousValue extends AbstractReference {

	private final IValue value;

	protected AnonymousValue(IValue value) {
		this.value = value;
	}

	public AnonymousValue() {
		this(new Value());
	}

	public boolean isReference() {
		return false;
	}

	@Override
	public IValue getValue() {
		return value;
	}

	@Override
	public IValue createValue() {
		return getValue();
	}

	public void delete(boolean force) {
	}

	public String getName() {
		return "";
	}

	public IValueReference getParent() {
		return null;
	}

	protected String getToStringPrefix() {
		return "AnonymousValue";
	}

	@Override
	public String toString() {
		return getToStringPrefix() + "@" + System.identityHashCode(value);
	}

}
