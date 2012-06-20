package server.model.players;

import server.util.Misc;

public class DialogueHandler {

	private Client c;
	
	public DialogueHandler(Client client) {
		this.c = client;
	}
	
	/**
	 * Handles all talking
	 * @param dialogue The dialogue you want to use
	 * @param npcId The npc id that the chat will focus on during the chat
	 */
	public void sendDialogues(int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch(dialogue) {
		case 30:
			sendNpcChat4("Congratulations!","You have killed 20 monkeys hope you learned something..", "would you like to escape?","Do not break anymore rules!", c.talkingNpc, "Mosol Rei");
			c.dialogueAction = 26;
			c.nextChat = 31;
			break;
		case 31:
			sendOption2("Yes get me out of this hell hole!",  "Hell no! I love it here, I'm nuts for these monkeys!");
			c.dialogueAction = 27;
			c.nextChat = 0;	
			break;
		case 50:
			//sendOption2("Ardougne - Multi",  "Yanille - 1v1");
			//c.dialogueAction = 50;
			break;
		case 32:
			sendNpcChat4("You cannot Escape yet!","You've killed "+c.monkeyk0ed+" out of 20 monkeys!","Come back when you have killed 20","Kthxbai", c.talkingNpc, "Mosol Rei");
			c.dialogueAction = 30;
			c.nextChat = 0;
			break;
		case 0:
			c.talkingNpc = -1;
			c.getPA().removeAllWindows();
			c.nextChat = 0;
			break;
		case 20:
			sendOption4("Information", "Black Jack","Five", "Maybe later...");
			c.dialogueAction = 100;
			break;

		case 25:
			sendOption4("","Black Jack", "Five","");
			c.dialogueAction = 101;
			break;

		case 21:
			sendNpcChat4("The way we play this game is simple. The way you win is", 
					"You need to get a higher number than me and you win the", 
					"500,000 coins. You need to bet 250,000 coins per round.",
					"If you get over 22 you bust and you lose.", 
					c.talkingNpc, "~ Black Jack ~");
					c.nextChat = 22;
					break;

		case 22:
			sendNpcChat4("", 
					"If i get 22+ I bust and I lose. If you get 21 then you have black", 
					"jack and you win double of what you bet.",
					"", 
					c.talkingNpc, "~ Black Jack ~");
					c.nextChat = 0;
					break;

		case 23:
			sendNpcChat4("This is my own game which I made. It's pretty simple", 
					"and resembles poker but it's a lot different. The aim of this", 
					"game is to get the same number like the random number",
					"You got 2 numbers if both hit the same you win.", 
					c.talkingNpc, "~ Five ~");
					c.nextChat = 24;
					break;
		case 24:
			sendNpcChat4("", 
					"To play this game you need to bet 1,000,000 coins. You", 
					"can win a lot of good items but also lose a lot of cash.",
					"", 
					c.talkingNpc, "~ Five ~");
					c.nextChat = 0;
					break;
		case 1:
			sendStatement("You found a hidden tunnel! Do you want to enter it?");
			c.dialogueAction = 1;
			c.nextChat = 2;
			break;
		case 45:
			sendNpcChat2("Since you haven't shown me a defender to", "prove your prowess as a warrior.", 4289, "Kamfreena");
			c.nextChat = 46;
			break;
		case 46:
			sendNpcChat3("I'll release some Cyclopes which might drop bronze", "defenders for you to start off with, unless you show me", "another. Have fun in there.", 4289, "Kamfreena");
			c.nextChat = -1;
			break;
		case 47:
			sendNpcChat2("The cyclops will now drop:", "" + c.getWarriorsGuild().getCyclopsDrop126(c) + " defenders.", 4289, "Kamfreena");
			c.nextChat = -1;
			break;
		case 2:
			sendOption2("Yea! I'm fearless!",  "No way! That looks scary!");
			c.dialogueAction = 1;
			c.nextChat = 0;
			break;
		case 3:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
			"Would you like a slayer task?", c.talkingNpc, "Duradel");
			c.nextChat = 4;
		break;
		case 5:
			sendNpcChat4("Hello adventurer...", "My name is Kolodion, the master of this mage bank.", "Would you like to play a minigame in order ", 
						"to earn points towards recieving magic related prizes?", c.talkingNpc, "Kolodion");
			c.nextChat = 6;
		break;
		case 6:
			sendNpcChat4("The way the game works is as follows...", "You will be teleported to the wilderness,", 
			"You must kill mages to recieve points,","redeem points with the chamber guardian.", c.talkingNpc, "Kolodion");
			c.nextChat = 15;
		break;
		case 11:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
			"Would you like a slayer task?", c.talkingNpc, "Duradel");
			c.nextChat = 12;
		break;
		case 12:
			sendOption2("Yes I would like a slayer task.", "No I would not like a slayer task.");
			c.dialogueAction = 5;
		break;
		case 13:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I see I have already assigned you a task to complete.", 
			"Would you like me to give you an easier task?", c.talkingNpc, "Duradel");
			c.nextChat = 14;
		break;
		case 14:
			sendOption2("Yes I would like an easier task.", "No I would like to keep my task.");
			c.dialogueAction = 6;
		break;
		case 15:
			sendOption2("Yes I would like to play", "No, sounds too dangerous for me.");
			c.dialogueAction = 7;
		break;
		case 16:
			sendOption2("I would like to reset my barrows brothers.", "I would like to fix all my barrows");
			c.dialogueAction = 8;
		break;
		case 17:
			sendOption5("Air", "Mind", "Water", "Earth", "More");
			c.dialogueAction = 10;
			c.dialogueId = 17;
			c.teleAction = -1;
		break;
		case 18:
			sendOption5("Fire", "Body", "Cosmic", "Astral", "More");
			c.dialogueAction = 11;
			c.dialogueId = 18;
			c.teleAction = -1;
		break;
		case 19:
			sendOption5("Nature", "Law", "Death", "Blood", "More");
			c.dialogueAction = 12;
			c.dialogueId = 19;
			c.teleAction = -1;
		break;
		case 51:
			sendPlayerChat1("What are you doing here?");
			c.nextChat = 52;
			c.dialogueAction = 100;
		break;
		case 52:
			sendNpcChat4("I have sets of powerful gloves. You can", "unlock differnt pairs if you participate", "in my game. Are you interested?", 
			"", c.talkingNpc, "Gypsy");
			c.nextChat = 53;
		break;
		case 53:
			sendOption2("Yes, i'll play your game.", "No, thanks.");
			c.dialogueAction = 101;
		break;
		case 54:
			sendPlayerChat1("Yes, i'll play your game.");
			c.nextChat = 55;
		break;
		case 55://Action after case 4
			c.getPA().enterRFD();
			c.talkingNpc = -1;
			c.getPA().removeAllWindows();
			for(int p = 0; p < c.PRAYER.length; p++) { 
				c.prayerActive[p] = false;
				c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);	
			}
		break;
		case 56://No, thanks
			sendPlayerChat1("No, thanks.");
			c.nextChat = 0;
		break;
		case 57://Teleporter Dialogue
			sendNpcChat4("Where would you like to go?", "", "", "", c.talkingNpc, "Restless Ghost");
			c.nextChat = 58;
		break;
		case 58://Options
			c.getDH().sendOption5("Godwars", "Tormented Demons", "TzHaar Cave", "Barrows", "Corporeal Beast");
			c.teleAction = 3;
		break;
		case 59://PvP Options
			c.getDH().sendOption2("Varrock PvP", "Falador PvP");
			c.teleAction = 101;
		break;
		case 60://KBD GUIDE
			sendNpcChat4("Would you like for me to bring you to KBD?", "Please know that this is, in the wilderness!", "", "", c.talkingNpc, "Hobbes");
			c.nextChat = 61;
		break;
		case 61://KBD GUIDE OPTION
			c.getDH().sendOption2("Yes, of course!", "No, thanks.");
			c.teleAction = 102;
		break;
		}
	}
	
	/*
	 * Information Box
	 */
	
	public void sendStartInfo(String text, String text1, String text2, String text3, String title) {
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(text, 6181);
		c.getPA().sendFrame126(text1, 6182);
		c.getPA().sendFrame126(text2, 6183);
		c.getPA().sendFrame126(text3, 6184);
		c.getPA().sendFrame164(6179);
	}
	
	/*
	 * Options
	 */
	
	private void sendOption(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2470);
	 	c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126("Click here to continue", 2473);
		c.getPA().sendFrame164(13758);
	}	
	
	private void sendOption2(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	private void sendOption3(String s, String s1, String s2) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame126(s2, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	public void sendOption4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame126("Select an Option", 2481);
		c.getPA().sendFrame126(s, 2482);
		c.getPA().sendFrame126(s1, 2483);
		c.getPA().sendFrame126(s2, 2484);
		c.getPA().sendFrame126(s3, 2485);
		c.getPA().sendFrame164(2480);
	}
	
	public void sendOption5(String s, String s1, String s2, String s3, String s4) {
		c.getPA().sendFrame126("Select an Option", 2493);
		c.getPA().sendFrame126(s, 2494);
		c.getPA().sendFrame126(s1, 2495);
		c.getPA().sendFrame126(s2, 2496);
		c.getPA().sendFrame126(s3, 2497);
		c.getPA().sendFrame126(s4, 2498);
		c.getPA().sendFrame164(2492);
	}

	/*
	 * Statements
	 */
	
	private void sendStatement(String s) { // 1 line click here to continue chat box interface
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}
	
	/*
	 * Npc Chatting
	 */
	
	private void sendNpcChat1(String s) {
		
	}
	
	private void sendNpcChat4(String s, String s1, String s2, String s3, int ChatNpc, String name) {
		c.getPA().sendFrame200(4901, 580);
		c.getPA().sendFrame126(name, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4900);
	}
	
	/*
	 * Player Chating Back
	 */
	
	private void sendPlayerChat1(String s) {
		c.getPA().sendFrame200(969, 580);
		c.getPA().sendFrame126(Misc.optimizeText(c.playerName), 970);
		c.getPA().sendFrame126(s, 971);
		c.getPA().sendFrame185(969);
		c.getPA().sendFrame164(968);
	}
	
	private void sendPlayerChat2(String s, String s1) {
		c.getPA().sendFrame200(974, 580);
		c.getPA().sendFrame126(c.playerName, 975);
		c.getPA().sendFrame126(s, 976);
		c.getPA().sendFrame126(s1, 977);
		c.getPA().sendFrame185(974);
		c.getPA().sendFrame164(973);
	}

	public void sendNpcChat2(String s, String s1, int ChatNpc, String name) {
		c.getPA().sendFrame200(4888, 580);
		c.getPA().sendFrame126(name, 4889);
		c.getPA().sendFrame126(s, 4890);
		c.getPA().sendFrame126(s1, 4891);
		c.getPA().sendFrame75(ChatNpc, 4888);
		c.getPA().sendFrame164(4887);
	}
	
	public void sendNpcChat3(String s, String s1, String s2, int ChatNpc, String name) {
		c.getPA().sendFrame200(4894, 580);
		c.getPA().sendFrame126(name, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(ChatNpc, 4894);
		c.getPA().sendFrame164(4893);
	}
	
	private void sendPlayerChat3(String s, String s1, String s2) {
		c.getPA().sendFrame200(980, 580);
		c.getPA().sendFrame126(c.playerName, 981);
		c.getPA().sendFrame126(s, 982);
		c.getPA().sendFrame126(s1, 983);
		c.getPA().sendFrame126(s2, 984);
		c.getPA().sendFrame185(980);
		c.getPA().sendFrame164(979);
	}

	public void talk(int face, String line1, String line2, String line3, String line4, int npcID) {
		c.getPA().sendFrame200(4901, face);
		c.getPA().sendFrame126(c.getPA().GetNpcName(npcID).replaceAll("_", " "), 4902);
		c.getPA().sendFrame126(""+line1, 4903);
		c.getPA().sendFrame126(""+line2, 4904);
		c.getPA().sendFrame126(""+line3, 4905);
		c.getPA().sendFrame126(""+line4, 4906);
		c.getPA().sendFrame126("Click here to continue", 4907);
		c.getPA().sendFrame75(npcID, 4901);
		c.getPA().sendFrame164(4900);
	}
	
	private void sendPlayerChat4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame200(987, 580);
		c.getPA().sendFrame126(c.playerName, 988);
		c.getPA().sendFrame126(s, 989);
		c.getPA().sendFrame126(s1, 990);
		c.getPA().sendFrame126(s2, 991);
		c.getPA().sendFrame126(s3, 992);
		c.getPA().sendFrame185(987);
		c.getPA().sendFrame164(986);
	}
}
