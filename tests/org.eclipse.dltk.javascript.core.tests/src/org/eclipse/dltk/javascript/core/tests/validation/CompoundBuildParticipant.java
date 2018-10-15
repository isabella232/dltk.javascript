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
package org.eclipse.dltk.javascript.core.tests.validation;

import java.util.Arrays;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;

public class CompoundBuildParticipant implements IBuildParticipant {

	private final IBuildParticipant[] participants;

	public CompoundBuildParticipant(IBuildParticipant... participants) {
		this.participants = Arrays.copyOf(participants, participants.length);
	}

	public void build(IBuildContext context) throws CoreException {
		for (IBuildParticipant participant : participants) {
			participant.build(context);
		}
	}

}
