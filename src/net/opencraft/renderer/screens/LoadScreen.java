package net.opencraft.renderer.screens;

import static net.opencraft.renderer.RenderDragon.bindTexture;

import java.awt.Graphics;

import net.opencraft.renderer.display.Display;

public class LoadScreen extends Screen {

	public LoadScreen(Display display) {
		super(display);
	}

	@Override
	public void render(Graphics g) {
		int width = display.getWidth();
		int height = display.getHeight();
		
		g.clearRect(0, 0, width, height); // Empty the screen
		g.drawImage(bindTexture("/gui/title/background/loadscreen.jpg"), 0, 0, width, height, 0, 0, 855, 481, null);

	}

}
