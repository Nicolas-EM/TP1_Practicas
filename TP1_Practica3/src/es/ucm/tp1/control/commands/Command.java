package es.ucm.tp1.control.commands;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.exceptions.*;

public abstract class Command {
	protected static final String UNKNOWN_COMMAND_MSG = "Unknown command";
	protected static final String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";

	/* @formatter:off */
	protected static final Command[] AVAILABLE_COMMANDS = {
		new HelpCommand(),
		new InfoCommand(),
		new UpdateCommand(),
		new GoUpCommand(),
		new GoDownCommand(),
		new ExitCommand(),
		new ResetCommand(),
		new ToggleTestCommand(),
		new ShootCommand(),
		new GrenadeCommand(),
		new WaveCommand(),
		new SerializeCommand(),
		new SaveCommand(),
		new DumpCommand(),
		new ShowRecordCommand(),
		new ClearCommand(),
		new CheatCommand(),				
	};
	/* @formatter:on */

	public static Command getCommand(String[] commandWords) throws GameException {
		Command command = null;
		
		for(int i = 0; i < AVAILABLE_COMMANDS.length && command == null; i++) {
			try {
				command = AVAILABLE_COMMANDS[i].parse(commandWords);
			}
			catch(GameException e) {
				throw e;
			}
		}
		if(command == null) throw new CommandParseException(String.format("[ERROR]: %s%n%n", UNKNOWN_COMMAND_MSG));
	
		return command;
	}

	private final String name;
	private final String shortcut;
	private final String details;
	private final String help;

	public Command(String name, String shortcut, String details, String help) {
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}

	public abstract boolean execute(Game game) throws GameException;

	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
	}

	protected Command parse(String[] words)  throws GameException {
		if (matchCommandName(words[0])) {
			if (words.length != 1) {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s%n%n", name, INCORRECT_NUMBER_OF_ARGS_MSG));
			} else {
				return this;
			}
		}
		return null;
	}
	
	public String toString() {
		return this.details + ": " + this.help;
	}
}