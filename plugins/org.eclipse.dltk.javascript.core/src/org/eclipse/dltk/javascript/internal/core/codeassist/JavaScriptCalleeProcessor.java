/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.core.codeassist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.compiler.SourceElementRequestorAdaptor;
import org.eclipse.dltk.compiler.env.ModuleSource;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.ICalleeProcessor;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.javascript.core.JavaScriptNature;

public class JavaScriptCalleeProcessor implements ICalleeProcessor {
	protected static int EXACT_RULE = SearchPattern.R_EXACT_MATCH
			| SearchPattern.R_CASE_SENSITIVE;

	private Map fSearchResults = new HashMap();

	private IMethod method;

	// private IDLTKSearchScope scope;

	public JavaScriptCalleeProcessor(IMethod method, IProgressMonitor monitor,
			IDLTKSearchScope scope) {
		this.method = method;
		// this.scope = scope;
	}

	int index;

	private class CaleeSourceElementRequestor extends
			SourceElementRequestorAdaptor {

		@Override
		public void acceptMethodReference(String methodName, int argCount,
				int sourcePosition, int sourceEndPosition) {
			int off = 0;
			try {
				off = method.getSourceRange().getOffset() + index;
			} catch (ModelException e) {
				e.printStackTrace();
			}
			// TODO RMOVE HACK
			SimpleReference ref = new SimpleReference(off + sourcePosition, off
					+ sourceEndPosition + 1, methodName);
			IMethod[] methods = findMethods(methodName, argCount, off
					+ sourcePosition);
			fSearchResults.put(ref, methods);
		}

	}

	public Map doOperation() {
		try {
			String methodSource = method.getSource();
			this.index = methodSource.indexOf('{');
			methodSource = methodSource.substring(index);
			CaleeSourceElementRequestor requestor = new CaleeSourceElementRequestor();
			ISourceElementParser parser = DLTKLanguageManager
					.getSourceElementParser(JavaScriptNature.NATURE_ID);
			parser.setRequestor(requestor);
			parser.parseSourceModule(new ModuleSource(methodSource));
			return fSearchResults;
		} catch (ModelException e) {
			DLTKCore.error("Error parsing methods source: " + method, e);
		}
		return fSearchResults;
	}

	public IMethod[] findMethods(final String methodName, int argCount,
			int sourcePosition) {
		final List methods = new ArrayList();
		ISourceModule module = this.method.getSourceModule();
		try {
			IModelElement[] elements = module.codeSelect(sourcePosition,
					methodName.length());
			for (int i = 0; i < elements.length; ++i) {
				if (elements[i] instanceof IMethod) {
					methods.add(elements[i]);
				}
			}
		} catch (ModelException e) {
			DLTKCore.error("Error finding methods elements: " + method, e);
		}
		// final String nsName;
		// if( methodName.indexOf("::") != -1 ) {
		// String mmName = methodName;
		// if( mmName.startsWith("::")) {
		// mmName = mmName.substring(2);
		// }
		// if( mmName.indexOf("::") != -1 ) {
		// int posb = mmName.indexOf("::");
		// nsName = mmName.substring(0, posb);
		// }
		// else {
		// nsName = null;
		// }
		// }
		// else {
		// nsName = null;
		// }
		// SearchRequestor requestor = new SearchRequestor() {
		// public void acceptSearchMatch(SearchMatch match) throws CoreException
		// {
		// Object element = match.getElement();
		// if( element instanceof IMethod ) {
		// IMethod method = (IMethod)element;
		// String mn = method.getTypeQualifiedName('$', false).replaceAll("\\$",
		// "::");
		// if( mn.equals(methodName) && !isIgnoredBySearchScope(method) ) {
		// methods.add(method);
		// }
		// }
		// else {
		// IType type = (IType) element;
		// if( !( type.getParent() instanceof ISourceModule )) {
		// return;
		// }
		// processTypeFunctions(type);
		// }
		// }
		// private void processTypeFunctions(IType type) throws ModelException {
		// IMethod[] tmethods = type.getMethods();
		// for (int i = 0; i < tmethods.length; ++i) {
		// String mn = tmethods[i].getTypeQualifiedName('$',
		// false).replaceAll("\\$", "::");
		// if( mn.equals(methodName) && !isIgnoredBySearchScope(tmethods[i]) ) {
		// methods.add(tmethods[i]);
		// }
		// }
		// IType[] types = type.getTypes();
		// for( int i = 0; i < types.length; ++i ) {
		// processTypeFunctions(types[i]);
		// }
		// }
		// };
		//		
		// try {
		// String pattern = methodName;
		// if( pattern.startsWith("::")) {
		// pattern = pattern.substring(2);
		// }
		// if( pattern.indexOf("::")==-1) {
		// search(pattern, IDLTKSearchConstants.METHOD,
		// IDLTKSearchConstants.DECLARATIONS, scope, requestor);
		// }
		// if( nsName != null ) {
		// search(nsName, IDLTKSearchConstants.TYPE,
		// IDLTKSearchConstants.DECLARATIONS, scope, requestor);
		// }
		// } catch (CoreException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		return (IMethod[]) methods.toArray(new IMethod[methods.size()]);
	}

	protected void search(String patternString, int searchFor, int limitTo,
			IDLTKSearchScope scope, SearchRequestor resultCollector)
			throws CoreException {
		search(patternString, searchFor, limitTo, EXACT_RULE, scope,
				resultCollector);
	}

	protected void search(String patternString, int searchFor, int limitTo,
			int matchRule, IDLTKSearchScope scope, SearchRequestor requestor)
			throws CoreException {
		if (patternString.indexOf('*') != -1
				|| patternString.indexOf('?') != -1) {
			matchRule |= SearchPattern.R_PATTERN_MATCH;
		}
		SearchPattern pattern = SearchPattern.createPattern(patternString,
				searchFor, limitTo, matchRule, scope.getLanguageToolkit());
		new SearchEngine().search(pattern,
				new SearchParticipant[] { SearchEngine
						.getDefaultSearchParticipant() }, scope, requestor,
				null);
	}
}
