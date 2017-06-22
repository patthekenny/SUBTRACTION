package com.didey.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class WallObject extends WorldObject {

	public WallObject(Vector2f position, WorldObjectID id, int spriteX, int spriteY, boolean isSolid) {
		super(position, id, spriteX, spriteY, isSolid);
	}

	@Override
	public void render(Graphics g) {
		getSprite().draw(getPosition().x, getPosition().y);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		
	}

}
