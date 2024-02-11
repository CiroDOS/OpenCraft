package net.opencraft.crash;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

import org.sibermatica.util.StringUtils;

public class CrashReport {

	public final Throwable cause;

	public CrashReport(final Throwable cause) {
		this.cause = cause;
	}

	public String dumpStackTrace() {
		String[] commentaries = new String[] { "Oops...", "Oh, that is", "F my bro", "Guaranted", "I did what?",
				"Mojang Quality System", "Help on stackoverflow.com" };

		StringBuilder stackTrace = new StringBuilder();

		stackTrace.append("--- OPENCRAFT CRASH REPORT BEGIN ---");
		stackTrace.append("\n");
		stackTrace.append("// " + commentaries[(int) (Math.random() * 10) % commentaries.length]);
		stackTrace.append("\n");
		stackTrace.append(this.cause.getStackTrace()[0].toString());
		stackTrace.append("\n");
		stackTrace.append("--- OPENCRAFT CRASH REPORT END ---");

		return stackTrace.toString();
	}

	public void dumpInFile() {
		Calendar cInstance = Calendar.getInstance();
		
		String filepath = "crash-reports\\crash-" + ((Integer) (cInstance.get(Calendar.YEAR))).toString()
				+ "-" + cInstance.get(Calendar.MONTH) + "-"
				+ cInstance.get(Calendar.DAY_OF_MONTH) + "_" + cInstance.get(Calendar.HOUR)
				+ "-" + cInstance.get(Calendar.MINUTE) + "-" + cInstance.get(Calendar.SECOND);
		try {

			new File(filepath).createNewFile();
			FileOutputStream fileStream = new FileOutputStream(filepath);

			fileStream.write(this.dumpStackTrace().getBytes(StandardCharsets.UTF_8));

			fileStream.close(); // Closes the filestream

		} catch (Exception ignored) {
			System.out.println("cannot write or create crash-file");
			System.out.println("Halting Game!");
			System.exit(-1);
		}
	}

	/**
	 * ...
	 * */
	public static void launchCorruptionMessage() {
		StringBuilder message = new StringBuilder("\n\n\n\n\n\n\n");

		message.append(StringUtils.Color.Foreground.RED);
		message.append("----------->>>>>>>>>>>>>>--SIBERMATICA---PROTECTION---PROGRAM--<<<<<<<<<<<<<<-----------");
		message.append("  ¡IF YOU RECEIVE THIS MESSAGE YOUR OPENCRAFT AND JAVA ARE GETTING SEVERALLY CORRUPTED!\n");
		message.append("  THE PROGRAM WILL EXIT INMEDIATLY.                 ¡SAVE YOURSELF!\n");
		message.append('\n');
		message.append("                               ¡DON'T LET HIM ESCAPE!\n");
		message.append('\n');
		message.append("  DANGER CODE: 666\n");
		message.append("----------->>>>>>>>>>>>>>--SIBERMATICA---PROTECTION---PROGRAM--<<<<<<<<<<<<<<-----------");
		System.err.println(message.toString());

		new IllegalStateException().printStackTrace();
		System.exit(-2);
	}

}
