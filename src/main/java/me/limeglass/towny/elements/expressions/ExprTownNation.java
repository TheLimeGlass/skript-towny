package me.limeglass.towny.elements.expressions;

import org.eclipse.jdt.annotation.Nullable;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;

@Name("Town Nation")
@Description("Get the nation of a Town.")
public class ExprTownNation extends SimplePropertyExpression<Town, Nation> {

	static {
		register(ExprTownNation.class, Nation.class, "[towny] nation[s]", "towns");
	}

	@Override
	public Class<? extends Nation> getReturnType() {
		return Nation.class;
	}

	@Override
	@Nullable
	public Nation convert(Town town) {
		return town.getNationOrNull();
	}

	@Override
	protected String getPropertyName() {
		return "nation";
	}

}
