package net.opencraft.client;

import java.util.Calendar;

import javax.swing.JOptionPane;

import org.sibermatica.util.logging.Logger;

import net.opencraft.config.GameConfig;
import net.opencraft.config.GameExperiments;
import net.opencraft.renderer.RenderDragon;
import net.opencraft.sound.SoundDragon;
import net.opencraft.util.Screens;

public class Main implements Runnable {

	/**
	 * The class that renders all the things.
	 */
	private static RenderDragon renderDragon = new RenderDragon();
	private boolean running = false;

	/**
	 * An executive class. It logs.
	 */
	private static final Logger LOGGER = new Logger(
			"[${HOUR_24}:${MINUTE}:${SECOND}] [${CLASS_SIMPLE_NAME}/$Upper{LOGGING_LEVEL}]: ${MESSAGE}");

	private Main() {
	}

	public static void main(String[] args) {
		// Parse arguments
		GameConfig.parseArguments(args);
		checkIfCiroIsDead(); // A little easter egg
		new Main().run(); // Start the game
	}

	/**
	 * This easter egg checks if i'm dead. So useful.... :v
	 */
	private static void checkIfCiroIsDead() {
		if (Calendar.getInstance().get(Calendar.YEAR) >= 2111) {
			JOptionPane.showMessageDialog(null, "If you see this message is because Ciro's dead", "R.I.P. Ciro", 1);
		}
	}

	/**
	 * This function starts the game and
	 * initializes all the necessary stuff.
	 */
	@Override
	public void run() {
		init();
	
		final int NANOSECONDS = 1000000000;
		final byte TPS_OBJECTIVE = 60;
		final double NANO_PER_TICK = NANOSECONDS / TPS_OBJECTIVE;

		long lastUpdate = System.nanoTime();

		double timePassed;
		double delta = 0;

		while (running) {
			final long loopStart = System.nanoTime();

			timePassed = loopStart - lastUpdate;
			lastUpdate = loopStart;

			delta += timePassed / NANO_PER_TICK;

			while (delta >= 1) {
				tick();
				delta--;
			}

		}
		
	}
	
	private void tick() {
		renderDragon.tick(); // Rendering system

		/*
		 * If the experiment GameExperiments.PLAY_SOUND_ONCE is enabled, don't check if
		 * any sound is playing
		 */
		if (!GameExperiments.PLAY_SOUND_ONCE) {
			SoundDragon.tick();
		}
	}
	
	private void init() {
		running = true;
		
		Screens.setCurrent(Screens.LOAD_SCREEN);

		renderDragon.init(); // Start renderSystem
		SoundDragon.tick();  // Check if correct sound playing

		/*
		 * Shows advanced info about the logo's rendering
		 */
		if (GameConfig.DEBUG_MODE) {
			LOGGER.debug("[Render Thread] Logo renderized opencraft:logo/"
					+ (GameConfig.LOGO_ID == 5 ? "minceraft.png" : "opencraft.png"), RenderDragon.class);
			LOGGER.technical("[Render Thread] Logo ID - " + GameConfig.LOGO_ID, RenderDragon.class);
		}
		Screens.setCurrent(Screens.TITLE_SCREEN);
	}

}
