package com.didey.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.main.SharedConstants;


public class MainMenuState extends BasicGameState {

	
	// To be continued...
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		SharedConstants.initValues();
		sbg.enterState(1);
	}
	
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return 0;
	}

}
