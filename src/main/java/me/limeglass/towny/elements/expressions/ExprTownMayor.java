package me.limeglass.towny.elements.expressions;

import org.eclipse.jdt.annotation.Nullable;

import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;

@Name("Town Mayor")
@Description("Get the town mayor of a Town.")
public class ExprTownMayor extends SimplePropertyExpression<Town, Resident> {

	static {
		register(ExprTownMayor.class, Resident.class, "[town] mayor", "towns");
	}

	@Override
	public Class<? extends Resident> getReturnType() {
		return Resident.class;
	}

	@Override
	public @Nullable Resident convert(Town town) {
		return town.getMayor();
	}

	@Override
	protected String getPropertyName() {
		return "mayor";
	}

}
