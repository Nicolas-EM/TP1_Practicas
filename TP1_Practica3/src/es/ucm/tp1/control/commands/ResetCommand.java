	package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.exceptions.CommandExecuteException;
import es.ucm.tp1.logic.exceptions.CommandParseException;
import es.ucm.tp1.logic.exceptions.GameException;
public class ResetCommand extends Command {
	private static final String NAME = "r";
	private static final String DETAILS = "[r]eset [<level> <seed>]";
	private static final String SHORTCUT = "r";
	private static final String HELP = "reset game";
	private static final String SEED_INFO_MSG = "Random generator initialized with seed: ";
	private Level level;
	private long seed;
	
	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public ResetCommand(Level level, long seed) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.level = level;
		this.seed = seed;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		if(this.level != null) {
			System.out.println("Level: " + this.level.name());
			System.out.println(SEED_INFO_MSG + this.seed);
			game.resetLevel(this.level, this.seed);
		}
		else game.reset();
		return true;
	}

	protected Command parse(String[] words) throws GameException {
		if (matchCommandName(words[0])) {
			if (words.length == 1) {
				return this;
			} else if(words.length == 3) {
				// Parse level
				level = Level.valueOfIgnoreCase(words[1]);
				if(level == null) {
					throw new CommandParseException(String.format("[ERROR]: Command %s: %s%n%n", SHORTCUT, "Level must be one of: TEST, EASY, HARD, ADVANCED"));
				}
				
				// Parse seed
				try {
					seed = Long.parseLong(words[2]);
				}
				catch(Exception exception) {
					throw new NumberFormatException(String.format("[ERROR]: Command %s: the seed is not a proper long number%n%n", NAME));
				}
				
				// Create new ResetCommand
				return new ResetCommand(level, seed);
			}
			else {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s%n%n", SHORTCUT, INCORRECT_NUMBER_OF_ARGS_MSG));		
			}
		}
		return null;
	}
}
