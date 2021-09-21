package me.limeglass.towny.elements.expressions;

import java.util.Arrays;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.palmergames.bukkit.towny.object.Resident;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

@Name("Resident Nation Ranks")
@Description("Get the nation ranks of a Resident.")
public class ExprResidentNationRanks extends PropertyExpression<Resident, String> {

	static {
		register(ExprResidentNationRanks.class, String.class, "nation ranks", "residents");
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setExpr((Expression<? extends Resident>) exprs[0]);
		return true;
	}

	@Override
	protected String[] get(Event event, Resident[] source) {
		return Arrays.stream(source).flatMap(resident -> resident.getNationRanks().stream()).toArray(String[]::new);
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (debug || event == null)
			return "resident nation ranks";
		return "Nation ranks of " + getExpr().toString(event, debug);
	}

}
