package com.didey.physics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.world.WorldManager;

public class Raycast {

	private float range;
	private float x1, y1, x2, y2;
	private float angle, deltaX, deltaY;
	private boolean isColliding;

	public Raycast(float x1, float y1, float x2, float y2, float range) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		// Do a squared range comparison, it's cheaper.
		this.range = range * range;
		angle = (float) Math.atan2(y2 - y1, x2 - x1);
		deltaX = (float) Math.cos(angle);
		deltaY = (float) Math.sin(angle);
	}

	public void updateRay(GameContainer gc, StateBasedGame sbg, int delta) {
		while (lengthSquared() <= range) {
			x2 += deltaX;
			y2 += deltaY;
			if(WorldManager.checkPointInSolidObject(x2, y2)) {
				break;
			}
		}
	}

	public void draw(Graphics g) {
		g.drawLine(x1, y1, x2, y2);
	}

	public float lengthSquared() {
		return ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1));
	}

	public void set(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public float getX1() {
		return x1;
	}

	public void setX1(float x1) {
		this.x1 = x1;
	}

	public float getY1() {
		return y1;
	}

	public void setY1(float y1) {
		this.y1 = y1;
	}

	public float getX2() {
		return x2;
	}

	public void setX2(float x2) {
		this.x2 = x2;
	}

	public float getY2() {
		return y2;
	}

	public void setY2(float y2) {
		this.y2 = y2;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public float getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(float deltaX) {
		this.deltaX = deltaX;
	}

	public float getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(float deltaY) {
		this.deltaY = deltaY;
	}

	public boolean isColliding() {
		return isColliding;
	}

	public void setColliding(boolean isColliding) {
		this.isColliding = isColliding;
	}


}
