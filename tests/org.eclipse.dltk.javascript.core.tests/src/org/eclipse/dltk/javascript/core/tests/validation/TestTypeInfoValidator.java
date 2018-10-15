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

import org.eclipse.dltk.internal.javascript.ti.TypeInferencer2;
import org.eclipse.dltk.internal.javascript.validation.TypeInfoValidator;
import org.eclipse.dltk.javascript.core.tests.typeinference.TestTypeInferencer2;

@SuppressWarnings("restriction")
class TestTypeInfoValidator extends TypeInfoValidator {
	@Override
	protected TypeInferencer2 createTypeInferencer() {
		return new TestTypeInferencer2();
	}
}
