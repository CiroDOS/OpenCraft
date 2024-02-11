package net.opencraft.renderer.screens;

import static net.opencraft.renderer.RenderDragon.*;

import java.awt.Graphics;
import net.opencraft.renderer.display.Display;

public class ConfigScreen extends Screen {

	public ConfigScreen(Display display) {
		super(display);
	}

	@Override
	public void render(Graphics g) {
		final int width = display.getWidth();
		final int height = display.getHeight();
		

		for (int x = 0; x < width; x += 128) {
			for (int y = 0; y < height; y += 128) {
				g.drawImage(bindTexture("/gui/options_background.png"), x, y, 128, 128, display);
			}
		}
		
		g.drawImage(bindTexture("/gui/widgets.png"), 50, 50, 300, 89, 0, 66, 200, 86, display);
		
	}
	
	@Override
	public void init() {
		super.init();
		display.setResizable(false);
	}

}
