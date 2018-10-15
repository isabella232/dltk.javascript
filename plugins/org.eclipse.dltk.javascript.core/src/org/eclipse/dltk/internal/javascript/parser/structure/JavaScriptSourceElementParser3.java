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
package org.eclipse.dltk.internal.javascript.parser.structure;

import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.javascript.ast.Script;
import org.eclipse.dltk.javascript.parser.JavaScriptParserUtil;
import org.eclipse.dltk.javascript.structure.IStructureNode;
import org.eclipse.dltk.javascript.typeinfo.ReferenceSource;

public class JavaScriptSourceElementParser3 implements ISourceElementParser {

	protected ISourceElementRequestor fRequestor = null;
	protected IProblemReporter fReporter = null;

	public void setRequestor(ISourceElementRequestor requestor) {
		this.fRequestor = requestor;
	}

	public void setReporter(IProblemReporter reporter) {
		this.fReporter = reporter;
	}

	public void parseSourceModule(IModuleSource module) {
		final Script script = parse(module);
		final StructureReporter3 reporter = new StructureReporter3(
				ReferenceSource.create(module.getModelElement()));
		final IStructureNode node = reporter.visit(script);
		reportStructure(script, node);
	}

	protected void reportStructure(final Script script,
			final IStructureNode node) {
		fRequestor.enterModule();
		node.reportStructure(new StructureRequestor(fRequestor),
				new StructureContext());
		fRequestor.exitModule(script.sourceEnd());
	}

	protected Script parse(IModuleSource module) {
		return JavaScriptParserUtil.parse(module, fReporter);
	}

}
