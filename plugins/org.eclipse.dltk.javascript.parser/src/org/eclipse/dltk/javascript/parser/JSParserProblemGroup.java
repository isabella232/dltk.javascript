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
package org.eclipse.dltk.javascript.parser;

import org.eclipse.dltk.compiler.problem.IProblemIdentifier;
import org.eclipse.dltk.compiler.problem.IProblemIdentifierFactory;

public enum JSParserProblemGroup implements IProblemIdentifier {

	DUPLICATE_DECLARATION, DUPLICATE_DECLARATION_OTHER_KIND,
	DECLARATION_HIDES_OTHER;

	public String contributor() {
		return JavaScriptParserPlugin.PLUGIN_ID;
	}

	public static class Resolver implements IProblemIdentifierFactory {

		public IProblemIdentifier valueOf(String localName)
				throws IllegalArgumentException {
			return JSParserProblemGroup.valueOf(localName);
		}

		public IProblemIdentifier[] values() {
			return JSParserProblemGroup.values();
		}

	}

}
