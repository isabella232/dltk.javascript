/*******************************************************************************
 * Copyright (c) 2006, 2008 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.javascript.core.refactoring.descriptors;

import java.util.Map;

import org.eclipse.dltk.core.manipulation.IScriptRefactorings;
import org.eclipse.dltk.internal.corext.refactoring.ScriptRefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringContribution;
import org.eclipse.ltk.core.refactoring.RefactoringCore;

/**
 * Refactoring descriptor for the extract local variable refactoring.
 * <p>
 * An instance of this refactoring descriptor may be obtained by calling
 * {@link RefactoringContribution#createDescriptor()} on a refactoring
 * contribution requested by invoking
 * {@link RefactoringCore#getRefactoringContribution(String)} with the
 * appropriate refactoring id.
 * </p>
 * <p>
 * Note: this class is not intended to be instantiated by clients.
 * </p>
 *
 * @since 1.1
 *
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public final class ExtractLocalDescriptor extends ScriptRefactoringDescriptor {

	/**
	 * Creates a new refactoring descriptor.
	 */
	public ExtractLocalDescriptor() {
		super(IScriptRefactorings.EXTRACT_LOCAL_VARIABLE);
	}

	/**
	 * Creates a new refactoring descriptor.
	 *
	 * @param project
	 *            the non-empty name of the project associated with this
	 *            refactoring, or <code>null</code> for a workspace
	 *            refactoring
	 * @param description
	 *            a non-empty human-readable description of the particular
	 *            refactoring instance
	 * @param comment
	 *            the human-readable comment of the particular refactoring
	 *            instance, or <code>null</code> for no comment
	 * @param arguments
	 * 			  a map of arguments that will be persisted and describes
	 * 			  all settings for this refactoring
	 * @param flags
	 *            the flags of the refactoring descriptor
	 *
	 * @since 1.2
	 */
	public ExtractLocalDescriptor(String project, String description, String comment, Map arguments, int flags) {
		super(IScriptRefactorings.EXTRACT_LOCAL_VARIABLE, project, description, comment, arguments, flags);
	}

}