package com.didey.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.main.SharedConstants;

public abstract class WorldObject {
	
	private Vector2f position;
	private final WorldObjectID id;
	private Image sprite;
	private Shape hitbox;
	private boolean isSolid;
	private boolean isRenderable = true;
	
	public abstract void render(Graphics g);

	public abstract void update(GameContainer gc, StateBasedGame sbg, int delta);
	
	public WorldObject(Vector2f position, WorldObjectID id, int spriteX, int spriteY, boolean isSolid) {
		this.position = position;
		this.id = id;
		this.setSolid(isSolid);
		this.sprite = SharedConstants.worldSheet.getSprite(spriteX, spriteY);
		this.hitbox = new Rectangle(position.x, position.y, sprite.getWidth(), sprite.getHeight());
	}

	public WorldObjectID getID() {
		return id;
	}
	
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Shape getHitbox() {
		return hitbox;
	}

	public void setHitbox(Shape hitbox) {
		this.hitbox = hitbox;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public boolean isSolid() {
		return isSolid;
	}

	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	public boolean isRenderable() {
		return isRenderable;
	}

	public void setRenderable(boolean isRenderable) {
		this.isRenderable = isRenderable;
	}
}
