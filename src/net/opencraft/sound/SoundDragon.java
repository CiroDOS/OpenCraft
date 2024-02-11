package net.opencraft.sound;

import static net.opencraft.sound.Sounds.getCurrent;
import static net.opencraft.sound.Sounds.setCurrent;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.sibermatica.util.logging.Logger;

import net.opencraft.util.Screens;

public class SoundDragon {

	private static final Logger LOGGER = new Logger(
			"[${HOUR_24}:${MINUTE}:${SECOND}] [${CLASS_SIMPLE_NAME}/$Upper{LOGGING_LEVEL}]: ${MESSAGE}");
	
	private static final Clip player;
	public static final boolean SUPPORTED;
	
	static {
		Clip clip = null;
		boolean supported = true;
		try {
			clip = AudioSystem.getClip();
		} catch(Exception ignored) {
			clip = null;
			supported = false;
		}
		
		player = clip;
		SUPPORTED = supported;
	}
	
	private SoundDragon() {
	}
	
	public static void tick() {
		if (!isSupported())
			return;

		Sounds sound = Screens.getCurrentScreen().getSound();
		
		if (getCurrent() != sound) {
			resetPlayer();
			try {
				Sounds.play(player, sound);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), SoundDragon.class);
			}
			setCurrent(sound);
		}

	}

	public static void resetPlayer() {
		player.loop(0);
		player.stop();
		player.close();
	}

	public static boolean isSupported() {
		return SUPPORTED;
	}
}
