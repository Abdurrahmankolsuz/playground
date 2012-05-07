/*
Numeric - Graded IRC bot - Mahmut Bulut <mahmutbulut0@gmail.com>

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation.

*/
package numeric.uk.ac.port.dsg.numeric.plugins.regspeak;

import numeric.uk.ac.port.dsg.numeric.Brain;
import numeric.uk.ac.port.dsg.numeric.plugins.BrainInterface;
import numeric.org.jibble.pircbot.*;
import java.util.*;
import java.io.*;

public class NumericPlugin extends BrainInterface {

	private ArrayList triggers = new ArrayList();

	public NumericPlugin(Brain irc) {
		super(irc);
		try {
			BufferedReader in = new BufferedReader(new FileReader("data/regspeak.txt"));
			String strRaw;
			while ((strRaw = in.readLine()) != null) {
				String[] pr = strRaw.split("#");
				//System.out.println(pr[0]);
				triggers.add(pr);
			}
			in.close();
		} catch (IOException e) {
			System.out.println("Regspeak - Dagarcik acilamiyor");
			System.out.println(e.toString());
			System.exit(0);
		}
	}

	public int getImportance() {
		return 51;
	}

	private String triggerMatch(int t, String action, String sender) {

		String found = null;
		String response;
		String pre = "";

		if (action.matches("^.*Numeric.*\\?")) {
			pre = "q";
		} else if (t == brain.MA) {
			pre = "a";
		} else {
			pre = "p";
		}

		for (int i = 0; i < triggers.size(); i++) {
			String[] line = (String[]) triggers.get(i);
			String type = line[0];
			String trigger = line[1];
			response = line[2];
			if (type.equalsIgnoreCase(pre)) {
				if (action.toLowerCase().matches(trigger.toLowerCase())) {
					i = triggers.size();
					System.out.println("Tetikleyici eslesmesi: (" + trigger + ") eslesen string " + action);
					found = response;
					//System.out.println("before");
					if (response.matches("^.*SENDER.*$")) {
						//System.out.println("replace");
						found = response.replaceAll("SENDER", sender);
					}
				}
			}
		}
		return found;

	}

	public boolean doMessage(int type, String sender, String login, String hostname, String message) {
		if ((type == brain.MP) || (type > brain.MA)) {
			return false;
		}
		String response = triggerMatch(type, message, sender);
		if (response == null) {
			// no match
			return false;
		} else {
			brain.sendRatedAction(response);
			return true;
		}

	}

	public String getDescription() {
		return "Reg Speak plugin - simple regex pattern matching";
	}

	public String getName() {
		return "regspeak";
	}

}
