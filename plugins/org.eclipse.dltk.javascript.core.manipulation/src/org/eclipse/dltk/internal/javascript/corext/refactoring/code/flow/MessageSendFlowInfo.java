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


class MessageSendFlowInfo extends FlowInfo {

	public MessageSendFlowInfo() {
		super(NO_RETURN);
	}

	public void mergeArgument(FlowInfo info, FlowContext context) {
		if (info == null)
			return;
		mergeSequential(info, context);
	}

	public void mergeReceiver(FlowInfo info, FlowContext context) {
		if (info == null)
			return;
		mergeSequential(info, context);
	}

	/*public void mergeExceptions(IMethodBinding binding, FlowContext context) {
		if (binding == null)
			return;
		ITypeBinding[] exceptions= binding.getExceptionTypes();
		if (exceptions == null)
			return;
		for (int i= 0; i < exceptions.length; i++) {
			ITypeBinding exception= exceptions[i];
			if (context.isExceptionCaught(exception))
				addException(exception);
		}
	}*/
}
