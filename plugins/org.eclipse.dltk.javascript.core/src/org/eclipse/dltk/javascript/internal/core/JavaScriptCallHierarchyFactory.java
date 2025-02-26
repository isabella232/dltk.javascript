/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.core;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.ICallHierarchyFactory;
import org.eclipse.dltk.core.ICallProcessor;
import org.eclipse.dltk.core.ICalleeProcessor;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.javascript.internal.core.codeassist.JavaScriptCallProcessor;
import org.eclipse.dltk.javascript.internal.core.codeassist.JavaScriptCalleeProcessor;

public class JavaScriptCallHierarchyFactory implements ICallHierarchyFactory {
	public ICalleeProcessor createCalleeProcessor(IMethod method,
			IProgressMonitor monitor, IDLTKSearchScope scope) {
		return new JavaScriptCalleeProcessor(method,monitor,scope);
	}

	public ICallProcessor createCallProcessor() {
		return new JavaScriptCallProcessor();
	}

}
