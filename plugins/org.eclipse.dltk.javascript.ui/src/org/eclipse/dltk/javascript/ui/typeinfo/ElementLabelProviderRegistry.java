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
package org.eclipse.dltk.javascript.ui.typeinfo;

import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;
import org.eclipse.dltk.javascript.typeinfo.model.Element;
import org.eclipse.dltk.javascript.ui.typeinfo.IElementLabelProvider.Mode;
import org.eclipse.dltk.utils.LazyExtensionManager;
import org.eclipse.jface.resource.ImageDescriptor;

public class ElementLabelProviderRegistry {

	private static final LazyExtensionManager<IElementLabelProvider> manager = new LazyExtensionManager<IElementLabelProvider>(
			JavaScriptUI.PLUGIN_ID + ".typeInfoLabelProvider");

	public static ImageDescriptor getImageDescriptor(Element element) {
		for (IElementLabelProvider provider : manager) {
			ImageDescriptor descriptor = provider.getImageDescriptor(element);
			if (descriptor != null) {
				return descriptor;
			}
		}
		return null;
	}

	public static String getLabel(Element element, Mode mode) {
		return getLabel(element, mode, null);
	}

	public static String getLabel(Element element, Mode mode, Object context) {
		for (IElementLabelProvider provider : manager) {
			final String label;
			if (provider instanceof IElementLabelProviderExtension) {
				label = ((IElementLabelProviderExtension) provider).getLabel(
						element, mode, context);
			} else {
				label = provider.getLabel(element, mode);
			}
			if (label != null) {
				return label;
			}
		}
		return null;
	}

}
