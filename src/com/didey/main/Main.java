package com.didey.main;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.didey.states.Game;
import com.didey.states.LevelSelectionState;
import com.didey.states.MainMenuState;

public class Main extends StateBasedGame {

	public Main(String name) {
		super(name);
	}

	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new MainMenuState());
		this.addState(new LevelSelectionState());
		this.addState(new Game());	
	}

	public static void main(String[] args) {
		try {
			CanvasGameContainer agc;
			agc = new CanvasGameContainer(new Main("ADDITION"));
			agc.setSize(new Dimension(SharedConstants.WIDTH, SharedConstants.HEIGHT));
			JFrame frame = new JFrame("HOTLINE - DIDEY");
			frame.setSize(new Dimension(SharedConstants.WIDTH, SharedConstants.HEIGHT));
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					frame.dispose();
					System.exit(0);
				}
			});
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(agc);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			agc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
}
