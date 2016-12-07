/**
 *
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

final class StopCommand extends DBGPDebugger.Command {
	/**
	 *
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	StopCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	@Override
	void parseAndExecute(String command, HashMap options) {
		this.debugger.printResponse("<response command=\"run\"\r\n"
				+ "status=\"stopped\"" + " reason=\"ok\"" + " transaction_id=\""
				+ options.get("-i") + "\">\r\n" + "</response>\r\n" + "");
		System.exit(0);
	}
}