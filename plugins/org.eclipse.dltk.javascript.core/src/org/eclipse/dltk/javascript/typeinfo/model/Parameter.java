/**
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
 *
 * $Id: Parameter.java,v 1.5 2011/02/04 06:04:41 apanchenk Exp $
 */
package org.eclipse.dltk.javascript.typeinfo.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.dltk.javascript.typeinfo.model.Parameter#getKind <em>Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.dltk.javascript.typeinfo.model.TypeInfoModelPackage#getParameter()
 * @model
 * @generated
 */
public interface Parameter extends NamedElement, TypedElement {
	/**
     * Returns the value of the '<em><b>Kind</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.dltk.javascript.typeinfo.model.ParameterKind}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Kind</em>' attribute.
     * @see org.eclipse.dltk.javascript.typeinfo.model.ParameterKind
     * @see #setKind(ParameterKind)
     * @see org.eclipse.dltk.javascript.typeinfo.model.TypeInfoModelPackage#getParameter_Kind()
     * @model
     * @generated
     */
	ParameterKind getKind();

	/**
     * Sets the value of the '{@link org.eclipse.dltk.javascript.typeinfo.model.Parameter#getKind <em>Kind</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kind</em>' attribute.
     * @see org.eclipse.dltk.javascript.typeinfo.model.ParameterKind
     * @see #getKind()
     * @generated
     */
	void setKind(ParameterKind value);

} // Parameter
