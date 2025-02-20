/**
 * Copyright (c) 2011 NumberFour AG
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     NumberFour AG - initial API and Implementation (Alex Panchenko)
 */
package org.eclipse.dltk.javascript.typeinfo.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.internal.javascript.parser.JSModifiers;
import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Visibility</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.dltk.javascript.typeinfo.model.TypeInfoModelPackage#getVisibility()
 * @model
 * @generated
 */
public enum Visibility implements Enumerator {
    /**
     * The '<em><b>PUBLIC</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PUBLIC_VALUE
     * @generated
     * @ordered
     */
    PUBLIC(0, "PUBLIC", "PUBLIC"), /**
     * The '<em><b>PROTECTED</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PROTECTED_VALUE
     * @generated
     * @ordered
     */
    PROTECTED(50, "PROTECTED", "PROTECTED"), /**
     * The '<em><b>INTERNAL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INTERNAL_VALUE
     * @generated
     * @ordered
     */
    INTERNAL(75, "INTERNAL", "INTERNAL"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>PRIVATE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PRIVATE_VALUE
     * @generated
     * @ordered
     */
    PRIVATE(100, "PRIVATE", "PRIVATE"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>PUBLIC</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>PUBLIC</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PUBLIC
     * @model
     * @generated
     * @ordered
     */
    public static final int PUBLIC_VALUE = 0;

    /**
     * The '<em><b>PROTECTED</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>PROTECTED</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PROTECTED
     * @model
     * @generated
     * @ordered
     */
    public static final int PROTECTED_VALUE = 50;

    /**
     * The '<em><b>INTERNAL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>INTERNAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INTERNAL
     * @model
     * @generated
     * @ordered
     */
    public static final int INTERNAL_VALUE = 75;

    /**
     * The '<em><b>PRIVATE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>PRIVATE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PRIVATE
     * @model
     * @generated
     * @ordered
     */
    public static final int PRIVATE_VALUE = 100;

    /**
     * An array of all the '<em><b>Visibility</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final Visibility[] VALUES_ARRAY =
        new Visibility[] {
            PUBLIC,
            PROTECTED,
            INTERNAL,
            PRIVATE,
        };

    /**
     * A public read-only list of all the '<em><b>Visibility</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<Visibility> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Visibility</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Visibility get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Visibility result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Visibility</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Visibility getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Visibility result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Visibility</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Visibility get(int value) {
        switch (value) {
            case PUBLIC_VALUE: return PUBLIC;
            case PROTECTED_VALUE: return PROTECTED;
            case INTERNAL_VALUE: return INTERNAL;
            case PRIVATE_VALUE: return PRIVATE;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private Visibility(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getValue() {
      return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
      return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLiteral() {
      return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

	public int getFlags() {
		switch (this) {
		case PUBLIC:
			return JSModifiers.PUBLIC;
		case PROTECTED:
			return JSModifiers.PROTECTED;
		case PRIVATE:
			return JSModifiers.PRIVATE;
		default:
			return Modifiers.AccDefault;
		}
	}

	public static int getFlags(Visibility visibility) {
		return visibility != null ? visibility.getFlags()
				: Modifiers.AccDefault;
	}

} //Visibility
