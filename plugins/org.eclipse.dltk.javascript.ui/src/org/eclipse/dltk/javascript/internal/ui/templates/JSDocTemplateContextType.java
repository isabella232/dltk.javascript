/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.dltk.ui.templates.ScriptTemplateContextType;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;

public class JSDocTemplateContextType extends ScriptTemplateContextType {

	public static final String CONTEXT_TYPE_ID = "JSDocTemplateContextType";

	public JSDocTemplateContextType() {
		// empty constructor
	}

	public JSDocTemplateContextType(String id) {
		super(id);
	}

	public JSDocTemplateContextType(String id, String name) {
		super(id, name);
	}

	public ScriptTemplateContext createContext(IDocument document, int offset,
			int length, ISourceModule sourceModule) {
		return new JSDocScriptTemplateContext(this, document, offset, length,
				sourceModule);
	}

	@Override
	public ScriptTemplateContext createContext(IDocument document,
			Position position, ISourceModule sourceModule) {
		return new JSDocScriptTemplateContext(this, document, position,
				sourceModule);
	}
}
