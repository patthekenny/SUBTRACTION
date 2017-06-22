package com.didey.entities;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class TestEnemy extends EnemyCharacter {

	public TestEnemy(Vector2f position, float normalSpeed, float currentSpeed, EnemyCharacterID id, float rotationTheta,
			int animationSpeed, LinkedList<Vector2f> path) {
		super(position, normalSpeed, currentSpeed, id, rotationTheta, animationSpeed, path);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(getPosition().x, getPosition().y, 32, 32);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
	}

}
