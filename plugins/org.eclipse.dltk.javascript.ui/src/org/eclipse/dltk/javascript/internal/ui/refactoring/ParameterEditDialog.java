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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.javascript.corext.refactoring.Checks;
import org.eclipse.dltk.internal.javascript.corext.refactoring.ParameterInfo;
import org.eclipse.dltk.internal.ui.dialogs.TextFieldNavigationHandler;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.javascript.core.JavaScriptPlugin;
import org.eclipse.dltk.ui.viewsupport.BasicElementLabels;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ParameterEditDialog extends StatusDialog {

	private final ParameterInfo fParameter;
	private final boolean fEditType;
	private final boolean fEditDefault;
	//private final StubTypeContext fContext;
	private Text fType;
	private Text fName;
	private Text fDefaultValue;

	/**
	 * @param parentShell
	 * @param parameter
	 * @param canEditType
	 * @param canEditDefault
	 * @param context
	 *            the <code>IPackageFragment</code> for type ContentAssist. Can
	 *            be <code>null</code> if <code>canEditType</code> is
	 *            <code>false</code>.
	 */
	public ParameterEditDialog(Shell parentShell, ParameterInfo parameter,
			boolean canEditType, boolean canEditDefault/*, StubTypeContext context*/) {
		super(parentShell);
		fParameter = parameter;
		fEditType = canEditType;
		fEditDefault = canEditDefault;
		//fContext = context;
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(RefactoringMessages.ParameterEditDialog_title);
	}

	protected Control createDialogArea(Composite parent) {
		Composite result = (Composite) super.createDialogArea(parent);
		GridLayout layout = (GridLayout) result.getLayout();
		layout.numColumns = 2;
		Label label;
		GridData gd;

		label = new Label(result, SWT.NONE);
		String newName = fParameter.getNewName();
		if (newName.length() == 0)
			label.setText(RefactoringMessages.ParameterEditDialog_message_new);
		else
			label.setText(Messages.format(
					RefactoringMessages.ParameterEditDialog_message,
					BasicElementLabels.getJavaElementName(newName)));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);

		if (fEditType) {
			label = new Label(result, SWT.NONE);
			label.setText(RefactoringMessages.ParameterEditDialog_type);
			fType = new Text(result, SWT.BORDER);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			fType.setLayoutData(gd);
			fType.setText(fParameter.getNewTypeName());
			fType.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					validate((Text) e.widget);
				}
			});
			TextFieldNavigationHandler.install(fType);
			/*JavaTypeCompletionProcessor processor = new JavaTypeCompletionProcessor(
					true, false);
			processor.setCompletionContext(fContext.getCuHandle(),
					fContext.getBeforeString(), fContext.getAfterString());
			ControlContentAssistHelper.createTextContentAssistant(fType,
					processor);*/
		}

		label = new Label(result, SWT.NONE);
		fName = new Text(result, SWT.BORDER);
		initializeDialogUnits(fName);
		label.setText(RefactoringMessages.ParameterEditDialog_name);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = convertWidthInCharsToPixels(45);
		fName.setLayoutData(gd);
		fName.setText(newName);
		fName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				validate((Text) e.widget);
			}
		});
		TextFieldNavigationHandler.install(fName);

		if (fEditDefault && fParameter.isAdded()) {
			label = new Label(result, SWT.NONE);
			label.setText(RefactoringMessages.ParameterEditDialog_defaultValue);
			fDefaultValue = new Text(result, SWT.BORDER);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			fDefaultValue.setLayoutData(gd);
			fDefaultValue.setText(fParameter.getDefaultValue());
			fDefaultValue.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					validate((Text) e.widget);
				}
			});
			TextFieldNavigationHandler.install(fDefaultValue);
		}
		applyDialogFont(result);
		return result;
	}

	protected void okPressed() {
		if (fType != null) {
			fParameter.setNewTypeName(fType.getText());
		}
		fParameter.setNewName(fName.getText());
		if (fDefaultValue != null) {
			fParameter.setDefaultValue(fDefaultValue.getText());
		}
		super.okPressed();
	}

	private void validate(Text first) {
		IStatus[] result = new IStatus[3];
		if (first == fType) {
			result[0] = validateType();
			result[1] = validateName();
			result[2] = validateDefaultValue();
		} else if (first == fName) {
			result[0] = validateName();
			result[1] = validateType();
			result[2] = validateDefaultValue();
		} else {
			result[0] = validateDefaultValue();
			result[1] = validateName();
			result[2] = validateType();
		}
		for (int i = 0; i < result.length; i++) {
			IStatus status = result[i];
			if (status != null && !status.isOK()) {
				updateStatus(status);
				return;
			}
		}
		updateStatus(Status.OK_STATUS);
	}

	private IStatus validateType() {
		if (fType == null)
			return Status.OK_STATUS;
		String type = fType.getText();
		if ("".equals(type))
			return Status.OK_STATUS;
		//RefactoringStatus status = TypeContextChecker.checkParameterTypeSyntax(
		//		type, fContext.getCuHandle().getJavaProject());
		RefactoringStatus status = Checks.validateIdentifier(type);
		if (status == null || status.isOK())
			return Status.OK_STATUS;
		if (status.hasError())
			return createErrorStatus(status.getEntryWithHighestSeverity()
					.getMessage());
		else
			return createWarningStatus(status.getEntryWithHighestSeverity()
					.getMessage());
	}

	private IStatus validateName() {
		if (fName == null)
			return null;
		String text = fName.getText();
		if (text.length() == 0)
			return createErrorStatus(RefactoringMessages.ParameterEditDialog_name_error);
		/*IStatus status = fContext != null ? JavaConventionsUtil
				.validateFieldName(text, fContext.getCuHandle()
						.getJavaProject()) : JavaConventions.validateFieldName(
				text, JavaCore.VERSION_1_3, JavaCore.VERSION_1_3);
		if (status.matches(IStatus.ERROR))
			return status;
		if (!Checks.startsWithLowerCase(text))
			return createWarningStatus(RefactoringCoreMessages.ExtractTempRefactoring_convention);
		return Status.OK_STATUS;*/
		RefactoringStatus status = Checks.validateIdentifier(text);
		if (status == null || status.isOK())
			return Status.OK_STATUS;
		if (status.hasError())
			return createErrorStatus(status.getEntryWithHighestSeverity()
					.getMessage());
		else
			return createWarningStatus(status.getEntryWithHighestSeverity()
					.getMessage());
	}

	private IStatus validateDefaultValue() {
		if (fDefaultValue == null)
			return null;
		String defaultValue = fDefaultValue.getText();
		if (defaultValue.length() == 0)
			return createErrorStatus(RefactoringMessages.ParameterEditDialog_defaultValue_error);
		// TODO ChangeSignatureProcessor? really?
		// if (ChangeSignatureProcessor.isValidExpression(defaultValue))
		return Status.OK_STATUS;
		/*String msg = Messages.format(
				RefactoringMessages.ParameterEditDialog_defaultValue_invalid,
				new String[] { defaultValue });
		return createErrorStatus(msg);*/
	}

	private Status createWarningStatus(String message) {
		return new Status(IStatus.WARNING, JavaScriptPlugin.PLUGIN_ID,
				IStatus.WARNING, message, null);
	}

	private Status createErrorStatus(String message) {
		return new Status(IStatus.ERROR, JavaScriptPlugin.PLUGIN_ID,
				IStatus.ERROR, message, null);
	}
}
