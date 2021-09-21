package me.limeglass.towny.elements;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.eclipse.jdt.annotation.Nullable;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.TownyWorld;

import ch.njol.skript.classes.Converter;
import ch.njol.skript.registrations.Converters;
import me.limeglass.towny.elements.TownyHook.TownyRegion;

public class TownyConverters {

	static {
		Converters.registerConverter(TownyRegion.class, World.class, new Converter<TownyRegion, World>() {
			@Override
			@Nullable
			public World convert(TownyRegion town) {
				return town.getWorld();
			}
		});
		Converters.registerConverter(TownyRegion.class, Town.class, new Converter<TownyRegion, Town>() {
			@Override
			@Nullable
			public Town convert(TownyRegion town) {
				return town.getTown();
			}
		});
		Converters.registerConverter(Resident.class, Player.class, new Converter<Resident, Player>() {
			@Override
			@Nullable
			public Player convert(Resident resident) {
				return resident.getPlayer();
			}
		});
		Converters.registerConverter(Resident.class, Town.class, new Converter<Resident, Town>() {
			@Override
			@Nullable
			public Town convert(Resident resident) {
				return resident.getTownOrNull();
			}
		});
		Converters.registerConverter(Player.class, Resident.class, new Converter<Player, Resident>() {
			@Override
			@Nullable
			public Resident convert(Player player) {
				return TownyAPI.getInstance().getResident(player);
			}
		});
		Converters.registerConverter(TownyWorld.class, World.class, new Converter<TownyWorld, World>() {
			@Override
			@Nullable
			public World convert(TownyWorld world) {
				return Bukkit.getWorld(world.getName());
			}
		});
		Converters.registerConverter(World.class, TownyWorld.class, new Converter<World, TownyWorld>() {
			@Override
			@Nullable
			public TownyWorld convert(World world) {
				return TownyAPI.getInstance().getTownyWorld(world);
			}
		});
		Converters.registerConverter(Town.class, TownyWorld.class, new Converter<Town, TownyWorld>() {
			@Override
			@Nullable
			public TownyWorld convert(Town town) {
				return town.getHomeblockWorld();
			}
		});
		Converters.registerConverter(Town.class, Location.class, new Converter<Town, Location>() {
			@Override
			@Nullable
			public Location convert(Town town) {
				return town.getSpawnOrNull();
			}
		});
		Converters.registerConverter(Location.class, Town.class, new Converter<Location, Town>() {
			@Override
			@Nullable
			public Town convert(Location location) {
				return TownyAPI.getInstance().getTown(location);
			}
		});
		Converters.registerConverter(Town.class, Nation.class, new Converter<Town, Nation>() {
			@Override
			@Nullable
			public Nation convert(Town town) {
				return town.getNationOrNull();
			}
		});
		Converters.registerConverter(Location.class, TownBlock.class, new Converter<Location, TownBlock>() {
			@Override
			@Nullable
			public TownBlock convert(Location location) {
				return TownyAPI.getInstance().getTownBlock(location);
			}
		});
		Converters.registerConverter(TownBlock.class, Town.class, new Converter<TownBlock, Town>() {
			@Override
			@Nullable
			public Town convert(TownBlock block) {
				return block.getTownOrNull();
			}
		});
	}

}
