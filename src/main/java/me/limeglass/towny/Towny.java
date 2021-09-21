package me.limeglass.towny;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

public class Towny extends JavaPlugin {

	private static Towny instance;
	private SkriptAddon addon;

	public void onEnable() {
		try {
			addon = Skript.registerAddon(this)
					.loadClasses("me.limeglass.towny", "elements");
					//.setLanguageFileDirectory("lang");
		} catch (IOException e) {
			e.printStackTrace();
		}
		instance = this;
	}

	public SkriptAddon getAddonInstance() {
		return addon;
	}

	public static Towny getInstance() {
		return instance;
	}

}
