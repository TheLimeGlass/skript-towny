package me.limeglass.towny.elements.expressions;

import java.util.Arrays;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

@Name("Town Blocks")
@Description("Get the town blocks of a Town. TownBlocks are not item blocks.")
public class ExprTownBlocks extends PropertyExpression<Town, TownBlock> {

	static {
		register(ExprTownBlocks.class, TownBlock.class, "town (blocks|chunks)", "towns");
	}

	@Override
	public Class<? extends TownBlock> getReturnType() {
		return TownBlock.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setExpr((Expression<? extends Town>) exprs[0]);
		return true;
	}

	@Override
	protected TownBlock[] get(Event event, Town[] source) {
		return Arrays.stream(source).flatMap(resident -> resident.getTownBlocks().stream()).toArray(TownBlock[]::new);
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (debug || event == null)
			return "town blocks";
		return "town blocks of: " + getExpr().toString(event, debug);
	}

}
