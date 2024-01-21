package net.opencraft.renderer.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import net.opencraft.renderer.display.Display;

public class SbReferenceScreen extends Screen {

	public SbReferenceScreen(Display display) {
		super(display);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(a(display.getWidth(), display.getHeight()), 0, 0, null);
		display.setIconImage(null);
		display.setTitle("SB REFERENCE SCREEN v0.99.2 - SIBERMATICA OPENCRAFT INTEGRATED TEST SYSTEM");
	}

	private Image a(int b, int c) {
		BufferedImage d = new BufferedImage(b, c, 1);
		for (int x = 0; x < b; x++) {
			for (int y = 0; y < c; y++) {
				int e = (x*y) & 0xFF / 12;
				
				if (e > 255) e = 255;
				if (e < 0) e = 0;
				
				e = i(e, false);
				d.setRGB(x, y, new Color(e, e, e).getRGB());
			}
		}
		
		return d;
	}
	
	private int i(int e, boolean i1) {
		return i1 ? 255 - e : e;
	}

}
