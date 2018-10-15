/*******************************************************************************
 * Copyright (c) 2011 NumberFour AG
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
package org.eclipse.dltk.internal.javascript.ti;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.dltk.compiler.problem.IProblemCategory;
import org.eclipse.dltk.javascript.typeinference.ReferenceLocation;
import org.eclipse.dltk.javascript.typeinfo.IModelBuilder.IElement;
import org.eclipse.dltk.javascript.typeinfo.model.JSType;

public class JSElement implements IElement {

	private String name;
	private JSType type;
	private ReferenceLocation location = ReferenceLocation.UNKNOWN;

	public JSElement() {
	}

	public JSElement(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JSType getType() {
		return type;
	}

	public void setType(JSType type) {
		this.type = type;
	}

	public ReferenceLocation getLocation() {
		return location;
	}

	public void setLocation(ReferenceLocation location) {
		this.location = location;
	}

	private Set<IProblemCategory> suppressedWarnings = null;

	public Set<IProblemCategory> getSuppressedWarnings() {
		return suppressedWarnings != null ? suppressedWarnings : Collections
				.<IProblemCategory> emptySet();
	}

	public void addSuppressedWarning(IProblemCategory warningCategoryId) {
		if (suppressedWarnings == null) {
			suppressedWarnings = new HashSet<IProblemCategory>();
		}
		suppressedWarnings.add(warningCategoryId);
	}

}
