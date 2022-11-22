package es.ucm.tp1.logic.exceptions;

@SuppressWarnings("serial")
public class CommandExecuteException extends GameException {
	public CommandExecuteException(String errorMessage) {
		super(errorMessage);
	}
}
