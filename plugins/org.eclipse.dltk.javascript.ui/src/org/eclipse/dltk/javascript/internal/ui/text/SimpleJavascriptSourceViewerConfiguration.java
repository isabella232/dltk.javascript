/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.text;

import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.ITextEditor;



/**
 * A simple {@linkplain org.eclipse.dltk.ui.text.JavascriptSourceViewerConfiguration Javascript source viewer configuration}.
 * <p>
 * This simple source viewer configuration basically provides syntax coloring
 * and disables all other features like code assist, quick outlines, hyperlinking, etc.
 * </p>
 * 
	 *
 */
public class SimpleJavascriptSourceViewerConfiguration extends JavascriptSourceViewerConfiguration {


	private boolean fConfigureFormatter;

	/**
	 * Creates a new Javascript source viewer configuration for viewers in the given editor
	 * using the given preference store, the color manager and the specified document partitioning.
	 *
	 * @param colorManager the color manager
	 * @param preferenceStore the preference store, can be read-only
	 * @param editor the editor in which the configured viewer(s) will reside, or <code>null</code> if none
	 * @param partitioning the document partitioning for this configuration, or <code>null</code> for the default partitioning
	 * @param configureFormatter <code>true</code> if a content formatter should be configured
	 */
	public SimpleJavascriptSourceViewerConfiguration(IColorManager colorManager, IPreferenceStore preferenceStore, ITextEditor editor, String partitioning, boolean configureFormatter) {
		super(colorManager, preferenceStore, editor, partitioning);
		fConfigureFormatter= configureFormatter;
	}
	
	/*
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getAutoEditStrategies(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
	 */
	public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType) {
		return null;
	}

	/*
	 * @see SourceViewerConfiguration#getAnnotationHover(ISourceViewer)
	 */
	public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
		return null;
	}

	/*
	 * @see SourceViewerConfiguration#getOverviewRulerAnnotationHover(ISourceViewer)
	 */
	public IAnnotationHover getOverviewRulerAnnotationHover(ISourceViewer sourceViewer) {
		return null;
	}

	/*
	 * @see SourceViewerConfiguration#getConfiguredTextHoverStateMasks(ISourceViewer, String)
	 */
	public int[] getConfiguredTextHoverStateMasks(ISourceViewer sourceViewer, String contentType) {
		return null;
	}

	/*
	 * @see SourceViewerConfiguration#getTextHover(ISourceViewer, String, int)
	 */
	public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType, int stateMask) {
		return null;
	}

	/*
	 * @see SourceViewerConfiguration#getTextHover(ISourceViewer, String)
	 */
	public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType) {
		return null;
	}

	/*
	 * @see SourceViewerConfiguration#getContentFormatter(ISourceViewer)
	 */
	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
		if (fConfigureFormatter)
			return super.getContentFormatter(sourceViewer);
		else
			return null;
	}

	/*
	 * @see SourceViewerConfiguration#getInformationControlCreator(ISourceViewer)
	 */
	public IInformationControlCreator getInformationControlCreator(ISourceViewer sourceViewer) {
		return null;
	}

	/*
	 * @see SourceViewerConfiguration#getInformationPresenter(ISourceViewer)
	 */
	public IInformationPresenter getInformationPresenter(ISourceViewer sourceViewer) {
		return null;
	}

	/*
	 * @see org.eclipse.dltk.ui.text.JavascriptSourceViewerConfiguration#getOutlinePresenter(org.eclipse.jface.text.source.ISourceViewer, boolean)
	 */
	public IInformationPresenter getOutlinePresenter(ISourceViewer sourceViewer, boolean doCodeResolve) {
		return null;
	}

	/*
	 * @see org.eclipse.dltk.ui.text.JavascriptSourceViewerConfiguration#getHierarchyPresenter(org.eclipse.jface.text.source.ISourceViewer, boolean)
	 */
	public IInformationPresenter getHierarchyPresenter(ISourceViewer sourceViewer, boolean doCodeResolve) {
		return null;
	}

	/*
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getHyperlinkDetectors(org.eclipse.jface.text.source.ISourceViewer)
	 */
	public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
		return null;
	}
}
