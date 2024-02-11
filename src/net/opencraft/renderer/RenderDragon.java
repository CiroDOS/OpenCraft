package net.opencraft.renderer;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.opencraft.client.Game;
import net.opencraft.config.Workspace;
import net.opencraft.crash.CrashReport;
import net.opencraft.renderer.display.Display;
import net.opencraft.renderer.screens.ConfigScreen;
import net.opencraft.renderer.screens.LoadScreen;
import net.opencraft.renderer.screens.Screen;
import net.opencraft.renderer.screens.TitleScreen;
import net.opencraft.util.Resource;
import net.opencraft.util.Screens;

public class RenderDragon extends Canvas {
	private static final long serialVersionUID = 1L; // Some java strange stuff, bruh
	private static Display display = new Display(Game.TITLE);
	private static RenderDragon instance = null;

	
	private boolean initializated;
	private Screen titleScreen = new TitleScreen(display);
	private Screen configScreen = new ConfigScreen(display);
	private Screen loadScreen = new LoadScreen(display);

	public RenderDragon() {
		instance = this;
	}
	
	public void paint(Graphics g) {

		switch (Screens.getCurrentScreen()) {
			case TITLE_SCREEN:
				titleScreen.render(g);
				break;
			case CONFIG_SCREEN:
				configScreen.render(g);
				break;
			case LOAD_SCREEN:
				loadScreen.render(g);
				break;
		}
	}

	public void init() {
		display.defaultConfig();

		display.add(this);
		display.setVisible(true);
		display.pack();
	}

	public static Image bindTexture(String filepath) {
		Image image = null;
		try {
			image = ImageIO.read(new FileInputStream(Workspace.assetsDir + "/opencraft/textures" + filepath));
		} catch (Exception ignored) {
			InputStream in = new ByteArrayInputStream(Resource.MISSING_TEXTURE);
			try {
				image = ImageIO.read(in);
			} catch (IOException GGForYOU) {
				CrashReport.launchCorruptionMessage();
			}
		}

		return image;
	}

	public void tick() {
		render();
	}

	public Graphics forceGraphics() {
		if (display.getGraphics() != null)
			return display.getGraphics();
		if (getGraphics() != null)
			return getGraphics();
		return null;
	}

	public void render() {
		paint(forceGraphics());
	}

	public Display getDisplay() {
		if (!isInitializated())
			throw new IllegalStateException();

		return display;
	}

	public boolean isInitializated() {
		return instance != null && initializated;
	}
	
	public static void initScreen(Screens screen) {
		switch (screen) {
			case TITLE_SCREEN:
				instance.titleScreen.init();
				break;
				
				default:
		}
	}

}
