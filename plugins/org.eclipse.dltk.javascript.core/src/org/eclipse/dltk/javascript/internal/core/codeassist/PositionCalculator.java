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
package org.eclipse.dltk.javascript.internal.core.codeassist;

import org.eclipse.dltk.internal.javascript.typeinference.CompletionString;

public class PositionCalculator {

	private boolean isMember;
	private final String completion;

	// private final String completionPart;
	// private final String corePart;

	public PositionCalculator(String conString, int pos, boolean bothSides) {
		if (bothSides) {
			int maxPos = conString.length();
			if (pos < maxPos) {
				while (pos < maxPos) {
					char charAt = conString.charAt(pos);
					if (Character.isJavaIdentifierPart(charAt)) {
						pos++;
						continue;
					} else {
						break;
					}
				}
			} else
				pos = maxPos;
		}
		completion = CompletionString.parse(conString.substring(0, pos), false,
				true);
		int lastDot = completion.lastIndexOf('.');
		if (lastDot != -1) {
			isMember = true;
			// completionPart = completion.substring(lastDot + 1);
			// corePart = completion.substring(0, lastDot);
		} else {
			isMember = false;
			// completionPart = completion;
			// corePart = completion;
		}
	}

	public String getCompletion() {
		return completion;
	}

	// public String getCompletionPart() {
	// return completionPart;
	// }
	//
	// public String getCorePart() {
	// return corePart;
	// }

	public boolean isMember() {
		return isMember;
	}
}
