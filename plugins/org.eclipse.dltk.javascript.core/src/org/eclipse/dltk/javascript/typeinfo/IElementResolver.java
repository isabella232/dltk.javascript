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
package org.eclipse.dltk.javascript.typeinfo;

import java.util.Set;

import org.eclipse.dltk.annotations.ConfigurationElement;
import org.eclipse.dltk.javascript.typeinfo.model.Member;

@ConfigurationElement("resolver")
public interface IElementResolver {
	Member resolveElement(ITypeInfoContext context, String name);

	/**
	 * @param context
	 * @param prefix
	 *            the prefix, not <code>null</code>
	 * @return
	 */
	Set<String> listGlobals(ITypeInfoContext context, String prefix);
}
