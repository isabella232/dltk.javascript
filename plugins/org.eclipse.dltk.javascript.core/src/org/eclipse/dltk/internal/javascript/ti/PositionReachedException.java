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
package org.eclipse.dltk.internal.javascript.ti;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.javascript.typeinference.IValueReference;

@SuppressWarnings("serial")
public class PositionReachedException extends RuntimeException {

	private final ASTNode node;
	private final IValueReference reference;

	public PositionReachedException(ASTNode node, IValueReference reference) {
		this.node = node;
		this.reference = reference;
	}

	public ASTNode getNode() {
		return node;
	}

	public IValueReference getValue() {
		return reference;
	}

}
