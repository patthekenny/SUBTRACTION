package com.didey.engine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.states.Game;

public class CameraHandler {
	
	private Vector2f position;
	private Rectangle visionRect;

	public CameraHandler(Vector2f position, GameContainer gc) {
		this.position = position;
		visionRect = new Rectangle(position.x, position.y, gc.getWidth(), gc.getHeight());
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		position.x += ((-Game.player.getCoords().x + gc.getWidth() / 2.0) - position.x);
		position.y += ((-Game.player.getCoords().y + gc.getHeight() / 2.0) - position.y);
		visionRect.setBounds(-position.x, -position.y, gc.getWidth(), gc.getHeight());
	}
	
	public Rectangle getVisionRect() {
		return visionRect;
	}

	public void setVisionRect(Rectangle visionRect) {
		this.visionRect = visionRect;
	}
	
	public void setX(float newX) {
		position.x = newX;
	}

	public void setY(float newY) {
		position.y = newY;
	}
	
	public Vector2f getCoords() {
		return position;
	}

	
	
}
