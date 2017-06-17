package com.didey.states;

import java.io.File;
import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.world.WorldManager;

public class LevelSelectionState extends BasicGameState {

	private File[] levels;
	private LinkedList<Rectangle> levelRects = new LinkedList<Rectangle>();
		
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// Create an array of all files in the levels folder.
		levels = new File("resources/levels/").listFiles();
		for(int i = 0; i < levels.length; i++) {
			// Create a clickable rectangle for every file.
			levelRects.add(new Rectangle(30 + (i * 40), 40, 32, 32));
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.white);
		for(Rectangle r : levelRects) {
			g.draw(r);
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(gc.getInput().isMouseButtonDown(0)) {
			// If the mouse is down, check if it's clicking on any level boxes.
			for(Rectangle r : levelRects) {
				// Really weird hack because slick2d does mouse coordinates really weird on the Y axis.
				if(r.contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
					WorldManager.loadLevel("resources/levels/" + Integer.toString(levelRects.indexOf(r) + 1) + ".png");
					sbg.enterState(2);
				}
			}
		}
	}

	@Override
	public int getID() {
		return 1;
	}

}
