/**
 *
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

final class BreakCommand extends DBGPDebugger.Command {
	/**
	 *
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	BreakCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	@Override
	void parseAndExecute(String command, final HashMap options) {
		this.debugger.stackmanager.suspend();
		this.debugger.printResponse("<response command=\"break\"\r\n"
				+ "          success=\"1\"\r\n" + "          transaction_id=\""
				+ options.get("-i") + "\">\r\n" + "</response>\r\n" + "");

	}
}