package com.didey.entities;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.render.AnimationState;

public abstract class EnemyCharacter {

	private Vector2f position;
	private float normalSpeed, currentSpeed;
	private EnemyCharacterID id;
	private float rotationTheta;
	private AnimationState animationState = AnimationState.IDLE;
	private int animationSpeed;
	private LinkedList<Float> path;
	// This will basically be the minimum amount of tiles the enemy can go up/left, or right/down
	// (will stop early if going to hit a wall).
	private int minLeftUp, minRightDown;
	
	
	public abstract void render(Graphics g);

	public abstract void update(GameContainer gc, StateBasedGame sbg, int delta);

	public EnemyCharacter(Vector2f position, float normalSpeed, EnemyCharacterID id, int animationSpeed) {
		this.position = position;
		this.normalSpeed = normalSpeed;
		this.currentSpeed = normalSpeed;
		this.id = id;
		this.rotationTheta = 0.0f;
		this.animationSpeed = animationSpeed;
	
	}
	
	// Generate what kind of path I want, maybe a set amount of branch from left to right until it hits a solid object?
	
	public LinkedList<Float> path() {
		
		
		return null;
	}
	
	
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public float getNormalSpeed() {
		return normalSpeed;
	}

	public void setNormalSpeed(float normalSpeed) {
		this.normalSpeed = normalSpeed;
	}

	public float getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(float currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public EnemyCharacterID getId() {
		return id;
	}

	public void setId(EnemyCharacterID id) {
		this.id = id;
	}

	public float getRotationTheta() {
		return rotationTheta;
	}

	public void setRotationTheta(float rotationTheta) {
		this.rotationTheta = rotationTheta;
	}

	public AnimationState getAnimationState() {
		return animationState;
	}

	public void setAnimationState(AnimationState animationState) {
		this.animationState = animationState;
	}

	public int getAnimationSpeed() {
		return animationSpeed;
	}

	public void setAnimationSpeed(int animationSpeed) {
		this.animationSpeed = animationSpeed;
	}
}
