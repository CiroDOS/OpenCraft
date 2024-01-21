package net.opencraft.util;

public enum Sounds {
	MOOG_CITY("minecraft.sounds", "title.moog_city", "MoogCity.wav"),
	ARIA_MATH("minecraft.sounds", "ambient.aria_math", "AriaMath.wav"),
	ALERT("sibermatica.test", "alert", "Alert.wav");
	
	public static Sounds PLAYING = null;
	
	private final String origin;
	private final String soundId;
	private final String zipName;
	
	Sounds(String origin, String soundTitle, String zipName) {
		this.origin = origin;
		this.soundId = soundTitle;
		this.zipName = zipName;
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
	
	public String getZIPName() {
		return zipName;
	}
	
	public static Sounds getCurrent() {
		return PLAYING;
	}
	
	public Sounds fromResource(Resource res) {
		return switch (res.toString()) {
			case "minecraft.sounds:title.moog_city" -> MOOG_CITY;
			case "minecraft.sounds:ambient.aria_math" -> ARIA_MATH;
			default -> null;
		};
	}
	
	public Resource toResource() {
		return Resource.get(origin, soundId);
	}
	
	@Override
	public String toString() {
		return origin + ":" + soundId;
	}
	
}
