package server.model.players.skills;

import server.Config;
import server.util.Misc;
import server.model.players.Client;
import server.Server;

/**
 * Thieving.java
 *
 * @author Balla_
 *
 **/
 
public class Thieving {
	
	private Client c;
		
	public Thieving(Client c) {
		this.c = c;
	}
	
	public void stealFromNPC(int id) {
		if (System.currentTimeMillis() - c.lastThieve < 2000)
			return;
		for (int j = 0; j < npcThieving.length; j++) {
			if (npcThieving[j][0] == id) {
				if (c.playerLevel[c.playerThieving] >= npcThieving[j][1]) {
					if (Misc.random(c.playerLevel[c.playerThieving] + 2 - npcThieving[j][1]) != 1) {
						c.getPA().addSkillXP(npcThieving[j][2] * Config.THIEVING_EXPERIENCE, c.playerThieving);
						c.getItems().addItem(995, npcThieving[j][3]);
						c.startAnimation(881);
						c.lastThieve = System.currentTimeMillis();
						c.sendMessage("You thieve some money...");
						break;
					} else {
						c.setHitDiff(npcThieving[j][4]);
						c.setHitUpdateRequired(true);
						c.playerLevel[3] -= npcThieving[j][4];
						c.getPA().refreshSkill(3);
						c.lastThieve = System.currentTimeMillis() + 2000;
						c.sendMessage("You fail to thieve the NPC.");
						break;
					}
				} else {
					c.sendMessage("You need a thieving level of " + npcThieving[j][1] + " to thieve from this NPC.");
				}
			}		
		}
	}
	
	public void stealFromStall(int id, int amount, int xp, int level) {
		int ra = Misc.random(10);
		if (System.currentTimeMillis() - c.lastThieve < 2500) {
			return;
		}
		if(c.getItems().freeSlots() == 0) {
			c.sendMessage("You don't have enough inventory space to steal anymore!");
			return;
		}
		if (c.playerLevel[c.playerThieving] >= level) {
			if(ra == 10) {
				c.sendMessage("You get caught trying to thieve the stall..");
				c.startAnimation(3679);
				if(c.playerLevel[3] <= 30) {
					appendHit(Misc.random(4), c);
				} else {
					appendHit(Misc.random(5), c);
					return;
				}
			}
			c.sendMessage("You attempt to steal something from the stall...");
			c.getItems().addItem(id, amount);
			c.getItems().addItem(1121, 1);
			c.startAnimation(832);
			c.getPA().addSkillXP(xp * Config.THIEVING_EXPERIENCE, c.playerThieving);
			c.lastThieve = System.currentTimeMillis();
			c.sendMessage("You steal a " + server.model.items.Item.getItemName(id) + ".");
			}		
		else if(c.playerLevel[17] < level) {
			c.sendMessage("You need a theiving level of "+level+" to theif from this stall.");
		}
	}

	public static void appendHit(int damage, Client c) {
		Server.playerHandler.players[c.playerId].setHitDiff(damage);
		Server.playerHandler.players[c.playerId].playerLevel[3] -= damage;
		c.getPA().refreshSkill(3);
		Server.playerHandler.players[c.playerId].setHitUpdateRequired(true);	
		Server.playerHandler.players[c.playerId].updateRequired = true;		
	}	
	//npc, level, exp, coin amount
	public int[][] npcThieving = {{1,1,8,200,1},{18,25,26,500,1},{9,40,47,1000,2},{26,55,85,1500,3},{20,70,152,2000,4},{21,80,273,3000,5}};

}