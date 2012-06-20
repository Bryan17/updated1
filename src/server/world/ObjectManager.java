package server.world;

import java.util.ArrayList;

import server.model.objects.Object;
import server.util.Misc;
import server.model.players.Client;
import server.Server;

/**
 * @author Sanity
 */

public class ObjectManager {

	public ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<Object> toRemove = new ArrayList<Object>();
	public void process() {
		for (Object o : objects) {
			if (o.tick > 0)
				o.tick--;
			else {
				updateObject(o);
				toRemove.add(o);
			}		
		}
		for (Object o : toRemove) {
			if (isObelisk(o.newId)) {
				int index = getObeliskIndex(o.newId);
				if (activated[index]) {
					activated[index] = false;
					teleportObelisk(index);
				}
			}
			objects.remove(o);	
		}
		toRemove.clear();
	}
	
	public void removeObject(int x, int y) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(-1, x, y, 0, 10);			
			}	
		}	
	}
	
	public void updateObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(o.newId, o.objectX, o.objectY, o.face, o.type);			
			}	
		}	
	}
	
	public void placeObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				if (c.distanceToPoint(o.objectX, o.objectY) <= 60)
					c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
			}	
		}
	}
	
	public Object getObject(int x, int y, int height) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y && o.height == height)
				return o;
		}	
		return null;
	}
	
	public void loadObjects(Client c) {
		if (c == null)
			return;
		for (Object o : objects) {
			if (loadForPlayer(o,c))
				c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
		}
		loadCustomSpawns(c);
		if (c.distanceToPoint(2813, 3463) <= 60) {
			c.getFarming().updateHerbPatch();
		}
	}
	
	private int[][] customObjects = {{}};
	public void loadCustomSpawns(Client c) {
		//c.getPA().checkObjectSpawn(9706, 3094, 3500, 2, 10);//PvP Lever
		
		c.getPA().checkObjectSpawn(6552, 3097, 3506, 0, 10);//Ancient Altar
		c.getPA().checkObjectSpawn(13193, 3094, 3506, 0, 10); // Curse Prayers
		c.getPA().checkObjectSpawn(3415, 3096, 3480, 0, 10);//TMD Stairs
		
		//STALLS
		c.getPA().checkObjectSpawn(2561, 2503, 4722, 0, 10);//bread stall[1]
		c.getPA().checkObjectSpawn(2560, 2511, 4722, 0, 10);//silk stall[25]
		c.getPA().checkObjectSpawn(2563, 2511, 4714, 0, 10);//fur stall[50]
		c.getPA().checkObjectSpawn(2564, 2503, 4714, 0, 10);//spice stall[75]
		c.getPA().checkObjectSpawn(2565, 2507, 4714, 0, 10);//silver stall[90]
		c.getPA().checkObjectSpawn(2562, 2507, 4722, 0, 10);//gem stall[99]
		//END OF STALLS*/
		
		//BANKS
		c.getPA().checkObjectSpawn(2213, 2501, 4719, -1, 10);
		c.getPA().checkObjectSpawn(2213, 2515, 4719, -1, 10);
		//END OF BANKS
		c.getPA().checkObjectSpawn(13615, 2036, 4518, 0, 10);
		c.getPA().checkObjectSpawn(13620, 2041, 4518, 0, 10);
		c.getPA().checkObjectSpawn(13619, 2031, 4518, 0, 10);

		c.getPA().checkObjectSpawn(6163, 2029, 4527, 1, 10);
		c.getPA().checkObjectSpawn(6165, 2029, 4529, 1, 10);
		c.getPA().checkObjectSpawn(6166, 2029, 4531, 1, 10);

		c.getPA().checkObjectSpawn(1596, 3008, 3850, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3008, 3849, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10307, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10308, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10311, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10312, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10341, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10342, 1, 0);
		c.getPA().checkObjectSpawn(2213, 3047, 9779, 1, 10);
		c.getPA().checkObjectSpawn(2213, 3080, 9502, 1, 10);
		//c.getPA().checkObjectSpawn(2403, 3201, 3424, 1, 10);RFD CHEST
        //X     Y     ID -> ID X Y
		c.getPA().checkObjectSpawn(2213, 2832, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2213, 2832, 3438, -1, 10);
		c.getPA().checkObjectSpawn(2213, 2832, 3437, -1, 10);
		c.getPA().checkObjectSpawn(2213, 2832, 3436, -1, 10);
		c.getPA().checkObjectSpawn(2213, 2832, 3435, -1, 10);
		c.getPA().checkObjectSpawn(2213, 2832, 3440, 0, 10);
		c.getPA().checkObjectSpawn(2213, 2855, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2090, 2839, 3440, -1, 10);
		c.getPA().checkObjectSpawn(2094, 2839, 3441, -1, 10);
		c.getPA().checkObjectSpawn(2092, 2839, 3442, -1, 10);
		c.getPA().checkObjectSpawn(2096, 2839, 3443, -1, 10);
		c.getPA().checkObjectSpawn(2102, 2839, 3444, -1, 10);
		c.getPA().checkObjectSpawn(2105, 2839, 3445, 0, 10);
		c.getPA().checkObjectSpawn(1276, 2843, 3442, 0, 10);
		c.getPA().checkObjectSpawn(1281, 2844, 3499, 0, 10);
		c.getPA().checkObjectSpawn(4156, 3083, 3440, 0, 10);
		c.getPA().checkObjectSpawn(1308, 2846, 3436, 0, 10);
		c.getPA().checkObjectSpawn(1309, 2846, 3439, -1, 10);
		c.getPA().checkObjectSpawn(1306, 2850, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2783, 2841, 3436, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2861, 3429, 0, 10);
		c.getPA().checkObjectSpawn(3044, 2857, 3427, -1, 10);
		
		c.getPA().checkObjectSpawn(-1, 3096, 3479, -1, 10);//General Short Ladder Removed
		c.getPA().checkObjectSpawn(-1, 3096, 3476, -1, 10);//General Store Table Removed
		c.getPA().checkObjectSpawn(-1, 3090, 3479, -1, 10);//General Store Table Removed
		c.getPA().checkObjectSpawn(-1, 3090, 3476, -1, 10);//General Store Crate Removed
		
		c.getPA().checkObjectSpawn(-1, 3077, 3512, -1, 10);//General Short Ladder Removed
		c.getPA().checkObjectSpawn(-1, 3081, 3510, -1, 10);//General Store Table Removed
		c.getPA().checkObjectSpawn(-1, 3078, 3510, -1, 10);//General Store Table Removed
		c.getPA().checkObjectSpawn(-1, 3080, 3510, -1, 10);//General Store Crate Removed
		c.getPA().checkObjectSpawn(-1, 3213, 3432, -1, 10);//home statue removed
		c.getPA().checkObjectSpawn(-1, 2844, 3440, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2846, 3437, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2840, 3439, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2841, 3443, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2851, 3438, -1, 10);
		c.getPA().checkObjectSpawn(-1, 3095, 3499, -1, 10);//chair at edge
		c.getPA().checkObjectSpawn(-1, 3095, 3498, -1, 10);//table at edge
		c.getPA().checkObjectSpawn(-1, 3092, 3496, -1, 10);//chair
		c.getPA().checkObjectSpawn(-1, 3091, 3495, -1, 10);//table
		c.getPA().checkObjectSpawn(-1, 3090, 3494, -1, 10);//chair
		c.getPA().checkObjectSpawn(-1, 3091, 3496, -1, 10);//chair
		c.getPA().checkObjectSpawn(-1, 3090, 3496, -1, 10);//chair
		
		/**Varrock Objects**/
		/**Varrock Removed Objects**/
		c.getPA().checkObjectSpawn(-1, 3180, 3442, -1, 10);//Varrock Plant
		c.getPA().checkObjectSpawn(-1, 3180, 3438, -1, 10);//Varrock Plant[1]
		c.getPA().checkObjectSpawn(-1, 3180, 3443, -1, 10);//Varrock Desk
		c.getPA().checkObjectSpawn(-1, 3180, 3433, -1, 10);//Varrock Bank Depos
		c.getPA().checkObjectSpawn(2213, 3094, 3514, 0, 10);//Edge Bank1
		c.getPA().checkObjectSpawn(2213, 3095, 3514, 0, 10);//Edge Bank2
		c.getPA().checkObjectSpawn(2213, 3096, 3514, 0, 10);//Edge Bank3
		
		/**MageBank	Added Objects**/
		c.getPA().checkObjectSpawn(2213, 2530, 4712, -1, 10);//Mb Bank
		c.getPA().checkObjectSpawn(2213, 2530, 4713, -1, 10);//Mb Bank
		c.getPA().checkObjectSpawn(2213, 2530, 4714, -1, 10);//Mb Bank
		c.getPA().checkObjectSpawn(2213, 2530, 4715, -1, 10);//Mb Bank
		c.getPA().checkObjectSpawn(2213, 2530, 4716, -1, 10);//Mb Bank
		c.getPA().checkObjectSpawn(2213, 2530, 4717, -1, 10);//Mb Bank
		c.getPA().checkObjectSpawn(6552, 2545, 4717, 1, 10);//Ancient Altar
		c.getPA().checkObjectSpawn(13193, 2545, 4712, 1, 10); // Curse Prayers
		c.getPA().checkObjectSpawn(409, 3091, 3514, 0, 10);//Prayer Altar
		c.getPA().checkObjectSpawn(409, 2538, 4717, 0, 10);//Prayer Altar

		
		/**MageBank Removed Objects**/
		c.getPA().checkObjectSpawn(-1, 2534, 4717, -1, 10);//Mb Removed
		c.getPA().checkObjectSpawn(-1, 2534, 4718, -1, 10);//Mb Removed
		c.getPA().checkObjectSpawn(-1, 2533, 4719, -1, 10);//Mb Removed
		c.getPA().checkObjectSpawn(-1, 2533, 4720, -1, 10);//Mb Removed
		c.getPA().checkObjectSpawn(-1, 2534, 4720, -1, 10);//Mb Removed
		c.getPA().checkObjectSpawn(-1, 2535, 4720, -1, 10);//Mb Removed
		c.getPA().checkObjectSpawn(-1, 2535, 4721, -1, 10);//Mb Removed
		c.getPA().checkObjectSpawn(-1, 2537, 4720, -1, 10);//Mb Removed
		c.getPA().checkObjectSpawn(-1, 2536, 4720, -1, 10);//Mb Removed
		c.getPA().checkObjectSpawn(-1, 2536, 4718, -1, 10);//Mb Removed
		c.getPA().checkObjectSpawn(-1, 2535, 4714, -1, 10);//Mb rock
		c.getPA().checkObjectSpawn(-1, 2538, 4713, -1, 10);//Mb rock
		c.getPA().checkObjectSpawn(-1, 2539, 4720, -1, 10);//Mb rock
		c.getPA().checkObjectSpawn(-1, 2543, 4712, -1, 10);//Mb rock
		c.getPA().checkObjectSpawn(-1, 2545, 4718, -1, 10);//Mb rock
		c.getPA().checkObjectSpawn(-1, 2531, 4713, -1, 10);//Mb rock
		c.getPA().checkObjectSpawn(-1, 2543, 4715, -1, 10);//Mb rock

	 if (c.heightLevel == 0) {
			c.getPA().checkObjectSpawn(2492, 2911, 3614, 1, 10);
		 }else{
			c.getPA().checkObjectSpawn(-1, 2911, 3614, 1, 10);
	}
	}
	
	public final int IN_USE_ID = 14825;
	public boolean isObelisk(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return true;
		}
		return false;
	}
	public int[] obeliskIds = {14829,14830,14827,14828,14826,14831};
	public int[][] obeliskCoords = {{3154,3618},{3225,3665},{3033,3730},{3104,3792},{2978,3864},{3305,3914}};
	public boolean[] activated = {false,false,false,false,false,false};
	
	public void startObelisk(int obeliskId) {
		int index = getObeliskIndex(obeliskId);
		if (index >= 0) {
			if (!activated[index]) {
				activated[index] = true;
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
			}
		}	
	}
	
	public int getObeliskIndex(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return j;
		}
		return -1;
	}
	
	public void teleportObelisk(int port) {
		int random = Misc.random(5);
		while (random == port) {
			random = Misc.random(5);
		}
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				int xOffset = c.absX - obeliskCoords[port][0];
				int yOffset = c.absY - obeliskCoords[port][1];
				if (c.goodDistance(c.getX(), c.getY(), obeliskCoords[port][0] + 2, obeliskCoords[port][1] + 2, 1)) {
					c.getPA().startTeleport2(obeliskCoords[random][0] + xOffset, obeliskCoords[random][1] + yOffset, 0);
				}
			}		
		}
	}
	
	public boolean loadForPlayer(Object o, Client c) {
		if (o == null || c == null)
			return false;
		return c.distanceToPoint(o.objectX, o.objectY) <= 60 && c.heightLevel == o.height;
	}
	
	public void addObject(Object o) {
		if (getObject(o.objectX, o.objectY, o.height) == null) {
			objects.add(o);
			placeObject(o);
		}	
	}




}