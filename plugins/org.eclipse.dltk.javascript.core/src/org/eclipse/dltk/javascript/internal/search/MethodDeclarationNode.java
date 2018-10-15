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
import org.eclipse.dltk.core.search.MethodDeclarationMatch;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.matching2.MatchLevel;
import org.eclipse.dltk.javascript.ast.Expression;
import org.eclipse.dltk.javascript.typeinfo.IModelBuilder.IMethod;

public class MethodDeclarationNode extends AbstractMatchingNode<Expression> {

	final IMethod method;

	public MethodDeclarationNode(Expression node, IMethod method) {
		super(node);
		this.method = method;
	}

	public SearchMatch createMatch(IModelElement element,
			SearchParticipant participant, MatchLevel level) {
		return new MethodDeclarationMatch(element,
				level.toSearchMatchAccuracy(), node.sourceStart(), length(),
				participant, element.getResource());
	}

}
