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
 * $Id: TypeInfoModelFactory.java,v 1.18 2012/06/12 10:40:32 apanchenk Exp $
 */
package org.eclipse.dltk.javascript.typeinfo.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.dltk.javascript.typeinfo.model.TypeInfoModelPackage
 * @generated
 */
public interface TypeInfoModelFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	TypeInfoModelFactory eINSTANCE = org.eclipse.dltk.javascript.typeinfo.model.impl.TypeInfoModelFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Type</em>'.
     * @generated
     */
	Type createType();

	/**
     * Returns a new object of class '<em>Constructor</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Constructor</em>'.
     * @generated
     */
    Constructor createConstructor();

    /**
     * Returns a new object of class '<em>Type Alias</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Type Alias</em>'.
     * @generated
     */
    TypeAlias createTypeAlias();

    /**
     * Returns a new object of class '<em>Method</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Method</em>'.
     * @generated
     */
	Method createMethod();

	/**
     * Returns a new object of class '<em>Parameter</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Parameter</em>'.
     * @generated
     */
	Parameter createParameter();

	/**
     * Returns a new object of class '<em>Property</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Property</em>'.
     * @generated
     */
	Property createProperty();

	/**
     * Returns a new object of class '<em>Function Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Function Type</em>'.
     * @generated
     */
    FunctionType createFunctionType();

    /**
     * Returns a new object of class '<em>Simple Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Simple Type</em>'.
     * @generated
     */
    SimpleType createSimpleType();

    /**
     * Returns a new object of class '<em>Array Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Array Type</em>'.
     * @generated
     */
    ArrayType createArrayType();

    /**
     * Returns a new object of class '<em>Any Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Any Type</em>'.
     * @generated
     */
    AnyType createAnyType();

    /**
     * Returns a new object of class '<em>Union Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Union Type</em>'.
     * @generated
     */
    UnionType createUnionType();

    /**
     * Returns a new object of class '<em>Parameterized Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Parameterized Type</em>'.
     * @generated
     */
    ParameterizedType createParameterizedType();

    /**
     * Returns a new object of class '<em>Generic Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Generic Type</em>'.
     * @generated
     */
    GenericType createGenericType();

    /**
     * Returns a new object of class '<em>Type Variable Reference</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Type Variable Reference</em>'.
     * @generated
     */
    TypeVariableReference createTypeVariableReference();

    /**
     * Returns a new object of class '<em>RType</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>RType</em>'.
     * @generated
     */
    RType createRType();

    /**
     * Returns a new object of class '<em>Generic Method</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Generic Method</em>'.
     * @generated
     */
    GenericMethod createGenericMethod();

    /**
     * Returns a new object of class '<em>Type Literal</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Type Literal</em>'.
     * @generated
     */
    TypeLiteral createTypeLiteral();

    /**
     * Returns a new object of class '<em>Type Variable Class Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Type Variable Class Type</em>'.
     * @generated
     */
    TypeVariableClassType createTypeVariableClassType();

    /**
     * Returns a new object of class '<em>Map Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Map Type</em>'.
     * @generated
     */
    MapType createMapType();

    /**
     * Returns a new object of class '<em>Record Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Record Type</em>'.
     * @generated
     */
    RecordType createRecordType();

    /**
     * Returns a new object of class '<em>Record Property</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Record Property</em>'.
     * @generated
     */
    RecordProperty createRecordProperty();

    /**
     * Returns a new object of class '<em>Class Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Class Type</em>'.
     * @generated
     */
    ClassType createClassType();

    /**
     * Returns a new object of class '<em>Undefined Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Undefined Type</em>'.
     * @generated
     */
    UndefinedType createUndefinedType();

    /**
     * Returns a new object of class '<em>Type Variable</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Type Variable</em>'.
     * @generated
     */
    TypeVariable createTypeVariable();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	TypeInfoModelPackage getTypeInfoModelPackage();

} //TypeInfoModelFactory
