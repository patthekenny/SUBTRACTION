package com.didey.entities;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.main.SharedConstants;
import com.didey.physics.Raycast;
import com.didey.render.AnimationState;
import com.didey.world.WorldManager;

public abstract class EnemyCharacter {

	protected Vector2f position;
	protected float normalSpeed, currentSpeed;
	protected int animationWidth, animationHeight;
	protected EnemyCharacterID id;
	protected float rotationTheta;
	protected AnimationState animationState = AnimationState.IDLE;
	protected int animationSpeed;
	protected int directionalTiles;
	protected Vector2f[] path;
	protected MovementDirection movementDirection;
	protected int currentTarget = 1;
	protected int moveTimer = 0;
	protected String sheetPath;
	protected Animation animation;
	protected LinkedList<Raycast> vision = new LinkedList<Raycast>();

	private Random rand = new Random();

	public abstract void render(Graphics g);

	public abstract void update(GameContainer gc, StateBasedGame sbg, int delta);

	public EnemyCharacter(Vector2f position, float normalSpeed, EnemyCharacterID id, int animationSpeed,
			int moveableTiles, String sheetPath, int animationWidth, int animationHeight, int visionRange) {
		this.position = position;
		this.normalSpeed = normalSpeed;
		this.currentSpeed = normalSpeed;
		this.id = id;
		this.rotationTheta = 0.0f;
		this.animationSpeed = animationSpeed;
		this.directionalTiles = moveableTiles;
		this.animationWidth = animationWidth;
		this.animationHeight = animationHeight;
		this.movementDirection = rand.nextInt(102) >= 50 ? MovementDirection.HORIZONTAL : MovementDirection.VERTICAL;
		this.path = generatePath();
		try {
			this.animation = new Animation(new SpriteSheet(sheetPath, animationWidth, animationHeight),
					this.animationSpeed);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		updateAnimationRotation();

		// Create vision lines.
		float centeredX = position.x + (this.animationWidth / 2f);
		float centeredY = position.y + (this.animationHeight / 2f);
		
		for (float i = 0; i < 10; i++) {
			float angle = (float)Math.toDegrees(rotationTheta + i);
			float dx = (float)Math.cos(angle);
			float dy = (float)Math.sin(angle);
			vision.add(new Raycast(centeredX, centeredY, centeredX + dx, centeredY + dy, 100f));
		}
		System.out.println(vision);
	}
	
	protected void updateVision(GameContainer gc, StateBasedGame sbg, int delta) {
		float centeredX = position.x + (this.animationWidth / 2f);
		float centeredY = position.y + (this.animationHeight / 2f);
		
		
		for (int i = 0; i < vision.size(); i++) {
			float angle = (float)Math.toDegrees(rotationTheta + i);
			float dx = (float)Math.cos(angle);
			float dy = (float)Math.sin(angle);
			vision.get(i).set(centeredX, centeredY, centeredX + dx, centeredY + dy);
		}
		
		for(Raycast c : vision) {
			c.updateRay(gc, sbg, delta);
		}
		
	}

	protected void updateAnimationRotation() {
		float deltaX = path[currentTarget].x - position.x;
		float deltaY = path[currentTarget].y - position.y;
		float theta = (float) Math.toDegrees(Math.atan2(deltaX, deltaY));

		rotationTheta = theta;
		animation.getCurrentFrame().setRotation(rotationTheta);
	}

	protected void updateAnimation(int delta) {
		updateAnimationRotation();

		if (animationState == AnimationState.IDLE) {
			getAnimation().setCurrentFrame(0);
			getAnimation().stop();
		} else {
			getAnimation().start();
		}
	}

	protected Vector2f[] generatePath() {
		LinkedList<Vector2f> tempPath = new LinkedList<Vector2f>();
		tempPath.add(new Vector2f(position.x, position.y));

		switch (movementDirection) {
		default:
		case HORIZONTAL: {
			for (int i = 1; i <= directionalTiles; i++) {

				if (WorldManager.getObject((int) position.x + (SharedConstants.TILE_WIDTH * (i)),
						(int) position.y) != null
						&& EntityManager.getEnemy((int) position.x + (SharedConstants.TILE_WIDTH * (i)),
								(int) position.y) == null)
					continue;

				boolean temp = true;
				for (Vector2f v : tempPath) {
					if (v.x == (int) position.x + (SharedConstants.TILE_WIDTH * (i - 1))) {
						temp = false;
					}
				}

				if (temp)
					continue;

				tempPath.add(new Vector2f(position.x + (SharedConstants.TILE_WIDTH * i), position.y));
			}

			for (int ix = 1; ix <= directionalTiles; ix++) {
				if (WorldManager.getObject((int) position.x - (SharedConstants.TILE_WIDTH * (ix)),
						(int) position.y) != null
						&& EntityManager.getEnemy((int) position.x - (SharedConstants.TILE_WIDTH * (ix)),
								(int) position.y) == null)
					continue;

				boolean temp = true;
				for (Vector2f v : tempPath) {
					if (v.x == (int) position.x - (SharedConstants.TILE_WIDTH * (ix - 1))) {
						temp = false;
					}
				}
				if (temp)
					continue;
				tempPath.add(new Vector2f(position.x - (SharedConstants.TILE_WIDTH * ix), position.y));

			}

			Collections.sort(tempPath, new Comparator<Vector2f>() {
				public int compare(Vector2f v1, Vector2f v2) {
					return v1.x < v2.x ? -1 : v1.x == v2.x ? 0 : 1;
				}
			});

			break;
		}
		/*
		 * 
		 * 
		 * VERTICAL
		 * 
		 * 
		 */
		case VERTICAL: {
			for (int i = 1; i <= directionalTiles; i++) {

				if (WorldManager.getObject((int) position.x,
						(int) position.y + (SharedConstants.TILE_WIDTH * (i))) != null
						&& EntityManager.getEnemy((int) position.x,
								(int) position.y + (SharedConstants.TILE_WIDTH * (i))) == null)
					continue;

				boolean temp = true;
				for (Vector2f v : tempPath) {
					if (v.y == (int) position.y + (SharedConstants.TILE_WIDTH * (i - 1))) {
						temp = false;
					}
				}
				if (temp)
					continue;
				tempPath.add(new Vector2f(position.x, position.y + (SharedConstants.TILE_WIDTH * i)));

			}

			for (int ix = 1; ix <= directionalTiles; ix++) {
				if (WorldManager.getObject((int) position.x,
						(int) position.y - (SharedConstants.TILE_WIDTH * (ix))) != null
						&& EntityManager.getEnemy((int) position.x,
								(int) position.y - (SharedConstants.TILE_WIDTH * (ix))) == null)
					continue;

				boolean temp = true;
				for (Vector2f v : tempPath) {
					if (v.y == (int) position.y - (SharedConstants.TILE_WIDTH * (ix - 1))) {
						temp = false;
					}
				}
				if (temp)
					continue;
				tempPath.add(new Vector2f(position.x, position.y - (SharedConstants.TILE_WIDTH * ix)));

			}

			Collections.sort(tempPath, new Comparator<Vector2f>() {
				public int compare(Vector2f v1, Vector2f v2) {
					return v1.y < v2.y ? -1 : v1.y == v2.y ? 0 : 1;
				}
			});

			break;
		}
		}

		Vector2f[] tempVecs = { tempPath.get(0), tempPath.get(tempPath.size() - 1) };

		position.x = tempVecs[0].x;
		position.y = tempVecs[0].y;

		return tempVecs;
	}

	protected void updateMovement(int delta) {
		float angle = (float) Math.atan2(getPath()[getCurrentTarget()].y - getPosition().y,
				getPath()[getCurrentTarget()].x - getPosition().x);
		float xVel = getCurrentSpeed() * (float) Math.cos(angle) * (delta / 1000f);
		float yVel = getCurrentSpeed() * (float) Math.sin(angle) * (delta / 1000f);

		if ((float) Math.round(getPosition().x) == getPath()[(getCurrentTarget())].x
				&& (float) Math.round(getPosition().y) == getPath()[getCurrentTarget()].y) {
			animationState = AnimationState.IDLE;
			moveTimer += delta;
			if (moveTimer >= 1000) {
				setCurrentTarget((getCurrentTarget() == 0 ? 1 : 0));
				moveTimer = 0;
			}

		} else {
			animationState = AnimationState.MOVING;
			this.setX(getPosition().x + xVel);
			this.setY(getPosition().y + yVel);
		}

	}

	
	public LinkedList<Raycast> getVision() {
		return vision;
	}

	public void setVision(LinkedList<Raycast> vision) {
		this.vision = vision;
	}
	public int getDirectionalTiles() {
		return directionalTiles;
	}

	public void setDirectionalTiles(int directionalTiles) {
		this.directionalTiles = directionalTiles;
	}

	public MovementDirection getMovementDirection() {
		return movementDirection;
	}

	public void setMovementDirection(MovementDirection movementDirection) {
		this.movementDirection = movementDirection;
	}

	public String getSheetPath() {
		return sheetPath;
	}

	public void setSheetPath(String sheetPath) {
		this.sheetPath = sheetPath;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
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

	public Vector2f[] getPath() {
		return path;
	}

	public void setPath(Vector2f[] path) {
		this.path = path;
	}

	public int getCurrentTarget() {
		return currentTarget;
	}

	public void setCurrentTarget(int currentTarget) {
		this.currentTarget = currentTarget;
	}
}