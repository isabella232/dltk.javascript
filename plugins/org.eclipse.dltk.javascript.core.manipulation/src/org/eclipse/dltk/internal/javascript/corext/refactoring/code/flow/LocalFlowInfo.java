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



class LocalFlowInfo extends FlowInfo {

	private int fVariableId;

	public LocalFlowInfo(VariableBinding binding, int localAccessMode, FlowContext context) {
		super(NO_RETURN);
		fVariableId=binding.getVariableId();
		if (context.considerAccessMode()) {
			createAccessModeArray(context);
			fAccessModes[fVariableId]= localAccessMode;
			context.manageLocal(binding);
		}
	}

	public LocalFlowInfo(LocalFlowInfo info, int localAccessMode, FlowContext context) {
		super(NO_RETURN);
		fVariableId= info.fVariableId;
		if (context.considerAccessMode()) {
			createAccessModeArray(context);
			fAccessModes[fVariableId]= localAccessMode;
		}
	}

	public void setWriteAccess(FlowContext context) {
		if (context.considerAccessMode()) {
			fAccessModes[fVariableId]= FlowInfo.WRITE;
		}
	}
}

