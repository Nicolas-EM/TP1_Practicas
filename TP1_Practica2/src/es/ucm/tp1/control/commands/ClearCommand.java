package es.ucm.tp1.control.commands;

import es.ucm.tp1.logic.Game;

public class ClearCommand extends Command {
	private static final String NAME = "clear";
	private static final String DETAILS = "Clear [0]";
	private static final String SHORTCUT = "0";
	private static final String HELP = "Clears the road";
	
	public ClearCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) {
		game.clear();
		return true;
	}

	@Override
	protected Command parse(String[] words) {
		if (matchCommandName(words[0])) {
			if (words.length != 1) {
				System.out.format("[ERROR]: Command %s: %s%n%n", NAME, UNKNOWN_COMMAND_MSG);
				return null;
			}
			else {
				return this;
			}
		}
		return null;
	}
}
