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
package org.eclipse.dltk.javascript.parser.tests;

import org.eclipse.dltk.ast.utils.ASTUtil;
import org.eclipse.dltk.javascript.ast.FunctionStatement;
import org.eclipse.dltk.javascript.ast.Script;
import org.eclipse.dltk.javascript.ast.VariableDeclaration;

public class FunctionDeclarationTests extends AbstractJSParserTest {

	public void testNoArgs() {
		Script script = parse("function a(){}");
		assertFalse(reporter.hasErrors());
		FunctionStatement func = uniqueResult(ASTUtil.select(script,
				FunctionStatement.class));
		assertTrue(func.isDeclaration());
		assertEquals("a", func.getName().getName());
		assertTrue(func.getArguments().isEmpty());
	}

	public void testArg1() {
		Script script = parse("function a(x){}");
		assertFalse(reporter.hasErrors());
		FunctionStatement func = uniqueResult(ASTUtil.select(script,
				FunctionStatement.class));
		assertTrue(func.isDeclaration());
		assertEquals("a", func.getName().getName());
		assertEquals(1, func.getArguments().size());
		assertEquals("x", func.getArguments().get(0).getArgumentName());
	}

	public void testArg2() {
		Script script = parse("function a(x,y){}");
		assertFalse(reporter.hasErrors());
		FunctionStatement func = uniqueResult(ASTUtil.select(script,
				FunctionStatement.class));
		assertTrue(func.isDeclaration());
		assertEquals("a", func.getName().getName());
		assertEquals(2, func.getArguments().size());
		assertEquals("x", func.getArguments().get(0).getArgumentName());
		assertEquals("y", func.getArguments().get(1).getArgumentName());
	}

	public void testFunctionExpression() {
		Script script = parse("var method = function a(x,y){}");
		assertFalse(reporter.hasErrors());
		FunctionStatement func = uniqueResult(ASTUtil.select(script,
				FunctionStatement.class));
		assertFalse(func.isDeclaration());
		assertEquals("a", func.getName().getName());
		assertEquals(2, func.getArguments().size());
		assertEquals("x", func.getArguments().get(0).getArgumentName());
		assertEquals("y", func.getArguments().get(1).getArgumentName());
	}

	public void testFunctionScopeBlock1() {
		Script script = parse("(function(){})();");
		assertFalse(reporter.hasErrors());
		final FunctionStatement func = uniqueResult(ASTUtil.select(script,
				FunctionStatement.class));
		assertTrue(func.isInlineBlock());
	}

	public void testFunctionScopeBlock2() {
		Script script = parse("(function(){}());");
		assertFalse(reporter.hasErrors());
		final FunctionStatement func = uniqueResult(ASTUtil.select(script,
				FunctionStatement.class));
		assertTrue(func.isInlineBlock());
	}

	public void testScopeDeclarations() {
		Script script = parse("function test() { var x=1; function y() {} }");
		assertFalse(reporter.hasErrors());
		assertEquals(1, script.getDeclarations().size());
		final FunctionStatement func = ASTUtil.select(script,
				FunctionStatement.class).get(0);
		assertEquals("test", func.getFunctionName());
		assertSame(func, script.getDeclarations().get(0));
		assertEquals(2, func.getDeclarations().size());
		assertEquals("x",
				((VariableDeclaration) func.getDeclarations().get(0))
						.getVariableName());
		assertEquals("y",
				((FunctionStatement) func.getDeclarations().get(1))
						.getFunctionName());
	}

}
