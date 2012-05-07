/*
Numeric - Graded IRC bot - Mahmut Bulut <mahmutbulut0@gmail.com>

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation.

*/

package numeric.uk.ac.port.dsg.numeric;

import numeric.uk.ac.port.dsg.numeric.plugins.BrainInterface;
import numeric.org.jibble.pircbot.*;
import java.util.*;

public class Brain extends Thread {

	private ParsBot bot;
	private final String CHANNEL;

	private ArrayList plugins;
	private ArrayList commands = new ArrayList();
	HashMap throttle = new HashMap();

	public static final int MB = 0;
	public static final int MP = 1;
	public static final int MA = 2;
	public static final int JOIN = 3;
	public static final int PART = 4;

	private int reloadBrainFlag = -1;

	public class BrainCommand {
		public final int type;
		public final String sender;
		public final String login;
		public final String hostname;
		public final String message;

		public BrainCommand(int type, String sender, String login, String hostname, String message) {
			this.type = type;
			this.sender = sender;
			this.login = login;
			this.hostname = hostname;
			this.message = message;
		}
	}

	public Brain(ParsBot parent, String chan) {
		bot = parent;
		CHANNEL = chan;
		reloadBrain();
		//sendPluginsToChannel();
	}

	public String getChannel() {
		return new String(CHANNEL);
	}

	public void run() {
		//Debug.out("Running socket server");
		try {
			while (true) {
				try {
					synchronized (commands) {

						if (commands.isEmpty()) {
							commands.wait();
						}
						if (commands.isEmpty()) {
							// do fuck all
						} else {
							BrainCommand cmd = (BrainCommand) commands.remove(0);
							processCommand(cmd);
						}

					}
				} catch (java.lang.InterruptedException e) {
					System.out.println(e.toString());
				}
			}
		} catch (Exception e) {
			//System.out.println("#Exception starting server handler: " + e);
		}

	}

	private void sendPluginsToChannel() {
		bot.printMessage(CHANNEL, "------------------");
		bot.printMessage(CHANNEL, "Listelenen pluginler:");
		for (int x = 0; x < plugins.size(); x++) {
			String pluginName = ((BrainInterface) plugins.get(x)).getDescription();
			bot.printMessage(CHANNEL, "* " + pluginName);
		}
		bot.printMessage(CHANNEL, "------------------");
	}

	private void reloadBrain() {
		plugins = null;
		System.gc();
		plugins = PluginManager.loadPlugins(this);
		reloadBrainFlag = 0;

	}

	private void processCommand(BrainCommand cmd) {

		//System.out.print("Handling message: ");
		//System.out.println(type);

		switch (reloadBrainFlag) {
			case 0 :
				// process message
				for (int x = 0; x < plugins.size(); x++) {
					boolean handled = ((BrainInterface) plugins.get(x)).doMessage(cmd.type, cmd.sender, cmd.login, cmd.hostname, cmd.message);
					if (handled) {
						System.out.println("Mesajin ustesinden gelindi: " + ((BrainInterface) plugins.get(x)).getName() + " (" + cmd.sender + ": '" + cmd.message + "')");
						x = plugins.size();
					}
				}
				if (reloadBrainFlag == 1) {
					reloadBrainFlag = -1;
					reloadBrain();
					bot.printAction(CHANNEL, "Numeric beynini yeniden yukledi");
					//sendPluginsToChannel();
				}
				break;
			case -1 :
				System.out.println(reloadBrainFlag);
				// brain is not loaded
				System.out.println("Beyin yuklenemedi");
				break;
		}
	}

	public void command(int type, String sender, String login, String hostname, String message) {
		BrainCommand c = new BrainCommand(type, sender, login, hostname, message);
		synchronized (commands) {
			commands.add(c);
			commands.notifyAll();
		}
		System.out.println(CHANNEL + " mesaj terminale insert edildi (" + message + ")");
	}

	public synchronized void requestBrainReload() {
		reloadBrainFlag = 1;
	}

	public void sendRatedAction(String action) {
		Long search = (Long) throttle.get(action);
		if (search == null) {
			//System.out.println("Hash: new");
			bot.printAction(CHANNEL, action);
			//it wasnt in there
		} else {
			long val = search.longValue();
			if ((System.currentTimeMillis() - val) < 8000) {
				System.out.print("Mesaj reytingi isaretlendi (");
				System.out.print((System.currentTimeMillis() - val) / 1000);
				System.out.println(")");
			} else {
				bot.printAction(CHANNEL, action);
				//System.out.println("Hash: sent");
			}

		}
		throttle.put(action, new Long(System.currentTimeMillis()));
	}

	public void sendAction(String action) {
		bot.printAction(CHANNEL, action);
	}

	public void sendPMAction(String nick, String action) {
		bot.printAction(nick, action);
	}

	public void sendMessage(String message) {
		bot.printMessage(CHANNEL, message);
	}

	private void kill(boolean banthem, String hostname, String login, String target, String reason) {
		//hostname = "192.168.0.100";
		if (hostname == null) {
			hostname = "";
			login = "";
			// we have to look up the hostname for this user
			User u = findUser(target);
			if (u == null || login == null) {
				// can't locate this user - they must have already left or something
				System.out.println("kill: kullanici bulunmuyor: " + target);
			} else {
				System.out.println("Kullanici bulundu " + target);
				hostname = u.getHostname();
				login = u.getLogin();
			}
		}

		if ((!hostname.equals("")) && banthem) {
			String ban;
			if (hostname.matches("[0-9]*\\.[0-9]*\\.[0-9]*\\.[0-9]*")) {
				// they have an ip address for a hostname
				ban = "*!" + login + "@" + hostname.replaceAll("\\.[0-9]*$", ".*");
			} else {
				ban = "*!" + login + "@*" + hostname.substring(hostname.indexOf("."));
			}
			//System.out.println(ban);
			if (banthem) {
				bot.ban(CHANNEL, ban);
				System.out.println("Banlandi : " + ban);
			}
		} else {
			if (banthem) {
				System.out.println("ban: Banlanamiyor hostadi bulunamadi: " + target + " (" + hostname + "," + login + ")");
			}
		}

		bot.kick(CHANNEL, target, reason);
	}

	public void banKick(String hostname, String login, String target, String reason) {
		kill(true, hostname, login, target, reason);
	}

	public void kick(String target, String reason) {
		kill(false, null, null, target, reason);
	}

	public User findUser(String name) {
		User[] userList = bot.getUsers(CHANNEL);
		for (int i = 0; i < userList.length; i++) {
			User u = userList[i];
			if (name.equalsIgnoreCase(u.getNick())) {
				return u;
			}
		}
		return null;
	}

	public boolean isOp(String name) {
		User u = findUser(name);
		if (u != null) {
			if (u.isOp()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean isWhite(String name) {
		HashMap names = new HashMap();
		names.put("ragrag", "");
		names.put("cubcub", "");
		names.put("tamtam", "");

		if (names.get(name) != null) {
			return true;

		} else {
			return false;
		}
	}

}
