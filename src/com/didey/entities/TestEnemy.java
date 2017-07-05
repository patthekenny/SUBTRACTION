package com.didey.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.physics.Raycast;

public class TestEnemy extends EnemyCharacter {

	public TestEnemy(Vector2f position, float normalSpeed, EnemyCharacterID id, int animationSpeed, int moveableTiles,
			String movementAnimationSheetPath, int animationWidth, int animationHeight, int visionRange) {
		super(position, normalSpeed, id, animationSpeed, moveableTiles, movementAnimationSheetPath, animationWidth,
				animationHeight, visionRange);
	}

	@Override
	public void render(Graphics g) {
		getAnimation().draw(getPosition().x, getPosition().y);
		
		for (Raycast s : getVision()) {
			g.setColor(s.isColliding() ? Color.red : Color.yellow);
			s.draw(g);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		updateMovement(delta);
		updateAnimation(delta);
		updateVision(gc, sbg, delta);

	}
}
