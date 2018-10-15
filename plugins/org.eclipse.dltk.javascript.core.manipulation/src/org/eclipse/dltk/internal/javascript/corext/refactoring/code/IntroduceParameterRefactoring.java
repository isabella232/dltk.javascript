/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Felix Pahl (fpahl@web.de) - contributed fix for:
 *       o introduce parameter throws NPE if there are compiler errors
 *         (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=48325)
 *******************************************************************************/

package org.eclipse.dltk.internal.javascript.corext.refactoring.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceRange;
import org.eclipse.dltk.internal.corext.refactoring.ScriptRefactoringDescriptor;
import org.eclipse.dltk.internal.corext.refactoring.base.ScriptStatusContext;
import org.eclipse.dltk.internal.corext.refactoring.changes.DynamicValidationRefactoringChange;
import org.eclipse.dltk.internal.javascript.core.manipulation.Messages;
import org.eclipse.dltk.internal.javascript.corext.refactoring.ParameterInfo;
import org.eclipse.dltk.internal.javascript.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.internal.javascript.corext.refactoring.structure.BodyUpdater;
import org.eclipse.dltk.internal.javascript.corext.refactoring.structure.ChangeSignatureProcessor;
import org.eclipse.dltk.javascript.core.JavaScriptPlugin;
import org.eclipse.dltk.javascript.core.dom.BinaryExpression;
import org.eclipse.dltk.javascript.core.dom.DomFactory;
import org.eclipse.dltk.javascript.core.dom.Expression;
import org.eclipse.dltk.javascript.core.dom.FunctionExpression;
import org.eclipse.dltk.javascript.core.dom.Identifier;
import org.eclipse.dltk.javascript.core.dom.Node;
import org.eclipse.dltk.javascript.core.dom.Source;
import org.eclipse.dltk.javascript.core.dom.VariableReference;
import org.eclipse.dltk.javascript.core.dom.rewrite.ASTConverter;
import org.eclipse.dltk.javascript.core.dom.rewrite.NodeFinder;
import org.eclipse.dltk.javascript.core.dom.rewrite.RefactoringUtils;
import org.eclipse.dltk.javascript.core.refactoring.descriptors.ChangeMethodSignatureDescriptor;
import org.eclipse.dltk.javascript.core.refactoring.descriptors.IntroduceParameterDescriptor;
import org.eclipse.dltk.javascript.parser.JavaScriptParserUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.ProcessorBasedRefactoring;

public class IntroduceParameterRefactoring extends Refactoring {

	private static final String ATTRIBUTE_ARGUMENT= "argument"; //$NON-NLS-1$

	//private static final String[] KNOWN_METHOD_NAME_PREFIXES= {"get", "is"}; //$NON-NLS-2$ //$NON-NLS-1$

	private ISourceModule fSourceCU;
	private Source root;
	private String source;
	private int fSelectionStart;
	private int fSelectionLength;

	private IMethod fMethod;
	private Refactoring fChangeSignatureRefactoring;
	private ChangeSignatureProcessor fChangeSignatureProcessor;
	private ParameterInfo fParameter;
	//private String fParameterName;
	//private ScriptRefactoringArguments fArguments;

	private Expression fSelectedExpression;
	//private String[] fExcludedParameterNames;

	/**
	 * Creates a new introduce parameter refactoring.
	 * @param unit the compilation unit, or <code>null</code> if invoked by scripting
	 * @param selectionStart start
	 * @param selectionLength length
	 */
	public IntroduceParameterRefactoring(ISourceModule unit, int selectionStart, int selectionLength) {
		Assert.isTrue(selectionStart >= 0);
		Assert.isTrue(selectionLength >= 0);
		fSourceCU= unit;
		fSelectionStart= selectionStart;
		fSelectionLength= selectionLength;
	}

    /*public IntroduceParameterRefactoring(ScriptRefactoringArguments arguments, RefactoringStatus status) {
   		this(null, 0, 0);
   		RefactoringStatus initializeStatus= initialize(arguments);
   		status.merge(initializeStatus);
    }

	// ------------------- IDelegateUpdating ----------------------

	public boolean canEnableDelegateUpdating() {
		return true;
	}

	public boolean getDelegateUpdating() {
		return (fChangeSignatureProcessor != null) ? fChangeSignatureProcessor.getDelegateUpdating() : false;
	}

	public void setDelegateUpdating(boolean updating) {
		if (fChangeSignatureProcessor != null)
			fChangeSignatureProcessor.setDelegateUpdating(updating);
	}

	public void setDeprecateDelegates(boolean deprecate) {
		if (fChangeSignatureProcessor != null)
			fChangeSignatureProcessor.setDeprecateDelegates(deprecate);
	}

	public boolean getDeprecateDelegates() {
		return (fChangeSignatureProcessor != null) ? fChangeSignatureProcessor.getDeprecateDelegates() : false;
	}*/

