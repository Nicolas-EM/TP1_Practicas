package es.ucm.tp1.control.commands;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.GameObjectGenerator;

public class CheatCommand extends Command {
	private static final String NAME = "cheat";
	private static final String DETAILS = "Cheat [1..5]";
	private static final String SHORTCUT = "1";
	private static final String HELP = "Removes all elements of last visible column, and adds an Advanced Object";
	private int id;
	
	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) {
		game.clearColumn();
		GameObjectGenerator.forceAdvanceObject(game, this.id, game.getPlayerX() + game.getVisibility() - 1);
		return true;
	}
	
	@Override
	protected boolean matchCommandName(String name) {
		switch(name) {
		case "1":			
		case "2":
		case "3":
		case "4":
		case "5":
			return true;
		default:
			return false;
		}
	}
	
	@Override
	protected Command parse(String[] words) {
		if (matchCommandName(words[0])) {
			if (words.length != 1) {
				System.out.format("[ERROR]: Command %s: %s%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			} else {
				if(Integer.parseInt(words[0]) < 1 || Integer.parseInt(words[0]) > 5) {
					System.out.format("[ERROR]: Command %s: %s%n%n", NAME, "CHEAT NUMBER DOES NOT EXIST");
					return null;
				}
				else {
					this.id = Integer.parseInt(words[0]);
					return this;
				}
			}
		}
		return null;
	}
}