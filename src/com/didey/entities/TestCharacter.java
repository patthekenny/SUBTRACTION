package com.didey.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.items.Item;
import com.didey.render.AnimationState;
import com.didey.world.WorldManager;

public class TestCharacter extends ControllableCharacter {

	public TestCharacter(float maxHP, float maxEXP, float health, float exp, Vector2f position, float normalSpeed,
			String movementAnimationSheetPath, int animationWidth, int animationHeight, int animationSpeed,
			Item[] items, ControllableCharacterID id) {
		super(maxHP, maxEXP, health, exp, position, normalSpeed, movementAnimationSheetPath, animationWidth,
				animationHeight, animationSpeed, items, id);
	}

	@Override
	public void render(Graphics g) {
		getAnimation().draw(getCoords().x, getCoords().y);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		updateHitbox();
		handleControls(gc, sbg, delta);
		getAnimation().update(delta);

		if (getCurrentAnimationState() == AnimationState.IDLE) {
			getAnimation().setCurrentFrame(0);
			getAnimation().stop();
		} else {
			getAnimation().start();			
		}
	}

	@Override
	public void updateHitbox() {

		// yall already know what the fuck goin on.
		float time = 4.0f;

		Image idle = this.getAnimation().getImage(0);

		this.setLeftHitbox(new Rectangle(getCoords().x, getCoords().y + (idle.getHeight() / 5f), idle.getWidth() / time,
				idle.getHeight() / 1.6f));

		this.setRightHitbox(new Rectangle(getCoords().x + idle.getWidth() - (idle.getWidth() / time), getCoords().y + (idle.getHeight() / 5f), idle.getWidth() / time,
				idle.getHeight() / 1.6f));

		this.setTopHitbox(new Rectangle(getCoords().x + (idle.getWidth() / 14f), getCoords().y, idle.getWidth() - (idle.getWidth() / 6f), idle.getHeight() / time));

		this.setBottomHitbox(new Rectangle(getCoords().x + (idle.getWidth() / 14f), getCoords().y + idle.getHeight() - (idle.getHeight() / time),
				idle.getWidth() - (idle.getWidth() / 6f), idle.getHeight() / time));

	}

	@Override
	public void handleControls(GameContainer gc, StateBasedGame sbg, int delta) {
		// Double check to make sure I'm not insane.
		if (gc.getInput().isKeyDown(Input.KEY_A) && !WorldManager.checkColliding(getLeftHitbox())) {
			setCurrentAnimationState(AnimationState.MOVING);
			setX(getCoords().x - (getMoveSpeed() * (delta / 1000.0f)));
		}
		if (gc.getInput().isKeyDown(Input.KEY_D) && !WorldManager.checkColliding(getRightHitbox())) {
			setCurrentAnimationState(AnimationState.MOVING);
			setX(getCoords().x + (getMoveSpeed() * (delta / 1000.0f)));
		}
		if (gc.getInput().isKeyDown(Input.KEY_W) && !WorldManager.checkColliding(getTopHitbox())) {
			setCurrentAnimationState(AnimationState.MOVING);
			setY(getCoords().y - (getMoveSpeed() * (delta / 1000.0f)));
		}
		if (gc.getInput().isKeyDown(Input.KEY_S) && !WorldManager.checkColliding(getBottomHitbox())) {
			setCurrentAnimationState(AnimationState.MOVING);
			setY(getCoords().y + (getMoveSpeed() * (delta / 1000.0f)));
		}

		if (!gc.getInput().isKeyDown(Input.KEY_S) && !gc.getInput().isKeyDown(Input.KEY_W)
				&& !gc.getInput().isKeyDown(Input.KEY_D) && !gc.getInput().isKeyDown(Input.KEY_A)) {
			this.setCurrentAnimationState(AnimationState.IDLE);
		}
	}

}
