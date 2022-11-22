package es.ucm.tp1.control.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.exceptions.CommandParseException;
import es.ucm.tp1.logic.exceptions.GameException;
import es.ucm.tp1.logic.exceptions.InputOutputRecordException;
import es.ucm.tp1.utils.StringUtils;

public class DumpCommand extends Command {
	private static final String NAME = "dump";
	private static final String DETAILS = "[d]ump <filename>";
	private static final String SHORTCUT = "d";
	private static final String HELP = "Shows the content of a saved file";
	
	private String filename;
	
	public DumpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public DumpCommand(String filename) {
		this();
		this.filename = filename + ".txt";
	}
	
	@Override
	public boolean execute(Game game) throws GameException {
		try(BufferedReader inStream = new BufferedReader(new FileReader(filename))) {
			String line = inStream.readLine();
			while (line != null) {
				System.out.println(line);
				line = inStream.readLine();
			}
		}
		catch (IOException ioe) { throw new InputOutputRecordException("An error ocurred on reading a file" + StringUtils.LINE_SEPARATOR + StringUtils.LINE_SEPARATOR); }
		return false;
	}
	
	@Override
	protected Command parse(String[] words) throws GameException {
		if (matchCommandName(words[0])) {
			if (words.length == 2) {
				return new DumpCommand(words[1]);
			} else if(words.length == 1) {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s%n%n", NAME, "Save must be followed by a filename"));
			}
			else {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s%n%n", NAME, "Invalid filename: the filename contains spaces"));		
			}
		}
		return null;
	}
	
}