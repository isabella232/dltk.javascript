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

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTVisitor;

public class ForInStatement extends LoopStatement {

	private Keyword forKeyword;
	private Keyword inKeyword;
	private Expression item;
	private Expression iterator;
	private int LP = -1;
	private int RP = -1;

	public ForInStatement(JSNode parent) {
		super(parent);
	}

	/**
	 * @see org.eclipse.dltk.javascript.ast.Statement#traverse(org.eclipse.dltk.ast.ASTVisitor)
	 */
	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (forKeyword != null)
				forKeyword.traverse(visitor);
			if (inKeyword != null)
				inKeyword.traverse(visitor);
			if (item != null)
				item.traverse(visitor);
			if (iterator != null)
				iterator.traverse(visitor);

			Statement body = getBody();
			if (body != null) {
				body.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

	public Expression getItem() {
		return this.item;
	}

	public void setItem(Expression item) {
		this.item = item;
	}

	public Expression getIterator() {
		return this.iterator;
	}

	public void setIterator(Expression iterator) {
		this.iterator = iterator;
	}

	public Keyword getForKeyword() {
		return this.forKeyword;
	}

	public void setForKeyword(Keyword keyword) {
		this.forKeyword = keyword;
	}

	public Keyword getInKeyword() {
		return this.inKeyword;
	}

	public void setInKeyword(Keyword keyword) {
		this.inKeyword = keyword;
	}

	public int getLP() {
		return this.LP;
	}

	public void setLP(int LP) {
		this.LP = LP;
	}

	public int getRP() {
		return this.RP;
	}

	public void setRP(int RP) {
		this.RP = RP;
	}

	@Override
	public String toSourceString(String indentationString) {

		Assert.isTrue(sourceStart() >= 0);
		Assert.isTrue(sourceEnd() > 0);
		Assert.isTrue(LP > 0);
		Assert.isTrue(RP > 0);

		StringBuffer buffer = new StringBuffer();

		buffer.append(indentationString);
		buffer.append(Keywords.FOR);
		buffer.append(" (");
		buffer.append(getItem().toSourceString(indentationString));
		buffer.append(" ");
		buffer.append(Keywords.IN);
		buffer.append(" ");
		buffer.append(getIterator().toSourceString(indentationString));
		buffer.append(")");
		if (getBody() != null) {
			buffer.append("\n");
			buffer.append(getBody().toSourceString(indentationString));
		} else {
			buffer.append("\n");
		}

		return buffer.toString();
	}

}
