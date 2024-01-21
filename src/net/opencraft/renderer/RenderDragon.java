package net.opencraft.renderer;

import static net.opencraft.client.Game.NAME;
import static net.opencraft.client.Game.VERSION;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.opencraft.crash.CrashReport;
import net.opencraft.renderer.display.Display;
import net.opencraft.renderer.screens.MenuScreen;
import net.opencraft.renderer.screens.SbReferenceScreen;
import net.opencraft.util.Resource;
import net.opencraft.util.Screens;

public class RenderDragon extends Canvas {
	private static final long serialVersionUID = 1L; // Some java strange stuff, bruh
	private static Display display = new Display(NAME + " " + VERSION);

	private boolean initializated = false;
	private MenuScreen menuScreen = new MenuScreen(display);
	private SbReferenceScreen refScreen = new SbReferenceScreen(display);

	public void paint(Graphics g) {

		switch (Screens.getCurrentScreen()) {
		case TITLE_SCREEN:
			menuScreen.render(g);
			break;
		case SBREFERENCE_SCREEN:
			refScreen.render(g);
			break;
		}

	}

	public void init() {
		display.defaultConfig();

		display.add(this);
		display.setVisible(true);

		initializated = true;
	}

	public static Image bindTexture(String filepath) {
		Image image = null;
		try {
			image = ImageIO.read(
					RenderDragon.class.getClassLoader().getResourceAsStream("assets/minecraft/textures" + filepath));
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
		return initializated;
	}

	public static boolean mouseIn(int x, int y, int x2, int y2, Display d) {
		int mouse_x = (int) MouseInfo.getPointerInfo().getLocation().x;
		int mouse_y = (int) MouseInfo.getPointerInfo().getLocation().y;

		int relMouse_x = mouse_x - d.getX();
		int relMouse_y = mouse_y - d.getY();
		if (relMouse_x > d.getWidth())
			relMouse_x = d.getWidth();
		
		if (relMouse_y > d.getHeight())
			relMouse_y = d.getHeight();
		
		if (relMouse_x < 0)
			relMouse_x = 0;
		
		if (relMouse_y < 0)
			relMouse_y = 0;

		return (relMouse_x > x) && (relMouse_x < x2) && (relMouse_y > y) && (relMouse_y < y2);
	}

}
