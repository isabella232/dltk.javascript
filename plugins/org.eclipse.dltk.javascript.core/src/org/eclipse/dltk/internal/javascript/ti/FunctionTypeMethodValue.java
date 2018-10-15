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

import org.eclipse.dltk.javascript.typeinference.IAssignProtection;
import org.eclipse.dltk.javascript.typeinference.IValueReference;
import org.eclipse.dltk.javascript.typeinference.ReferenceKind;
import org.eclipse.dltk.javascript.typeinfo.IRFunctionType;
import org.eclipse.dltk.javascript.typeinfo.IRMethod;
import org.eclipse.dltk.javascript.typeinfo.IRType;
import org.eclipse.dltk.javascript.typeinfo.JSTypeSet;
import org.eclipse.dltk.javascript.typeinfo.RTypes;

class FunctionTypeMethodValue extends ElementValue implements IValue {

	private TypeValue functionOperator;
	private final IRFunctionType functionType;
	private final FunctionMethod functionMethod;
	private final IRMethod method;

	public FunctionTypeMethodValue(IRFunctionType functionType,
			FunctionMethod functionMethod) {
		this.functionType = functionType;
		this.functionMethod = functionMethod;
		this.method = functionMethod.create(functionType);
	}

	@Override
	protected IRMethod getElements() {
		return method;
	}

	@Override
	public ReferenceKind getKind() {
		return ReferenceKind.METHOD;
	}

	public IValue getChild(String name, boolean resolve) {
		if (IValueReference.FUNCTION_OP.equals(name)) {
			if (functionType.getReturnType() != null) {
				if (functionOperator == null) {
					functionOperator = new TypeValue(
							functionType.getReturnType());
				}
				return functionOperator;
			} else {
				return null;
			}
		}
		final IValue child = ElementValue.findMemberA(getDeclaredType(), name,
				resolve);
		if (child != null) {
			return child;
		}
		return null;
	}

	public IRType getDeclaredType() {
		return RTypes.FUNCTION;
	}

	public JSTypeSet getDeclaredTypes() {
		return JSTypeSet.singleton(getDeclaredType());
	}

	@Override
	public Object getAttribute(String key, boolean includeReferences) {
		if (IAssignProtection.ATTRIBUTE.equals(key)) {
			return UNASSIGNABLE_METHOD;
		}
		return super.getAttribute(key, includeReferences);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + '<' + functionType + '.'
				+ functionMethod + '>';
	}
}
