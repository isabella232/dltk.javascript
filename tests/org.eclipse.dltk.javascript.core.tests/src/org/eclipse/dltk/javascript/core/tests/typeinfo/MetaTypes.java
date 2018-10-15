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

public abstract class MetaTypes extends DefaultMetaType {

	public static final MetaTypes ONE = new MetaTypes() {
		@Override
		public String getId() {
			return MetaTypes.class.getName() + ".ONE";
		}
	};

	public static final MetaTypes TWO = new MetaTypes() {
		@Override
		public String getId() {
			return MetaTypes.class.getName() + ".TWO";
		}
	};

}
