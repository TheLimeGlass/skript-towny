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

@Name("Resident Friends")
@Description("Get the friends of a Resident.")
public class ExprResidentFriends extends PropertyExpression<Resident, Resident> {

	static {
		register(ExprResidentFriends.class, Resident.class, "[resident] friends", "residents");
	}

	@Override
	public Class<? extends Resident> getReturnType() {
		return Resident.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setExpr((Expression<? extends Resident>) exprs[0]);
		return true;
	}

	@Override
	protected Resident[] get(Event event, Resident[] source) {
		return Arrays.stream(source).flatMap(resident -> resident.getFriends().stream()).toArray(Resident[]::new);
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (debug || event == null)
			return "resident friends";
		return "Friends of " + getExpr().toString(event, debug);
	}

}
