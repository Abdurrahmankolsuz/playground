/*
Numeric - Graded IRC bot - Mahmut Bulut <mahmutbulut0@gmail.com>

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation.

*/
package numeric.uk.ac.port.dsg.numeric.plugins.zrandom;

import numeric.uk.ac.port.dsg.numeric.Brain;
import numeric.uk.ac.port.dsg.numeric.plugins.BrainInterface;
import numeric.org.jibble.pircbot.*;
import java.util.*;
import java.io.*;

public class NumericPlugin extends BrainInterface {

	private ArrayList messages = new ArrayList();
	private final static int ODDS = 100;
	private int countBetween = 0;

	public NumericPlugin(Brain irc) {
		super(irc);
		loadVocab();
	}

	public int getImportance() {
		return 100;
	}

	private void loadVocab() {
		System.out.println("Zrandom dagarciki yeniden yukluyor");
		messages = new ArrayList();
		try {
			BufferedReader in = new BufferedReader(new FileReader("data/random.txt"));
			String strRaw;
			while ((strRaw = in.readLine()) != null) {
				messages.add(strRaw);
			}
			in.close();
		} catch (IOException e) {
			System.out.println("Zrandom - dagarcik dosyasi yuklenemedi");
			System.out.println(e.toString());
			System.exit(0);
		}
	}

	public boolean doMessage(int type, String sender, String login, String hostname, String message) {
		Random r = new Random();
		int randInt = (Math.abs(r.nextInt()) % (ODDS)) + 1;
		if ((randInt != 1)) {
			// no match
			countBetween++;
			return false;
		} else {
			randInt = (Math.abs(r.nextInt()) % (messages.size()));
			String response = (String) messages.get(randInt);
			messages.remove(randInt);
			if (messages.size() == 0) {
				loadVocab();
			}
			if (response.matches("^.*SENDER.*$")) {
				//System.out.println("replace");
				response = response.replaceAll("SENDER", sender);
			}
			brain.sendRatedAction(response);
			return true;
		}

	}

	public String getDescription() {
		return "Z Random plugin - say strange cat things every now and then";
	}

	public String getName() {
		return "zrandom";
	}

}
