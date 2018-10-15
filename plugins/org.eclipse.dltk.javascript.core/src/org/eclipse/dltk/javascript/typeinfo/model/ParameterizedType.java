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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.dltk.javascript.typeinfo.model.ParameterizedType#getActualTypeArguments <em>Actual Type Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.dltk.javascript.typeinfo.model.TypeInfoModelPackage#getParameterizedType()
 * @model
 * @generated
 */
public interface ParameterizedType extends SimpleType {
    /**
     * Returns the value of the '<em><b>Actual Type Arguments</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.dltk.javascript.typeinfo.model.JSType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Actual Type Arguments</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Actual Type Arguments</em>' containment reference list.
     * @see org.eclipse.dltk.javascript.typeinfo.model.TypeInfoModelPackage#getParameterizedType_ActualTypeArguments()
     * @model containment="true"
     * @generated
     */
    EList<JSType> getActualTypeArguments();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    String getRawName();

} // GenericType
