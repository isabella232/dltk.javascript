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
package org.eclipse.dltk.javascript.typeinfo;

import org.eclipse.dltk.javascript.typeinfo.model.Member;

public enum MemberPredicates implements MemberPredicate {
	STATIC {
		public boolean evaluate(Member member) {
			return member.isStatic();
		}

		public boolean evaluate(IRMember member) {
			return member.isStatic();
		}
	},
	NON_STATIC {
		public boolean evaluate(Member member) {
			return !member.isStatic();
		}

		public boolean evaluate(IRMember member) {
			return !member.isStatic();
		}
	},
	PROTOTYPE {
		public boolean evaluate(Member member) {
			return !member.isStatic();
		}

		public boolean evaluate(IRMember member) {
			return !member.isStatic();
		}
	},
	ALWAYS_TRUE {
		public boolean evaluate(Member member) {
			return true;
		}

		public boolean evaluate(IRMember member) {
			return true;
		}
	};

	public boolean isCompatibleWith(MemberPredicate predicate) {
		return this == predicate;
	}
}
