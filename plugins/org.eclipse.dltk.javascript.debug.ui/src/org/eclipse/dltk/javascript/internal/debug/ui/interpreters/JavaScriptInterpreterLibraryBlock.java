/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
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

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterLibraryBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.LibraryLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;

/**
 * Control used to edit the libraries associated with a Interpreter install
 */
public class JavaScriptInterpreterLibraryBlock extends
		AbstractInterpreterLibraryBlock {

	public JavaScriptInterpreterLibraryBlock(AddScriptInterpreterDialog d) {
		super(d);
	}

	protected IBaseLabelProvider getLabelProvider() {
		return new LibraryLabelProvider();
	}
}