	// ------------------- /IDelegateUpdating ---------------------

	public String getName() {
		return RefactoringCoreMessages.IntroduceParameterRefactoring_name;
	}

	//--- checkActivation

	public RefactoringStatus checkInitialConditions(IProgressMonitor pm) throws CoreException {
		try {
			pm.beginTask("", 7); //$NON-NLS-1$

			if (! fSourceCU.isStructureKnown())
				return RefactoringStatus.createFatalErrorStatus(RefactoringCoreMessages.IntroduceParameterRefactoring_syntax_error);
			root = (Source)ASTConverter.convert(JavaScriptParserUtil.parse(fSourceCU));
			source = fSourceCU.getSource();
			initializeSelectedExpression();
			RefactoringStatus result= new RefactoringStatus();
			
			result.merge(checkSelection());
			if (result.hasFatalError())
				return result;
			
			pm.worked(1);

			/*if (fArguments != null) {
				// invoked by script
				fChangeSignatureProcessor= new ChangeSignatureProcessor(fArguments, result);
				if (!result.hasFatalError()) {
					fChangeSignatureRefactoring= new ProcessorBasedRefactoring(fChangeSignatureProcessor);
					fChangeSignatureRefactoring.setValidationContext(getValidationContext());
					result.merge(fChangeSignatureProcessor.checkInitialConditions(new SubProgressMonitor(pm, 2)));
					if (result.hasFatalError())
						return result;
				} else {
					pm.worked(2);
					return result;
				}
			} else {*/
				// first try:
				fChangeSignatureProcessor= new ChangeSignatureProcessor(fMethod);
				fChangeSignatureRefactoring= new ProcessorBasedRefactoring(fChangeSignatureProcessor);
				fChangeSignatureRefactoring.setValidationContext(getValidationContext());
				result.merge(fChangeSignatureProcessor.checkInitialConditions(new SubProgressMonitor(pm, 1)));
				/*if (result.hasFatalError()) {
					RefactoringStatusEntry entry= result.getEntryMatchingSeverity(RefactoringStatus.FATAL);
					if (entry.getCode() == RefactoringStatusCodes.OVERRIDES_ANOTHER_METHOD || entry.getCode() == RefactoringStatusCodes.METHOD_DECLARED_IN_INTERFACE) {
						// second try:
						IMethod method= (IMethod) entry.getData();
						fChangeSignatureProcessor= RefactoringAvailabilityTester.isChangeSignatureAvailable(method) ? new ChangeSignatureProcessor(method) : null;
						if (fChangeSignatureProcessor == null) {
							String msg= Messages.format(RefactoringCoreMessages.IntroduceParameterRefactoring_cannot_introduce, entry.getMessage());
							return RefactoringStatus.createFatalErrorStatus(msg);
						}
						result= fChangeSignatureProcessor.checkInitialConditions(new SubProgressMonitor(pm, 1));
						if (result.hasFatalError())
							return result;
					} else {
						return result;
					}
				} else {
					pm.worked(1);
				}*/
				if (result.hasFatalError())
					return result;
			//}

			//CompilationUnitRewrite cuRewrite= fChangeSignatureProcessor.getBaseCuRewrite();
			//if (! cuRewrite.getCu().equals(fSourceCU))
			//	cuRewrite= new CompilationUnitRewrite(fSourceCU); // TODO: should try to avoid throwing away this AST

			//initializeExcludedParameterNames();

			addParameterInfo();

			fChangeSignatureProcessor.setBodyUpdater(new BodyUpdater() {
				public void updateBody(FunctionExpression methodDeclaration) {
					replaceSelectedExpression(methodDeclaration);
				}
			});
			return result;
		} finally {
			pm.done();
			/*if (fChangeSignatureRefactoring != null)
				fChangeSignatureRefactoring.setValidationContext(null);*/
		}
	}

	private void addParameterInfo() throws ModelException {
		//ITypeBinding typeBinding= Bindings.normalizeForDeclarationUse(fSelectedExpression.resolveTypeBinding(), fSelectedExpression.getAST());
		//String typeName= cuRewrite.getImportRewrite().addImport(typeBinding);
		//String name= fParameterName != null ? fParameterName : guessedParameterName();
		String defaultValue= fSourceCU.getBuffer().getText(fSelectedExpression.getBegin(), fSelectedExpression.getEnd()-fSelectedExpression.getBegin());
		//fParameter= ParameterInfo.createInfoForAddedParameter(typeBinding, typeName, name, defaultValue);
		fParameter= ParameterInfo.createInfoForAddedParameter("","",defaultValue);
		//if (fArguments == null) {
		fChangeSignatureProcessor.getParameterInfos().add(fParameter);
		//}
	}

