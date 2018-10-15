/*******************************************************************************
 * Copyright (c) 2009 xored software, Inc.  
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0  
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Vladimir Belov)
 *******************************************************************************/
package org.eclipse.dltk.javascript.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ast.parser.IModuleDeclaration;
import org.eclipse.dltk.javascript.internal.parser.JSLiterals;

public class Script extends JSNode implements ISourceable, IModuleDeclaration,
		JSScope {

	private final List<Comment> comments = new ArrayList<Comment>();
	private final List<Statement> statements = new ArrayList<Statement>();
	private final List<JSDeclaration> declarations = new ArrayList<JSDeclaration>();

	public Script() {
		super(null);
	}

	@Override
	public Script getScript() {
		return this;
	}

	public String toSourceString(String indentationString) {
		final StringBuilder buffer = new StringBuilder();

		for (Statement statement : statements) {
			buffer.append(statement.toSourceString(indentationString));
		}

		return buffer.toString();
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void addStatement(Statement statement) {
		statements.add(statement);
	}

	public <E> void visitAll(ASTVisitor<E> visitor) {
		for (Statement statement : statements) {
			visitor.visit(statement);
		}
	}

	@Override
	public String toString() {
		return toSourceString(JSLiterals.EMPTY);
	}

	@Override
	public void traverse(org.eclipse.dltk.ast.ASTVisitor visitor)
			throws Exception {
		if (visitor.visit(this)) {
			for (Statement statement : statements) {
				statement.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

	public void addDeclaration(JSDeclaration declaration) {
		this.declarations.add(declaration);
	}

	public List<JSDeclaration> getDeclarations() {
		return declarations;
	}

	private Map<String, Object> attributes;

	/**
	 * @since 4.1
	 */
	public synchronized Object getAttribute(String key) {
		return attributes != null ? attributes.get(key) : null;
	}

	/**
	 * @since 4.1
	 */
	public synchronized void setAttribute(String key, Object value) {
		if (value != null) {
			if (attributes == null) {
				attributes = new HashMap<String, Object>();
			}
			attributes.put(key, value);
		} else {
			if (attributes != null) {
				attributes.remove(key);
			}
		}
	}
}
