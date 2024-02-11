package net.opencraft.client;

public class Game {
	
	public static final String NAME = "OpenCraft";
	public static final String VERSION = "1.2";
	public static final VersionType VERSION_TYPE = VersionType.RELEASE;
	public static final String TITLE = NAME + " " + VERSION + VERSION_TYPE.menuScreenTitle();

	public static enum VersionType {
		RELEASE("release"), INDEV("indev"), ALPHA("alpha"), BETA("beta"), PRECLASSIC("pre-classic"),
		SNAPSHOT("snapshot");

		private String name;

		VersionType(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}
		
		public String menuScreenTitle() {
			if (this.equals(RELEASE))
				return "";
			
			return " " + this.name;
		}

		public VersionType fromString(String str) {
			return switch (str.toUpperCase()) {
				case "RELEASE" -> VersionType.RELEASE;
				case "INDEV" -> VersionType.INDEV;
				case "ALPHA" -> VersionType.ALPHA;
				case "BETA" -> VersionType.BETA;
				case "PRE-CLASSIC" -> VersionType.PRECLASSIC;
				case "SNAPSHOT" -> VersionType.SNAPSHOT;
				default -> null;
			};
		}

	}

}
