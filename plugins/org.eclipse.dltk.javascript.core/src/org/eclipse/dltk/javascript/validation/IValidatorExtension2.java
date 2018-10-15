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
package org.eclipse.dltk.javascript.validation;

import org.eclipse.dltk.compiler.problem.IValidationStatus;
import org.eclipse.dltk.javascript.typeinfo.model.JSType;
import org.eclipse.dltk.javascript.typeinfo.model.Type;

public interface IValidatorExtension2 extends IValidatorExtension {

	/**
	 * Validates accessibility of the specified type.
	 * 
	 * @param type
	 * @return
	 */
	IValidationStatus validateAccessibility(Type type);

	/**
	 * Validates the semantics/accessibility of the specified type expression.
	 * 
	 * @param type
	 * @return
	 */
	IValidationStatus validateTypeExpression(JSType type);

}
