/*
Numeric - Graded IRC bot - Mahmut Bulut <mahmutbulut0@gmail.com>

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation.

*/
package numeric.uk.ac.port.dsg.numeric.plugins.command;

import numeric.uk.ac.port.dsg.numeric.Brain;
import numeric.uk.ac.port.dsg.numeric.plugins.BrainInterface;
import numeric.org.jibble.pircbot.*;

public class NumericPlugin extends BrainInterface {

	public NumericPlugin(Brain irc) {
		super(irc);
	}

	public int getImportance() {
		return 49;
	}

	public boolean doMessage(int type, String sender, String login, String hostname, String message) {
		if (type != brain.MP) {
			//System.out.println("Command not PM");
			return false;
		} else {
			//System.out.println("Command sending brain reload");
			if (brain.isOp(sender) || brain.isWhite(sender)) {
				String[] pr = message.split(" ");
				message = message.toLowerCase();
				if (message.startsWith("reload")) {
					brain.requestBrainReload();
				} else {
					String target = pr[1];
					if (target.startsWith("#")) {
						System.out.println("Kanal komutu");
						if (target.equalsIgnoreCase(brain.getChannel())) {
							System.out.println("Bu kanal");
							target = pr[2];
						} else {
							System.out.println("Bu kanal degil");
						}
					}

					if (brain.findUser(target) != null) {
						if (message.startsWith("yala") && (pr.length >= 1)) {
							System.out.println("Yala");
							brain.sendRatedAction("yalanan " + target);
						} else if (message.startsWith("isir") && (pr.length >= 1)) {
							System.out.println("Isir");
							if (brain.isOp(target) || (brain.isWhite(sender) && brain.isWhite(target))) {
								brain.sendRatedAction(sender + " isirdi " + target + "i Numeric isirisiyla.");
							} else {
								brain.kick(target, "Numeric seni isirdi ( " + sender + ")");
							}
						} else if (message.startsWith("ye") && (pr.length >= 1)) {
							if (brain.isOp(target) || (brain.isWhite(sender) && brain.isWhite(target))) {
								brain.sendRatedAction(sender + "bildigin yedi" + target + "i Numeric isiriklariyla...");
							} else {
								brain.banKick(null, null, target, "Numeric seni yedi (" + sender + ")");
							}
						} else{
							System.out.println("Bilinmeyen komut");
						}
					} else {
						System.out.println("Kullanici yok");
					}
					
				}
				return true;
			} else {
				System.out.println("komut: kullaniciyi kickler");
				/**
												brain.banKick(hostname, login, sender, "Numeric seni sevmiyor");
												return true;
												**/
				return true;
			}
		}

	}

	public String getDescription() {
		return "Command plugin - handles orders controlling Numerics brain";
	}

	public String getName() {
		return "komut";
	}

}
