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
package org.eclipse.dltk.javascript.core.tests.contentassist;

import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.codeassist.ICompletionEngine;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.core.CompletionRequestor;
import org.eclipse.dltk.core.IScriptProject;

public class CompoundCompletionEngine implements ICompletionEngine {

	private final ICompletionEngine[] engines;
	private CompletionRequestor requestor;

	public CompoundCompletionEngine(ICompletionEngine[] engines) {
		this.engines = engines;
	}

	@Override
	public void complete(IModuleSource module, int position, int i) {
		for (ICompletionEngine engine : engines) {
			engine.complete(module, position, i);
			if (requestor != null
					&& requestor.isIgnored(CompletionRequestor.ALL)) {
				break;
			}
		}
	}

	@Override
	public void setRequestor(CompletionRequestor requestor) {
		this.requestor = requestor;
		for (ICompletionEngine engine : engines) {
			engine.setRequestor(requestor);
		}
	}

	@Override
	public void setOptions(Map options) {
		for (ICompletionEngine engine : engines) {
			engine.setOptions(options);
		}
	}

	@Override
	public void setProject(IScriptProject project) {
		for (ICompletionEngine engine : engines) {
			engine.setProject(project);
		}
	}

	@Override
	public void setProgressMonitor(IProgressMonitor progressMonitor) {
		for (ICompletionEngine engine : engines) {
			engine.setProgressMonitor(progressMonitor);
		}
	}

}
