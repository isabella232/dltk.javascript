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

package org.eclipse.dltk.internal.javascript.validation;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.IBuildParticipantFactory;
import org.eclipse.dltk.javascript.ast.FunctionStatement;

public class FlowValidationFactory implements IBuildParticipantFactory {

	public IBuildParticipant createBuildParticipant(IScriptProject project)
			throws CoreException {
		return new FlowValidation() {

			private Set<FunctionStatement> inconsistentReturns;

			@Override
			public void build(IBuildContext context) throws CoreException {
				super.build(context);
				if (inconsistentReturns != null) {
					context.set(
							JavaScriptValidations.ATTR_INCONSISTENT_RETURNS,
							inconsistentReturns);
					inconsistentReturns = null;
				}
			}

			@Override
			protected void reportInconsistentReturn(FunctionStatement node) {
				if (inconsistentReturns == null) {
					inconsistentReturns = new HashSet<FunctionStatement>();
				}
				inconsistentReturns.add(node);
			}
		};
	}

}
