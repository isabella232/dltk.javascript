/**
 *
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

final class StdErrCommand extends DBGPDebugger.Command {
	/**
	 *
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	StdErrCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	@Override
	void parseAndExecute(String command, HashMap options) {
		this.debugger.printResponse("<response command=\"stderr\"\r\n"
				+ "          success=\"1\"\r\n" + "          transaction_id=\""
				+ options.get("-i") + "\">\r\n" + "</response>\r\n" + "");
	}
}