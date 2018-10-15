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

import org.eclipse.dltk.javascript.typeinfo.IRProperty;
import org.eclipse.dltk.javascript.typeinfo.IRType;
import org.eclipse.dltk.javascript.typeinfo.IRTypeDeclaration;
import org.eclipse.dltk.javascript.typeinfo.model.Property;

public class RProperty extends RMember<Property> implements IRProperty {

	public RProperty(Property member, IRTypeDeclaration typeDeclaration) {
		super(member, typeDeclaration);
	}

	public RProperty(Property member, IRType type,
			IRTypeDeclaration typeDeclaration) {
		super(member, type, typeDeclaration);
	}

	public boolean isReadOnly() {
		return member.isReadOnly();
	}

	@Override
	public Property getSource() {
		return member;
	}

}
