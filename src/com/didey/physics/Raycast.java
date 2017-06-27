package com.didey.physics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.world.WorldManager;

public class Raycast {

	private int range;
	private float x1, y1, x2, y2;
	private float angle, deltaX, deltaY;
	private Line line;
	private boolean isColliding;
	
	public Raycast(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		line = new Line(x1, y1, x2, y2);
	}
	
	public void updateRay(GameContainer gc, StateBasedGame sbg, int delta) {
		isColliding = WorldManager.checkColliding(line);
		if(!isColliding) {
			line.set(x1, y1, x2 + , ey);			
		}
	}
	
}
