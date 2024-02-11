package net.opencraft.renderer.display;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import net.opencraft.config.GameConfig;
import net.opencraft.renderer.RenderDragon;

public class Display extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public Display(String title) {
		super(title);
	}
	
	public Display() {
	}

	public void defaultConfig() {
		setSize(GameConfig.DEFAULT_RESOLUTION);
		setPreferredSize(GameConfig.DEFAULT_RESOLUTION);
		setIconImage(RenderDragon.bindTexture("/gui/release_icon.png"));
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
