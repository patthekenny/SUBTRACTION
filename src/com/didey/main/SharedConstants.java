package com.didey.main;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class SharedConstants {

	public static int healthBarWidth = 400;
	public static int healthBarHeight = 30;
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static SpriteSheet worldSheet;
	public static int TILE_WIDTH = 32;
	public static int TILE_HEIGHT = 32;
	
	
	public static void initValues() {
		try {
			worldSheet = new SpriteSheet("resources/sheets/world/world.png", 32, 32);
		} catch (SlickException e) {
			e.printStackTrace();
		}		
	}
	
}
