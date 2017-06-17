package com.didey.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.items.Item;
import com.didey.render.AnimationState;

public class TestCharacter extends ControllableCharacter {

	public TestCharacter(float maxHP, float maxEXP, float health, float exp, Vector2f position, float normalSpeed,
			String movementAnimationSheetPath, int animationWidth, int animationHeight, int animationSpeed,
			Item[] items, ControllableCharacterID id) {
		super(maxHP, maxEXP, health, exp, position, normalSpeed, movementAnimationSheetPath, animationWidth, animationHeight,
				animationSpeed, items, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {

		if (getCurrentAnimationState() == AnimationState.IDLE) {
			getAnimation().setCurrentFrame(0);
		}
		
		// This is to make sure that the idle frame isn't present in the animations when the player is moving.
		if(getAnimation().getCurrentFrame() == getAnimation().getImage(0) && getCurrentAnimationState() == AnimationState.MOVING) {
			getAnimation().setCurrentFrame(1);
		}
		
		getAnimation().draw(getCoords().x, getCoords().y);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {

		if (gc.getInput().isKeyDown(Input.KEY_A)) {
			setCurrentAnimationState(AnimationState.MOVING);
			setX(getCoords().x - (getMoveSpeed() * (delta / 1000.0f)));
		}
		if (gc.getInput().isKeyDown(Input.KEY_D)) {
			setCurrentAnimationState(AnimationState.MOVING);
			setX(getCoords().x + (getMoveSpeed() * (delta / 1000.0f)));
		}
		if (gc.getInput().isKeyDown(Input.KEY_W)) {
			setCurrentAnimationState(AnimationState.MOVING);
			setY(getCoords().y - (getMoveSpeed() * (delta / 1000.0f)));
		}
		if (gc.getInput().isKeyDown(Input.KEY_S)) {
			setCurrentAnimationState(AnimationState.MOVING);
			setY(getCoords().y + (getMoveSpeed() * (delta / 1000.0f)));
		}

		if (!gc.getInput().isKeyDown(Input.KEY_S) && !gc.getInput().isKeyDown(Input.KEY_W)
				&& !gc.getInput().isKeyDown(Input.KEY_D) && !gc.getInput().isKeyDown(Input.KEY_A)) {
			this.setCurrentAnimationState(AnimationState.IDLE);
		}

	}

	@Override
	public void handleControls(GameContainer gc, StateBasedGame sbg, int delta) {

	}

}
