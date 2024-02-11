package net.opencraft.renderer.screens;

import static net.opencraft.renderer.RenderDragon.bindTexture;
import static net.opencraft.util.Assets.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Calendar;
import java.util.Locale;

import net.opencraft.client.Game;
import net.opencraft.config.GameConfig;
import net.opencraft.language.Translator;
import net.opencraft.renderer.coords.Coordinates;
import net.opencraft.renderer.coords.Vec2;
import net.opencraft.renderer.display.Display;
import net.opencraft.util.Assets;
import net.opencraft.util.Fonts;
import net.opencraft.util.Resource;
import net.opencraft.util.Screens;

public class TitleScreen extends Screen {

	private final Image logo = bindTexture(
			"/gui/title/" + ((GameConfig.LOGO_ID == 5) ? "minceraft" : "minecraft") + ".png");

	public static final Resource RESOURCE = Screens.TITLE_SCREEN.toResource();
	public static final Screens ID = Screens.TITLE_SCREEN;

	public TitleScreen(Display display) {
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
			g.setFont(Fonts.MOJANGLES.getFont(Font.PLAIN, 18));

		g.drawImage(bindTexture("/gui/title/background/background.jpg"), 0, 0, width, height, null);

		int[] b1 = Coordinates.XYWHtoP4(Vec2.newTemp((width - 400) / 2, center_y - 50), new Dimension(400, 45));
		int[] b2 = Coordinates.XYWHtoP4(Vec2.newTemp((width - 400) / 2, center_y), new Dimension(400, 45));

		int[] bb = Assets.getButtonBounds(NORMAL_BUTTON);
		g.drawImage(bindTexture("/gui/widgets.png"), b1[0], b1[1], b1[2], b1[3], bb[0], bb[1], bb[2], bb[3], null);
		g.drawImage(bindTexture("/gui/widgets.png"), b2[0], b2[1], b2[2], b2[3], bb[0], bb[1], bb[2], bb[3], null);
		
		g.drawImage(logo, (center_x * 2 - 700) / 2, (GameConfig.DEFAULT_RESOLUTION.height > height) ? 10 : 30, 700, 200,
				null);

		g.setColor(Color.WHITE);

		final String COPYLEFT = String.format("CopyLeft (-C) Sibermatica 2023-%d",
				Calendar.getInstance().get(Calendar.YEAR));

		g.drawString(COPYLEFT, width - 390, height - 70);
		g.drawString(Game.TITLE, 15, height - 70);

		if (!GameConfig.UNICODE_FONT)
			g.setFont(Fonts.MOJANGLES.getFont(Font.PLAIN, 20));
		
		g.drawString(Translator.translate("opencraft.buttons:singleplayer"), width / 2 - 69, height / 2 - 23);
		
		// In Spanish the words are a little bit large
		int settings_x = GameConfig.LANGUAGE == Locale.forLanguageTag("es-ES") ? width / 2 - 73 : width / 2 - 45;
		g.drawString(Translator.translate("opencraft.buttons:config"), settings_x, height / 2 + 27);
		
	}

	@Override
	public void init() {
		super.init();
	}

}