	private void replaceSelectedExpression(Node root) {
		// cannot use fSelectedExpression here, since it could be from another AST (if method was replaced by overridden):
		Expression expression=(Expression)NodeFinder.findNode(root, fSelectedExpression.getBegin(), fSelectedExpression.getEnd());
		VariableReference result = DomFactory.eINSTANCE.createVariableReference();
		Identifier id = DomFactory.eINSTANCE.createIdentifier();
		id.setName(fParameter.getNewName());
		result.setVariable(id);
		EReference ref = expression.eContainmentFeature();
		if (ref.isMany()) {
			EList<Expression> exprList = (EList<Expression>)expression.eContainer().eGet(ref);
			exprList.set(exprList.lastIndexOf(expression), result);
		} else {
			expression.eContainer().eSet(ref, result);
		}
	}

	private void initializeSelectedExpression() {
		Node selected = NodeFinder.findNode(root, fSelectionStart, fSelectionStart+fSelectionLength);
		if (!(selected instanceof Expression))
			return;
		for(int i=fSelectionStart;i<selected.getBegin();i++)
			if (!Character.isWhitespace(source.charAt(i))) return;
		for(int i=selected.getEnd();i<fSelectionStart+fSelectionLength;i++)
			if (!Character.isWhitespace(source.charAt(i))) return;
		fSelectedExpression=(Expression)selected;
	}

	private RefactoringStatus createFatalError(String message) {
		SourceRange range = new SourceRange(fSelectionStart,fSelectionLength);
		return RefactoringStatus.createFatalErrorStatus(message, ScriptStatusContext.create(fSourceCU, range));
	}

	private RefactoringStatus checkSelection() throws ModelException {
		if (fSelectedExpression == null) {
			return createFatalError(RefactoringCoreMessages.IntroduceParameterRefactoring_select);
		}
		Node node = NodeFinder.findEnclosingNode(fSelectedExpression);
		if (!(node instanceof FunctionExpression))
			return RefactoringStatus.createFatalErrorStatus(RefactoringCoreMessages.IntroduceParameterRefactoring_expression_in_method);
		IModelElement element = fSourceCU.getElementAt(fSelectionStart);
		if (element == null || !(element instanceof IMethod))
			return RefactoringStatus.createFatalErrorStatus(RefactoringCoreMessages.IntroduceParameterRefactoring_expression_in_method);
		fMethod = (IMethod)element;
		return checkExpression();

			//result.merge(checkExpressionBinding());
			//if (result.hasFatalError())
			//	return result;

//			if (isUsedInForInitializerOrUpdater(getSelectedExpression().getAssociatedExpression()))
//				return RefactoringStatus.createFatalErrorStatus(RefactoringCoreMessages.getString("ExtractTempRefactoring.for_initializer_updater")); //$NON-NLS-1$
//			pm.worked(1);
//
//			if (isReferringToLocalVariableFromFor(getSelectedExpression().getAssociatedExpression()))
//				return RefactoringStatus.createFatalErrorStatus(RefactoringCoreMessages.getString("ExtractTempRefactoring.refers_to_for_variable")); //$NON-NLS-1$
//			pm.worked(1);
	}

	private RefactoringStatus checkExpression() {
		//TODO: adjust error messages (or generalize for all refactorings on expression-selections?)
		Expression selectedExpression= fSelectedExpression;

		if (selectedExpression instanceof BinaryExpression
				&& selectedExpression.eContainer() instanceof Expression
				&& RefactoringUtils.isAssignment(((BinaryExpression)selectedExpression).getOperation()))
			return RefactoringStatus.createFatalErrorStatus(RefactoringCoreMessages.ExtractTempRefactoring_assignment);
		return null;
	}

	public List<ParameterInfo> getParameterInfos() {
		return fChangeSignatureProcessor.getParameterInfos();
	}

	public ParameterInfo getAddedParameterInfo() {
		return fParameter;
	}

	public String getMethodSignaturePreview() throws ModelException {
		return fChangeSignatureProcessor.getNewMethodSignature();
	}

//--- Input setting/validation

