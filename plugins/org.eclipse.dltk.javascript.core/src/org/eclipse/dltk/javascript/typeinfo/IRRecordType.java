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
package org.eclipse.dltk.javascript.typeinfo;

import java.util.Collection;

import org.eclipse.dltk.javascript.typeinfo.model.Member;
import org.eclipse.emf.common.util.EList;

public interface IRRecordType extends IRType {

	IRRecordMember getMember(String name);

	Collection<IRRecordMember> getMembers();

	void init(ITypeSystem context, EList<Member> members);

}
