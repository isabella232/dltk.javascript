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
package org.eclipse.dltk.javascript.core.tests.typeinference;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.javascript.ti.TypeInferencer2;
import org.eclipse.dltk.javascript.ast.Script;
import org.eclipse.dltk.javascript.parser.JavaScriptParser;
import org.eclipse.dltk.javascript.typeinference.IValueCollection;
import org.eclipse.dltk.javascript.typeinference.ValueCollectionFactory;
import org.eclipse.dltk.javascript.typeinfo.IMemberEvaluator;
import org.eclipse.dltk.javascript.typeinfo.ITypeInfoContext;
import org.eclipse.dltk.javascript.typeinfo.model.Element;

public class ExampleMemberEvaluator implements IMemberEvaluator {

    public IValueCollection valueOf(ITypeInfoContext context, Element member) {
        IValueCollection collection = (IValueCollection) member.getAttribute(ExampleElementResolver.MEMBER_VALUE);
        if (collection == null) {
            ISourceModule globals = (ISourceModule) member.getAttribute(ExampleElementResolver.LAZY_MEMBER_VALUE);
            if (globals != null && globals.exists()) {
                final Script script = new JavaScriptParser().parse((IModuleSource) globals, null);
                if (script != null) {
                    TypeInferencer2 inferencer = new TypeInferencer2();
                    inferencer.setModelElement(globals);
                    inferencer.doInferencing(script);
                    collection = inferencer.getCollection();
                }

            }
        }
        return collection;
    }

    public IValueCollection getTopValueCollection(ITypeInfoContext context) {
        if (context.getSource() != null && context.getSource().getSourceModule() != null
                && context.getSource().getSourceModule().getResource() != null
                && context.getSource().getSourceModule().getResource().getName().equals("globals1.js")) {
            IFile file2 = context
                    .getSource()
                    .getSourceModule()
                    .getResource()
                    .getParent()
                    .getFile(Path.fromPortableString("globals2.js"));
            IFile file3 = context
                    .getSource()
                    .getSourceModule()
                    .getResource()
                    .getParent()
                    .getFile(Path.fromPortableString("globals3.js"));
            IValueCollection collection = ValueCollectionFactory.createScopeValueCollection();
            ValueCollectionFactory.copyInto(collection, ValueCollectionFactory.createValueCollection(file2, true));
            ValueCollectionFactory.copyInto(collection, ValueCollectionFactory.createValueCollection(file3, true));
            return collection;
        }
        return null;
    }

}
