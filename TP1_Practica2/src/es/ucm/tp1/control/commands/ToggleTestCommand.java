package es.ucm.tp1.control.commands;

import es.ucm.tp1.logic.Game;

public class ToggleTestCommand extends Command {
	private static final String NAME = "test";
	private static final String DETAILS = "[t]est";
	private static final String SHORTCUT = "t";
	private static final String HELP = "enables test mode";

	public ToggleTestCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		game.toggleTest();
		return true;
	}
}
