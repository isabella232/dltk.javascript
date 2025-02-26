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
package org.eclipse.dltk.javascript.internal.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.internal.javascript.ti.ITypeInferenceContext;
import org.eclipse.dltk.internal.javascript.ti.TypeInferencerVisitor;
import org.eclipse.dltk.javascript.typeinference.IValueReference;

public class CollectingVisitor extends TypeInferencerVisitor {
	public final Map<ASTNode, IValueReference> bindings = new HashMap<ASTNode, IValueReference>();

	public CollectingVisitor(ITypeInferenceContext context) {
		super(context);
	}

	@Override
	public IValueReference visit(ASTNode node) {
		final IValueReference reference = super.visit(node);
		if (reference != null && node != null) {
			bindings.put(node, reference);
		}
		return reference;
	}
}
