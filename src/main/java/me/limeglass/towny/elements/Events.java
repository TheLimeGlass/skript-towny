package me.limeglass.towny.elements;

import org.bukkit.entity.Player;
import org.eclipse.jdt.annotation.Nullable;

import com.palmergames.bukkit.towny.event.TownAddResidentRankEvent;
import com.palmergames.bukkit.towny.event.TownBlockSettingsChangedEvent;
import com.palmergames.bukkit.towny.event.TownPreAddResidentEvent;
import com.palmergames.bukkit.towny.event.TownPreClaimEvent;
import com.palmergames.bukkit.towny.event.TownPreRenameEvent;
import com.palmergames.bukkit.towny.event.TownRemoveResidentEvent;
import com.palmergames.bukkit.towny.event.TownRemoveResidentRankEvent;
import com.palmergames.bukkit.towny.event.town.TownLeaveEvent;
import com.palmergames.bukkit.towny.event.town.TownPreUnclaimEvent;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

public class Events {

	static {
		// Add resident
		Skript.registerEvent("town add resident", SimpleEvent.class, TownPreAddResidentEvent.class , "town add resident");
		EventValues.registerEventValue(TownPreAddResidentEvent.class, Resident.class, new Getter<Resident, TownPreAddResidentEvent>() {
			@Override
			@Nullable
			public Resident get(TownPreAddResidentEvent event) {
				return event.getResident();
			}
		}, 0);
		EventValues.registerEventValue(TownPreAddResidentEvent.class, Town.class, new Getter<Town, TownPreAddResidentEvent>() {
			@Override
			@Nullable
			public Town get(TownPreAddResidentEvent event) {
				return event.getTown();
			}
		}, 0);

		// Add town rank
		Skript.registerEvent("town add resident rank", SimpleEvent.class, TownAddResidentRankEvent.class , "town add resident rank");
		EventValues.registerEventValue(TownAddResidentRankEvent.class, Resident.class, new Getter<Resident, TownAddResidentRankEvent>() {
			@Override
			@Nullable
			public Resident get(TownAddResidentRankEvent event) {
				return event.getResident();
			}
		}, 0);
		EventValues.registerEventValue(TownAddResidentRankEvent.class, Town.class, new Getter<Town, TownAddResidentRankEvent>() {
			@Override
			@Nullable
			public Town get(TownAddResidentRankEvent event) {
				return event.getTown();
			}
		}, 0);
		EventValues.registerEventValue(TownAddResidentRankEvent.class, String.class, new Getter<String, TownAddResidentRankEvent>() {
			@Override
			@Nullable
			public String get(TownAddResidentRankEvent event) {
				return event.getRank();
			}
		}, 0);

		// Remove town rank
		Skript.registerEvent("town remove resident rank", SimpleEvent.class, TownAddResidentRankEvent.class , "town remove resident rank");
		EventValues.registerEventValue(TownRemoveResidentRankEvent.class, Resident.class, new Getter<Resident, TownRemoveResidentRankEvent>() {
			@Override
			@Nullable
			public Resident get(TownRemoveResidentRankEvent event) {
				return event.getResident();
			}
		}, 0);
		EventValues.registerEventValue(TownRemoveResidentRankEvent.class, Town.class, new Getter<Town, TownRemoveResidentRankEvent>() {
			@Override
			@Nullable
			public Town get(TownRemoveResidentRankEvent event) {
				return event.getTown();
			}
		}, 0);
		EventValues.registerEventValue(TownRemoveResidentRankEvent.class, String.class, new Getter<String, TownRemoveResidentRankEvent>() {
			@Override
			@Nullable
			public String get(TownRemoveResidentRankEvent event) {
				return event.getRank();
			}
		}, 0);

		// Block settings change
		Skript.registerEvent("town block settings change", SimpleEvent.class, TownBlockSettingsChangedEvent.class , "town block settings change");
		EventValues.registerEventValue(TownBlockSettingsChangedEvent .class, Town.class, new Getter<Town, TownBlockSettingsChangedEvent>() {
			@Override
			@Nullable
			public Town get(TownBlockSettingsChangedEvent event) {
				return event.getTown();
			}
		}, 0);
		EventValues.registerEventValue(TownBlockSettingsChangedEvent.class, TownBlock.class, new Getter<TownBlock, TownBlockSettingsChangedEvent>() {
			@Override
			@Nullable
			public TownBlock get(TownBlockSettingsChangedEvent event) {
				return event.getTownBlock();
			}
		}, 0);

		// Town claim
		Skript.registerEvent("town claim", SimpleEvent.class, TownPreClaimEvent.class , "town claim");
		EventValues.registerEventValue(TownPreClaimEvent.class, TownBlock.class, new Getter<TownBlock, TownPreClaimEvent>() {
			@Override
			@Nullable
			public TownBlock get(TownPreClaimEvent event) {
				return event.getTownBlock();
			}
		}, 0);
		EventValues.registerEventValue(TownPreClaimEvent.class, Player.class, new Getter<Player, TownPreClaimEvent>() {
			@Override
			@Nullable
			public Player get(TownPreClaimEvent event) {
				return event.getPlayer();
			}
		}, 0);

		// Town unclaim
		Skript.registerEvent("town unclaim", SimpleEvent.class, TownPreUnclaimEvent.class , "town unclaim");
		EventValues.registerEventValue(TownPreUnclaimEvent.class, TownBlock.class, new Getter<TownBlock, TownPreUnclaimEvent>() {
			@Override
			@Nullable
			public TownBlock get(TownPreUnclaimEvent event) {
				return event.getTownBlock();
			}
		}, 0);

		// Town rename
		Skript.registerEvent("town rename", SimpleEvent.class, TownPreRenameEvent.class , "town rename");
		EventValues.registerEventValue(TownPreRenameEvent.class, String.class, new Getter<String, TownPreRenameEvent>() {
			@Override
			@Nullable
			public String get(TownPreRenameEvent event) {
				return event.getOldName();
			}
		}, -1);
		EventValues.registerEventValue(TownPreRenameEvent.class, String.class, new Getter<String, TownPreRenameEvent>() {
			@Override
			@Nullable
			public String get(TownPreRenameEvent event) {
				return event.getNewName();
			}
		}, 1);
		EventValues.registerEventValue(TownPreRenameEvent.class, Town.class, new Getter<Town, TownPreRenameEvent>() {
			@Override
			@Nullable
			public Town get(TownPreRenameEvent event) {
				return event.getTown();
			}
		}, 0);

		// Town remove resident
		Skript.registerEvent("town remove resident", SimpleEvent.class, TownRemoveResidentEvent.class , "town remove resident");
		EventValues.registerEventValue(TownRemoveResidentEvent.class, Resident.class, new Getter<Resident, TownRemoveResidentEvent>() {
			@Override
			@Nullable
			public Resident get(TownRemoveResidentEvent event) {
				return event.getResident();
			}
		}, 0);
		EventValues.registerEventValue(TownRemoveResidentEvent.class, Town.class, new Getter<Town, TownRemoveResidentEvent>() {
			@Override
			@Nullable
			public Town get(TownRemoveResidentEvent event) {
				return event.getTown();
			}
		}, 0);

		// Town remove resident
		Skript.registerEvent("town leave", SimpleEvent.class, TownLeaveEvent.class , "town leave");
		EventValues.registerEventValue(TownLeaveEvent.class, Resident.class, new Getter<Resident, TownLeaveEvent>() {
			@Override
			@Nullable
			public Resident get(TownLeaveEvent event) {
				return event.getResident();
			}
		}, 0);
		EventValues.registerEventValue(TownLeaveEvent.class, Town.class, new Getter<Town, TownLeaveEvent>() {
			@Override
			@Nullable
			public Town get(TownLeaveEvent event) {
				return event.getTown();
			}
		}, 0);
	}
}
