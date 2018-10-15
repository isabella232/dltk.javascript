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

public abstract class Method extends ObjectInitializerPart implements
		ISourceable, ISourceableBlock {

	private Identifier name;
	private StatementBlock body;
	private int LP = -1;
	private int RP = -1;

	public Method(JSNode parent) {
		super(parent);
	}

	public Identifier getName() {
		return this.name;
	}

	public void setName(Identifier name) {
		this.name = name;
	}

	public StatementBlock getBody() {
		return this.body;
	}

	public void setBody(StatementBlock body) {
		this.body = body;
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

	public boolean isBlock() {
		return true;
	}

}
