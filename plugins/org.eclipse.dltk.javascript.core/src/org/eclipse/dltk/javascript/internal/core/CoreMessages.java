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

import org.eclipse.osgi.util.NLS;

public class CoreMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.javascript.internal.core.CoreMessages"; //$NON-NLS-1$
	public static String JSBindings_currentTypeSystem;
	public static String JSBindings_not_available;
	public static String JSBindings_precomputedBindings;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, CoreMessages.class);
	}

	private CoreMessages() {
	}
}
