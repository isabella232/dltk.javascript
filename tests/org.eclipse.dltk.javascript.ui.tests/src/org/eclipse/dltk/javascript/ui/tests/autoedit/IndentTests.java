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
package org.eclipse.dltk.javascript.ui.tests.autoedit;

import org.eclipse.dltk.core.tests.util.StringList;
import org.eclipse.jface.text.BadLocationException;
import org.junit.Test;

public class IndentTests extends JSAutoEditStrategyTestCase {

	@Test
	public void testIndentInFunctionBody() throws BadLocationException {
		StringList code = new StringList();
		code.add("function test() {");
		code.add("}");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getEndOfLineOffset(0)));
		StringList expected = new StringList();
		expected.add("function test() {");
		expected.add(TAB);
		expected.add("}");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testIndentInObjectLiteral() throws BadLocationException {
		StringList code = new StringList();
		code.add("var x = {");
		code.add("}");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getEndOfLineOffset(0)));
		StringList expected = new StringList();
		expected.add("var x = {");
		expected.add(TAB);
		expected.add("}");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testIndentInObjectLiteralInFunctionCall()
			throws BadLocationException {
		StringList code = new StringList();
		code.add("run({)");
		final Document document = createDocument(code);
		execute(document,
				createCommand(ENTER, document.getEndOfLineOffset(0) - 1));
		StringList expected = new StringList();
		expected.add("run({");
		expected.add(TAB);
		expected.add("})");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testIndentInObjectLiteralFunctionBody()
			throws BadLocationException {
		StringList code = new StringList();
		code.add("var methods = {");
		code.add(TAB + "/**");
		code.add(TAB + " * Hello");
		code.add(TAB + " */");
		code.add(TAB + "hello: function() {");
		code.add(TAB + TAB + "var a = 1");
		code.add(TAB + "}");
		code.add("}");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getEndOfLineOffset(4)));
		StringList expected = new StringList();
		expected.add("var methods = {");
		expected.add(TAB + "/**");
		expected.add(TAB + " * Hello");
		expected.add(TAB + " */");
		expected.add(TAB + "hello: function() {");
		expected.add(TAB + TAB);
		expected.add(TAB + TAB + "var a = 1");
		expected.add(TAB + "}");
		expected.add("}");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testIndentIfBetweenFunctionAndDoc() throws BadLocationException {
		StringList code = new StringList();
		code.add("var methods = {");
		code.add(TAB + "/**");
		code.add(TAB + " * Hello");
		code.add(TAB + " */");
		code.add(TAB + "hello: function() {");
		code.add(TAB + TAB + "var a = 1");
		code.add(TAB + "}");
		code.add("}");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getLineOffset(4)));
		StringList expected = new StringList();
		expected.add("var methods = {");
		expected.add(TAB + "/**");
		expected.add(TAB + " * Hello");
		expected.add(TAB + " */");
		expected.add("");
		expected.add(TAB + "hello: function() {");
		expected.add(TAB + TAB + "var a = 1");
		expected.add(TAB + "}");
		expected.add("}");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testIndentInObjectLiteralFunctionBody1()
			throws BadLocationException {
		StringList code = new StringList();
		code.add("var methods = {");
		code.add(TAB + "/**");
		code.add(TAB + " * Hello");
		code.add(TAB + " */");
		code.add(TAB + "hello: function() {");
		code.add("");
		code.add(TAB + TAB + "var a = 1");
		code.add(TAB + "}");
		code.add("}");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getEndOfLineOffset(5)));
		StringList expected = new StringList();
		expected.add("var methods = {");
		expected.add(TAB + "/**");
		expected.add(TAB + " * Hello");
		expected.add(TAB + " */");
		expected.add(TAB + "hello: function() {");
		expected.add("");
		expected.add(TAB + TAB);
		expected.add(TAB + TAB + "var a = 1");
		expected.add(TAB + "}");
		expected.add("}");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testIndentInObjectLiteralFunctionBody2()
			throws BadLocationException {
		StringList code = new StringList();
		code.add("var methods = {");
		code.add(TAB + "/**");
		code.add(TAB + " * Hello");
		code.add(TAB + " */");
		code.add(TAB + "hello: function() {");
		code.add("");
		code.add("");
		code.add(TAB + TAB + "var a = 1");
		code.add(TAB + "}");
		code.add("}");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getEndOfLineOffset(6)));
		StringList expected = new StringList();
		expected.add("var methods = {");
		expected.add(TAB + "/**");
		expected.add(TAB + " * Hello");
		expected.add(TAB + " */");
		expected.add(TAB + "hello: function() {");
		expected.add("");
		expected.add("");
		expected.add(TAB + TAB);
		expected.add(TAB + TAB + "var a = 1");
		expected.add(TAB + "}");
		expected.add("}");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testIndentInObjectLiteralFunctionBody2ws()
			throws BadLocationException {
		StringList code = new StringList();
		code.add("var methods = {");
		code.add(TAB + "/**");
		code.add(TAB + " * Hello");
		code.add(TAB + " */");
		code.add(TAB + "hello: function() {");
		code.add(TAB);
		code.add(TAB);
		code.add(TAB + TAB + "var a = 1");
		code.add(TAB + "}");
		code.add("}");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getEndOfLineOffset(6)));
		StringList expected = new StringList();
		expected.add("var methods = {");
		expected.add(TAB + "/**");
		expected.add(TAB + " * Hello");
		expected.add(TAB + " */");
		expected.add(TAB + "hello: function() {");
		expected.add(TAB);
		expected.add(TAB);
		expected.add(TAB + TAB);
		expected.add(TAB + TAB + "var a = 1");
		expected.add(TAB + "}");
		expected.add("}");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testAfterClassCreate() throws BadLocationException {
		StringList code = new StringList();
		code.add("var x = test1(),");
		code.add(TAB + "y = test2()");
		code.add("");
		code.add("var a = Class.create({");
		code.add(TAB + "a: function() {}");
		code.add("});");
		final Document document = createDocument(code);
		execute(document,
				createCommand(ENTER,
						document.getEndOfLineOffset(code.size() - 1)));
		StringList expected = new StringList();
		expected.addAll(code);
		expected.add("");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testAfterVarStatement() throws BadLocationException {
		StringList code = new StringList();
		code.add("function test() {");
		code.add(TAB + "var x = null;");
		code.add("}");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getEndOfLineOffset(1)));
		StringList expected = new StringList();
		expected.add("function test() {");
		expected.add(TAB + "var x = null;");
		expected.add(TAB);
		expected.add("}");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testAfterTypedVarStatement() throws BadLocationException {
		StringList code = new StringList();
		code.add("function test() {");
		code.add(TAB + "var x:String = null;");
		code.add("}");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getEndOfLineOffset(1)));
		StringList expected = new StringList();
		expected.add("function test() {");
		expected.add(TAB + "var x:String = null;");
		expected.add(TAB);
		expected.add("}");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testVarInitializedWithArray() throws BadLocationException {
		StringList code = new StringList();
		code.add("var x = []");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getEndOfLineOffset(0)));
		StringList expected = new StringList();
		expected.add("var x = []");
		expected.add("");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testAfterFunction() throws BadLocationException {
		StringList code = new StringList();
		code.add("function test() {");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getEndOfLineOffset(0)));
		StringList expected = new StringList();
		expected.add("function test() {");
		expected.add(TAB);
		expected.add("}");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testAfterIf() throws BadLocationException {
		StringList code = new StringList();
		code.add("if (a > b)");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getEndOfLineOffset(0)));
		StringList expected = new StringList();
		expected.add("if (a > b)");
		expected.add(TAB);
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testAfterIfBrace() throws BadLocationException {
		StringList code = new StringList();
		code.add("if (a > b) {");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getEndOfLineOffset(0)));
		StringList expected = new StringList();
		expected.add("if (a > b) {");
		expected.add(TAB);
		expected.add("}");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testElse() throws BadLocationException {
		StringList code = new StringList();
		code.add("if (a > b)");
		code.add(TAB + "els");
		final Document document = createDocument(code);
		execute(document, createCommand("e", document.getEndOfLineOffset(1)));
		StringList expected = new StringList();
		expected.add("if (a > b)");
		expected.add("else");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testCaseIndent() throws BadLocationException {
		StringList code = new StringList();
		code.add("switch (x) {");
		code.add("case 0:");
		code.add("}");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getEndOfLineOffset(1)));
		StringList expected = new StringList();
		expected.add("switch (x) {");
		expected.add("case 0:");
		expected.add(TAB);
		expected.add("}");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testCaseNext() throws BadLocationException {
		StringList code = new StringList();
		code.add("switch (x) {");
		code.add("case 0:");
		code.add(TAB + "cas");
		code.add("}");
		final Document document = createDocument(code);
		execute(document, createCommand("e", document.getEndOfLineOffset(2)));
		StringList expected = new StringList();
		expected.add("switch (x) {");
		expected.add("case 0:");
		expected.add("case");
		expected.add("}");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testAfterArrayLiteralNext() throws BadLocationException {
		StringList code = new StringList();
		code.add("var a = [1, 2, 3]");
		final Document document = createDocument(code);
		execute(document, createCommand(ENTER, document.getEndOfLineOffset(0)));
		StringList expected = new StringList();
		expected.add("var a = [1, 2, 3]");
		expected.add("");
		assertEquals(expected.toString(), document.get());
	}

	@Test
	public void testRightBrace() throws BadLocationException {
		StringList code = new StringList();
		code.add("if (a > b) {");
		code.add(TAB);
		final Document document = createDocument(code);
		execute(document, createCommand("}", document.getEndOfLineOffset(1)));
		StringList expected = new StringList();
		expected.add("if (a > b) {");
		expected.add("}");
		assertEquals(expected.toString(), document.get());
	}

}
