package org.eclipse.dltk.internal.javascript.corext.refactoring.rename;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.corext.refactoring.rename.RenameModelElementProcessor;
import org.eclipse.dltk.internal.javascript.corext.refactoring.Checks;
import org.eclipse.dltk.internal.javascript.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.javascript.core.JavaScriptLanguageToolkit;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;

public class RenameLocalVariableProcessor extends RenameModelElementProcessor {
	public static final String IDENTIFIER = "org.eclipse.dltk.javascript.renameLocalVariableProcessor"; //$NON-NLS-1$

	public RenameLocalVariableProcessor(IModelElement localVariable) {
		super(localVariable, JavaScriptLanguageToolkit.getDefault());
	}

	@Override
	public String getIdentifier() {
		return IDENTIFIER;
	}

	public RefactoringStatus checkNewElementName(String newName)
			throws CoreException {
		return Checks.validateIdentifier(newName);
	}

	@Override
	public boolean isApplicable() throws CoreException {
		return Checks.isAvailable(fModelElement);
	}

	@Override
	public String getProcessorName() {
		return RefactoringCoreMessages.RenameTempRefactoring_rename;
	}
}
