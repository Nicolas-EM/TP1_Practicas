package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.logic.Game;

public class ResetCommand extends Command {
	private static final String NAME = "reset";
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
	public boolean execute(Game game) {
		if(this.level != null) {
			game.reset(this.level, this.seed);
			System.out.println("Level: " + this.level.name());
			System.out.println(SEED_INFO_MSG + this.seed);
		}
		else game.reset();
		return true;
	}

	protected Command parse(String[] words) {
		if (matchCommandName(words[0])) {
			if (words.length == 1) {
				return this;
			} else if(words.length == 3) {
				// Parse level
				level = Level.valueOfIgnoreCase(words[1]);
				if(level == null) {
					System.out.format("[ERROR]: Command %s: %s%n%n", SHORTCUT, "Level must be one of: TEST, EASY, HARD, ADVANCED");
					return null;
				}
				
				// Parse seed
				try {
					seed = Long.parseLong(words[2]);
				}
				catch(Exception exception) {
					System.out.println("The seed must be a number!");
				}
				
				// Create new ResetCommand
				return new ResetCommand(level, seed);
			}
			else {
				System.out.format("[ERROR]: Command %s: %s%n%n", SHORTCUT, INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			}
		}
		return null;
	}
}
