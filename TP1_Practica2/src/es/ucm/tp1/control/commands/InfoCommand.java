package es.ucm.tp1.control.commands;

import es.ucm.tp1.logic.Game;

public class InfoCommand extends Command {

	private static final String NAME = "info";
	private static final String DETAILS = "[i]nfo";
	private static final String SHORTCUT = "i";
	private static final String HELP = "prints gameobject info";

	public InfoCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println("Available objects:");
		System.out.println(game.getDescription());
		return false;
	}

}