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



class ConditionalFlowInfo extends FlowInfo {

	public ConditionalFlowInfo() {
		super(NO_RETURN);
	}

	public void mergeCondition(FlowInfo info, FlowContext context) {
		if (info == null)
			return;
		mergeAccessModeSequential(info, context);
	}

	public void merge(FlowInfo truePart, FlowInfo falsePart, FlowContext context) {
		if (truePart == null && falsePart == null)
			return;

		GenericConditionalFlowInfo cond= new GenericConditionalFlowInfo();
		if (truePart != null)
			cond.mergeAccessMode(truePart, context);

		if (falsePart != null)
			cond.mergeAccessMode(falsePart, context);

		if (truePart == null || falsePart == null)
			cond.mergeEmptyCondition(context);

		mergeAccessModeSequential(cond, context);
	}
}


