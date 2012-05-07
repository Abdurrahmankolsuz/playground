/*
Numeric - Graded IRC bot - Mahmut Bulut <mahmutbulut0@gmail.com>

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation.

*/
package numeric.uk.ac.port.dsg.numeric.plugins.roulette;

import numeric.uk.ac.port.dsg.numeric.Brain;
import numeric.uk.ac.port.dsg.numeric.plugins.BrainInterface;
import java.util.*;

public class NumericPlugin extends BrainInterface {

	private ArrayList triggers = new ArrayList();

	private int blanks = 5;

	public NumericPlugin(Brain irc) {
		super(irc);
	}

	public int getImportance() {
		return 50;
	}

	private void reLoad() {
		blanks = 5;
		//brain.sendAction("stops laughing and reloads");
	}

	public boolean doMessage(int type, String sender, String login, String hostname, String message) {
		if (message.toLowerCase().matches("^.*numeric.*rulet.*$")) {//^.*numeric.*russian rulet.*$
			Random r = new Random();
			int randInt = (Math.abs(r.nextInt()) % (blanks + 1)) + 1;
			//System.out.println(randInt);
			if (randInt == 1) {
				brain.kick(sender, "BUMMM!");
				reLoad();
			} else {
				brain.sendAction("tetik cekildi *tik*");
				blanks--;
			}
			return true;
		} else {
			return false;
		}

	}

	public String getDescription() {
		return "Russian Roulette plugin - rolled dice";
	}

	public String getName() {
		return "rulet";
	}

}
