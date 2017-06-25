package com.didey.entities;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class EntityManager {

	public static ArrayList<EnemyCharacter> entities = new ArrayList<EnemyCharacter>();
	public static ArrayList<ArrayList<Integer>> enemyPaths = new ArrayList<ArrayList<Integer>>();

	public static void renderEntities(Graphics g) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) == null)
				continue;

			entities.get(i).render(g);
		}
	}

	public static void updateEntities(GameContainer gc, StateBasedGame sbg, int delta) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) == null)
				continue;

			entities.get(i).update(gc, sbg, delta);
		}
	}

	public static EnemyCharacter getEnemy(int x, int y) {
		for (EnemyCharacter c : entities) {
			if (c.getPosition().x == x && c.getPosition().y == y) {
				return c;
			}
		}
		return null;
	}

	public static void addEntity(EnemyCharacter ec) {
		entities.add(ec);
	}

	public static void removeEntity(EnemyCharacter ec) {
		entities.remove(ec);
	}
}
