/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Jens Lukowski/Innoopract - initial renaming/restructuring
 *     
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.text;


/**
 * ISSUE: need to provide functionality in improved API. 
 */

public class TagMarker {

	// a ITextRegion (meant to be updated with the model) marking the position
	// where this tagname becomes effective
	protected ITextRegion fMarker = null;

	// the tagname
	protected String fTagName = null;

	/**
	 *  
	 */
	public TagMarker() {
		super();
	}

	public TagMarker(String tagname) {
		super();
		setTagName(tagname);
	}

	public TagMarker(String tagname, ITextRegion marker) {
		super();
		setTagName(tagname);
		setMarker(marker);
	}

	public final ITextRegion getMarker() {
		return fMarker;
	}

	/**
	 * @return java.lang.String
	 */
	public final String getTagName() {
		return fTagName;
	}

	/**
	 * @return boolean
	 */
	public boolean isGlobal() {
		return fMarker == null;
	}

	/**
	 * @param newMarker
	 */
	public final void setMarker(ITextRegion newMarker) {
		fMarker = newMarker;
	}

	/**
	 * @param newTagname
	 *            java.lang.String
	 */
	public final void setTagName(String newTagName) {
		fTagName = newTagName;
	}
}
