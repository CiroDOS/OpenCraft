package net.opencraft.language;

import java.util.Locale;

import net.opencraft.config.GameConfig;
import net.opencraft.util.Resource;

public class ComponentTranslator {
	
	public static String translate(String property, Locale language) {
		if (language.equals(Locale.forLanguageTag("es-ES"))) {
			return switch(property) {
				case "minecraft.menu_screen:singleplayer_button" -> "Un jugador";
				case "minecraft.menu_screen:multiplayer_button" -> "Multijugador";
				case "minecraft.item:wooden_pickaxe" -> "Pico de madera";
				
				default -> property;
			};
		}
		
		return switch(property) {
			case "minecraft.menu_screen:singleplayer_button" -> "Singleplayer";
			case "minecraft.menu_screen:multiplayer_button" -> "Multiplayer";
			case "minecraft.item:wooden_pickaxe" -> "Wooden pickaxe";
		
			default -> property;
		};
	}

	
	public static String translate(String property) {
		return ComponentTranslator.translate(property, GameConfig.LANGUAGE);
	}
	
	public static String translate(Resource resource, Locale language) {
		return ComponentTranslator.translate(resource.toString(), language);
	}
	
	public static String translate(Resource resource) {
		return ComponentTranslator.translate(resource, GameConfig.LANGUAGE);
	}
}
