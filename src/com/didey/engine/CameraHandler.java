package com.didey.engine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.states.Game;

public class CameraHandler {
	
	private Vector2f position;
	
	public CameraHandler(Vector2f position) {
		this.position = position;
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		position.x += ((-Game.player.getCoords().x + gc.getWidth() / 2.0) - position.x);
		position.y += ((-Game.player.getCoords().y + gc.getHeight() / 2.0) - position.y);
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
