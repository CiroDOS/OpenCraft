package net.opencraft.renderer.display;

public class DisplayManager {

	public static final byte CUSTOM = 0;
	public static final byte DEFAULT_CONFIG = 1;
	
	public static final int WIDTH = 854;
	public static final int HEIGHT = 480;
	
	public static Display createDisplay(String title, byte config) {
		Display d = new Display(title);
		
		if (config == DEFAULT_CONFIG)
			d.defaultConfig();
		
		d.setVisible(true);
		return d;
	}
	public static void destroyDisplay(Display d) {
		d.dispose();
	}
	
}
