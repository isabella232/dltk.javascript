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
package org.eclipse.dltk.javascript.core.tests.contentassist;

public class StandardTypeMemberTests extends AbstractCompletionTest {

	public void testString() {
		assertEquals(40, getMembersOfString().size());
	}

	public void testNumber() {
		assertEquals(10, getMembersOfNumber().size());
	}

	public void testObject() {
		assertEquals(7, getMembersOfObject().size());
	}

	public void testXML() {
		assertEquals(39, getMembersOfXML().size());
	}
	
	public void testArray() {
		assertEquals(25, getMembersOfArray().size());
	}

	public void testFunction() {
		assertEquals(12, getMembersOfFunction().size());
	}


}
