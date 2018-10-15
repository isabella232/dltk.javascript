/*******************************************************************************
 * Copyright (c) 2011,2012 NumberFour AG
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

class RMapType extends RType implements IRMapType {

	private final IRType valueType;
	private final IRType keyType;

	public RMapType(IRType keyType, IRType valueType) {
		this.keyType = keyType;
		this.valueType = valueType;
	}

	public String getName() {
		// if the key type is set but it is a String then just default to
		// without it.
		if (valueType != null && keyType != null
				&& !ITypeNames.STRING.equals(keyType.getName())) {
			return ITypeNames.OBJECT + '<' + keyType.getName() + ','
					+ valueType.getName() + '>';
		}
		return valueType != null ? ITypeNames.OBJECT + '<'
				+ valueType.getName() + '>' : ITypeNames.OBJECT;
	}

	@Override
	public int hashCode() {
		return valueType.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RMapType) {
			final RMapType other = (RMapType) obj;
			return valueType.equals(other.valueType);
		}
		return false;
	}

	@Override
	public boolean isExtensible() {
		return true;
	}

	@Override
	public TypeCompatibility isAssignableFrom(IRType type) {
		if (super.isAssignableFrom(type).ok()) {
			return TypeCompatibility.TRUE;
		}
		if (type instanceof RMapType) {
			return valueType.isAssignableFrom(((RMapType) type).valueType);
		} else if (type instanceof IRRecordType) {
			TypeCompatibility result = TypeCompatibility.TRUE;
			for (IRRecordMember member : ((IRRecordType) type).getMembers()) {
				final TypeCompatibility compatibility = valueType
						.isAssignableFrom(member.getType());
				if (compatibility == TypeCompatibility.FALSE) {
					return compatibility;
				} else if (compatibility != result
						&& compatibility.after(result)) {
					result = compatibility;
				}
			}
			return result;
		}
		return TypeCompatibility.FALSE;
	}

	public IRType getKeyType() {
		return keyType;
	}

	public IRType getValueType() {
		return valueType;
	}

}
