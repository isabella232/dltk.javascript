/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterContainerWizardPage;
import org.eclipse.dltk.javascript.core.JavaScriptNature;

public class JavaScriptInterpreterContainerWizardPage extends
		AbstractInterpreterContainerWizardPage {

	public String getScriptNature() {
		return JavaScriptNature.NATURE_ID;
	}
}
