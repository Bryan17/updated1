package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;
import server.util.Misc;


import java.io.*;

/**
 * Commands
 **/
public class Commands implements PacketType 
{

    
    @Override
    public void processPacket(Client c, int packetType, int packetSize) 
    {
    String playerCommand = c.getInStream().readString();
		if (!playerCommand.startsWith("/"))
		{
			c.getPA().writeCommandLog(playerCommand);
		}
		if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
			if (c.clanId >= 0) {
				System.out.println(playerCommand);
				playerCommand = playerCommand.substring(1);
				Server.clanChat.playerMessageToClan(c.playerId, playerCommand, c.clanId);
			} else {
				if (c.clanId != -1)
				c.clanId = -1;
				c.sendMessage("You are not in a clan.");
			}
			return;       
		}
    if (Config.SERVER_DEBUG)
        Misc.println(c.playerName+" playerCommand: "+playerCommand);
    
    if (c.playerRights >= 0)
        playerCommands(c, playerCommand);
    if (c.playerRights == 1 || c.playerRights == 2 || c.playerRights == 3) 
        moderatorCommands(c, playerCommand);
    if (c.playerRights == 2 || c.playerRights == 3) 
        administratorCommands(c, playerCommand);
    if (c.playerRights == 3) 
        ownerCommands(c, playerCommand);
        if (c.playerRights == 4) 
        DonatorCommands(c, playerCommand);
    
    }


    
    public void playerCommands(Client c, String playerCommand)
    {
			if (playerCommand.startsWith("yell") && c.isDonator == 0) {
				c.sendMessage("Only donators can use this command!");
			}
			if (playerCommand.equalsIgnoreCase("pvp")) {
				c.getPA().startTeleport(3182, 3441, 0, "modern");
			}
			
		    	if (playerCommand.equalsIgnoreCase("players")) {
			c.sendMessage("There are currently "+PlayerHandler.getPlayerCount()+ " players online.");
		    	}
			if (playerCommand.startsWith("changepassword") && playerCommand.length() > 15) {
				c.playerPass = playerCommand.substring(15);
				c.sendMessage("Your password is now: " + c.playerPass);			
			}

			if (playerCommand.startsWith("smsk")) {
				c.startAnimation(8525);
				c.gfx0(1515);
			}

			if (playerCommand.startsWith("sit") && c.sit == false) {
			if(c.inWild()) {
			c.sendMessage("You cannot do this in wildy");
			return;
			}
			c.sit = true;
			if(c.playerRights == 1) {
			c.startAnimation(4113);
			}
			if(c.playerRights == 2 || c.playerRights == 3) {
			c.startAnimation(4117);
			}
			if(c.isDonator == 0) {
			c.startAnimation(4115);
			}
			if(c.playerRights == 4) {
			c.startAnimation(4116);
			}
			}

			if (playerCommand.startsWith("unsit") && c.sit == true) {
			if(c.inWild()) {
			c.sendMessage("You cannot do this in wildy");
			return;
			}
			c.sit = false;
			c.startAnimation(4191);
			}

			if (playerCommand.startsWith("qpsk")) {
				c.startAnimation(4945);
				c.gfx0(816);
			}
			if (playerCommand.startsWith("ep") || playerCommand.startsWith("Ep") || playerCommand.startsWith("EP") || playerCommand.startsWith("eP")) {
			c.sendMessage("EP: "+ c.earningPotential+"");
			}

			if (playerCommand.startsWith("yell")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
							if (c.playerRights == 0){
								c.sendMessage("You must be a donator to use this command!");
							}
							if (c.playerRights == 1){
							
								c2.sendMessage("<col=255>[Mod]</col><img=1>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 2){
							
								c2.sendMessage("<col=255>[Admin]</col><img=2>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 3){
								c2.sendMessage("<shad=15695415>[Owner]</col><img=2>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 4){
								c2.sendMessage("<shad=6081134>[Donator]</col><img=0>"+ Misc.optimizeText(c.playerName) +": "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
						}
						}
					}
				}
        
        
    }
    
    public void moderatorCommands(Client c, String playerCommand)
    {
			if(playerCommand.startsWith("jail")) {
				try {
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					c2.teleportToX = 3102;
					c2.teleportToY = 9516;
					c2.Jail = true;
					c2.sendMessage("You have been jailed by "+c.playerName+"");
					c.sendMessage("Successfully Jailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}	
			if (playerCommand.startsWith("mute")) {
				try {	
					String playerToBan = playerCommand.substring(5);
					Connection.addNameToMuteList(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								c2.sendMessage(" " +c2.playerName+ " Got Muted By " + c.playerName+ ".");
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}		
			if (playerCommand.startsWith("unmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");

				}			
			}
				if (playerCommand.startsWith("checkbank")) {
				String[] args = playerCommand.split(" ");
				for(int i = 0; i < Config.MAX_PLAYERS; i++)
				{
					Client o = (Client) Server.playerHandler.players[i];
					if(Server.playerHandler.players[i] != null)
					{
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1]))
						{
                 						c.getPA().otherBank(c, o);
						break;
						}
					}
				}
			}
			if (playerCommand.startsWith("kick") && playerCommand.charAt(4) == ' ') {
				try {	
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				}
			if(playerCommand.startsWith("unjail")) {
				try {
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					c2.teleportToX = 3086;
                        		c2.teleportToY = 3493;
					c2.monkeyk0ed = 0;
					c2.Jail = false;
					c2.sendMessage("You have been unjailed by "+c.playerName+".");
					c.sendMessage("Successfully unjailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
        
    }
    
    public void administratorCommands(Client c, String playerCommand)
    {

			if (playerCommand.startsWith("ipmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP Muted the user: "+Server.playerHandler.players[i].playerName);
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								c2.sendMessage(" " +c2.playerName+ " Got IpMuted By " + c.playerName+ ".");
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}	
				}	

			if (playerCommand.equalsIgnoreCase("master")) {
				for (int i = 0; i < 21; i++) {
					c.playerLevel[i] = 99;
					c.playerXP[i] = c.getPA().getXPForLevel(100);
					c.getPA().refreshSkill(i);	
				}
				c.getPA().requestUpdates();
			}

			if (playerCommand.startsWith("object")) {
				String[] args = playerCommand.split(" ");				
				c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
			}
			
			if (playerCommand.equalsIgnoreCase("mypos")) {
				c.sendMessage("X: "+c.absX+" Y: "+c.absY+" H: "+c.heightLevel);
			}

			if (playerCommand.startsWith("interface")) {
				String[] args = playerCommand.split(" ");
				c.getPA().showInterface(Integer.parseInt(args[1]));
			}

			if (playerCommand.startsWith("gfx")) {
				String[] args = playerCommand.split(" ");
				c.gfx0(Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("tele")) {
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
				else if (arg.length == 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),c.heightLevel);
			}
			if (playerCommand.startsWith("alltome")) {
				try {
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					String name = playerCommand.substring(10);
					if (Server.playerHandler.players[i] != null) {
						Client other = (Client) Server.playerHandler.players[i];
						other.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel);
						} 
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}		
			if (playerCommand.startsWith("xteletome")) {
				try {	
					String playerToTele = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToTele)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been teleported to " + c.playerName);
								c2.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}		

			if (playerCommand.startsWith("ban") && playerCommand.charAt(3) == ' ') {
				try {	
					String playerToBan = playerCommand.substring(4);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
						Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage(" " +c2.playerName+ " Got Banned By " + c.playerName+ ".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				}
			if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
						}
					}
				}			
			}
			if (playerCommand.equalsIgnoreCase("bank")) {
				c.getPA().openUpBank();
			}
			if (playerCommand.startsWith("unipmute")) {
				try {	
					String playerToBan = playerCommand.substring(9);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have Un Ip-Muted the user: "+Server.playerHandler.players[i].playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
						}			
					}
			if (playerCommand.startsWith("ipban")) {
				try {
					String playerToBan = playerCommand.substring(6);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
								Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP banned the user: "+Server.playerHandler.players[i].playerName+" with the host: "+Server.playerHandler.players[i].connectedFrom);
						Client c2 = (Client)Server.playerHandler.players[i];
								Server.playerHandler.players[i].disconnected = true;
								c2.sendMessage(" " +c2.playerName+ " Got IpBanned By " + c.playerName+ ".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("unban")) {
				try {	
					String playerToBan = playerCommand.substring(6);
					Connection.removeNameFromBanList(playerToBan);
					c.sendMessage(playerToBan + " has been unbanned.");
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
        
    }
    
    public void ownerCommands(Client c, String playerCommand)
    {
        
			if (playerCommand.startsWith("update")) {
				String[] args = playerCommand.split(" ");
				int a = Integer.parseInt(args[1]);
				PlayerHandler.updateSeconds = a;
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}
			if (playerCommand.startsWith("empty")) {
        		c.getItems().removeAllItems();
        		c.sendMessage("You empty your inventory");
			}
			if(playerCommand.startsWith("npc")) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(4));
					if(newNPC > 0) {
						Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
						c.sendMessage("You spawn a Npc.");
					} else {
						c.sendMessage("No such NPC.");
					}
				} catch(Exception e) {
					
				}			
			}

			if (playerCommand.startsWith("anim")) {
				String[] args = playerCommand.split(" ");
				c.startAnimation(Integer.parseInt(args[1]));
				c.getPA().requestUpdates();
			}

			if (playerCommand.startsWith("spec")) {
				c.specAmount = 500.0;
			}

			if (playerCommand.startsWith("giveadmin")) {
				try {	
					String playerToAdmin = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToAdmin)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been given admin status by " + c.playerName);
								c2.playerRights = 2;
								c2.logout();
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

			if (playerCommand.startsWith("givemod")) {
				try {	
					String playerToMod = playerCommand.substring(8);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been given mod status by " + c.playerName);
								c2.playerRights = 1;
								c2.logout();
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

            if (playerCommand.startsWith("pnpc"))
                {
                try {
                    int newNPC = Integer.parseInt(playerCommand.substring(5));
                    if (newNPC <= 200000 && newNPC >= 0) {
                        c.npcId2 = newNPC;
                        c.isNpc = true;
                        c.updateRequired = true;
                        c.setAppearanceUpdateRequired(true);
                    } 
                    else {
                        c.sendMessage("No such P-NPC.");
                    }
                } catch(Exception e) {
                    c.sendMessage("Wrong Syntax! Use as ::pnpc #");
                }
            }

			
				if (playerCommand.startsWith("givedonor")) {
				try {	
					String playerToMod = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been given donator status by " + c.playerName);
								c2.playerRights = 4;
								c2.isDonator = 1;
								c2.logout();
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

			
			if (playerCommand.startsWith("demote")) {
				try {	
					String playerToDemote = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToDemote)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been demoted by " + c.playerName);
								c2.playerRights = 0;
								c2.logout();
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

			if (playerCommand.startsWith("item")) {
				try {
					String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						int newItemID = Integer.parseInt(args[1]);
						int newItemAmount = Integer.parseInt(args[2]);
						if ((newItemID <= 20000) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						} else {
							c.sendMessage("That item ID does not exist.");
						}
					} else {
						c.sendMessage("Wrong usage: (Ex:(::pickup_ID_Amount)(::item 995 1))");
					}
				} catch(Exception e) {
					
				} // HERE?
				} // HERE?
    
    }

    public void DonatorCommands(Client c, String playerCommand)
    {
        
}
}