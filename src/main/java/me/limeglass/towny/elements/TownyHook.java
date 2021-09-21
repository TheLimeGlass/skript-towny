package me.limeglass.towny.elements;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.Sets;
import com.google.common.collect.Streams;
import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.TownyPermission.ActionType;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;

import ch.njol.skript.hooks.regions.RegionsPlugin;
import ch.njol.skript.hooks.regions.classes.Region;
import ch.njol.skript.util.AABB;
import ch.njol.skript.variables.Variables;
import ch.njol.yggdrasil.Fields;
import ch.njol.yggdrasil.YggdrasilID;

/**
 * Hook for Towny plugin.
 * @author LimeGlass
 */
public class TownyHook extends RegionsPlugin<Towny> {

	static {
		Variables.yggdrasil.registerSingleClass(TownyRegion.class);
	}

	private final TownyAPI towny;

	public TownyHook() throws IOException {
		super.init();
		towny = TownyAPI.getInstance();
	}

	public TownyRegion getRegion(String name) {
		return new TownyRegion(towny.getTown(name));
	}

	@Override
	public boolean canBuild_i(Player player, Location location) {
		return PlayerCacheUtil.getCachePermission(player, location, location.getBlock().getType(), ActionType.BUILD);
	}

	@Override
	protected Class<? extends Region> getRegionClass() {
		return TownyRegion.class;
	}

	@Override
	@Nullable
	public Region getRegion_i(World world, String name) {
		return new TownyRegion(towny.getTown(name));
	}

	@Override
	public Collection<? extends Region> getRegionsAt_i(Location location) {
		return Sets.newHashSet(new TownyRegion(towny.getTown(location)));
	}

	@Override
	public boolean hasMultipleOwners_i() {
		return false;
	}

	@Override
	public String getName() {
		return "Towny";
	}

	@YggdrasilID("TownyRegion")
	public final class TownyRegion extends Region {

		private transient Town town;

		public TownyRegion(Town town) {
			this.town = town;
		}

		public World getWorld() {
			return town.getWorld();
		}

		public String getName() {
			return town.getName();
		}

		public Town getTown() {
			return town;
		}

		@Override
		public boolean contains(Location location) {
			TownBlock block = towny.getTownBlock(location);
			return town.hasTownBlock(block);
		}

		@Override
		public boolean isMember(OfflinePlayer player) {
			return town.hasResident(player.getUniqueId());
		}

		@Override
		public Collection<OfflinePlayer> getMembers() {
			return town.getResidents().stream().map(resident -> resident.getPlayer()).collect(Collectors.toSet());
		}

		@Override
		public boolean isOwner(OfflinePlayer player) {
			return town.isMayor(towny.getResident(player.getUniqueId()));
		}

		@Override
		public Collection<OfflinePlayer> getOwners() {
			return Sets.newHashSet(town.getMayor().getPlayer());
		}

		@Override
		public Iterator<Block> getBlocks() {
			World world = town.getWorld();
			return town.getTownBlocks().stream()
					.flatMap(block -> Streams.stream(new AABB(world.getChunkAt(block.getX(), block.getZ())).iterator()))
					.iterator();
		}

		@Override
		public String toString() {
			return town.getName() + " in world " + town.getWorld().getName();
		}

		@Override
		public RegionsPlugin<?> getPlugin() {
			return TownyHook.this;
		}

		@Override
		public boolean equals(@Nullable Object object) {
			if (this == object)
				return true;
			if (object == null || getClass() != object.getClass())
				return false;
			TownyRegion other = (TownyRegion) object;
			return Objects.equals(object, other.town);
		}

		@Override
		public int hashCode() {
			return Objects.hash(town);
		}

		@Override
		public Fields serialize() throws NotSerializableException {
			return new Fields(this);
		}

		@Override
		public void deserialize(Fields fields) throws StreamCorruptedException, NotSerializableException {
			new Fields(fields).setFields(this);
		}

	}

}
