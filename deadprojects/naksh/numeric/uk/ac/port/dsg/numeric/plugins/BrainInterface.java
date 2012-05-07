/*
Numeric - Graded IRC bot - Mahmut Bulut <mahmutbulut0@gmail.com>

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation.

*/
package numeric.uk.ac.port.dsg.numeric.plugins;

import numeric.uk.ac.port.dsg.numeric.Brain;
import numeric.org.jibble.pircbot.*;

public class BrainInterface {

	protected Brain brain;
	public BrainInterface(Brain irc) {
		brain = irc;
	}

	public boolean doMessage(int type, String sender, String login, String hostname, String message) {
      return false;
	}
	
	public String getDescription(){
		return "Bu pluginin ismi yok";
	}

	public String getName(){
		return "isimsiz";
	}	
	
	public int getImportance(){
		return 50;
	}
	
}