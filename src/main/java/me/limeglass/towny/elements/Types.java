package me.limeglass.towny.elements;

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

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;

public class Types {

	static {
		Classes.registerClass(new ClassInfo<>(Town.class, "town")
				.user("towns?")
				.name("Town")
				.defaultExpression(new EventValueExpression<>(Town.class))
				.parser(new Parser<Town>() {
	
					@Override
					@Nullable
					public Town parse(String input, ParseContext context) {
						return TownyAPI.getInstance().getTown(input);
					}
	
					@Override
					public boolean canParse(ParseContext context) {
						return true;
					}
	
					@Override
					public String toString(Town town, int flags) {
						return "Town: " + town.getName();
					}
	
					@Override
					public String toVariableNameString(Town town) {
						return town.getName();
					}

		}));
		Classes.registerClass(new ClassInfo<>(Nation.class, "nation")
				.user("nations?")
				.name("Nation")
				.defaultExpression(new EventValueExpression<>(Nation.class))
				.parser(new Parser<Nation>() {
	
					@Override
					@Nullable
					public Nation parse(String input, ParseContext context) {
						return TownyAPI.getInstance().getNation(input);
					}
	
					@Override
					public boolean canParse(ParseContext context) {
						return true;
					}
	
					@Override
					public String toString(Nation nation, int flags) {
						return "Nation: " + nation.getName();
					}
	
					@Override
					public String toVariableNameString(Nation nation) {
						return nation.getName();
					}

		}));
		Classes.registerClass(new ClassInfo<>(Resident.class, "resident")
				.user("residents?")
				.name("Resident")
				.defaultExpression(new EventValueExpression<>(Resident.class))
				.parser(new Parser<Resident>() {
	
					@Override
					@Nullable
					public Resident parse(String input, ParseContext context) {
						Player player = Classes.getExactClassInfo(Player.class).getParser().parse(input, context);
						if (player == null)
							return null;
						return TownyAPI.getInstance().getResident(player);
					}
	
					@Override
					public boolean canParse(ParseContext context) {
						return true;
					}
	
					@Override
					public String toString(Resident resident, int flags) {
						return "Resident: " + resident.getName();
					}
	
					@Override
					public String toVariableNameString(Resident resident) {
						return resident.getName();
					}

		}));
		Classes.registerClass(new ClassInfo<>(TownyWorld.class, "townyworld")
				.user("towny ?worlds?")
				.name("TownyWorld")
				.defaultExpression(new EventValueExpression<>(TownyWorld.class))
				.parser(new Parser<TownyWorld>() {
	
					@Override
					@Nullable
					public TownyWorld parse(String input, ParseContext context) {
						World world = Classes.getExactClassInfo(World.class).getParser().parse(input, context);
						if (world == null)
							return null;
						return TownyAPI.getInstance().getTownyWorld(world);
					}
	
					@Override
					public boolean canParse(ParseContext context) {
						return true;
					}
	
					@Override
					public String toString(TownyWorld world, int flags) {
						return "World: " + world.getName();
					}
	
					@Override
					public String toVariableNameString(TownyWorld world) {
						return world.getName();
					}

		}));
		Classes.registerClass(new ClassInfo<>(TownBlock.class, "townblock")
				.user("town ?blocks?")
				.name("TownBlock")
				.defaultExpression(new EventValueExpression<>(TownBlock.class))
				.parser(new Parser<TownBlock>() {
	
					@Override
					@Nullable
					public TownBlock parse(String input, ParseContext context) {
						Location location = Classes.getExactClassInfo(Location.class).getParser().parse(input, context);
						if (location == null)
							return null;
						return TownyAPI.getInstance().getTownBlock(location);
					}
	
					@Override
					public boolean canParse(ParseContext context) {
						return true;
					}
	
					@Override
					public String toString(TownBlock block, int flags) {
						return "TownBlock: " + block.getName();
					}
	
					@Override
					public String toVariableNameString(TownBlock block) {
						return block.getName();
					}

		}));
	}

}
