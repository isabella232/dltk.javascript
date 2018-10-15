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
package org.eclipse.dltk.javascript.core.tests.search;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.tests.ProjectSetup;
import org.eclipse.dltk.core.tests.model.TestSearchResults;
import org.eclipse.dltk.javascript.core.tests.AllTests;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class SearchDeclarationTests extends Assert implements
		IDLTKSearchConstants {

	@ClassRule
	public static final ProjectSetup PROJECT = new ProjectSetup(
			AllTests.WORKSPACE, "search",
			ProjectSetup.Option.WAIT_INDEXES_READY);

	@Test
	public void testFunctionHello1() throws CoreException {
		final TestSearchResults results = PROJECT.search("hello", METHOD,
				DECLARATIONS);
		assertEquals(1, results.size());
		final IModelElement method = results.locate(IMethod.class, "hello");
		final IModelElement parent = method.getParent();
		assertEquals(IModelElement.SOURCE_MODULE, parent.getElementType());
		assertEquals("hello.js", parent.getElementName());
	}

	@Test
	public void testFunctionHello2() throws CoreException {
		final TestSearchResults results = PROJECT.search("hello2", METHOD,
				DECLARATIONS);
		assertEquals(1, results.size());
		final IModelElement method = results.locate(IMethod.class, "hello2");
		final IModelElement parent = method.getParent();
		assertEquals(IModelElement.SOURCE_MODULE, parent.getElementType());
		assertEquals("hello2.js", parent.getElementName());
	}

	@Test
	public void testVarA() throws CoreException {
		final TestSearchResults results = PROJECT.search("a", FIELD,
				DECLARATIONS);
		assertEquals(1, results.size());
		final IModelElement method = results.locate(IField.class, "a");
		final IModelElement parent = method.getParent();
		assertEquals(IModelElement.SOURCE_MODULE, parent.getElementType());
		assertEquals("a.js", parent.getElementName());
	}
	

	@Test
	public void testVartextFormatters() throws CoreException {
		final TestSearchResults results = PROJECT.search("textFormatters", FIELD,
				DECLARATIONS);
		assertEquals(1, results.size());
		final IModelElement method = results.locate(IField.class, "textFormatters");
		final IModelElement parent = method.getParent();
		assertEquals(IModelElement.SOURCE_MODULE, parent.getElementType());
		assertEquals("selection2.js", parent.getElementName());
	}


	@Test
	public void testVarB() throws CoreException {
		final TestSearchResults results = PROJECT.search("b", FIELD,
				DECLARATIONS);
		assertEquals(1, results.size());
		final IModelElement method = results.locate(IField.class, "b");
		final IModelElement parent = method.getParent();
		assertEquals(IModelElement.SOURCE_MODULE, parent.getElementType());
		assertEquals("b.js", parent.getElementName());
	}

	
	@Test
	public void testThisFunctionTest() throws CoreException {
		final TestSearchResults results = PROJECT.search("myfunction", METHOD,
				DECLARATIONS);
		assertEquals(1, results.size());
		final IModelElement element = results.get(0);
		final IModelElement parent = element
				.getAncestor(IModelElement.SOURCE_MODULE);
		assertEquals("thisobject.js", parent.getElementName());
	}
	
	@Test
	public void testThisVariableTest() throws CoreException {
//		final TestSearchResults results = PROJECT.search("myvariable", FIELD,
//				DECLARATIONS);
//		assertEquals(1, results.size());
//		final IModelElement element = results.get(0);
//		final IModelElement parent = element
//				.getAncestor(IModelElement.SOURCE_MODULE);
//		assertEquals("thisobject.js", parent.getElementName());
	}
}
