package es.ucm.tp1.control.commands;

import es.ucm.tp1.logic.Game;

public class GoUpCommand extends Command {
	private static final String NAME = "up";
	private static final String DETAILS = "[q]";
	private static final String SHORTCUT = "q";
	private static final String HELP = "go up";

	public GoUpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		game.movePlayer(1, -1);
		game.update();
		return true;
	}
}
