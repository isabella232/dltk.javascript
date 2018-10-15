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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import junit.framework.TestResult;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemIdentifier;
import org.eclipse.dltk.compiler.problem.ProblemCollector;
import org.eclipse.dltk.core.tests.TestSupport;
import org.eclipse.dltk.javascript.ast.DecimalLiteral;
import org.eclipse.dltk.javascript.ast.Identifier;
import org.eclipse.dltk.javascript.ast.Script;
import org.eclipse.dltk.javascript.parser.JSProblem;
import org.eclipse.dltk.javascript.parser.JavaScriptParser;
import org.eclipse.dltk.javascript.parser.JavaScriptParserProblems;

import com.google.common.base.Throwables;

public abstract class AbstractJSParserTest extends TestCase {

	protected final ProblemCollector reporter = new ProblemCollector();
	protected final JavaScriptParser parser = new JavaScriptParser();

	@Override
	public void run(TestResult result) {
		if (TestSupport.ignored(this))
			return;
		super.run(result);
	}

	protected Script parseRaw(final String source) {
		reporter.reset();
		return parser.parse(source, reporter);
	}

	protected Script parse(final String source) {
		final Script script = parseRaw(source);
		if (!script.getStatements().isEmpty()
				&& !getProblemIds().contains(
						JavaScriptParserProblems.SYNTAX_ERROR)) {
			new ASTVerifier(script, source).verify();
		}
		assertNotNull(script);
		for (IProblem problem : reporter.getProblems()) {
			if (problem instanceof JSProblem) {
				Throwables.propagate(((JSProblem) problem).getCause());
			}
		}
		return script;
	}

	protected static <E> E uniqueResult(List<E> elements) {
		if (elements.size() == 1) {
			return elements.get(0);
		} else {
			throw new AssertionFailedError(elements.isEmpty() ? "No elements"
					: "Single element expected");
		}
	}

	protected static void assertIdentifier(String expected, ASTNode id) {
		assertEquals(expected, ((Identifier) id).getName());
	}

	protected static void assertDecimal(String expected, ASTNode decimal) {
		assertEquals(expected, ((DecimalLiteral) decimal).getText());
	}

	public IProblemIdentifier getProblemId() {
		final Set<IProblemIdentifier> problemIds = getProblemIds();
		assertEquals(1, problemIds.size());
		return problemIds.toArray(new IProblemIdentifier[1])[0];
	}

	public Set<IProblemIdentifier> getProblemIds() {
		return extractIds(reporter.getProblems());
	}

	protected Set<IProblemIdentifier> extractIds(final List<IProblem> problems) {
		final Set<IProblemIdentifier> ids = new HashSet<IProblemIdentifier>();
		for (IProblem problem : problems) {
			ids.add(problem.getID());
		}
		return ids;
	}

}
