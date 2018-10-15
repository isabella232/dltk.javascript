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
package org.eclipse.dltk.javascript.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.javascript.internal.parser.JSLiterals;

public class EmptyStatement extends Statement implements ISemicolonStatement {

	public EmptyStatement(JSNode parent) {
		super(parent);
	}

	@Override
	public String toSourceString(String indentationString) {
		return JSLiterals.SEMICOLON + JSLiterals.EOL;
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			visitor.endvisit(this);
		}
	}

	public int getSemicolonPosition() {
		return sourceStart();
	}

}
