package net.opencraft.sound;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import net.opencraft.config.Workspace;
import net.opencraft.util.Resource;

public enum Sounds {
	MOOG_CITY("opencraft.sounds", "title.moog_city", "MoogCity.wav"),
	ARIA_MATH("opencraft.sounds", "ambient.aria_math", "AriaMath.wav");

	public static Sounds PLAYING = null;

	final String origin;
	final String soundId;
	final String path;

	Sounds(String origin, String soundTitle, String name) {
		this.origin = origin;
		this.soundId = soundTitle;
		this.path = name;
	}

	public static void setCurrent(Sounds aures) {
		PLAYING = aures;
	}

	public String getOrigin() {
		return origin;
	}

	public String getSoundId() {
		return soundId;
	}

	public String getRelativePath() {
		return path;
	}

	public String getPath() {
		return Workspace.assetsDir + "/opencraft/sounds/" + path;
	}

	public static Sounds getCurrent() {
		return PLAYING;
	}

	public Sounds fromResource(Resource res) {
		return switch (res.toString()) {
		case "opencraft.sounds:title.moog_city" -> MOOG_CITY;
		case "opencraft.sounds:ambient.aria_math" -> ARIA_MATH;
		default -> null;
		};
	}

	public static void play(Clip player, Sounds sound)
			throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		if (player == null)
			return;

		AudioInputStream audioStream = AudioSystem.getAudioInputStream(getSound(sound));
		player.open(audioStream);
		player.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void play(Clip player) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		play(player, this);
	}

	public static InputStream getSound(Sounds snd) throws IOException {
		return new BufferedInputStream(new FileInputStream(snd.getPath()));
	}

	public Resource toResource() {
		return Resource.get(origin, soundId);
	}

	@Override
	public String toString() {
		return origin + ":" + soundId;
	}

}
