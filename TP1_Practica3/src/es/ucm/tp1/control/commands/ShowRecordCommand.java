package es.ucm.tp1.control.commands;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.exceptions.GameException;

public class ShowRecordCommand extends Command {
	private static final String NAME = "record";
	private static final String DETAILS = "rec[o]rd";
	private static final String SHORTCUT = "o";
	private static final String HELP = "show level record";
	
	public ShowRecordCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	private static String formatTime(long t) {
		return String.format("%.2f s", t / 1000.0);
	}
	
	@Override
	public boolean execute(Game game) throws GameException {
		System.out.println(game.getLevel() + " record is " + formatTime(game.getRecord()));
		return false;
	}
}
