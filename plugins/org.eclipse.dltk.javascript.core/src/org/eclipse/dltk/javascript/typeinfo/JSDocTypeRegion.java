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
package org.eclipse.dltk.javascript.typeinfo;

import org.eclipse.dltk.core.ISourceNode;

/**
 * Value type returned by
 * {@link JSDocTypeUtil#findTypeAt(org.eclipse.dltk.internal.javascript.ti.TypeInferencer2, String, int)}
 * , it contains primitive type declaration and it's source range.
 */
public class JSDocTypeRegion implements ISourceNode {

	private final String name;
	private final int start;
	private final int end;

	JSDocTypeRegion(String name, int start, int end) {
		this.name = name;
		this.start = start;
		this.end = end;
	}

	public String name() {
		return name;
	}

	public int start() {
		return start;
	}

	public int end() {
		return end;
	}

	public int length() {
		return end - start;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + name + ",start=" + start
				+ ",end=" + end + "]";
	}
}
