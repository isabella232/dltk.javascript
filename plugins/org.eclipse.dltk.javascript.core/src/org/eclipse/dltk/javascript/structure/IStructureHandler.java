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

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.internal.javascript.parser.structure.StructureReporter3;
import org.eclipse.dltk.javascript.typeinfo.ITypeInferenceExtensionFactory;

/**
 * Handler which can be used as plugin for {@link StructureReporter3}, it is
 * created by {@link ITypeInferenceExtensionFactory}.
 */
public interface IStructureHandler {

	/**
	 * The special value to return if next handler should be called
	 */
	public static final IStructureNode CONTINUE = new StructureNode(null) {
		public void reportStructure(IStructureRequestor requestor,
				IStructureContext context) {
		}

		public int start() {
			return -1;
		}
	};

	IStructureNode handle(ASTNode node);

}
