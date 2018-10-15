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
package org.eclipse.dltk.javascript.internal.core;

import java.util.concurrent.Callable;

import org.eclipse.dltk.internal.javascript.ti.TypeInferencer2;
import org.eclipse.dltk.javascript.typeinfo.ITypeSystem;
import org.eclipse.dltk.javascript.typeinfo.ITypeSystem.ThreadTypeSystem;
import org.eclipse.dltk.javascript.typeinfo.ReferenceSource;

public class ThreadTypeSystemImpl extends ThreadLocal<ITypeSystem> implements
		ThreadTypeSystem {

	public ReferenceSource getCurrentSource() {
		final ITypeSystem ts = get();
		return ts instanceof TypeInferencer2 ? ((TypeInferencer2) ts)
				.getSource() : null;
	}

	public void runWith(ITypeSystem typeSystem, Runnable runnable) {
		final ITypeSystem saved = get();
		try {
			set(typeSystem);
			runnable.run();
		} finally {
			set(saved);
		}
	}

	public <V> V runWith(ITypeSystem typeSystem, Callable<V> callable)
			throws Exception {
		final ITypeSystem saved = get();
		try {
			set(typeSystem);
			return callable.call();
		} finally {
			set(saved);
		}
	}

}
