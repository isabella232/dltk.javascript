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
 *     xored software, Inc. - initial API and Implementation (Vladislav Kuzkokov)
 *******************************************************************************/
package org.eclipse.dltk.javascript.core.manipulation;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.manipulation.IRefactoringEngine;
import org.eclipse.dltk.internal.corext.refactoring.rename.ScriptRenameProcessor;
import org.eclipse.dltk.internal.javascript.corext.refactoring.Checks;
import org.eclipse.dltk.internal.javascript.corext.refactoring.rename.RenameFunctionProcessor;
import org.eclipse.dltk.internal.javascript.corext.refactoring.rename.RenameLocalVariableProcessor;

public class RefactoringEngine implements IRefactoringEngine {

	public boolean isRenameAvailable(IModelElement element)
			throws ModelException {
		return Checks.isAvailable(element);
	}

	public ScriptRenameProcessor createRenameProcessor(IModelElement element) {
		switch (element.getElementType()) {
		case IModelElement.TYPE:
		case IModelElement.METHOD:
			return new RenameFunctionProcessor(element);
		case IModelElement.FIELD:
		case IModelElement.LOCAL_VARIABLE:
			return new RenameLocalVariableProcessor(element);
		}
		return null;
	}
}
