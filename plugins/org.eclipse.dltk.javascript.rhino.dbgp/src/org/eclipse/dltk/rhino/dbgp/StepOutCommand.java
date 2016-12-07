/**
 *
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

final class StepOutCommand extends DBGPDebugger.Command {
	/**
	 *
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	StepOutCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	@Override
	void parseAndExecute(String command, HashMap options) {
		Object tid = options.get("-i");
		this.debugger.runTransctionId = (String) tid;
		this.debugger.stackmanager.stepOut();
	}
}