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
package org.eclipse.dltk.internal.javascript.parser.structure;

import org.eclipse.dltk.core.ISourceNode;

class NameNode implements ISourceNode {

	private final int start;
	private final int end;

	public NameNode(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int start() {
		return start;
	}

	public int end() {
		return end;
	}

}
