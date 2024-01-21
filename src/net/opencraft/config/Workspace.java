package net.opencraft.config;

import java.io.File;

import net.opencraft.crash.CrashReport;

public final class Workspace {
	
	public       static String GAME_DIRECTORY         = ".";
	public       static String ASSETS_DIRECTORY       = Workspace.GAME_DIRECTORY + "\\assets";
	public       static String CRASH_REPORT_DIRECTORY = Workspace.GAME_DIRECTORY + "\\crash-reports";
	
	public final static String WORLD_DIRECTORY = Workspace.GAME_DIRECTORY + "\\worlds";
	
	private static boolean initializated = false;
	
	private Workspace() {
		
	}
	
	public static boolean init() {
		try {
			
			File gameDir = new File(Workspace.GAME_DIRECTORY);
			File assetsDir = new File(Workspace.ASSETS_DIRECTORY);
			File crshRportDir = new File(Workspace.CRASH_REPORT_DIRECTORY );
			
			if (!gameDir.exists())
				new CrashReport(new IllegalStateException("no game directory")).dumpStackTrace();
			
			if (!assetsDir.exists())
				assetsDir.mkdir();
			
			if (!crshRportDir.exists())
				crshRportDir.mkdir();
			
		} catch(Exception ignored) {
			return false;
		}
		
		initializated = true;
		return true;
		
	}
	
	public static boolean isInitializated() {
		return Workspace.initializated;
	}

}
