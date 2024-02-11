package net.opencraft.util;

import net.opencraft.config.GameExperiments;
import net.opencraft.renderer.RenderDragon;
import net.opencraft.sound.SoundDragon;
import net.opencraft.sound.Sounds;

public enum Screens {
	LOAD_SCREEN("opencraft.screen", "load"),
	TITLE_SCREEN("opencraft.screen", "title"),
	CONFIG_SCREEN("opencraft.screen", "config");
	
	private static Screens currentScreen = TITLE_SCREEN;
	
	private final String origin;
	private final String screenId;
	
	Screens(String origin, String scrName) {
		this.origin = origin;
		this.screenId = scrName;
	}

	public static Screens getCurrentScreen() {
		return currentScreen;
	}

	public static void setCurrent(Screens w) {
		// Changes the screen
		currentScreen = w;
		
		/*
		 * If {@linkplain GameExperiments.PLAY_SOUND_ONE_TIME} is activated,
		 * the sound will be checked when the screen changes.
		 * 
		 * Otherwise, if the sound is not playing once, the {@code Main} class
		 * will be updating continuously the {@code SoundDragon} class, then is not
		 * necessary updating the sound system.
		 * */
		if (GameExperiments.PLAY_SOUND_ONCE)
			SoundDragon.tick();
		
		RenderDragon.initScreen(w);
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public String getScreenId() {
		return screenId;
	}
	
	public Resource toResource() {
		return Resource.get(origin, screenId);
	}

	public static Screens fromResource(Resource resource) {
		return switch(resource.toString()) {
			case "opencraft.screen:load" -> Screens.LOAD_SCREEN;
			case "opencraft.screen:title" -> Screens.TITLE_SCREEN;
			case "opencraft.screen:config" -> Screens.CONFIG_SCREEN;
			default -> null;
		};
	}
	
	public Sounds getSound() {
		return switch(this) {
			case TITLE_SCREEN -> Sounds.MOOG_CITY;
			default -> null;
		};
	}
	
	@Override
	public String toString() {
		return origin + ":" + screenId;
	}
	
}
