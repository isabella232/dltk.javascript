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
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.javascript.core.tests.typeinference;

import junit.framework.Assert;

import org.eclipse.dltk.internal.javascript.ti.TypeInferencer2;

public class TestTypeInferencer2 extends TypeInferencer2 {
	@Override
	protected void log(Throwable e) {
		if (e instanceof RuntimeException) {
			throw (RuntimeException) e;
		} else if (e instanceof Error) {
			throw (Error) e;
		} else {
			Assert.fail(e.toString());
		}
	}
}
