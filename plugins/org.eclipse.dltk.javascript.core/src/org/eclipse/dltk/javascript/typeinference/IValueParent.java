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
package org.eclipse.dltk.javascript.typeinference;

import java.util.Set;

public interface IValueParent {

	Set<String> getDirectChildren();

	Set<String> getDirectChildren(int flags);

	Set<String> getDeletedChildren();

	boolean hasChild(String name);

	/**
	 * Returns the handle of the child with the specified name. Not null,
	 * however the handle might reference to not existing child.
	 * 
	 * @param name
	 * @return the handle of the child with specified name.
	 */
	IValueReference getChild(String name);

	/**
	 * Creates the local child with the specified name.
	 * 
	 * @param name
	 * @return
	 */
	IValueReference createChild(String name);

}
