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
package org.eclipse.dltk.javascript.core.tests.typeinfo;

import org.eclipse.dltk.javascript.typeinfo.DefaultMetaType;

public class TestMetaType extends DefaultMetaType {

	public static final TestMetaType INSTANCE = new TestMetaType();

	private TestMetaType() {
	}

	@Override
	public String getId() {
		return TestMetaType.class.getName() + ".INSTANCE";
	}

}
