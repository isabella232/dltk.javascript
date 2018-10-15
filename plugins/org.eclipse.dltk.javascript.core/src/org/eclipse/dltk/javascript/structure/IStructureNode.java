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
package org.eclipse.dltk.javascript.structure;

import java.util.List;

import org.eclipse.dltk.compiler.ISourceElementRequestor;

/**
 * Node in the module structure. Used to build intermediate structure, which is
 * then reported to {@link ISourceElementRequestor}.
 */
public interface IStructureNode {

	/**
	 * Returns the start offset of the corresponding AST node(s).
	 */
	int start();

	List<? extends IStructureNode> getChildren();

	IParentNode getParent();

	IScope getScope();

	boolean isManyChildren();

	void reportStructure(IStructureRequestor requestor,
			IStructureContext context);

}
