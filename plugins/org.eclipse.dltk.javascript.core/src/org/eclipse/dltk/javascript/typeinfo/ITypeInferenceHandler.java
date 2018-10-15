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
package org.eclipse.dltk.javascript.typeinfo;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.internal.javascript.ti.AbstractReference;
import org.eclipse.dltk.internal.javascript.ti.IValue;
import org.eclipse.dltk.javascript.typeinference.IValueReference;

public interface ITypeInferenceHandler {

	/**
	 * The special value to return if next handler should be called
	 */
	public static final IValueReference CONTINUE = new AbstractReference() {

		public boolean isReference() {
			return false;
		}

		public IValueReference getParent() {
			return null;
		}

		public String getName() {
			return "CONTINUE";
		}

		public void delete(boolean force) {
		}

		@Override
		public IValue getValue() {
			return null;
		}

		@Override
		public IValue createValue() {
			return null;
		}

		@Override
		public String toString() {
			return ITypeInferenceHandler.class.getSimpleName() + ".CONTINUE";
		}
	};

	IValueReference handle(ASTNode node);

}
