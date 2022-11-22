package es.ucm.tp1.logic.exceptions;

@SuppressWarnings("serial")
public class CommandParseException extends GameException {
	public CommandParseException(String errorMessage) {
		super(errorMessage);
	}
}
