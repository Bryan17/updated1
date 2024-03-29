package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.items.GameItem;
import server.model.players.Client;
import server.model.players.SkillMenu;
import server.model.players.PacketType;
import server.model.players.SkillGuides;
import server.util.Misc;
import server.event.EventContainer;
import server.event.Event;
import server.event.EventManager;

/**
 * Clicking most buttons
 **/
public class ClickingButtons implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);
		//int actionButtonId = c.getInStream().readShort();
		if (c.isDead)
			return;
		if(c.playerRights >= 3)	
			Misc.println(c.playerName+ " - actionbutton: "+actionButtonId);
			
		switch (actionButtonId){
			//crafting + fletching interface:
			case 89223: //Deposit Inventory
				for(int itemID = 0; itemID < 101; itemID++) {
					for(int invSlot = 0; invSlot < 28; invSlot++) {
						c.getItems().bankItem(itemID, invSlot, 2147000000);
					}
				}
				break;
			case 150:
				if (c.autoRet == 0)
					c.autoRet = 1;
				else 
					c.autoRet = 0;
			break;
		case 17111://stop viewing viewing orb
                c.setSidebarInterface(10, 2449);
                c.viewingOrb = false;
                c.teleportToX = 2399;
                c.teleportToY = 5171;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59139://viewing orb southwest
                c.viewingOrb = true;
                c.teleportToX = 2388;
                c.teleportToY = 5138;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59138://viewing orb southeast
                c.viewingOrb = true;
                c.teleportToX = 2411;
                c.teleportToY = 5137;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59137://viewing orb northeast
                c.viewingOrb = true;
                c.teleportToX = 2409;
                c.teleportToY = 5158;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59136://viewing orb northwest
                c.viewingOrb = true;
                c.teleportToX = 2384;
                c.teleportToY = 5157;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59135://viewing orb middle
                c.viewingOrb = true;
                c.teleportToX = 2398;
                c.teleportToY = 5150;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;
			case 107229:
				if (c.isDonator == 1 && c.inGWD()) {
				c.Arma = 15;
				c.Band = 15;
				c.Sara = 15;
				c.Zammy = 15;
					c.sendMessage("Your magical donator rank forces your KC to raise to 15!");
				} else {
					c.sendMessage("You must be a donator and be in godwars dungeon to use this!");
				} 
			break;
			/** Quest Tab Buttons **/
			case 28164://1 info
				c.helpList();
			break;

			case 28165://2 user
			break;

			case 28166://3 pkp 
			break;

			case 28168://4 ep 
			break;

			case 28215://5 Hybrid Set
			if (c.inWild() && !c.isInBank()) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 14) {
				c.sendMessage("You need atleast 14 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 450000)) {
				c.getItems().deleteItem2(995, 450000);
				c.getItems().addItem(555, 300);
				c.getItems().addItem(560, 200);
				c.getItems().addItem(565, 100);
				c.getItems().addItem(4675, 1);
				c.getItems().addItem(2497, 1);
				c.getItems().addItem(2415, 1);
				c.getItems().addItem(10828, 1);
				c.getItems().addItem(3841, 1);
				c.getItems().addItem(2503, 1);
				c.getItems().addItem(7460, 1);
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(2550, 1);
				c.getItems().addItem(4091, 1);
				c.getItems().addItem(4093, 1);
				c.getItems().addItem(3105, 1);
			} else {
				c.sendMessage("You need atleast 450,000 coins to use this feature.");
			}
			break;

			case 28171://6 Melee Set
			if (c.inWild() && !c.isInBank()) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 11) {
				c.sendMessage("You need atleast 11 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 310000)) {
				c.getItems().deleteItem2(995, 310000);
				c.getItems().addItem(1127, 1);
				c.getItems().addItem(4587, 1);
				c.getItems().addItem(10828, 1);
				c.getItems().addItem(557, 500);
				c.getItems().addItem(1079, 1);
				c.getItems().addItem(3841, 1);
				c.getItems().addItem(1725, 1);
				c.getItems().addItem(9075, 100);
				c.getItems().addItem(7460, 1);
				c.getItems().addItem(3105, 1);
				c.getItems().addItem(2550, 1);
				c.getItems().addItem(560, 150);
			} else {
				c.sendMessage("You need atleast 310,000 coins to use this feature.");
			}
			break;
			
			case 28170://Barrage Rune Set
			 if (c.inWild() && !c.isInBank()) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 3) {
				c.sendMessage("You need atleast 3 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 200000)) {
				c.getItems().deleteItem2(995, 200000);
				c.getItems().addItem(560, 400);
				c.getItems().addItem(555, 600);
				c.getItems().addItem(565, 200);
			} else {
				c.sendMessage("You need atleast 200,000 coins to use this feature.");
			}
			break;

			case 28172:// Vengance Rune Set
			if (c.inWild() && !c.isInBank()) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 3) {
				c.sendMessage("You need atleast 3 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 100000)) {
				c.getItems().deleteItem2(995, 100000);
				c.getItems().addItem(9075, 400);
				c.getItems().addItem(560, 200);
				c.getItems().addItem(557, 1000);
			} else {
				c.sendMessage("You need atleast 200,000 coins to use this feature.");
			}
			break;

			case 28178:// Food/Pots
			if (c.inWild() && !c.isInBank()) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 5) {
				c.sendMessage("You need atleast 5 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 300000)) {
				c.getItems().deleteItem2(995, 300000);
				c.getItems().addItem(392, 1000);
				c.getItems().addItem(2435, 100);
				c.getItems().addItem(2437, 100);
				c.getItems().addItem(2441, 100);
				c.getItems().addItem(2443, 100);
			} else {
				c.sendMessage("You need atleast 300,000 coins to use this feature.");
			}
			break;

			case 28173://10
				c.sendMessage("Will add more features soon. - Bryan");
			break;
			
			case 108003:
				if (c.isDonator == 1) {
				c.setSidebarInterface(4, 27620);
				} else {
				c.sendMessage("You must be an donator to view this tab!");
				return;				
				}
				break;

				case 107231:
				if (c.isDonator == 1) {
				c.getPA().spellTeleport(2036, 4525, 0);
				c.sendMessage("You teleported to donator-zone a place to chill/relax, theres also alot of benefits.");
				} else {
				c.sendMessage("You must be an donator to teleport to the donator-zone!");
				return;				
				}
				break;

			case 108006:
				if (c.xpLock == false) {
					c.xpLock = true;
					c.sendMessage("Your XP are now LOCKED!");
				} else {
					c.xpLock = false;
					c.sendMessage("Your XP are now UNLOCKED!");
				} 
			break;
			case 107230:
			if(c.isDonator == 0 || c.inWild()) {
			c.sendMessage("You must be outside wilderness and be a donator to use this!");
			return;
			}
			if (c.playerMagicBook == 0 && c.isDonator == 1 && !c.inWild()) {
				c.playerMagicBook = 1;
				c.setSidebarInterface(6, 12855);
				//c.setSidebarInterface(0, 328);
				c.sendMessage("An ancient wisdomin fills your mind.");
				c.getPA().resetAutocast();
				return;
			}	
			if (c.playerMagicBook == 1 && c.isDonator == 1 && !c.inWild()) {
				c.playerMagicBook = 2;
				//c.setSidebarInterface(0, 328);
				c.setSidebarInterface(6, 16640);
				c.sendMessage("Your mind becomes stirred with thoughs of dreams.");
				c.getPA().resetAutocast();
				return;
			}
			if (c.playerMagicBook == 2 && c.isDonator == 1 && !c.inWild()) {
				c.setSidebarInterface(6, 1151); //modern
				c.playerMagicBook = 0;
				//c.setSidebarInterface(0, 328);
				c.sendMessage("You feel a drain on your memory.");
				c.autocastId = -1;
				c.getPA().resetAutocast();
				return;
			}
			break;
			//1st tele option
			case 9190:
				if (c.teleAction == 1) {
					//rock crabs
					c.getPA().spellTeleport(2676, 3715, 0);
				} else if (c.teleAction == 2) {
					//barrows
					c.getPA().spellTeleport(3565, 3314, 0);
				} else if (c.teleAction == 3) {
					//godwars
					c.getPA().spellTeleport(2717, 9802, 0);
				} else if (c.teleAction == 4) {
					//edgeville
					c.getPA().spellTeleport(3093, 3493, 0);
					//c.getPA().spellTeleport(2539, 4716, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(3046,9779,0);
				} else if (c.teleAction == 20) {
					//lum
					c.getPA().spellTeleport(3222, 3218, 0);//3222 3218
				}
				
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2845, 4832, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2786, 4839, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2398, 4841, 0);
					c.dialogueAction = -1;
				}
				break;
				//mining - 3046,9779,0
			//smithing - 3079,9502,0
