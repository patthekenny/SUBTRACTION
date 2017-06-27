package com.didey.states;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.engine.CameraHandler;
import com.didey.entities.ControllableCharacterID;
import com.didey.entities.EntityManager;
import com.didey.entities.TestCharacter;
import com.didey.main.SharedValues;
import com.didey.render.UI;
import com.didey.world.WorldManager;

public class Game extends BasicGameState {

	public static TestCharacter player = new TestCharacter(100.0f, 100.0f, 100.0f, 100.0f, new Vector2f(100.0f, 100.0f),
			200, "resources/sheets/entities/testcharacter/movementsheet.png", 32, 32, 400, null,
			ControllableCharacterID.MAIN_PLAYER);
	
	public static CameraHandler cameraHandler;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		gc.setAlwaysRender(true);
		cameraHandler = new CameraHandler(new Vector2f(0.0f, 0.0f), gc);
		
		//EntityManager.addEntity(new TestEnemy(new Vector2f(400, 400), 100, 100, EnemyCharacterID.TEST_GUARD, 0.0f, 100, null));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.translate(cameraHandler.getCoords().x, cameraHandler.getCoords().y);
		g.setBackground(new Color(130, 151, 186));
		WorldManager.renderObjects(g);
		EntityManager.renderEntities(g);
		player.render(g);

		if (SharedValues.isDebug)
			UI.renderDebug(gc, g);
		
		
		
		g.translate(-cameraHandler.getCoords().x, -cameraHandler.getCoords().y);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		updatePlayerDirection(gc, sbg, delta);
		player.update(gc, sbg, delta);
		cameraHandler.update(gc, sbg, delta);
		WorldManager.updateObjects(gc, sbg, delta);
		EntityManager.updateEntities(gc, sbg, delta);
		player.getAnimation().update(delta);
		if (gc.getInput().isKeyPressed(Keyboard.KEY_F5)) {
			SharedValues.isDebug = !SharedValues.isDebug;
			System.out.println("DEBUG MODE " + (SharedValues.isDebug ? "IS NOW ON" : "IS NOW OFF"));
		}
	}

	// I could make a vector of the mouse and player coords and do the angle calculations there, keep this in mind for later.
	// Probably not, this kinda math is much less expensive.
	public void updatePlayerDirection(GameContainer gc, StateBasedGame sbg, int delta) {
		
		// Great SO post explaining the logic here:
		// https://stackoverflow.com/questions/10099895/java-slick2d-how-to-translate-mouse-coordinates-to-world-coordinates
		
		float deltaX = (gc.getInput().getMouseX() - cameraHandler.getCoords().x) - player.getCoords().x;
		float deltaY = (gc.getInput().getMouseY() - cameraHandler.getCoords().y) - player.getCoords().y;
		
		float theta = (float) Math.toDegrees(Math.atan2(deltaX, -deltaY));
		
		player.getAnimation().getCurrentFrame().setRotation(theta);
		
	}
	
	@Override
	public int getID() {
		return 2;
	}

}
