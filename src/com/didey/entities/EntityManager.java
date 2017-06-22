package com.didey.entities;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.main.SharedConstants;

public class EntityManager {

	public static LinkedList<EnemyCharacter> entities = new LinkedList<EnemyCharacter>();
	
	public static void renderEntities(Graphics g) {
		for(int i = 0; i < entities.size(); i++) { 
			if(entities.get(i) == null) continue;
			
			entities.get(i).render(g);
		}
	}
	
	public static void updateEntities(GameContainer gc, StateBasedGame sbg, int delta) {
		for(int i = 0; i < entities.size(); i++) { 
			if(entities.get(i) == null) continue;
			
			entities.get(i).update(gc, sbg, delta);
		}
	}
	
	public static void generateEntity(int x, int y, int r, int g, int b, Image image) {
		if(r >= 50) return;
		LinkedList<Vector2f> path = new LinkedList<Vector2f>();
		System.out.println("here");

		path.add(new Vector2f(x * SharedConstants.TILE_WIDTH, y * SharedConstants.TILE_HEIGHT));
		
		for(int ix = 0; ix < image.getWidth(); ix++) {
			for(int iy = 0; iy < image.getHeight(); iy++) {
				Color c = image.getColor(ix, iy);
				int sr = (int) c.r * 255;
				int sg = (int) c.g * 255;
				int sb = (int) c.b * 255;
				
				if(sr == r && sg == g && sb == b - 1) {
					path.add(new Vector2f(ix * SharedConstants.TILE_WIDTH, iy * SharedConstants.TILE_HEIGHT));
				}
			}
		}
		
		switch(r) {
		default:
		case(1): {
			System.out.println(path.toString());
			addEntity(new TestEnemy(new Vector2f(x * SharedConstants.TILE_WIDTH, y * SharedConstants.TILE_HEIGHT), 100, 100, EnemyCharacterID.TEST_GUARD, 0.0f, 100, path));
			break;
		}
		}
	}
	
	public static void addEntity(EnemyCharacter ec) {
		entities.add(ec);
	}
	
	public static void removeEntity(EnemyCharacter ec) {
		entities.remove(ec);
	}
}
