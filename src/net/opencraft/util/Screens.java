package net.opencraft.util;

import net.opencraft.config.GameExperiments;
import net.opencraft.sound.SoundDragon;

public enum Screens {
	TITLE_SCREEN("minecraft.screen", "title"), SBREFERENCE_SCREEN("sibermatica.test", "reference");
	
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
			case "minecraft.screen:title" -> Screens.TITLE_SCREEN;
			default -> null;
		};
	}
	
	@Override
	public String toString() {
		return origin + ":" + screenId;
	}
	
}
