package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.Buyable;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.action.ShootAction;
import es.ucm.tp1.logic.exceptions.CommandParseException;
import es.ucm.tp1.logic.exceptions.GameException;

public class ShootCommand extends Command implements Buyable {
	private static final String NAME = "shoot";
	private static final String DETAILS = "[s]hoot";
	private static final String SHORTCUT = "s";
	private static final String HELP = "shoot bullet";
	private final int cost = 1;

	public ShootCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws GameException {
		if(this.buy(game)) {
			game.execute(new ShootAction());
			game.update();
			return true;
		}
		else {
			throw new CommandParseException(String.format("%s%n[ERROR]: Failed to shoot%n%n", INSUFFICIENT_COINS));
			
		}
	}

	@Override
	public int cost() {
		return this.cost;
	}
	
}
