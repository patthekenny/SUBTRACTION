package com.didey.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public abstract class WorldObject {
	
	private Vector2f position;
	private float width, height;
	private Color color;
	private final WorldObjectID id;
	
	public abstract void render(Graphics g);

	public abstract void update(GameContainer gc, StateBasedGame sbg, int delta);
	
	public WorldObject(Vector2f position, float width, float height, Color color, WorldObjectID id, int spriteCol, int spriteRow) {
		this.position = position;
		this.width = width;
		this.height = height;
		this.color = color;
		this.id = id;
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

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
