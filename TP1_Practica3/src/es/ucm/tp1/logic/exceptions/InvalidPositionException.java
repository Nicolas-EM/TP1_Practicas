package es.ucm.tp1.logic.exceptions;

@SuppressWarnings("serial")
public class InvalidPositionException extends CommandExecuteException {

	public InvalidPositionException(String errorMessage) {
		super(errorMessage);
	}

}
