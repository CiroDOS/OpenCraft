package net.opencraft.config;

import java.io.File;

import net.opencraft.crash.CrashReport;

public final class Workspace {

	public static File gameDir = new File("./");
	public static File cReportDir = new File(gameDir.getPath() + "/crash-reports/");
	public static File worldDir = new File(gameDir.getPath() + "/worlds/");
	public static File assetsDir = new File(gameDir.getPath() + "/assets/");

	static {
		try {
			if (!gameDir.exists()) {
				new CrashReport(new IllegalStateException("no game directory")).dumpStackTrace();
				System.exit(-1);
			}

			if (!cReportDir.exists())
				cReportDir.mkdir();
		} catch (Exception ignored) {
		}
	}

	private Workspace() {
	}
	
}
