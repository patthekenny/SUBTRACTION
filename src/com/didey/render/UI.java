package com.didey.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.didey.main.SharedConstants;
import com.didey.states.Game;

public class UI {

	public static void renderHealth(GameContainer gc, Graphics g) {
		// In order to render correctly, need to get the width of the player's sprite, but
		// I can't at the moment, because I'm just debugging the player, it's sprite is not added.
		float healthBarX = Game.player.getCoords().x - (SharedConstants.healthBarWidth / 2.0f) + 16;
		float healthBarY = Game.player.getCoords().y + ((gc.getHeight() / 2.0f) - 50);

		g.setColor(Color.white);
		g.drawRect(healthBarX, healthBarY, SharedConstants.healthBarWidth, SharedConstants.healthBarHeight);

		g.setColor(Color.red);
		// These are fine, as an offset of one pixel will be pretty much the same on all displays.
		g.fillRect(healthBarX + 1, healthBarY + 1,
				((SharedConstants.healthBarWidth) / 100.0f) * Game.player.getHealthPoints() - 1,
				SharedConstants.healthBarHeight - 1);

		g.setColor(Color.white);
		String hpString = "HP: " + Float.toString((Game.player.getHealthPoints()));
		g.drawString(hpString,
				healthBarX + (SharedConstants.healthBarWidth / 2.0f) - (g.getFont().getWidth(hpString) / 2.0f),
				healthBarY + (SharedConstants.healthBarHeight / 2.0f) - (g.getFont().getHeight(hpString) / 2.0f));
	}

	public static void renderDebug(GameContainer gc, Graphics g) {
		float debugX = Game.player.getCoords().x - (gc.getWidth() / 2.0f) + (gc.getWidth() / 188.0f);
		float debugY = Game.player.getCoords().y - (gc.getHeight() / 2.0f) + (gc.getHeight() / 40.0f);
		
		g.setColor(Color.white);
		g.drawString("X: " + Float.toString(Game.player.getCoords().x), debugX, debugY);
		g.drawString("Y: " + Float.toString(Game.player.getCoords().y), debugX, debugY + (gc.getHeight() / 64.0f));
		g.drawString("MOUSEX: " + Float.toString(gc.getInput().getMouseX()), debugX, debugY + (gc.getHeight() / 64.0f) * 2);
		g.drawString("MOUSEY: " + Float.toString(gc.getInput().getMouseY()), debugX, debugY + (gc.getHeight() / 64.0f) * 3);
		
		
	}

}