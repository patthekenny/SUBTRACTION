package com.didey.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class WallObject extends WorldObject {

	public WallObject(Vector2f position, float width, float height, Color color, WorldObjectID id) {
		super(position, width, height, color, id);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(getColor());
		g.fillRect(getPosition().x, getPosition().y, getWidth(), getHeight());
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		
	}

}
