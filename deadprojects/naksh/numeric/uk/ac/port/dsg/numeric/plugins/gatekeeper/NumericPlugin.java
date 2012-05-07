/*
Numeric - Graded IRC bot - Mahmut Bulut <mahmutbulut0@gmail.com>

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation.

*/

package numeric.uk.ac.port.dsg.numeric.plugins.gatekeeper;

import numeric.uk.ac.port.dsg.numeric.Brain;
import numeric.uk.ac.port.dsg.numeric.plugins.BrainInterface;
import numeric.org.jibble.pircbot.*;
import java.util.*;
import java.io.*;

public class NumericPlugin extends BrainInterface {

	private class Bouncer {
		private long firstBounce;
		private int totalBounces;
		private String nick;

		private final int TRACKMINS = 10;
		private long trackTime;

		public Bouncer(String looser) {
			trackTime = TRACKMINS * 60 * 1000;

			nick = looser;
			firstBounce = System.currentTimeMillis();
			totalBounces++;
		}

		public int bounce() {
			if ((firstBounce + trackTime) > System.currentTimeMillis()) {
				totalBounces++;
				System.out.println("Extra ziplama");
			} else {
				totalBounces = 1;
				firstBounce = System.currentTimeMillis();
				System.out.println("Ziplama reseti");
			}

			return totalBounces;
		}

	}

	private HashMap joiners = new HashMap();
	private HashMap bouncers = new HashMap();

	public NumericPlugin(Brain irc) {
		super(irc);
	}

	public int getImportance() {
		return 20;
	}

	public boolean doMessage(int type, String sender, String login, String hostname, String message) {
		if (type == brain.JOIN) {
			joiners.put(sender, sender);
			if (bouncers.get(sender) != null) {
				// warn the bouncer
				brain.sendRatedAction("boyle ziplayan seyleri sevmem (otur yerine " + sender + ")");
			}
			return true;
		}
		if (type == brain.PART) {
			if (joiners.get(sender) != null) {
				// lamer alert!!!!!
				if (bouncers.get(sender) == null) {
					bouncers.put(sender, new Bouncer(sender));
					System.out.println("Yeni ziplayan " + sender);
				} else {
					// this is a repeat offender
					System.out.println("Surekli ziplayan " + sender);
					int strikes = ((Bouncer) bouncers.get(sender)).bounce();
					System.out.print("Sayilan ziplamalar = ");
					System.out.println(strikes);
					if (strikes == 3) {
						brain.banKick(hostname, login, sender, "seni yedi 10 dakikada 3 ziplama yaptigin icin (Baglantini duzelt!)");
						brain.sendPMAction(sender, "seni yedi 10 dakikada 3 ziplama yaptigin icin (Baglantini duzelt!)");
						brain.sendAction("ziplayan zimbirtiyi yedi.");
						bouncers.remove(sender);
					}
				}

				joiners.remove(sender);
			} else {
				bouncers.remove(sender);
				System.out.println("Yokmus gibi davrandik: " + sender);
			}
			return true;
		} else if (type != brain.MP) {
			if (joiners.get(sender) != null) {
				// first message from this user
				String killMsg = null;
				String warnMsg = "";
				if ((message.toLowerCase().matches("!list.*$"))) {
					killMsg = "Numeric seni yedi cok !komut girdigin icin";
					warnMsg = sender + " icin tisliyor " + " cok !komut kullandigin icin...";
				}
				if ((message.toLowerCase().matches("^.*tp:\\/.*$"))) {
					warnMsg = sender + " icin tisliyor " + " Link spamı yaptigi icin";
					killMsg = "Numeric seni yedi Link spami yaptigin icin (Bi daha görmiim)";
				}
				if ((message.toLowerCase().matches("^.*outwar.com.*$"))) {
					warnMsg = sender + " icin tisliyor " + " (Baris yandasiyiz savas degil)";
					killMsg = "Numeric seni yedi (Baris yandasiyiz savas degil)";

				}
				if ((message.toLowerCase().matches("^.*come.* #.*$")) || (message.toLowerCase().matches("^.*join.* #.*$"))) {
					warnMsg = sender + " icin tisliyor " + "#kanal spami yaptigi icin...";
					killMsg = "Numeric seni yedi (join #numeric-afiyet)";
				}

				if (killMsg != null) {
					// first thing they said was a link - kill them
					if (brain.isOp(sender) || (brain.isWhite(sender))) {
						// dont hurt ops
						brain.sendRatedAction(warnMsg);
					} else {
						brain.banKick(null, null, sender, killMsg);
					}
				}
				joiners.remove(sender);
			} else {
				return false;
			}
		}

		return false;
	}

	public String getDescription() {
		return "Gatekeeper plugin - stops lamers bouncing and join spamming";
	}

	public String getName() {
		return "kapıgorevlisi";
	}

}
