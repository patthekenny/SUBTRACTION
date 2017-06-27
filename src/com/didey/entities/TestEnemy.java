package com.didey.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class TestEnemy extends EnemyCharacter {


	public TestEnemy(Vector2f position, float normalSpeed, EnemyCharacterID id, int animationSpeed, int moveableTiles,
			String movementAnimationSheetPath, int animationWidth, int animationHeight, int visionRange) {
		super(position, normalSpeed, id, animationSpeed, moveableTiles, movementAnimationSheetPath, animationWidth,
				animationHeight, visionRange);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		for (Vector2f v : this.getPath()) {
			g.drawRect(v.x, v.y, 32, 32);
		}
		
		getAnimation().draw(getPosition().x, getPosition().y);
		g.setColor(Color.yellow);
		for(Shape s : getVisionRays()) {
			g.draw(s);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		updateMovement(delta);
		updateAnimation(delta);
		updateVision();
		
	}
}
