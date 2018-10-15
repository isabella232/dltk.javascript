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
package org.eclipse.dltk.javascript.ui.tests.typeinfo;

import org.eclipse.dltk.javascript.typeinfo.model.Element;
import org.eclipse.dltk.javascript.typeinfo.model.Type;
import org.eclipse.dltk.javascript.ui.typeinfo.IElementLabelProvider;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.resource.ImageDescriptor;

public class ExampleElementLabelProvider implements IElementLabelProvider {

	public ImageDescriptor getImageDescriptor(Element element) {
		if (element instanceof Type) {
			Type type = (Type) element;
			if ("XML".equals(type.getName())) {
				return DLTKPluginImages.DESC_OBJS_EXTJAR;
			}
		}
		return null;
	}

	public String getLabel(Element element, Mode mode) {
		return null;
	}

}