	/*public void setParameterName(String name) {
		Assert.isNotNull(name);
		fParameter.setNewName(name);
	}

	/**
	 * must only be called <i>after</i> checkActivation()
	 * @return guessed parameter name
	 *
	public String guessedParameterName() {
		String[] proposals= guessParameterNames();
		if (proposals.length == 0)
			return ""; //$NON-NLS-1$
		else
			return proposals[0];
	}

// --- TODO: copied from ExtractTempRefactoring - should extract ------------------------------

	/**
	 * Must only be called <i>after</i> checkActivation().
	 * The first proposal should be used as "best guess" (if it exists).
	 * @return proposed variable names (may be empty, but not null).
	 *
	public String[] guessParameterNames() {
		LinkedHashSet proposals= new LinkedHashSet(); //retain ordering, but prevent duplicates
		if (fSelectedExpression instanceof MethodInvocation){
			proposals.addAll(guessTempNamesFromMethodInvocation((MethodInvocation) fSelectedExpression, fExcludedParameterNames));
		}
		proposals.addAll(guessTempNamesFromExpression(fSelectedExpression, fExcludedParameterNames));
		return (String[]) proposals.toArray(new String[proposals.size()]);
	}

	private List/*<String>* guessTempNamesFromMethodInvocation(MethodInvocation selectedMethodInvocation, String[] excludedVariableNames) {
		String methodName= selectedMethodInvocation.getName().getIdentifier();
		for (int i= 0; i < KNOWN_METHOD_NAME_PREFIXES.length; i++) {
			String prefix= KNOWN_METHOD_NAME_PREFIXES[i];
			if (! methodName.startsWith(prefix))
				continue; //not this prefix
			if (methodName.length() == prefix.length())
				return Collections.EMPTY_LIST; // prefix alone -> don't take method name
			char firstAfterPrefix= methodName.charAt(prefix.length());
			if (! Character.isUpperCase(firstAfterPrefix))
				continue; //not uppercase after prefix
			//found matching prefix
			String proposal= Character.toLowerCase(firstAfterPrefix) + methodName.substring(prefix.length() + 1);
			methodName= proposal;
			break;
		}
		String[] proposals= StubUtility.getLocalNameSuggestions(fSourceCU.getJavaProject(), methodName, 0, excludedVariableNames);
		return Arrays.asList(proposals);
	}

	private List/*<String>* guessTempNamesFromExpression(Expression selectedExpression, String[] excluded) {
		ITypeBinding expressionBinding= Bindings.normalizeForDeclarationUse(
			selectedExpression.resolveTypeBinding(),
			selectedExpression.getAST());
		String typeName= getQualifiedName(expressionBinding);
		if (typeName.length() == 0)
			typeName= expressionBinding.getName();
		if (typeName.length() == 0)
			return Collections.EMPTY_LIST;
		int typeParamStart= typeName.indexOf("<"); //$NON-NLS-1$
		if (typeParamStart != -1)
			typeName= typeName.substring(0, typeParamStart);
		String[] proposals= StubUtility.getLocalNameSuggestions(fSourceCU.getJavaProject(), typeName, expressionBinding.getDimensions(), excluded);
		return Arrays.asList(proposals);
	}

// ----------------------------------------------------------------------

	private static String getQualifiedName(ITypeBinding typeBinding) {
		if (typeBinding.isAnonymous())
			return getQualifiedName(typeBinding.getSuperclass());
		if (! typeBinding.isArray())
			return typeBinding.getQualifiedName();
		else
			return typeBinding.getElementType().getQualifiedName();
	}

	private void initializeExcludedParameterNames() {
		Set<String> visible = VariableLookup.getVisibleNames(fSelectedExpression);
		fExcludedParameterNames = visible.toArray(new String[visible.size()]);
	}*/

	public RefactoringStatus validateInput() {
		return fChangeSignatureProcessor.checkSignature();
	}

//--- checkInput

	public RefactoringStatus checkFinalConditions(IProgressMonitor pm) throws CoreException {
		fChangeSignatureRefactoring.setValidationContext(getValidationContext());
		try {
			return fChangeSignatureRefactoring.checkFinalConditions(pm);
		} finally {
			fChangeSignatureRefactoring.setValidationContext(null);
		}
	}

	public Change createChange(IProgressMonitor pm) throws CoreException {
		fChangeSignatureRefactoring.setValidationContext(getValidationContext());
		try {
			Change[] changes= fChangeSignatureProcessor.getAllChanges();
			return new DynamicValidationRefactoringChange(getRefactoringDescriptor(), RefactoringCoreMessages.IntroduceParameterRefactoring_name, changes);
		} finally {
			fChangeSignatureRefactoring.setValidationContext(null);
			pm.done();
		}
	}

