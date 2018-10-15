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
package org.eclipse.dltk.javascript.ui.tests.autoedit;

import org.eclipse.dltk.core.tests.util.StringList;
import org.eclipse.dltk.javascript.internal.ui.text.Symbols;
import org.eclipse.dltk.javascript.scriptdoc.JavaHeuristicScanner;
import org.eclipse.jface.text.BadLocationException;
import org.junit.Test;

public class HeuristicScannerTest extends JSAutoEditStrategyTestCase {

	@Test
	public void testNewLineAfterIdentifier() throws BadLocationException {
		final Document document = createDocument(new StringList("a", ""));
		final JavaHeuristicScanner scanner = new JavaHeuristicScanner(document);
		assertEquals(Symbols.TokenIDENT, scanner.previousToken(
				document.getEndOfLineOffset(1), JavaHeuristicScanner.UNBOUND));
	}

	@Test
	public void testNewLineAfterReturn() throws BadLocationException {
		final Document document = createDocument(new StringList("return", ""));
		final JavaHeuristicScanner scanner = new JavaHeuristicScanner(document);
		assertEquals(Symbols.TokenSEMICOLON, scanner.previousToken(
				document.getEndOfLineOffset(1), JavaHeuristicScanner.UNBOUND));
	}

}
