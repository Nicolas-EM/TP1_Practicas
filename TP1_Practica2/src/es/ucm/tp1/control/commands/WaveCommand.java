package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.Buyable;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.action.WaveAction;

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
	public boolean execute(Game game) {
		if(this.buy(game)) {
			game.execute(new WaveAction());
			game.update();
			return true;
		}
		else System.out.println(INSUFFICIENT_COINS);
		return false;
	}
	
	@Override
	public int cost() {
		return this.cost;
	}
}
