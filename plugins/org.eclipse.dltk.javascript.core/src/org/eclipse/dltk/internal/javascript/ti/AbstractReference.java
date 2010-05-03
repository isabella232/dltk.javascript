/*******************************************************************************
 * Copyright (c) 2010 xored software, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.ti;

import java.util.Collections;
import java.util.Set;

import org.eclipse.dltk.javascript.typeinfo.model.Type;

public abstract class AbstractReference implements IValueReference,
		IValueProvider {

	public abstract IValue getValue();

	public abstract IValue createValue();

	public void addValue(IValueReference value) {
		if (value == null) {
			return;
		}
		IValue val = createValue();
		if (val != null) {
			IValue src = ((IValueProvider) value).getValue();
			if (src == null)
				return;
			val.addValue(src);
		}
	}

	public void clear() {
		IValue value = getValue();
		if (value != null) {
			value.clear();
		}
	}

	public boolean exists() {
		return getValue() != null;
	}

	public Object getAttribute(String key) {
		IValue value = getValue();
		return value != null ? value.getAttribute(key) : null;
	}

	public Type getDeclaredType() {
		IValue value = getValue();
		return value != null ? value.getDeclaredType() : null;
	}

	public ReferenceKind getKind() {
		IValue value = getValue();
		return value != null ? value.getKind() : ReferenceKind.UNKNOWN;
	}

	public ReferenceLocation getLocation() {
		IValue value = getValue();
		return value != null ? value.getLocation() : ReferenceLocation.UNKNOWN;
	}

	public Set<Type> getTypes() {
		IValue value = getValue();
		return value != null ? value.getTypes() : Collections.<Type> emptySet();
	}

	public void setAttribute(String key, Object value) {
		IValue val = createValue();
		if (val != null) {
			val.setAttribute(key, value);
		}
	}

	public void setDeclaredType(Type type) {
		IValue value = createValue();
		if (value != null) {
			value.setDeclaredType(type);
		}
	}

	public void setKind(ReferenceKind kind) {
		IValue value = createValue();
		if (value != null) {
			value.setKind(kind);
		}
	}

	public void setLocation(ReferenceLocation location) {
		IValue value = createValue();
		if (value != null) {
			value.setLocation(location);
		}
	}

	public IValueReference getChild(String name) {
		return new ChildReference(this, name);
	}

	public boolean hasChild(String name) {
		IValue value = getValue();
		return value != null && value.getChild(name) != null;
	}

	public Set<String> getDirectChildren() {
		final IValue value = getValue();
		return value != null ? value.getDirectChildren() : Collections
				.<String> emptySet();
	}

}
