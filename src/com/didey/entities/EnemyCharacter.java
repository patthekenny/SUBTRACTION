package com.didey.entities;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.main.SharedConstants;
import com.didey.render.AnimationState;
import com.didey.world.WorldManager;

public abstract class EnemyCharacter {

	private Random rand = new Random();

	private Vector2f position;
	private float normalSpeed, currentSpeed;
	private EnemyCharacterID id;
	private float rotationTheta;
	private AnimationState animationState = AnimationState.IDLE;
	private int animationSpeed;
	private LinkedList<Vector2f> path;
	// This will basically be the minimum amount of tiles the enemy can go
	// up/left, or right/down
	// (will stop early if going to hit a wall).
	private int directionalTiles;
	private MovementDirection movementDirection;

	public abstract void render(Graphics g);

	public abstract void update(GameContainer gc, StateBasedGame sbg, int delta);

	public EnemyCharacter(Vector2f position, float normalSpeed, EnemyCharacterID id, int animationSpeed,
			int moveableTiles) {
		this.position = position;
		this.normalSpeed = normalSpeed;
		this.currentSpeed = normalSpeed;
		this.id = id;
		this.rotationTheta = 0.0f;
		this.animationSpeed = animationSpeed;
		this.directionalTiles = moveableTiles;
		// this.movementDirection = rand.nextInt(102) >= 50 ?
		// MovementDirection.HORIZONTAL : MovementDirection.VERTICAL;
		this.movementDirection = MovementDirection.HORIZONTAL;
		this.path = generatePath();
	}

	// Can refactor this code to be more memory efficient(make the points two
	// vectors, not however many).

	public LinkedList<Vector2f> generatePath() {
		LinkedList<Vector2f> tempPath = new LinkedList<Vector2f>();

		switch (movementDirection) {
		default:
		case HORIZONTAL: {
			// Two ifs to not have to debug expressions... just too lazy.
			for (int i = 0; i < directionalTiles; i++) {
				boolean isColL = true;
				boolean isColR = true;
				
				if (WorldManager.getObject((int) (position.x - (SharedConstants.TILE_WIDTH * i)),
						(int) position.y) == null
						|| EntityManager.getEnemy((int) (position.x - (SharedConstants.TILE_WIDTH * i)),
								(int) position.y) != null) {
					
					for(Vector2f v : tempPath) {
						if((int) (position.x - (SharedConstants.TILE_WIDTH * (i - 1))) == (int)v.x && (int)v.y == (int)position.y || EntityManager.getEnemy((int)(position.x - (SharedConstants.TILE_WIDTH * (i - 1))), (int)position.y) != null) {
							isColL = false;
						}
					}
					if(!isColL) {
						tempPath.add(new Vector2f(position.x - (SharedConstants.TILE_WIDTH * i), position.y));						
					}
				}
				if (WorldManager.getObject((int) (position.x + (SharedConstants.TILE_WIDTH * i)),
						(int) position.y) == null
						|| WorldManager.getObject((int) (position.x + (SharedConstants.TILE_WIDTH * i)),
								(int) position.y) != null) {
					tempPath.add(new Vector2f(position.x + (SharedConstants.TILE_WIDTH * i), position.y));
				}
			}
			Collections.sort(tempPath, new Comparator<Vector2f>() {
				public int compare(Vector2f v1, Vector2f v2) {
					return v1.x < v2.x ? -1 : v1.x == v2.x ? 0 : 1;
				}
			});

			break;
		}
		case VERTICAL: {
			for (int i = 0; i < directionalTiles; i++) {
				tempPath.add(new Vector2f(position.x, position.y - (SharedConstants.TILE_HEIGHT * i)));
				tempPath.add(new Vector2f(position.x, position.y + (SharedConstants.TILE_HEIGHT * i)));
			}
			break;
		}
		}

		System.out.println((this.movementDirection == MovementDirection.HORIZONTAL ? "HORIZONTAL: " : "VERTICAL: ")
				+ directionalTiles + " " + tempPath.toString());

		return tempPath;
	}

	public void setX(float newX) {
		position.x = newX;
	}

	public void setY(float newY) {
		position.y = newY;
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

	public LinkedList<Vector2f> getPath() {
		return path;
	}

	public void setPath(LinkedList<Vector2f> path) {
		this.path = path;
	}
}
