package net.opencraft.renderer.screens;

import java.awt.Graphics;

import net.opencraft.renderer.display.Display;

public abstract class Screen {
	
	protected final Display display;
	
	public Screen(Display display) {
		this.display = display;
	}
	
	public abstract void render(Graphics g);
	
	public void init() {
		display.defaultConfig();
	}
	
}
