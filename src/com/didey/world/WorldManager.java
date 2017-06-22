package com.didey.world;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.entities.EntityManager;
import com.didey.main.SharedConstants;
import com.didey.states.Game;

public class WorldManager {

	public static LinkedList<WorldObject> worldObjects = new LinkedList<WorldObject>();

	public static void loadLevel(String levelPath) {
		try {
			Image image = new Image(levelPath);
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					Color scanColor = image.getColor(x, y);
					int r = (int) scanColor.r * 255;
					int g = (int) scanColor.g * 255;
					int b = (int) scanColor.b * 255;

					float test = scanColor.r * 255;
					
					// What you are about to see is not pretty, abandon hope all
					// who enter here.

					if (test <= 50 && test != 0) {
						EntityManager.generateEntity(x, y, r, g, b, image);
					}

					if (r == 255 && g == 0 && b == 0) {
						Game.player.setX(x * SharedConstants.TILE_WIDTH);
						Game.player.setY(y * SharedConstants.TILE_HEIGHT);
					} else if (r == 0 && g == 255 && b == 0) {
						addObjectToWorld(new WallObject(
								new Vector2f(x * SharedConstants.TILE_WIDTH, y * SharedConstants.TILE_HEIGHT),
								WorldObjectID.WALL, 0, 0, true));
					}

				}
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	// Removes all world items AND all entities.
	public static void clearCurrentLevel() {
		worldObjects.clear();
		EntityManager.entities.clear();
	}

	public static void renderObjects(Graphics g) {
		for (int i = 0; i < worldObjects.size(); i++) {
			if (worldObjects.get(i) == null || worldObjects.get(i).isRenderable() == false)
				continue;

			worldObjects.get(i).render(g);
		}
	}

	public static void updateObjects(GameContainer gc, StateBasedGame sbg, int delta) {
		for (int i = 0; i < worldObjects.size(); i++) {
			worldObjects.get(i).update(gc, sbg, delta);
			checkIsRenderable();
		}
	}

	public static void checkIsRenderable() {
		for (int i = 0; i < worldObjects.size(); i++) {
			worldObjects.get(i)
					.setRenderable(worldObjects.get(i).getHitbox().intersects(Game.cameraHandler.getVisionRect()));
		}
	}

	public static boolean checkColliding(Shape shape) {
		for (int i = 0; i < worldObjects.size(); i++) {
			if (!worldObjects.get(i).isSolid())
				continue;
			if (worldObjects.get(i).getHitbox().intersects(shape)) {
				return true;
			}
		}
		return false;
	}

	public static void addObjectToWorld(WorldObject object) {
		worldObjects.add(object);
	}

	public static void removeObjectFromWorld(WorldObject object) {
		worldObjects.remove(object);
	}
}
