/*
Numeric - Graded IRC bot - Mahmut Bulut <mahmutbulut0@gmail.com>

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation.

*/

package numeric.uk.ac.port.dsg.numeric;

import numeric.org.jibble.pircbot.*;
import java.util.*;

public class ParsBot extends PircBot {

	private ParsBot bot;
	private HashMap Brains = new HashMap();

	private static void crapWait(int secs) {
		long wait = secs * 1000;
		long start = System.currentTimeMillis();
		long now = 0;
		while (now < (start + wait)) {
			now = System.currentTimeMillis();
		}
	}

	public ParsBot() {
		bot = this;

		bot.setLogin("numeric");
		bot.setName("numeric");
		// Enable debugging output.
		//bot.setVerbose(true);

		// Connect to the IRC server.

		try {
			try {
				bot.connect("irc.freenode.org");
			} catch (NickAlreadyInUseException e) {
				bot.setName("numeric");
				bot.connect("irc.freenode.org");
			}
		} catch (Exception fe) {
			System.out.println("Baglanamiyor - Olu");
			System.out.println(fe);
			fe.printStackTrace();
			System.exit(0);
		}

	}

	public void printMessage(String target, String action) {
		sendMessage(target, action);
	}

	public void printAction(String target, String action) {
		sendAction(target, action);
	}

	public void onFinger(String sourceNick, String sourceLogin, String sourceHostname, String target) {
		//sendAction(channel, "bites " + sourceNick + "s finger off");
	}

	public void onPing(String sourceNick, String sourceLogin, String sourceHostname, String target, String pingValue) {
		//sendAction(channel, "sits on the ICMP packet from " + sourceNick);
	}

	public void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
		if (recipientNick.equalsIgnoreCase(getNick())) {
			joinChannel(channel);
		} else {
			sendAction(channel, "tisliyor " + recipientNick);
		}
	}

	public void onConnect() {
		bot.setMessageDelay(10);
		if (bot.getName().equalsIgnoreCase("numeric")) {
			sendMessage("nickserv", "regain numeric ??????");
		} else {
			sendMessage("nickserv", "identify ??????");
		}

                /*
                 * Brains must be here if you want to replace channels;
                 * Edit this Brains or more with custom channels.
                 */
                addBrain("#pardus");
                addBrain("#ozgurlukicin");
		addBrain("#numeric");
		addBrain("#ogu");
		

	}

	private void addBrain(String chan) {
		bot.joinChannel(chan);
		Brain w = new Brain(bot, chan);
		Brains.put(chan, w);
		w.start();
	}

	private Brain findBrain(String channel) {
		return (Brain) Brains.get(channel);
	}

	public void onUserList(String channel, User[] users) {
		//System.out.println("onUserList");
	}

	public void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
		Brain numeric = findBrain(channel);
		if (!numeric.isOp(sourceNick)) {
			System.out.println(sourceNick + " ten davet var " + " (" + channel + ") kanalina");
			numeric.banKick(sourceHostname, sourceLogin, sourceNick, "numeric davet edilemez(Aristokratizm)!");
		} else {
			System.out.println(sourceNick + " ten " + " (" + channel + ") kanalina davet geldi");
		}
	}

	public void onOp(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
		if (!recipient.equalsIgnoreCase(bot.getNick())) {
			sendAction(channel, "aklini aliyor " + recipient);
		} else {
			sendAction(channel, "aklini aliyor " + sourceNick + " kullanicisinin ve ona sinirleniyor");
		}
	}

	public void onVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
		if (!recipient.equalsIgnoreCase(getNick())) {
			sendAction(channel, "Miyavladi " + recipient);
		}
	}

	public void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
		if (recipient.equalsIgnoreCase(bot.getNick())) {
			sendMessage("chanserv", "op " + channel + " numeric");
		}
	}

	public void onServerResponse(int code, String response) {
		System.out.println(code);
		//System.out.println(" onServerResponse: " + response);
	}

	public void onNickChange(String oldNick, String login, String hostname, String newNick) {

	}

	private void brainBroadcast(int type, String sender, String login, String hostname, String message) {
		Collection c = Brains.values();
		for (Iterator it = c.iterator(); it.hasNext();) {
			Brain b = (Brain) it.next();
			System.out.println(b.getChannel() + " a yayin yapiliyor.");
			b.command(type, sender, login, hostname, message);
		}
	}

	public void onPrivateMessage(String sender, String login, String hostname, String message) { // private message is probably spam
		brainBroadcast(Brain.MP, sender, login, hostname, message);
	}

	public void onAction(String sender, String login, String hostname, String target, String action) {
		Brain numeric = findBrain(target);
		if (numeric != null) {
			numeric.command(Brain.MA, sender, login, hostname, action);
		} else {
			System.out.println("Komut kullanici uzerinde etkili oldu (!=kanal)");
		}
	}

	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		//System.out.println("Finding " + channel + " brain");
		Brain numeric = findBrain(channel);
		numeric.command(Brain.MB, sender, login, hostname, message);
	}

	public void onPart(String channel, String sender, String login, String hostname) {
		Brain numeric = findBrain(channel);
		if (sender.equalsIgnoreCase(bot.getNick())) {
			//
		} else {
			numeric.command(Brain.PART, sender, login, hostname, "");
		}
	}

	public void onJoin(String channel, String sender, String login, String hostname) {
		Brain numeric = findBrain(channel);
		if (sender.equalsIgnoreCase(bot.getNick())) {
			sendMessage("chanserv", "op " + channel + " numeric");
		} else {
			numeric.command(Brain.JOIN, sender, login, hostname, "");
		}
	}

}
