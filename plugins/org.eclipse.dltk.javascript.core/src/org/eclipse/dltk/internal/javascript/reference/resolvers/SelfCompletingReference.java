/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.reference.resolvers;

import org.eclipse.dltk.internal.javascript.typeinference.IReference;

public interface SelfCompletingReference extends IReference{

	

	int getKind();
	
	char[] getSignature();
	
	char[] getDeclarationSignature();

	char[][] getParameterNames();

	
}
