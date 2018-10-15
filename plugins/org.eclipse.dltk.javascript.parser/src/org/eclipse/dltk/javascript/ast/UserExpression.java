/*******************************************************************************
 * Copyright (c) 2011 xored software, Inc.  
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
package org.eclipse.dltk.javascript.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.javascript.internal.parser.JSLiterals;

/**
 * Abstract base class for user-defined AST nodes extending {@link Expression}
 */
public abstract class UserExpression extends Expression implements JSUserNode {

	public UserExpression(JSNode parent) {
		super(parent);
	}

	protected JSNode original;

	public JSNode getOriginal() {
		return original;
	}

	public void setOriginal(JSNode original) {
		this.original = original;
	}

	@Override
	public String toSourceString(String indentationString) {
		return original != null ? original.toSourceString(indentationString)
				: JSLiterals.EMPTY;
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			traverseChildren(visitor);
			visitor.endvisit(this);
		}
	}

	protected void traverseChildren(ASTVisitor visitor) throws Exception {
		if (original != null) {
			original.traverse(visitor);
		}
	}

}
