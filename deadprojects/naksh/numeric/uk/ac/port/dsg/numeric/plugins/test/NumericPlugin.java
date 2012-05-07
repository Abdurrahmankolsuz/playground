/*
Numeric - Graded IRC bot - Mahmut Bulut <mahmutbulut0@gmail.com>

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation.

*/
package numeric.uk.ac.port.dsg.numeric.plugins.test;

import numeric.uk.ac.port.dsg.numeric.Brain;
import numeric.uk.ac.port.dsg.numeric.plugins.BrainInterface;

public class NumericPlugin extends BrainInterface {

	public NumericPlugin(Brain irc) {
		super(irc);
	}

	public int getImportance() {
		return 101;
	}

	public boolean doMessage(int type, String sender, String login, String hostname, String message) {
		return false;
	}

	public String getDescription() {
		return "Test plugin - doesn't do anything";
	}

	public String getName() {
		return "test";
	}

}
