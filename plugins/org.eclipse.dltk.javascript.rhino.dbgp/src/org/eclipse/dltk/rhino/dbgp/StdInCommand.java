/**
 *
 */
package org.eclipse.dltk.rhino.dbgp;

import java.util.HashMap;

final class StdInCommand extends DBGPDebugger.Command {
	/**
	 *
	 */
	private final DBGPDebugger debugger;

	/**
	 * @param debugger
	 */
	StdInCommand(DBGPDebugger debugger) {
		this.debugger = debugger;
	}

	@Override
	void parseAndExecute(String command, HashMap options) {
		this.debugger.printResponse("<response command=\"stdin\"\r\n"
				+ "          success=\"1\"\r\n" + "          transaction_id=\""
				+ options.get("-i") + "\">\r\n" + "</response>\r\n" + "");
	}
}