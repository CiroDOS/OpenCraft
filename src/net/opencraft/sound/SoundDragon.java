package net.opencraft.sound;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.sibermatica.util.logging.Logger;

import net.opencraft.util.Sounds;
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

		if (Screens.getCurrentScreen() == Screens.TITLE_SCREEN) {
			if (Sounds.getCurrent() != Sounds.MOOG_CITY) {
				resetPlayer();
				try {
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(getSound(Sounds.MOOG_CITY));
					player.open(audioStream);
					player.loop(Clip.LOOP_CONTINUOUSLY);
					Sounds.setCurrent(Sounds.MOOG_CITY);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), SoundDragon.class);
				}
			}
		} else if (Screens.getCurrentScreen() == Screens.SBREFERENCE_SCREEN) {
			if (Sounds.getCurrent() != Sounds.ALERT) {
				resetPlayer();
				try {
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(getSound(Sounds.ALERT));
					player.open(audioStream);
					player.loop(Clip.LOOP_CONTINUOUSLY);
					Sounds.setCurrent(Sounds.ARIA_MATH);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), SoundDragon.class);
				}
			}
			
		} else {
			resetPlayer();
		}

	}

	public static void resetPlayer() {
		player.loop(0);
		player.stop();
		player.close();
	}
	
	public static InputStream getSound(Sounds snd) throws IOException {
		InputStream in = SoundDragon.class.getResourceAsStream(getSoundsPack());
		ZipInputStream zin = new ZipInputStream(in);
		
		ZipEntry entry = null;
		while((entry = zin.getNextEntry()) != null) {
			if (entry.getName().equalsIgnoreCase(snd.getZIPName()))
				break;
		}
		
		return new ByteArrayInputStream(zin.readAllBytes());
	}
	
	private static String getSoundsPack() {
		return "/assets/minecraft/sounds.zip";
	}

	public static boolean isSupported() {
		return SUPPORTED;
	}
}
