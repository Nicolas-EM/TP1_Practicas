package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.Buyable;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.exceptions.CommandParseException;
import es.ucm.tp1.logic.exceptions.GameException;
import es.ucm.tp1.logic.gameobjects.Grenade;

public class GrenadeCommand extends Command implements Buyable {
	private static final String NAME = "grenade";
	private static final String DETAILS = "[g]renade <x> <y>";
	private static final String SHORTCUT = "g";
	private static final String HELP = "add a grenade in position x, y";
	private final String INVALID_POSITION = "Failed to add grenade";
	private final int cost = 3;
	private int x, y;
	
	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws GameException {
		// verificando que se puede comprar y que la posicion es valida
		if(this.x >= 0 && game.isPositionEmpty(x + game.getPlayerX(), y) && this.buy(game) && this.x < game.getVisibility() && this.y >= 0 && this.y < game.getRoadWidth()) {
			this.x += game.getPlayerX();
			game.forceAddObject(new Grenade(game, x, y));
			game.update();
			return true;
		}
		else {		
			throw new CommandParseException(String.format("Invalid position.%n[ERROR]: %s%n%n",INVALID_POSITION));
		}
	}
	
	@Override
	protected Command parse(String[] words) throws GameException {
		if (matchCommandName(words[0])) {
			if (words.length != 3) {
				throw new CommandParseException(String.format("[ERROR]: %s for %s command: %s%n%n", INCORRECT_NUMBER_OF_ARGS_MSG, NAME, DETAILS));
			} else {
				this.x = Integer.parseInt(words[1]);
				this.y = Integer.parseInt(words[2]);
				return this;
			}
		}
		return null;
	}
	
	@Override
	public int cost() {
		return this.cost;
	}

}
