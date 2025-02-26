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

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.javascript.ast.Keywords;
import org.eclipse.dltk.javascript.typeinference.ReferenceKind;
import org.eclipse.dltk.javascript.typeinfo.IRType;

/**
 * Value representing "this" in function bodies.
 */
public class ThisValue extends AnonymousReferenceValue {
	@Override
	protected String getToStringPrefix() {
		final IRType declaredType = getDeclaredType();
		return Keywords.THIS
				+ (declaredType != null ? "(" + declaredType + ")" : "");
	}

	@Override
	public ReferenceKind getKind() {
		return ReferenceKind.THIS;
	}

	@Override
	public void setKind(ReferenceKind kind) {
		Assert.isLegal(kind == ReferenceKind.THIS);
	}
}
