package es.ucm.tp1.control.commands;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.exceptions.GameException;

public class UpdateCommand extends Command {

	private static final String NAME = "update";
	private static final String DETAILS = "[n]one | []";
	private static final String SHORTCUT = "n";
	private static final String HELP = "update";

	public UpdateCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		game.movePlayer(1, 0);
		game.update();
		return true;
	}

	@Override
	protected Command parse(String[] commandWords) throws GameException {
		if ("".equalsIgnoreCase(commandWords[0])) {
			commandWords[0] = SHORTCUT;
		}
		return super.parse(commandWords);
	}
}
