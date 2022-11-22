package es.ucm.tp1.logic.exceptions;

@SuppressWarnings("serial")
public class NotEnoughCoinsException extends CommandExecuteException {

	public NotEnoughCoinsException(String errorMessage) {
		super(errorMessage);
	}

}
