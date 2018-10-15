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
package org.eclipse.dltk.internal.javascript.validation;

import org.eclipse.dltk.internal.javascript.ti.IReferenceAttributes;
import org.eclipse.dltk.javascript.typeinference.IValueReference;
import org.eclipse.dltk.javascript.typeinfo.IRMember;
import org.eclipse.dltk.javascript.validation.IMemberValidationEvent;

public class MemberValidationEvent implements IMemberValidationEvent {

	private IValueReference reference;
	private IRMember member;
	private boolean memberEvaluated;

	public IValueReference getReference() {
		return reference;
	}

	public IRMember getRMember() {
		if (!memberEvaluated) {
			member = (IRMember) reference
					.getAttribute(IReferenceAttributes.R_METHOD);
			if (member == null) {
				member = (IRMember) reference
						.getAttribute(IReferenceAttributes.R_VARIABLE);
			}
			memberEvaluated = true;
		}
		return member;
	}

	public void set(IValueReference reference, IRMember member) {
		this.reference = reference;
		this.member = member;
		this.memberEvaluated = member != null;
	}

}
