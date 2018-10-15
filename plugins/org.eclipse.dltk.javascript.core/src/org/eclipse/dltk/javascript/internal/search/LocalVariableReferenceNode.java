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
package org.eclipse.dltk.javascript.internal.search;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.search.LocalVariableReferenceMatch;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.matching2.MatchLevel;
import org.eclipse.dltk.javascript.ast.Identifier;
import org.eclipse.dltk.javascript.typeinference.ReferenceLocation;

public class LocalVariableReferenceNode extends
		AbstractMatchingNode<Identifier> {

	final ReferenceLocation declarationLoc;
	final boolean isDoc;

	public LocalVariableReferenceNode(Identifier node,
			ReferenceLocation declarationLoc) {
		this(node, declarationLoc, false);
	}

	public LocalVariableReferenceNode(Identifier node,
			ReferenceLocation declarationLoc, boolean isDoc) {
		super(node);
		this.declarationLoc = declarationLoc;
		this.isDoc = isDoc;
	}

	public SearchMatch createMatch(IModelElement element,
			SearchParticipant participant, MatchLevel level) {
		return new LocalVariableReferenceMatch(element,
				level.toSearchMatchAccuracy(), node.sourceStart(), length(),
				true, true, isDoc, participant, element.getResource());
	}

}
