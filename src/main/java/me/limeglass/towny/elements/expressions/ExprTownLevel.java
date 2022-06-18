package me.limeglass.towny.elements.expressions;

import org.eclipse.jdt.annotation.Nullable;

import com.palmergames.bukkit.towny.object.Town;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;

@Name("Town Level")
@Description("Get the level of a Town.")
public class ExprTownLevel extends SimplePropertyExpression<Town, Integer> {

	static {
		register(ExprTownLevel.class, Integer.class, "town level", "towns");
	}

	@Override
	public Class<? extends Integer> getReturnType() {
		return Integer.class;
	}

	@Override
	public @Nullable Integer convert(Town town) {
		return town.getLevel();
	}

	@Override
	protected String getPropertyName() {
		return "town level";
	}

}
