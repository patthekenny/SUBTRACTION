package com.didey.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class TestEnemy extends EnemyCharacter {

	public TestEnemy(Vector2f position, float normalSpeed, EnemyCharacterID id, int animationSpeed, int movementTiles) {
		super(position, normalSpeed, id, animationSpeed, movementTiles);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		for(Vector2f v : this.getPath()) {
			g.drawRect(v.x, v.y, 32, 32);
		}
		g.setColor(Color.red);
		g.fillRect(getPosition().x, getPosition().y, 32, 32);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		//this.setX(getPosition().x - (getCurrentSpeed() * (delta / 1000.0f)));
	}

}
