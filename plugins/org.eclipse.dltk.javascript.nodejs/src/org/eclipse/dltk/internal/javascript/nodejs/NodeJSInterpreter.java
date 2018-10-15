/*******************************************************************************
 * Copyright (c) 2011 NumberFour AG
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
package org.eclipse.dltk.internal.javascript.nodejs;

import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterRunner;

public class NodeJSInterpreter extends AbstractInterpreterInstall {

	public NodeJSInterpreter(IInterpreterInstallType type, String id) {
		super(type, id);
	}

	@Override
	public IInterpreterRunner getInterpreterRunner(String mode) {
		if (ILaunchManager.RUN_MODE.equals(mode)) {
			return new NodeJSInterpreterRunner(this);
		}
		return null;
	}

}
