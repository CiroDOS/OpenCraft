package net.opencraft.util;

import net.opencraft.sound.Sounds;

public class Assets {

	public static final int NORMAL_BUTTON = 0;

	private Assets() {
	}
	
	public static int[] getButtonBounds(int button) {
		return switch (button) {
			case 0 -> new int[] { 0, 66, 200, 86 };
			default -> new int[] { 0, 0, 0, 0 };
		};
	}
	
	public static String getSoundPath(Sounds sound) {
		return sound.getPath();
	}
	
	public static String getSoundRelativePath(Sounds sound) {
		return sound.getRelativePath();
	}
	
	public static String getFontPath(Fonts font) {
		return font.getPath();
	}
	
	public static String getFontRelativePath(Fonts font) {
		return font.getRelativePath();
	}

}
