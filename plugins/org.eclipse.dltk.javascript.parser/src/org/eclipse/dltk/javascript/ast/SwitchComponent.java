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
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.javascript.ast;

import java.util.ArrayList;
import java.util.List;

public abstract class SwitchComponent extends JSNode implements ISourceable {

	private int colon = -1;
	private final List<Statement> statements = new ArrayList<Statement>();

	/**
	 * @param parent
	 */
	public SwitchComponent(JSNode parent) {
		super(parent);
	}

	/**
	 * @since 2.0
	 */
	public abstract Keyword getKeyword();

	/**
	 * @since 2.0
	 */
	public int getColonPosition() {
		return this.colon;
	}

	/**
	 * @since 2.0
	 */
	public void setColonPosition(int colon) {
		this.colon = colon;
	}

	/**
	 * @since 2.0
	 */
	public List<Statement> getStatements() {
		return statements;
	}

}