	private IntroduceParameterDescriptor getRefactoringDescriptor() {
		ChangeMethodSignatureDescriptor extended= (ChangeMethodSignatureDescriptor) fChangeSignatureProcessor.createDescriptor();
		//RefactoringContribution contribution= RefactoringCore.getRefactoringContribution(IScriptRefactorings.CHANGE_METHOD_SIGNATURE);

		//Map argumentsMap= contribution.retrieveArgumentMap(extended);

		final Map arguments= new HashMap();
		arguments.put(ATTRIBUTE_ARGUMENT, fParameter.getNewName());
		arguments.put(ScriptRefactoringDescriptor.ATTRIBUTE_SELECTION, new Integer(fSelectionStart).toString() + " " + new Integer(fSelectionLength).toString()); //$NON-NLS-1$
		//arguments.putAll(argumentsMap);
		String signature= fChangeSignatureProcessor.getMethodName();
		try {
			signature= fChangeSignatureProcessor.getOldMethodSignature();
		} catch (ModelException exception) {
			JavaScriptPlugin.error(exception);
		}
		final String description= Messages.format(RefactoringCoreMessages.IntroduceParameterRefactoring_descriptor_description_short, fChangeSignatureProcessor.getMethod().getElementName());
		//final String header= Messages.format(RefactoringCoreMessages.IntroduceParameterRefactoring_descriptor_description, new String[] { fParameter.getNewName(), signature, fSelectedExpression});
		//final JDTRefactoringDescriptorComment comment= new JDTRefactoringDescriptorComment(extended.getProject(), this, header);
		//comment.addSetting(Messages.format(RefactoringCoreMessages.IntroduceParameterRefactoring_original_pattern, JavaElementLabels.getTextLabel(fChangeSignatureProcessor.getMethod(),
		//		JavaElementLabels.ALL_FULLY_QUALIFIED)));
		//comment.addSetting(Messages.format(RefactoringCoreMessages.IntroduceParameterRefactoring_expression_pattern, BasicElementLabels.getJavaCodeString(ASTNodes.asString(fSelectedExpression))));
		//comment.addSetting(Messages.format(RefactoringCoreMessages.IntroduceParameterRefactoring_parameter_pattern, BasicElementLabels.getJavaElementName(getAddedParameterInfo().getNewName())));
		return new IntroduceParameterDescriptor(extended.getProject(), description, ""/*comment.asString()*/, arguments, extended.getFlags());
	}

	/*private RefactoringStatus initialize(JavaRefactoringArguments arguments) {
		fArguments= arguments;
		final String selection= arguments.getAttribute(JavaRefactoringDescriptorUtil.ATTRIBUTE_SELECTION);
		if (selection != null) {
			int offset= -1;
			int length= -1;
			final StringTokenizer tokenizer= new StringTokenizer(selection);
			if (tokenizer.hasMoreTokens())
				offset= Integer.valueOf(tokenizer.nextToken()).intValue();
			if (tokenizer.hasMoreTokens())
				length= Integer.valueOf(tokenizer.nextToken()).intValue();
			if (offset >= 0 && length >= 0) {
				fSelectionStart= offset;
				fSelectionLength= length;
			} else
				return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_illegal_argument, new Object[] { selection, JavaRefactoringDescriptorUtil.ATTRIBUTE_SELECTION}));
		} else
			return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, JavaRefactoringDescriptorUtil.ATTRIBUTE_SELECTION));
		final String handle= arguments.getAttribute(JavaRefactoringDescriptorUtil.ATTRIBUTE_INPUT);
		if (handle != null) {
			final IJavaElement element= JavaRefactoringDescriptorUtil.handleToElement(arguments.getProject(), handle, false);
			if (element == null || !element.exists() || element.getElementType() != IJavaElement.COMPILATION_UNIT)
				return JavaRefactoringDescriptorUtil.createInputFatalStatus(element, getName(), IJavaRefactorings.INTRODUCE_PARAMETER);
			else
				fSourceCU= ((IMethod) element).getCompilationUnit();
		} else
			return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, JavaRefactoringDescriptorUtil.ATTRIBUTE_INPUT));
		final String name= arguments.getAttribute(ATTRIBUTE_ARGUMENT);
		if (name != null && !"".equals(name)) //$NON-NLS-1$
			fParameterName= name;
		else
			return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, ATTRIBUTE_ARGUMENT));
		return new RefactoringStatus();
	}

	/**
	 * {@inheritDoc}
	 *
	public String getDelegateUpdatingTitle(boolean plural) {
		if (plural)
			return RefactoringCoreMessages.DelegateCreator_keep_original_changed_plural;
		else
			return RefactoringCoreMessages.DelegateCreator_keep_original_changed_singular;
	}*/
}