case 154:
			if(System.currentTimeMillis() - c.logoutDelay < 8000) {
c.sendMessage("You cannot do skillcape emotes in combat!");
return;
}
		if(System.currentTimeMillis() - c.lastEmote >= 7000) {
	if(c.getPA().wearingCape(c.playerEquipment[c.playerCape])) {
		c.stopMovement();
		c.gfx0(c.getPA().skillcapeGfx(c.playerEquipment[c.playerCape]));
		c.startAnimation(c.getPA().skillcapeEmote(c.playerEquipment[c.playerCape]));
	} else {
		c.sendMessage("You must be wearing a Skillcape to do this emote.");
}
			c.lastEmote = System.currentTimeMillis();
}
break;
			//2nd tele option
			case 9191:
				if (c.teleAction == 1) {
					//tav dungeon
					c.getPA().spellTeleport(2884, 9798, 0);
				} else if (c.teleAction == 2) {
					//pest control
					c.getPA().spellTeleport(3366, 3266, 0);
				} else if (c.teleAction == 3) {
					//corporal beast
					c.getPA().spellTeleport(3304, 9375, 0);
				} else if (c.teleAction == 4) {
					//graveyard
					//c.getPA().spellTeleport(2981, 3595, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(3079,9502,0);
				
				} else if (c.teleAction == 20) {
					c.getPA().spellTeleport(3210,3424,0);//3210 3424
				}
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2796, 4818, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2527, 4833, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2464, 4834, 0);
					c.dialogueAction = -1;
				}
				break;
			//3rd tele option	

			case 9192:
				if (c.teleAction == 1) {
					//slayer tower
					c.getPA().spellTeleport(3428, 3537, 0);
				} else if (c.teleAction == 2) {
					//tzhaar
					//c.getPA().spellTeleport(2438, 5168, 0);
				} else if (c.teleAction == 3) {
					c.getPA().spellTeleport(2438, 5168, 0);
				} else if (c.teleAction == 4) {
					//green dragons
					c.getPA().spellTeleport(3340, 3672, 0);
									
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2597,3408,0);
				}
				 else if (c.teleAction == 20) {
					c.getPA().spellTeleport(2757,3477,0);
				}

				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2713, 4836, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2162, 4833, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2207, 4836, 0);
					c.dialogueAction = -1;
				}
				break;
			//4th tele option
			case 9193:
				if (c.teleAction == 1) {
					//brimhaven dungeon
					c.getPA().spellTeleport(2710, 9466, 0);
					c.sendMessage("You teleported to brimhaven dungeon, be sure to bring antifire-shield.");
				} else if (c.teleAction == 2) {
					//duel arena
					c.getPA().spellTeleport(3366, 3266, 0);
				} else if (c.teleAction == 3) {
					c.getPA().spellTeleport(3565, 3307, 0);
				} else if (c.teleAction == 4) {
					//Fala
				//c.getPA().spellTeleport(3086, 3516, 0);

				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2724,3484,0);
					c.sendMessage("For magic logs, try north of the duel arena.");
				}
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2660, 4839, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					//c.getPA().spellTeleport(2527, 4833, 0); astrals here
					c.getRunecrafting().craftRunes(2489);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					//c.getPA().spellTeleport(2464, 4834, 0); bloods here
					c.getRunecrafting().craftRunes(2489);
					c.dialogueAction = -1;
				
				} else if (c.teleAction == 20) {
					c.getPA().spellTeleport(2964,3378,0);
				}
				break;
			case 9194:
				if (c.teleAction == 1) {
					//island
					c.getPA().spellTeleport(3117, 9847, 0);
				} else if (c.teleAction == 2) {
					//warrior's guild
					//c.getPA().spellTeleport(2865,3546,0);
					//c.getPA().closeAllWindows();
				} else if (c.teleAction == 3) {
					c.getPA().spellTeleport(3304 ,9375,0);
					c.sendMessage("Note: Ruby bolts (e) and Diamond bolts (e) are recommended!");
					c.getPA().closeAllWindows();
				} else if (c.teleAction == 4) {
					c.dialogueId = 50;
					c.getDH().sendDialogues(c.dialogueId, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2812,3463,0);
				}
				if (c.dialogueAction == 10 || c.dialogueAction == 11) {
					c.dialogueId++;
					c.getDH().sendDialogues(c.dialogueId, 0);
				} else if (c.dialogueAction == 12) {
					c.dialogueId = 17;
					c.getDH().sendDialogues(c.dialogueId, 0);
				
				} else if (c.teleAction == 20) {
					c.getPA().spellTeleport(3506,3496,0);
				}
				break;
			
			case 71074:
				if (c.clanId >= 0 && Server.clanChat.clans[c.clanId].owner.equalsIgnoreCase(c.playerName)) {
					if (c.CSLS == 0) {
		if(System.currentTimeMillis() - c.lastEmote >= 1500) {
						Server.clanChat.clans[c.clanId].CS = 1;
						Server.clanChat.sendLootShareMessage(c.clanId, "LootShare has been toggled to " + (!Server.clanChat.clans[c.clanId].lootshare ? "ON" : "OFF") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].lootshare = !Server.clanChat.clans[c.clanId].lootshare;
						c.CSLS = 1;
						Server.clanChat.updateClanChat(c.clanId);
			c.lastEmote = System.currentTimeMillis();
						return;
				}	
				}	
					if (c.CSLS == 1) {
		if(System.currentTimeMillis() - c.lastEmote >= 1500) {
						c.CSLS = 2;
						Server.clanChat.clans[c.clanId].CS = 2;
						Server.clanChat.updateClanChat(c.clanId);
						Server.clanChat.sendLootShareMessage(c.clanId, "LootShare has been toggled to " + (!Server.clanChat.clans[c.clanId].lootshare ? "ON" : "OFF") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].lootshare = !Server.clanChat.clans[c.clanId].lootshare;
			c.lastEmote = System.currentTimeMillis();
						return;

				}	
				}	
					if (c.CSLS == 2) {
		if(System.currentTimeMillis() - c.lastEmote >= 1500) {
						if(Server.clanChat.clans[c.clanId].playerz == 1) {
						c.sendMessage("There must be atleast 2 members in the clan chat to toggle Coinshare ON.");
						c.CSLS = 0;
						Server.clanChat.clans[c.clanId].CS = 0;
						Server.clanChat.updateClanChat(c.clanId);
			c.lastEmote = System.currentTimeMillis();
						return;
						}
						c.CSLS = 3;
						Server.clanChat.clans[c.clanId].CS = 3;
						Server.clanChat.updateClanChat(c.clanId);
						Server.clanChat.sendCoinShareMessage(c.clanId, "CoinShare has been toggled to " + (!Server.clanChat.clans[c.clanId].coinshare ? "ON" : "OFF") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].coinshare = !Server.clanChat.clans[c.clanId].coinshare;
						return;

				}	
				}	
					if (c.CSLS == 3) {
		if(System.currentTimeMillis() - c.lastEmote >= 1500) {
						c.CSLS = 0;
						Server.clanChat.clans[c.clanId].CS = 0;
						Server.clanChat.updateClanChat(c.clanId);
						Server.clanChat.sendCoinShareMessage(c.clanId, "CoinShare has been toggled to " + (!Server.clanChat.clans[c.clanId].coinshare ? "ON" : "OFF") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].coinshare = !Server.clanChat.clans[c.clanId].coinshare;
			c.lastEmote = System.currentTimeMillis();
						return;
				}	
				}	
					} else {
						c.sendMessage("Only the owner of the clan has the power to do that.");
				}	
			break;
			case 34185: case 34184: case 34183: case 34182: case 34189: case 34188: case 34187: case 34186: case 34193: case 34192: case 34191: case 34190:
				if (c.craftingLeather)
					c.getCrafting().handleCraftingClick(actionButtonId);
				if (c.getFletching().fletching)
					c.getFletching().handleFletchingClick(actionButtonId);
			break;
			
			case 15147:
				if (c.smeltInterface) {
					c.smeltType = 2349;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 15151:
				if (c.smeltInterface) {
					c.smeltType = 2351;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			
			case 15159:
				if (c.smeltInterface) {
					c.smeltType = 2353;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			
			case 29017:
				if (c.smeltInterface) {
					c.smeltType = 2359;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 29022:
				if (c.smeltInterface) {
					c.smeltType = 2361;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 29026:
				if (c.smeltInterface) {
					c.smeltType = 2363;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			case 108005:
			c.getPA().showInterface(19148);
			break;
			
			case 59004:
			c.getPA().removeAllWindows();
			break;
			
			case 70212:
				if (c.clanId > -1)
					Server.clanChat.leaveClan(c.playerId, c.clanId);
				else
					c.sendMessage("You are not in a clan.");
			break;
			case 62137:
				if (c.clanId >= 0) {
					c.sendMessage("You are already in a clan.");
					break;
				}
				if (c.getOutStream() != null) {
					c.getOutStream().createFrame(187);
					c.flushOutStream();
				}	
			break;
			
			case 9178:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(3428, 3538, 0, "modern");
				if (c.dialogueAction == 3)		
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(3565, 3314, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 4, "modern");
				}
				if(c.dialogueAction == 100) {
					c.getDH().sendDialogues(25, 946);
				}
					
			break;
			
			case 9179:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.AL_KHARID_X, Config.AL_KHARID_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2884, 3395, 0, "modern");
				if (c.dialogueAction == 3)
					c.getPA().startTeleport(3243, 3513, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(2444, 5170, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 12, "modern");
				}
				if(c.dialogueAction == 101) {
					c.getDH().sendDialogues(21, 946);
				}
				if(c.dialogueAction == 100) {
					c.getGamble().gambleBlackJack(c);
				}	
			break;
			
			case 9180:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.KARAMJA_X, Config.KARAMJA_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2471,10137, 0, "modern");	
				if (c.dialogueAction == 3)
					c.getPA().startTeleport(3363, 3676, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(2659, 2676, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 8, "modern");
				}
				if(c.dialogueAction == 101) {
					c.getDH().sendDialogues(23, 946);
				}
				if(c.dialogueAction == 100) {
					if(!c.getItems().playerHasItem(995, 1000000)) {
						c.sendMessage("You need at least 1M coins to play this game!");
						c.getPA().removeAllWindows();
						break;
					}
					c.getGamble().playGame(c);
				}
			break;
			
			case 9181:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.MAGEBANK_X, Config.MAGEBANK_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2669,3714, 0, "modern");
				if (c.dialogueAction == 3)	
					c.getPA().startTeleport(2540, 4716, 0, "modern");
				if (c.dialogueAction == 4) {
					c.getPA().startTeleport(3366, 3266, 0, "modern");
					c.sendMessage("Dueling is at your own risk. Refunds will not be given for items lost due to glitches.");
				}
				if (c.dialogueAction == 20) {
					//c.getPA().startTeleport(3366, 3266, 0, "modern");
					//c.killCount = 0;
					c.sendMessage("This will be added shortly");
				} else if (c.dialogueAction == 10 || c.dialogueAction == 101) {
					c.dialogueAction = 0;
					c.getPA().removeAllWindows();
				} else {
					c.getPA().removeAllWindows();
				}
				c.dialogueAction = 0;
				break;
			
			case 1093:
			case 1094:
			case 1097:
				if (c.autocastId > 0) {
					c.getPA().resetAutocast();
				} else {
					if (c.playerMagicBook == 1) {
						if (c.playerEquipment[c.playerWeapon] == 4675 || c.playerEquipment[c.playerWeapon] == 15050)
							c.setSidebarInterface(0, 1689);
						else
							c.sendMessage("You can't autocast ancients without an ancient staff.");
					} else if (c.playerMagicBook == 0) {
						if (c.playerEquipment[c.playerWeapon] == 4170) {
							c.setSidebarInterface(0, 12050);
						} else {
							c.setSidebarInterface(0, 1829);
						}	
					}
						
				}		
			break;

			case 9157://barrows tele to tunnels
				if(c.dialogueAction == 1) {
					int r = 4;
					//int r = Misc.random(3);
					switch(r) {
						case 0:
							c.getPA().movePlayer(3534, 9677, 0);
							break;
						
						case 1:
							c.getPA().movePlayer(3534, 9712, 0);
							break;
						
						case 2:
							c.getPA().movePlayer(3568, 9712, 0);
							break;
						
						case 3:
							c.getPA().movePlayer(3568, 9677, 0);
							break;
						case 4:
							c.getPA().movePlayer(3551, 9694, 0);
							break;
					}
				} else if (c.dialogueAction == 2) {
					c.getPA().movePlayer(2507, 4717, 0);		
				} else if (c.dialogueAction == 5) {
					c.getSlayer().giveTask();
				} else if (c.dialogueAction == 6) {
					c.getSlayer().giveTask2();
				} else if (c.dialogueAction == 7) {
					c.getPA().startTeleport(3088,3933,0,"modern");
					c.sendMessage("NOTE: You are now in the wilderness...");
				} else if (c.dialogueAction == 50) {
					c.getPA().startTeleport(2661,3307,0,"modern");
					c.sendMessage("This area is now FUNPK!");
				} else if (c.dialogueAction == 8) {
					c.getPA().resetBarrows();
					c.sendMessage("Your barrows have been reset.");
				} else if (c.dialogueAction == 100) {
					c.getDH().sendDialogues(52, 0);
				} else if (c.dialogueAction == 101) {
					c.getDH().sendDialogues(55, 0);
				} else if (c.teleAction == 101) {//Varrock PvP
					c.getPA().spellTeleport(3182, 3442, 0);
				} else if (c.teleAction == 102) {//KBD TELE
					c.getPA().spellTeleport(2271, 4680, 0);
				} else if (c.dialogueAction == 27) {
					c.getPA().movePlayer(3086, 3493, 0);
					c.monkeyk0ed = 0;
					c.Jail = false;
					c.forcedText = "I swear to god that i will never break the rules anymore!";
					c.forcedChatUpdateRequired = true;
					c.updateRequired = true;
				}
				c.dialogueAction = 0;
				c.getPA().removeAllWindows();
				break;
			
			case 9158:
				if (c.dialogueAction == 50) {
					//c.getPA().startTeleport(2559,3089,0,"modern");
					//c.sendMessage("This area is now FUNPK!");
					}
				if (c.dialogueAction == 8) {
					c.getPA().fixAllBarrows();
				} else if (c.teleAction == 101) {
					c.sendMessage("Coming Soon...");
					c.getPA().removeAllWindows();
				} else {
					c.dialogueAction = 0;
					c.getPA().removeAllWindows();
				}
				break;
			case 107243:
				c.setSidebarInterface(4, 1644);
				break;

			case 107215:
				c.setSidebarInterface(11, 904);
				break;
			
			/**Specials**/
			case 29188:
			c.specBarId = 7636; // the special attack text - sendframe126(S P E C I A L  A T T A C K, c.specBarId);
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29163:
			c.specBarId = 7611;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 33033:
			c.specBarId = 8505;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29038:
			if(c.playerEquipment[c.playerWeapon] == 15020) {
			c.specBarId = 7486;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			} else {
			c.specBarId = 7486;
			/*if (c.specAmount >= 5) {
				c.attackTimer = 0;
				c.getCombat().attackPlayer(c.playerIndex);
				c.usingSpecial = true;
				c.specAmount -= 5;
			}*/
			c.getCombat().handleGmaulPlayer();
			c.getItems().updateSpecialBar();
			}
			break;
			
			case 29063:
			if(c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
				c.gfx0(246);
				c.forcedChat("Raarrrrrgggggghhhhhhh!");
				c.startAnimation(1056);
				c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]) + (c.getLevelForXP(c.playerXP[2]) * 15 / 100);
				c.getPA().refreshSkill(2);
				c.getItems().updateSpecialBar();
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}
			break;
			
			case 48023:
			c.specBarId = 12335;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

			case 30108:
			c.specBarId = 7812;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29138:
			if(c.playerEquipment[c.playerWeapon] == 15050) {
			if(c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
				c.gfx0(1958);
				c.SolProtect = 120;
				c.startAnimation(10518);
				c.getItems().updateSpecialBar();
			c.usingSpecial = !c.usingSpecial;
			c.sendMessage("All damage will be split into half for 1 minute.");
			c.getPA().sendFrame126("@bla@S P E C I A L  A T T A C K", 7562);
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}	
			}			
			c.specBarId = 7586;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29113:
			c.specBarId = 7561;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29238:
			c.specBarId = 7686;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			/**Dueling**/			
			case 26065: // no forfeit
			case 26040:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(0);
			break;
			
			case 26066: // no movement
			case 26048:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(1);
			break;
			
			case 26069: // no range
			case 26042:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(2);
			break;
			
			case 26070: // no melee
			case 26043:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(3);
			break;				
			
			case 26071: // no mage
			case 26041:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(4);
			break;
				
			case 26072: // no drinks
			case 26045:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(5);
			break;
			
			case 26073: // no food
			case 26046:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(6);
			break;
			
			case 26074: // no prayer
			case 26047:	
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(7);
			break;
			
			case 26076: // obsticals
			case 26075:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(8);
			break;
			
			case 2158: // fun weapons
			case 2157:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(9);
			break;
			
			case 30136: // sp attack
			case 30137:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(10);
			break;	

			case 53245: //no helm
			c.duelSlot = 0;
			c.getTradeAndDuel().selectRule(11);
			break;
			
			case 53246: // no cape
			c.duelSlot = 1;
			c.getTradeAndDuel().selectRule(12);
			break;
			
			case 53247: // no ammy
			c.duelSlot = 2;
			c.getTradeAndDuel().selectRule(13);
			break;
			
			case 53249: // no weapon.
			c.duelSlot = 3;
			c.getTradeAndDuel().selectRule(14);
			break;
			
			case 53250: // no body
			c.duelSlot = 4;
			c.getTradeAndDuel().selectRule(15);
			break;
			
			case 53251: // no shield
			c.duelSlot = 5;
			c.getTradeAndDuel().selectRule(16);
			break;
			
			case 53252: // no legs
			c.duelSlot = 7;
			c.getTradeAndDuel().selectRule(17);
			break;
			
			case 53255: // no gloves
			c.duelSlot = 9;
			c.getTradeAndDuel().selectRule(18);
			break;
			
			case 53254: // no boots
			c.duelSlot = 10;
			c.getTradeAndDuel().selectRule(19);
			break;
			
			case 53253: // no rings
			c.duelSlot = 12;
			c.getTradeAndDuel().selectRule(20);
			break;
			
			case 53248: // no arrows
			c.duelSlot = 13;
			c.getTradeAndDuel().selectRule(21);
			break;
			
			case 26018:	
			Client o = (Client) Server.playerHandler.players[c.duelingWith];
			if(o == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}
			
			if(c.duelRule[2] && c.duelRule[3] && c.duelRule[4]) {
				c.sendMessage("You won't be able to attack the player with the rules you have set.");
				break;
			}
			c.duelStatus = 2;
			if(c.duelStatus == 2) {
				c.getPA().sendFrame126("Waiting for other player...", 6684);
				o.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			if(o.duelStatus == 2) {
				o.getPA().sendFrame126("Waiting for other player...", 6684);
				c.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			
			if(c.duelStatus == 2 && o.duelStatus == 2) {
				c.canOffer = false;
				o.canOffer = false;
				c.duelStatus = 3;
				o.duelStatus = 3;
				c.getTradeAndDuel().confirmDuel();
				o.getTradeAndDuel().confirmDuel();
			}
			break;
			
			case 25120:
			if(c.duelStatus == 5) {
				break;
			}
			Client o1 = (Client) Server.playerHandler.players[c.duelingWith];
			if(o1 == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}

			c.duelStatus = 4;
			if(o1.duelStatus == 4 && c.duelStatus == 4) {				
				c.getTradeAndDuel().startDuel();
				o1.getTradeAndDuel().startDuel();
				o1.duelCount = 4;
				c.duelCount = 4;
				c.duelDelay = System.currentTimeMillis();
				o1.duelDelay = System.currentTimeMillis();
			} else {
				c.getPA().sendFrame126("Waiting for other player...", 6571);
				o1.getPA().sendFrame126("Other player has accepted", 6571);
			}
			break;
	
			
			case 4169: // god spell charge
			c.usingMagic = true;
			if(!c.getCombat().checkMagicReqs(48)) {
				break;
			}
				
			if(System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
				c.sendMessage("You still feel the charge in your body!");
				break;
			}
			c.godSpellDelay	= System.currentTimeMillis();
			c.sendMessage("You feel charged with a magical power!");
			c.gfx100(c.MAGIC_SPELLS[48][3]);
			c.startAnimation(c.MAGIC_SPELLS[48][2]);
			c.usingMagic = false;
	        break;
			
			

			
			case 152:
			c.isRunning2 = !c.isRunning2;
			int frame = c.isRunning2 == true ? 1 : 0;
			c.getPA().sendFrame36(173,frame);
			break;
			
			case 9154:
			c.logout();
			break;
			
			case 21010:
			c.takeAsNote = true;
			break;

			case 21011:
			c.takeAsNote = false;
			break;
			
			
			case 117048:
			c.getPA().startTeleport(Config.START_LOCATION_X+Misc.random(2), Config.START_LOCATION_Y+Misc.random(2), 0, "modern");	
			break;		

			//home teleports
			case 4171:		
			case 50056:
			String type = c.playerMagicBook == 0 ? "modern" : "ancient";
			c.getPA().startTeleport(Config.START_LOCATION_X+Misc.random(2), Config.START_LOCATION_Y+Misc.random(2), 0, type);	
			break;
			
			case 50235:
			case 4140:
			case 117112:
			//c.getPA().startTeleport(Config.LUMBY_X, Config.LUMBY_Y, 0, "modern");
			c.getDH().sendOption5("Rock Crabs", "Taverly Dungeon", "Slayer Tower", "Brimhaven Dungeon", "Hill Giants");

			c.teleAction = 1;
			break;

			
			case 4143:
			case 50245:
			case 117123:
			c.getDH().sendOption5("Barrows", "Duel Arena", " ", " ", "More Telys Soon");
			c.teleAction = 2;
			break;
			
			case 50253:
			case 117131:
			case 4146:
			c.getDH().sendOption5("Tormented Demons", "Corporeal Beast", " ", " ", "More Telys Soon");
			c.teleAction = 3;
			break;
			

			case 51005:
			case 117154:
			case 4150:
			c.getDH().sendOption5("Edgeville", "Green Dragons", " ", " ", "More Telys Soon");
			c.teleAction = 4;
			break;			
			
			case 51013:
			case 6004:	
			case 117162:	
			//c.getPA().startTeleport(2852, 3432, 0, "modern");
			//c.getDH().sendOption5("Mining", "Smithing", "Fishing/Cooking", "Woodcutting", "Farming");
			//c.teleAction = 5;
			break; 
			
			
			case 51023:
			case 6005:
						//c.getDH().sendOption5("Lumbridge", "Varrock", "Camelot", "Falador", "Canifis");
			//c.teleAction = 20;
			break; 
			
			
			case 51031:
			case 29031:
			//c.getDH().sendOption5("Option 17", "Option 2", "Option 3", "Option 4", "Option 5");
			//c.teleAction = 7;
			break; 		

			case 72038:
			case 51039:
			//c.getDH().sendOption5("Option 18", "Option 2", "Option 3", "Option 4", "Option 5");
			//c.teleAction = 8;
			break;
			
      			case 9125: //Accurate
			case 6221: // range accurate
			case 22230: //kick (unarmed)
			case 48010: //flick (whip)
			case 21200: //spike (pickaxe)
			case 1080: //bash (staff)
			case 6168: //chop (axe)
			case 6236: //accurate (long bow)
			case 17102: //accurate (darts)
			case 8234: //stab (dagger)

			case 30088: //claws
			case 1177: //hammer
			c.fightMode = 0;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9126: //Defensive
			case 48008: //deflect (whip)
			case 22228: //punch (unarmed)
			case 21201: //block (pickaxe)
			case 1078: //focus - block (staff)
			case 6169: //block (axe)
			case 33019: //fend (hally)
			case 18078: //block (spear)
			case 8235: //block (dagger)
			case 1175: //accurate (darts)
			case 30089: //stab (dagger)
			c.fightMode = 1;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9127: // Controlled
			case 48009: //lash (whip)
			case 33018: //jab (hally)
			case 6234: //longrange (long bow)
			case 6219: //longrange
			case 18077: //lunge (spear)
			case 18080: //swipe (spear)
			case 18079: //pound (spear)
			case 17100: //longrange (darts)
			c.fightMode = 3;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9128: //Aggressive
			case 6220: // range rapid
			case 22229: //block (unarmed)
			case 21203: //impale (pickaxe)
			case 21202: //smash (pickaxe)
			case 1079: //pound (staff)
			case 6171: //hack (axe)
			case 6170: //smash (axe)
			case 33020: //swipe (hally)
			case 6235: //rapid (long bow)
			case 17101: //repid (darts)
			case 8237: //lunge (dagger)
			case 30091: //claws
			case 1176: //stat hammer
			case 8236: //slash (dagger)

			case 30090: //claws
			c.fightMode = 2;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;

			/**CURSE Prayers**/
			case 87231: // thick skin
			if(c.altarPrayed == 0) {
				return;
			}
			if(c.trade11 > 1) {
			for(int p = 0; p < c.PRAYER.length; p++) { // reset prayer glows 
				c.prayerActive[p] = false;
				c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);	
			}
			c.sendMessage("You must wait 15 minutes before using this!");
			return;
			}
			c.getCurse().activateCurse(0);
			break;
			case 87233: // burst of str
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(1);
			break;	
			case 87235: // charity of thought
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(2);
			break;	
			case 87237: // range
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(3);
			break;
			case 87239: // mage
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(4);
			break;
			case 87241: // berserker
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(5);
			c.gfx0(2266);
			c.startAnimation(12552);
			break;
			case 87243: // super human
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(6);
			break;
			case 87245:	// improved reflexes
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(7);
			break;
			case 87247: //hawk eye
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(8);
			break;
			case 87249:
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(9);
			break;
			case 87251: // Leech Attack
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(10);
			break;			
			case 87253: // 26 range
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(11);
			break;
			case 87255: // 27 mage
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(12);
			break;	
			case 88001: // steel skin
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(13);
			break;
			case 88003: // ultimate str
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(14);
			break;
			case 88005: // incredible reflex
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(15);
			break;	
			case 88007: // protect from magic
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(16);
			break;					
			case 88009: // protect from range
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(17);
			break;
			case 88011: // protect from melee
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(18);
			break;
			case 88013: // 44 range
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(19);
			break;	
			/**End of curse prayers**/
			
			
			/**Prayers**/
			case 97168: // thick skin
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(0);
			break;	
			case 97170: // burst of str
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(1);
			break;	
			case 97172: // charity of thought
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(2);
			break;	
			case 97174: // range
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(3);
			break;
			case 97176: // mage
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(4);
			break;
			case 97178: // rockskin
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(5);
			break;
			case 97180: // super human
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(6);
			break;
			case 97182:	// improved reflexes
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(7);
			break;
			case 97184: //hawk eye
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(8);
			break;
			case 97186:
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(9);
			break;
			case 97188: // protect Item
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(10);
			break;			
			case 97190: // 26 range
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(11);
			break;
			case 97192: // 27 mage
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(12);
			break;	
			case 97194: // steel skin
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(13);
			break;
			case 97196: // ultimate str
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(14);
			break;
			case 97198: // incredible reflex
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(15);
			break;	
			case 97200: // protect from magic
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(16);
			break;					
			case 97202: // protect from range
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(17);
			break;
			case 97204: // protect from melee
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(18);
			break;
			case 97206: // 44 range
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(19);
			break;	
			case 97208: // 45 mystic
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(20);
			break;				
			case 97210: // retrui
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(21);
			break;					
			case 97212: // redem
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(22);
			break;					
			case 97214: // smite
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(23);
			break;
			case 97216: // chiv
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(24);
			break;
			case 97218: // piety
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(25);
			break;
			//END OF PRAYER
			
			case 34155:
			case 33224: //Runecrafting
				SkillGuides.rcInterface(c);
				break;
			case 34158:
			case 33210: //Agility
				SkillGuides.agilityInterface(c);
				break;
			case 34161:
			case 33213: //Herblore
				SkillGuides.herbloreInterface(c);
				break;
			case 59199:
			case 33216: //Theiving
				SkillGuides.thievingInterface(c);
				break;	
			case 59202:
			case 33219: //craft
				SkillGuides.craftingInterface(c);
				break;	
			case 33222: //Fletching
				SkillGuides.fletchingInterface(c);
				break;	
			case 59205: 
			case 33208: //Mining
				SkillGuides.miningInterface(c);
				break;	
			case 33211: //Smithing
				SkillGuides.smithingInterface(c);
				break;	
			case 33214: //Fishing
				SkillGuides.fishingInterface(c);
				break;	
			case 33217: //Cooking
				SkillGuides.cookingInterface(c);
				break;	
			case 33220: //Firemaking
				SkillGuides.firemakingInterface(c);
				break;	
			case 33223: //Woodcutting
				SkillGuides.woodcuttingInterface(c);
				break;	
			case 54104: //Farming
				SkillGuides.farmingInterface(c);
				break;
			
			case 13092:
                        if (System.currentTimeMillis() - c.lastButton < 400) {

					c.lastButton = System.currentTimeMillis();

					break;

				} else {

					c.lastButton = System.currentTimeMillis();

				}
			Client ot = (Client) Server.playerHandler.players[c.tradeWith];
			if(ot == null) {
				c.getTradeAndDuel().declineTrade();
				c.sendMessage("Trade declined as the other player has disconnected.");
				break;
			}
			c.getPA().sendFrame126("Waiting for other player...", 3431);
			ot.getPA().sendFrame126("Other player has accepted", 3431);	
			c.goodTrade= true;
			ot.goodTrade= true;
			
			for (GameItem item : c.getTradeAndDuel().offeredItems) {
				if (item.id > 0) {
					if(ot.getItems().freeSlots() < c.getTradeAndDuel().offeredItems.size()) {					
						c.sendMessage(ot.playerName +" only has "+ot.getItems().freeSlots()+" free slots, please remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						ot.sendMessage(c.playerName +" has to remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items or you could offer them "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						c.goodTrade= false;
						ot.goodTrade= false;
						c.getPA().sendFrame126("Not enough inventory space...", 3431);
						ot.getPA().sendFrame126("Not enough inventory space...", 3431);
							break;
					} else {
						c.getPA().sendFrame126("Waiting for other player...", 3431);				
						ot.getPA().sendFrame126("Other player has accepted", 3431);
						c.goodTrade= true;
						ot.goodTrade= true;
						}
					}	
				}	
				if (c.inTrade && !c.tradeConfirmed && ot.goodTrade && c.goodTrade) {
					c.tradeConfirmed = true;
					if(ot.tradeConfirmed) {
						c.getTradeAndDuel().confirmScreen();
						ot.getTradeAndDuel().confirmScreen();
						break;
					}
							  
				}

		
			break;
					
			case 13218:
                         if (System.currentTimeMillis() - c.lastButton < 400) {

					c.lastButton = System.currentTimeMillis();

					break;

				} else {

					c.lastButton = System.currentTimeMillis();

				}
			c.tradeAccepted = true;
			Client ot1 = (Client) Server.playerHandler.players[c.tradeWith];
				if (ot1 == null) {
					c.getTradeAndDuel().declineTrade();
					c.sendMessage("Trade declined as the other player has disconnected.");
					break;
				}
				
				if (c.inTrade && c.tradeConfirmed && ot1.tradeConfirmed && !c.tradeConfirmed2) {
					c.tradeConfirmed2 = true;
					if(ot1.tradeConfirmed2) {	
						c.acceptedTrade = true;
						ot1.acceptedTrade = true;
						c.getTradeAndDuel().giveItems();
						ot1.getTradeAndDuel().giveItems();
						c.sendMessage("Trade accepted.");
						c.SaveGame();
						ot1.SaveGame();
						ot1.sendMessage("Trade accepted.");
						break;
					}
				ot1.getPA().sendFrame126("Other player has accepted.", 3535);
				c.getPA().sendFrame126("Waiting for other player...", 3535);
				}
				
			break;			
			/* Rules Interface Buttons */
			case 125011: //Click agree
				if(!c.ruleAgreeButton) {
					c.ruleAgreeButton = true;
					c.getPA().sendFrame36(701, 1);
				} else {
					c.ruleAgreeButton = false;
					c.getPA().sendFrame36(701, 0);
				}
				break;
			case 125003://Accept
				if(c.ruleAgreeButton) {
					c.getPA().showInterface(3559);
					c.newPlayer = false;
				} else if(!c.ruleAgreeButton) {
					c.sendMessage("You need to click on you agree before you can continue on.");
				}
				break;
			case 125006://Decline
				c.sendMessage("You have chosen to decline, Client will be disconnected from the server.");
				break;
			/* End Rules Interface Buttons */
			/* Player Options */
			case 74176:
				if(!c.mouseButton) {
					c.mouseButton = true;
					c.getPA().sendFrame36(500, 1);
					c.getPA().sendFrame36(170,1);
				} else if(c.mouseButton) {
					c.mouseButton = false;
					c.getPA().sendFrame36(500, 0);
					c.getPA().sendFrame36(170,0);					
				}
				break;
			case 74184:
				if(!c.splitChat) {
					c.splitChat = true;
					c.getPA().sendFrame36(502, 1);
					c.getPA().sendFrame36(287, 1);
				} else {
					c.splitChat = false;
					c.getPA().sendFrame36(502, 0);
					c.getPA().sendFrame36(287, 0);
				}
				break;
			case 100231:
				if(!c.chatEffects) {
					c.chatEffects = true;
					c.getPA().sendFrame36(501, 1);
					c.getPA().sendFrame36(171, 0);
				} else {
					c.chatEffects = false;
					c.getPA().sendFrame36(501, 0);
					c.getPA().sendFrame36(171, 1);
				}
				break;
			case 100237:
				if(!c.acceptAid) {
					c.acceptAid = true;
					c.getPA().sendFrame36(503, 1);
					c.getPA().sendFrame36(427, 1);
				} else {
					c.acceptAid = false;
					c.getPA().sendFrame36(503, 0);
					c.getPA().sendFrame36(427, 0);
				}
				break;
			case 74201://brightness1
				c.getPA().sendFrame36(505, 1);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166, 1);
				break;
			case 74203://brightness2
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 1);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,2);
				break;

			case 74204://brightness3
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 1);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,3);
				break;

			case 74205://brightness4
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 1);
				c.getPA().sendFrame36(166,4);
				break;
			case 74206://area1
				c.getPA().sendFrame36(509, 1);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74207://area2
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 1);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74208://area3
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 1);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74209://area4
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 1);
				break;
			case 168:
                c.startAnimation(855);		c.stopMovement();
            break;
            case 169:
                c.startAnimation(856);		c.stopMovement();
            break;
            case 162:
                c.startAnimation(857);		c.stopMovement();
            break;
            case 164:
                c.startAnimation(858);		c.stopMovement();
            break;
            case 165:
                c.startAnimation(859);		c.stopMovement();
            break;
            case 161:
                c.startAnimation(860);		c.stopMovement();
            break;
            case 170:
                c.startAnimation(861);		c.stopMovement();
            break;
            case 171:
                c.startAnimation(862);		c.stopMovement();
            break;
            case 163:
                c.startAnimation(863);		c.stopMovement();
            break;
            case 167:
                c.startAnimation(864);		c.stopMovement();
            break;
            case 172:
                c.startAnimation(865);		c.stopMovement();
            break;
            case 166:
                c.startAnimation(866);		c.stopMovement();
            break;
            case 52050:
                c.startAnimation(2105);		c.stopMovement();
            break;
            case 52051:
                c.startAnimation(2106);		c.stopMovement();
            break;
            case 52052:
                c.startAnimation(2107);		c.stopMovement();
            break;
            case 52053:
                c.startAnimation(2108);		c.stopMovement();
            break;
            case 52054:
                c.startAnimation(2109);		c.stopMovement();
            break;
            case 52055:
                c.startAnimation(2110);		c.stopMovement();
            break;
            case 52056:
                c.startAnimation(2111);		c.stopMovement();
            break;
            case 52057:
                c.startAnimation(2112);		c.stopMovement();
            break;
            case 52058:
                c.startAnimation(2113);		c.stopMovement();
            break;
            case 43092:
                c.startAnimation(0x558);		c.stopMovement();
				c.gfx0(574);
            break;
            case 2155:
                c.startAnimation(11044);		c.stopMovement();
				c.gfx0(1973);
            break;
            case 25103:
                c.startAnimation(10530);		c.stopMovement();
				c.gfx0(1864);
            break;
            case 25106:
				c.startAnimation(8770);
				c.gfx0(1553);		c.stopMovement();
            break;
            case 2154:
                c.startAnimation(7531);		c.stopMovement();
            break;
            case 52071:
                c.startAnimation(0x84F);		c.stopMovement();
            break;
            case 52072:
                c.startAnimation(0x850);		c.stopMovement();
            break;
            case 73003:
		c.startAnimation(6111);	c.stopMovement();
            break;
            case 73001:
                c.startAnimation(3544);		c.stopMovement();
            break;
            case 73000:
			if(System.currentTimeMillis() - c.logoutDelay < 8000) {
c.sendMessage("You cannot do skillcape emotes in combat!");
return;
}
                c.startAnimation(3543);		c.stopMovement();
            break;
			case 72032:
				c.startAnimation(9990);		c.stopMovement();
				c.gfx0(1734);
            break;
			case 72033:
				c.startAnimation(4278);		c.stopMovement();
            break;
			case 59062:
				c.startAnimation(4280);		c.stopMovement();
            break;
			case 72254:
				c.startAnimation(4275);		c.stopMovement();
            break;
			case 73004:
				c.startAnimation(7272);		c.stopMovement();
				c.gfx0(1244);
            break;
			case 72255:
			if(System.currentTimeMillis() - c.logoutDelay < 8000) {
c.sendMessage("You cannot do skillcape emotes in combat!");		c.stopMovement();
return;
}
				c.startAnimation(2414);
				c.gfx0(1537);
			break;
			/* END OF EMOTES */
			case 118098:
			if(c.castVeng == 1) {
				c.sendMessage("You have already cast vengeance!");
			} else {
				c.getPA().castVeng();
			}
			break; 

case 33206:
c.outStream.createFrame(27);
c.attackSkill = true;
break;
case 33209:
c.outStream.createFrame(27);
c.strengthSkill = true;
break;
case 33212:
c.outStream.createFrame(27);
c.defenceSkill = true;
break;
case 33215:
c.outStream.createFrame(27);
c.rangeSkill = true;
break;
case 33218:
c.outStream.createFrame(27);
c.prayerSkill = true;
break;
case 33221:
c.outStream.createFrame(27);
c.mageSkill = true;
break;
case 33207:
c.outStream.createFrame(27);
c.healthSkill = true;
break;
			
			case 47130:
				c.forcedText = "I must slay another " + c.taskAmount + " " + Server.npcHandler.getNpcListName(c.slayerTask) + ".";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
			
			case 24017:
				c.getPA().resetAutocast();
				//c.sendFrame246(329, 200, c.playerEquipment[c.playerWeapon]);
				c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon], c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));
				//c.setSidebarInterface(0, 328);
				//c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : 1151);
			break;
		}
		if (c.isAutoButton(actionButtonId))
			c.assignAutocast(actionButtonId);
	}

}
