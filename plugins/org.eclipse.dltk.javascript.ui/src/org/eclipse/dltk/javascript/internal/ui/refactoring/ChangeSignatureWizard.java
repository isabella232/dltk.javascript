/*******************************************************************************
 * Copyright (c) 2000, 2009 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.refactoring;

import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.javascript.corext.refactoring.ParameterInfo;
import org.eclipse.dltk.internal.javascript.corext.refactoring.structure.ChangeSignatureProcessor;
import org.eclipse.dltk.internal.ui.dialogs.TextFieldNavigationHandler;
import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.javascript.internal.ui.text.JavascriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.PixelConverter;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.Document;
import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.ui.refactoring.RefactoringWizard;
import org.eclipse.ltk.ui.refactoring.UserInputWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ChangeSignatureWizard extends RefactoringWizard {

	private final ChangeSignatureProcessor fProcessor;

	public ChangeSignatureWizard(ChangeSignatureProcessor processor,
			Refactoring refactoring) {
		super(refactoring, DIALOG_BASED_USER_INTERFACE);
		fProcessor = processor;
		setDefaultPageTitle(RefactoringMessages.ChangeSignatureRefactoring_modify_Parameters);
	}

	protected void addUserInputPages() {
		addPage(new ChangeSignatureInputPage(fProcessor));
	}

	private static class ChangeSignatureInputPage extends UserInputWizardPage {

		public static final String PAGE_NAME = "ChangeSignatureInputPage"; //$NON-NLS-1$
		private ScriptSourceViewer fSignaturePreview;
		private Document fSignaturePreviewDocument;
		// private Button fLeaveDelegateCheckBox;
		// private Button fDeprecateDelegateCheckBox;

		private final ChangeSignatureProcessor fProcessor;

		public ChangeSignatureInputPage(ChangeSignatureProcessor processor) {
			super(PAGE_NAME);
			fProcessor = processor;
			setMessage(RefactoringMessages.ChangeSignatureInputPage_change);
			fSignaturePreviewDocument = new Document();
		}

		/*
		 * @see
		 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt
		 * .widgets.Composite)
		 */
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE);
			final GridLayout layout = new GridLayout();
			composite.setLayout(layout);
			initializeDialogUnits(composite);

			try {
				createHeadControls(composite);

				createParameterTableControl(composite);
				/*
				 * createParameterExceptionsFolder(composite);
				 * fLeaveDelegateCheckBox=
				 * DelegateUIHelper.generateLeaveDelegateCheckbox(composite,
				 * getRefactoring(), false); if (fLeaveDelegateCheckBox != null)
				 * { fDeprecateDelegateCheckBox= new Button(composite,
				 * SWT.CHECK); GridData data= new GridData();
				 * data.horizontalAlignment= GridData.FILL;
				 * data.horizontalIndent= (layout.marginWidth +
				 * fDeprecateDelegateCheckBox.computeSize(SWT.DEFAULT,
				 * SWT.DEFAULT).x); data.horizontalSpan= 2;
				 * fDeprecateDelegateCheckBox.setLayoutData(data);
				 * fDeprecateDelegateCheckBox
				 * .setText(DelegateUIHelper.getDeprecateDelegateCheckBoxTitle
				 * ()); final ChangeSignatureProcessor processor=
				 * getChangeMethodSignatureProcessor();
				 * fDeprecateDelegateCheckBox
				 * .setSelection(DelegateUIHelper.loadDeprecateDelegateSetting
				 * (processor));
				 * processor.setDeprecateDelegates(fDeprecateDelegateCheckBox
				 * .getSelection());
				 * fDeprecateDelegateCheckBox.addSelectionListener(new
				 * SelectionAdapter() { public void
				 * widgetSelected(SelectionEvent e) {
				 * processor.setDeprecateDelegates
				 * (fDeprecateDelegateCheckBox.getSelection()); } });
				 * fDeprecateDelegateCheckBox
				 * .setEnabled(fLeaveDelegateCheckBox.getSelection());
				 * fLeaveDelegateCheckBox.addSelectionListener(new
				 * SelectionAdapter() { public void
				 * widgetSelected(SelectionEvent e) {
				 * fDeprecateDelegateCheckBox.
				 * setEnabled(fLeaveDelegateCheckBox.getSelection()); } }); }
				 */
				Label sep = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
				sep.setLayoutData((new GridData(GridData.FILL_HORIZONTAL)));
				createSignaturePreview(composite);

				update(false);
				setControl(composite);
				Dialog.applyDialogFont(composite);
			} catch (ModelException e) {
				ExceptionHandler
						.handle(e,
								RefactoringMessages.ChangeSignatureInputPage_Change_Signature,
								RefactoringMessages.ChangeSignatureInputPage_Internal_Error);
			}
			// PlatformUI.getWorkbench().getHelpSystem().setHelp(composite,
			// IJavaHelpContextIds.MODIFY_PARAMETERS_WIZARD_PAGE);
		}

		private void createHeadControls(Composite parent) throws ModelException {
			// must create controls column-wise to get mnemonics working:
			Composite composite = new Composite(parent, SWT.NONE);
			composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			GridLayout layout = new GridLayout(3, false);
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			composite.setLayout(layout);

			// createAccessControl(composite);
			// TODO: add return type
			// createReturnTypeControl(composite);
			createNameControl(composite);
		}

		/*
		 * private void createAccessControl(Composite parent) throws
		 * JavaModelException { Composite access= new Composite(parent,
		 * SWT.NONE); GridLayout layout= new GridLayout(); layout.marginHeight=
		 * 0; layout.marginWidth= 0; access.setLayout(layout);
		 * 
		 * final int[] availableVisibilities=
		 * getChangeMethodSignatureProcessor().getAvailableVisibilities(); int
		 * currentVisibility=
		 * getChangeMethodSignatureProcessor().getVisibility();
		 * 
		 * Label label= new Label(access, SWT.NONE);
		 * label.setText(RefactoringMessages
		 * .ChangeSignatureInputPage_access_modifier);
		 * 
		 * final Combo combo= new Combo(access, SWT.DROP_DOWN | SWT.READ_ONLY);
		 * if (availableVisibilities.length == 0) { combo.setEnabled(false); }
		 * else { for (int i= 0; i < availableVisibilities.length; i++) {
		 * combo.add(getAccessModifierString(availableVisibilities[i])); }
		 * combo.addSelectionListener(new SelectionAdapter() { public void
		 * widgetSelected(SelectionEvent e) { int newVisibility=
		 * availableVisibilities[combo.getSelectionIndex()];
		 * getChangeMethodSignatureProcessor().setVisibility(newVisibility);
		 * update(true); } }); }
		 * combo.setText(getAccessModifierString(currentVisibility));
		 * combo.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		 * 
		 * // ensure that "Access modifier:" and "Return type:" Labels are not
		 * too close: Dialog.applyDialogFont(access); access.pack(); int
		 * minLabelWidth= label.getSize().x + 3 * layout.horizontalSpacing; if
		 * (minLabelWidth > combo.getSize().x) label.setLayoutData(new
		 * GridData(minLabelWidth, label.getSize().y)); }
		 * 
		 * private String getAccessModifierString(int modifier) { switch
		 * (modifier) { case Modifier.PUBLIC : return
		 * JdtFlags.VISIBILITY_STRING_PUBLIC; case Modifier.PROTECTED : return
		 * JdtFlags.VISIBILITY_STRING_PROTECTED; case Modifier.NONE : return
		 * RefactoringMessages.ChangeSignatureInputPage_default; case
		 * Modifier.PRIVATE : return JdtFlags.VISIBILITY_STRING_PRIVATE; default
		 * : throw new IllegalArgumentException("\"" + modifier +
		 * "\" is not a Modifier constant"); //$NON-NLS-1$ //$NON-NLS-2$ } }
		 * 
		 * private void createReturnTypeControl(Composite parent) { Composite
		 * returnType= new Composite(parent, SWT.NONE);
		 * returnType.setLayoutData(new GridData(GridData.FILL_BOTH));
		 * GridLayout layout= new GridLayout(1, false); layout.marginHeight= 0;
		 * layout.marginWidth= 0; returnType.setLayout(layout);
		 * 
		 * Label label= new Label(returnType, SWT.NONE);
		 * label.setText(RefactoringMessages
		 * .ChangeSignatureInputPage_return_type);
		 * 
		 * final Text text= new Text(returnType, SWT.BORDER);
		 * text.setText(getChangeMethodSignatureProcessor
		 * ().getReturnTypeString()); text.setLayoutData((new
		 * GridData(GridData.FILL_HORIZONTAL)));
		 * TextFieldNavigationHandler.install(text);
		 * 
		 * if (getChangeMethodSignatureProcessor().canChangeNameAndReturnType())
		 * { text.addModifyListener(new ModifyListener(){ public void
		 * modifyText(ModifyEvent e) {
		 * getChangeMethodSignatureProcessor().setNewReturnTypeName
		 * (text.getText()); update(true); } }); } else {
		 * text.setEnabled(false); }
		 * 
		 * JavaTypeCompletionProcessor processor= new
		 * JavaTypeCompletionProcessor(true, true); StubTypeContext
		 * stubTypeContext=
		 * getChangeMethodSignatureProcessor().getStubTypeContext();
		 * processor.setCompletionContext(stubTypeContext.getCuHandle(),
		 * stubTypeContext.getBeforeString(), stubTypeContext.getAfterString());
		 * ControlContentAssistHelper.createTextContentAssistant(text,
		 * processor); }
		 */

		private void createNameControl(Composite parent) {
			Composite name = new Composite(parent, SWT.NONE);
			name.setLayoutData(new GridData(GridData.FILL_BOTH));
			GridLayout layout = new GridLayout(1, false);
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			name.setLayout(layout);

			Label label = new Label(name, SWT.NONE);
			label.setText(RefactoringMessages.ChangeSignatureInputPage_method_name);

			final Text text = new Text(name, SWT.BORDER);
			text.setText(fProcessor.getMethodName());
			text.setLayoutData((new GridData(GridData.FILL_HORIZONTAL)));
			TextFieldNavigationHandler.install(text);

			if (fProcessor.canChangeNameAndReturnType()) {
				text.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent e) {
						fProcessor.setNewMethodName(text.getText());
						update(true);
					}
				});
			} else {
				text.setEnabled(false);
			}
		}

		/*
		 * private void createParameterExceptionsFolder(Composite composite) {
		 * TabFolder folder= new TabFolder(composite, SWT.TOP);
		 * folder.setLayoutData(new GridData(GridData.FILL_BOTH));
		 * 
		 * TabItem item= new TabItem(folder, SWT.NONE);
		 * item.setText(RefactoringMessages
		 * .ChangeSignatureInputPage_parameters);
		 * item.setControl(createParameterTableControl(folder));
		 * 
		 * TabItem itemEx= new TabItem(folder, SWT.NONE);
		 * itemEx.setText(RefactoringMessages
		 * .ChangeSignatureInputPage_exceptions);
		 * itemEx.setControl(createExceptionsTableControl(folder));
		 * 
		 * folder.addSelectionListener(new SelectionAdapter() { public void
		 * widgetSelected(SelectionEvent e) { ((TabItem)
		 * e.item).getControl().setFocus(); } }); }
		 */

		private Control createParameterTableControl(Composite composite) {
			Composite border = new Composite(composite, SWT.NONE);
			border.setLayoutData(new GridData(GridData.FILL_BOTH));
			border.setLayout(new GridLayout());

			String labelText = null; // no label
			ChangeParametersControl cp = new ChangeParametersControl(border,
					SWT.NONE, labelText, new IParameterListChangeListener() {
						public void parameterChanged(ParameterInfo parameter) {
							update(true);
						}

						public void parameterListChanged() {
							update(true);
						}

						public void parameterAdded(ParameterInfo parameter) {
							update(true);
						}
					}, ChangeParametersControl.Mode.CHANGE_METHOD_SIGNATURE/*
																			 * ,
																			 * fProcessor
																			 * .
																			 * getStubTypeContext
																			 * (
																			 * )
																			 */);
			cp.setLayoutData(new GridData(GridData.FILL_BOTH));
			cp.setInput(fProcessor.getParameterInfos());
			return border;
		}

		/*
		 * private Control createExceptionsTableControl(Composite parent) {
		 * Composite border= new Composite(parent, SWT.NONE);
		 * border.setLayout(new GridLayout());
		 * 
		 * ChangeExceptionsControl cp= new ChangeExceptionsControl(border,
		 * SWT.NONE, new IExceptionListChangeListener() { public void
		 * exceptionListChanged() { update(true); } },
		 * getChangeMethodSignatureProcessor().getMethod().getJavaProject());
		 * cp.setLayoutData(new GridData(GridData.FILL_BOTH));
		 * cp.setInput(getChangeMethodSignatureProcessor().getExceptionInfos());
		 * return border; }
		 * 
		 * public void dispose() {
		 * DelegateUIHelper.saveLeaveDelegateSetting(fLeaveDelegateCheckBox);
		 * DelegateUIHelper
		 * .saveDeprecateDelegateSetting(fDeprecateDelegateCheckBox);
		 * super.dispose(); }
		 */

		private void createSignaturePreview(Composite composite) {
			Label previewLabel = new Label(composite, SWT.NONE);
			previewLabel
					.setText(RefactoringMessages.ChangeSignatureInputPage_method_Signature_Preview);

			// //XXX: use ViewForm to draw a flat border. Beware of common
			// problems with wrapping layouts
			// //inside GridLayout. GridData must be constrained to force
			// wrapping. See bug 9866 et al.
			// ViewForm border= new ViewForm(composite, SWT.BORDER | SWT.FLAT);

			IDLTKUILanguageToolkit toolkit = DLTKUILanguageManager
					.getLanguageToolkit(JavaScriptNature.NATURE_ID);
			IPreferenceStore store = toolkit.getCombinedPreferenceStore();
			fSignaturePreview = new ScriptSourceViewer(composite, null, null,
					false, SWT.READ_ONLY | SWT.V_SCROLL | SWT.WRAP /*
																	 * |
																	 * SWT.BORDER
																	 */, store);
			fSignaturePreview
					.configure(new JavascriptSourceViewerConfiguration(toolkit
							.getTextTools().getColorManager(), store, null,
							null));
			fSignaturePreview.getTextWidget().setBackground(
					composite.getBackground());
			fSignaturePreview.setDocument(fSignaturePreviewDocument);
			fSignaturePreview.setEditable(false);

			// Layouting problems with wrapped text: see
			// https://bugs.eclipse.org/bugs/show_bug.cgi?id=9866
			Control signaturePreviewControl = fSignaturePreview.getControl();
			PixelConverter pixelConverter = new PixelConverter(
					signaturePreviewControl);
			GridData gdata = new GridData(GridData.FILL_BOTH);
			gdata.widthHint = pixelConverter.convertWidthInCharsToPixels(50);
			gdata.heightHint = pixelConverter.convertHeightInCharsToPixels(2);
			signaturePreviewControl.setLayoutData(gdata);

			// //XXX must force JavaSourceViewer text widget to wrap:
			// border.setContent(signaturePreviewControl);
			// GridData borderData= new GridData(GridData.FILL_BOTH);
			// borderData.widthHint= gdata.widthHint;
			// borderData.heightHint= gdata.heightHint;
			// border.setLayoutData(borderData);
		}

		private void update(boolean displayErrorMessage) {
			updateStatus(displayErrorMessage);
			updateSignaturePreview();
		}

		private void updateStatus(boolean displayErrorMessage) {
			try {
				if (fProcessor.isSignatureSameAsInitial()) {
					if (displayErrorMessage)
						setErrorMessage(RefactoringMessages.ChangeSignatureInputPage_unchanged);
					else
						setErrorMessage(null);
					setPageComplete(false);
					return;
				}
				RefactoringStatus nameCheck = fProcessor.checkSignature();
				if (displayErrorMessage) {
					setPageComplete(nameCheck);
				} else {
					setErrorMessage(null);
					setPageComplete(true);
				}
			} catch (ModelException e) {
				setErrorMessage(RefactoringMessages.ChangeSignatureInputPage_Internal_Error);
				setPageComplete(false);
				DLTKUIPlugin.log(e);
			}
		}

		private void updateSignaturePreview() {
			try {
				int top = fSignaturePreview.getTextWidget().getTopPixel();
				fSignaturePreviewDocument.set(fProcessor
						.getNewMethodSignature());
				fSignaturePreview.getTextWidget().setTopPixel(top);
			} catch (ModelException e) {
				ExceptionHandler
						.handle(e,
								RefactoringMessages.ChangeSignatureRefactoring_modify_Parameters,
								RefactoringMessages.ChangeSignatureInputPage_exception);
			}
		}
	}
}
