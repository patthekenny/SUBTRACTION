package com.didey.world;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.states.Game;

public class WorldManager {

	public static LinkedList<WorldObject> worldObjects = new LinkedList<WorldObject>();

	public static void loadLevel(String levelPath) {
		try {
			Image image = new Image(levelPath);
			for(int x = 0; x < image.getWidth(); x++) {
				for(int y = 0; y < image.getHeight(); y++) {
					float r = image.getColor(x, y).r * 255;
					float g = image.getColor(x, y).g * 255;
					float b = image.getColor(x, y).b * 255;
					
					
					if(r == 255 && g == 0 && b == 0) {
						Game.player.setX(x* 32);
						Game.player.setY(y * 32);
					} else if(r == 0 && g == 255 && b == 0) {
						addObjectToWorld(new WallObject(new Vector2f(x * 32, y * 32), 32.0f, 32.0f, Color.gray, WorldObjectID.WALL));
					}
				}
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static void clearCurrentLevel() {
		worldObjects.clear();
	}
	
	public static void renderObjects(Graphics g) {
		for(int i = 0; i < worldObjects.size(); i++) {
			if(worldObjects.get(i) == null) continue;
			
			worldObjects.get(i).render(g);
		}
	}
	
	public static void updateObjects(GameContainer gc, StateBasedGame sbg, int delta) {
		for(int i = 0; i < worldObjects.size(); i++) {
			// Do the same check.
			if(worldObjects.get(i) == null) continue;
			
			worldObjects.get(i).update(gc, sbg, delta);
		}
	}
	
	public static void addObjectToWorld(WorldObject object) {
		worldObjects.add(object);
	}
	
	public static void removeObjectFromWorld(WorldObject object) {
		worldObjects.remove(object);
	}
}
