package es.ucm.tp1.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.exceptions.CommandParseException;
import es.ucm.tp1.logic.exceptions.GameException;
import es.ucm.tp1.logic.exceptions.InputOutputRecordException;
import es.ucm.tp1.view.GameSerializer;

public class SaveCommand extends Command{
	private static final String NAME = "save";
	private static final String DETAILS = "sa[v]e <filename>";
	private static final String SHORTCUT = "v";
	private static final String HELP = "Save the state of the game to a file.";
	
	private String filename;
	
	public SaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public SaveCommand(String filename) {
		this();
		this.filename = filename + ".txt";
	}

	@Override
	public boolean execute(Game game) throws GameException {
		try(BufferedWriter outStream = new BufferedWriter(new FileWriter(filename))) {
				outStream.write("Super cars 3.0");
				outStream.newLine();
				outStream.newLine();
				outStream.write(new GameSerializer(game).toString());
				
				System.out.println("Game successfully saved to file " + this.filename);
		}
		catch (IOException ioe) { throw new InputOutputRecordException(String.format("[ERROR]: Command %s: %s%n%n", NAME, "IO Exception")); }
		return false;
	}
	
	@Override
	protected Command parse(String[] words) throws GameException {
		if (matchCommandName(words[0])) {
			if (words.length == 2) {
				return new SaveCommand(words[1]);
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
