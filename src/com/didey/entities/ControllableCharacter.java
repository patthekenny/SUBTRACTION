package com.didey.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.items.Item;
import com.didey.render.AnimationState;

public abstract class ControllableCharacter {

	private float experience, maxExperience;
	private float healthPoints, maxHealth;
	private float moveSpeed, currentSpeed;
	private Vector2f coords;
	private final ControllableCharacterID characterID;
	private Animation animation;
	// Sync animations to current angle of mouse in relation to the player.
	private float rotationTheta;
	private AnimationState currentAnimationState = AnimationState.IDLE;
	private int animationSpeed;

	private Rectangle leftHitbox, rightHitbox, topHitbox, bottomHitbox;
	private boolean collidingLeft, collidingRight, collidingTop, collidingBottom;

	private Item[] items;

	public abstract void render(Graphics g);

	public abstract void update(GameContainer gc, StateBasedGame sbg, int delta);

	public abstract void handleControls(GameContainer gc, StateBasedGame sbg, int delta);

	public abstract void updateHitbox();

	public ControllableCharacter(float maxHP, float maxEXP, float health, float exp, Vector2f position,
			float normalSpeed, String movementAnimationSheetPath, int animationWidth, int animationHeight,
			int animationSpeed, Item[] items, ControllableCharacterID id) {
		this.characterID = id;
		this.maxHealth = maxHP;
		this.maxExperience = maxEXP;
		this.healthPoints = health;
		this.experience = exp;
		this.animationSpeed = animationSpeed;
		this.coords = position;
		this.moveSpeed = normalSpeed;
		this.currentSpeed = normalSpeed;
		this.items = items;
		try {

			this.animation = (new Animation(
					new SpriteSheet(movementAnimationSheetPath, animationWidth, animationHeight), this.animationSpeed));
		} catch (SlickException e1) {
			e1.printStackTrace();
		}

		Image idle = this.animation.getImage(0);

		// Throwback.
		float time = 4.0f;

		this.leftHitbox = new Rectangle(coords.x, coords.y, idle.getWidth() / time, idle.getHeight());

		this.rightHitbox = new Rectangle(coords.x + idle.getWidth() - (idle.getWidth() / time), coords.y,
				idle.getWidth() / time, idle.getHeight());

		this.topHitbox = new Rectangle(coords.x, coords.y, idle.getWidth(), idle.getHeight() / time);

		this.bottomHitbox = new Rectangle(coords.x, coords.y + idle.getHeight() - (idle.getHeight() / time),
				idle.getWidth(), idle.getHeight() / time);
	}

	public boolean isPointInHitboxes(float x, float y) {
		return this.rightHitbox.contains(x, y) || this.bottomHitbox.contains(x, y) || this.topHitbox.contains(x, y) || this.leftHitbox.contains(x, y);
	}
	
	public boolean isCollidingLeft() {
		return collidingLeft;
	}

	public void setCollidingLeft(boolean collidingLeft) {
		this.collidingLeft = collidingLeft;
	}

	public boolean isCollidingRight() {
		return collidingRight;
	}

	public void setCollidingRight(boolean collidingRight) {
		this.collidingRight = collidingRight;
	}

	public boolean isCollidingTop() {
		return collidingTop;
	}

	public void setCollidingTop(boolean collidingTop) {
		this.collidingTop = collidingTop;
	}

	public boolean isCollidingBottom() {
		return collidingBottom;
	}

	public void setCollidingBottom(boolean collidingBottom) {
		this.collidingBottom = collidingBottom;
	}

	public Rectangle getLeftHitbox() {
		return leftHitbox;
	}

	public void setLeftHitbox(Rectangle leftHitbox) {
		this.leftHitbox = leftHitbox;
	}

	public Rectangle getRightHitbox() {
		return rightHitbox;
	}

	public void setRightHitbox(Rectangle rightHitbox) {
		this.rightHitbox = rightHitbox;
	}

	public Rectangle getTopHitbox() {
		return topHitbox;
	}

	public void setTopHitbox(Rectangle topHitbox) {
		this.topHitbox = topHitbox;
	}

	public Rectangle getBottomHitbox() {
		return bottomHitbox;
	}

	public void setBottomHitbox(Rectangle bottomHitbox) {
		this.bottomHitbox = bottomHitbox;
	}

	public float getRotationTheta() {
		return rotationTheta;
	}

	public void setRotationTheta(float rotationTheta) {
		this.rotationTheta = rotationTheta;
	}

	public ControllableCharacterID getCharacterID() {
		return characterID;
	}

	public void setX(float newX) {
		coords.x = newX;
	}

	public void setY(float newY) {
		coords.y = newY;
	}

	public float getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(float healthPoints) {
		this.healthPoints = healthPoints;
	}

	public float getExperience() {
		return experience;
	}

	public void setExperience(float experience) {
		this.experience = experience;
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	public float getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}

	public float getMaxExperience() {
		return maxExperience;
	}

	public void setMaxExperience(float maxExperience) {
		this.maxExperience = maxExperience;
	}

	public float getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public float getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(float currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public Vector2f getCoords() {
		return coords;
	}

	public void setCoords(Vector2f coords) {
		this.coords = coords;
	}

	public AnimationState getCurrentAnimationState() {
		return currentAnimationState;
	}

	public void setCurrentAnimationState(AnimationState currentAnimationState) {
		this.currentAnimationState = currentAnimationState;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public int getAnimationSpeed() {
		return animationSpeed;
	}

	public void setAnimationSpeed(int animationSpeed) {
		this.animationSpeed = animationSpeed;
	}

}
