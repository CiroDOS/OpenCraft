package net.opencraft.renderer.screens;

import static net.opencraft.renderer.RenderDragon.bindTexture;
import static net.opencraft.renderer.RenderDragon.mouseIn;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Calendar;

import net.opencraft.client.Game;
import net.opencraft.config.GameConfig;
import net.opencraft.language.ComponentTranslator;
import net.opencraft.renderer.display.Display;
import net.opencraft.util.Resource;
import net.opencraft.util.Screens;

public class MenuScreen extends Screen {

	private final Image logo = bindTexture(
			"/gui/title/" + ((GameConfig.LOGO_ID == 5) ? "minceraft" : "minecraft") + ".png");

	public static final Resource RESOURCE = Screens.TITLE_SCREEN.toResource();
	public static final Screens ID = Screens.TITLE_SCREEN;

	public MenuScreen(Display display) {
		super(display);
	}

	@Override
	public void render(Graphics g) {

		int width = display.getWidth();
		int height = display.getHeight();

		final int center_x = width / 2;
		final int center_y = height / 2;

		g.setPaintMode();
		g.setColor(Color.BLACK);

		if (!GameConfig.UNICODE_FONT)
			g.setFont(new Font("Mojangles", Font.PLAIN, 18));

		g.drawImage(bindTexture("/gui/title/background/background.jpg"), 0, 0, width, height, null);

		boolean button1_hovered = mouseIn((center_x * 2 - 400) / 2, center_y - 50, center_x + 199, center_y - 5,
				display);

		if (button1_hovered) {
			g.drawImage(bindTexture("/gui/widgets.png"), (center_x * 2 - 400) / 2, center_y - 50, center_x + 199,
					center_y - 5, 0, 86, 200, 106, null);
		} else {
			g.drawImage(bindTexture("/gui/widgets.png"), (center_x * 2 - 400) / 2, center_y - 50, center_x + 199,
					center_y - 5, 0, 66, 200, 86, null);
		}

		g.drawImage(bindTexture("/gui/widgets.png"), (center_x * 2 - 400) / 2, center_y, center_x + 199, center_y + 45,
				0, 66, 200, 86, null);

		g.drawImage(logo, (center_x * 2 - 700) / 2, (GameConfig.DEFAULT_RESOLUTION.height > height) ? 10 : 30, 700, 200,
				null);

		g.setColor(Color.WHITE);

		final String COPYLEFT = String.format("CopyLeft (-C) Sibermatica 2023-%d",
				Calendar.getInstance().get(Calendar.YEAR));

		// drawText(g, copyright, frame.getWidth() - 210, frame.getHeight() - 70, 16);
		// drawText(g, GameConfig.NAME + ' ' + GameConfig.VERSION, 15, frame.getHeight()
		// - 70, 16);

		g.drawString(COPYLEFT, width - 390, height - 70);
		g.drawString(Game.NAME + ' ' + Game.VERSION, 15, height - 70);

		if (!GameConfig.UNICODE_FONT)
			g.setFont(new Font("Mojangles", Font.PLAIN, 20));

		g.drawString(ComponentTranslator.translate("minecraft.menu_screen:singleplayer_button"), width / 2 - 73,
				height / 2 - 23);
		g.drawString(ComponentTranslator.translate("minecraft.menu_screen:multiplayer_button"), width / 2 - 73,
				height / 2 + 27);

	}

}
