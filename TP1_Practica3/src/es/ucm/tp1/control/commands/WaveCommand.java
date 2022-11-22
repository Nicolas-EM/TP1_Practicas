package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.Buyable;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.action.WaveAction;
import es.ucm.tp1.logic.exceptions.CommandParseException;
import es.ucm.tp1.logic.exceptions.GameException;
import es.ucm.tp1.utils.StringUtils;

public class WaveCommand extends Command implements Buyable{
	private static final String NAME = "wave";
	private static final String DETAILS = "[w]ave";
	private static final String SHORTCUT = "w";
	private static final String HELP = "do wave";
	private final int cost = 5;
	
	public WaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws GameException {
		if(this.buy(game)) {
			game.execute(new WaveAction());
			game.update();
			return true;
		}
		throw new CommandParseException(String.format(INSUFFICIENT_COINS+StringUtils.LINE_SEPARATOR));
	}
	
	@Override
	public int cost() {
		return this.cost;
	}
	
}
