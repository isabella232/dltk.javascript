/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.corext.refactoring.code.flow;

import org.eclipse.dltk.javascript.core.dom.Expression;
import org.eclipse.dltk.javascript.core.dom.ReturnStatement;

class ReturnFlowInfo extends FlowInfo {

	public ReturnFlowInfo(ReturnStatement node) {
		super(getReturnFlag(node));
	}

	public void merge(FlowInfo info, FlowContext context) {
		if (info == null)
			return;

		assignAccessMode(info);
	}

	private static int getReturnFlag(ReturnStatement node) {
		Expression expression= node.getExpression();
		if (expression == null) // || expression.resolveTypeBinding() == node.getAST().resolveWellKnownType("void"))
			return VOID_RETURN;
		return VALUE_RETURN;
	}
}


