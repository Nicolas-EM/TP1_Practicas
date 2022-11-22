package es.ucm.tp1.logic.exceptions;

@SuppressWarnings("serial")
public class InputOutputRecordException extends CommandExecuteException {

	public InputOutputRecordException(String errorMessage) {
		super(errorMessage);
	}

}
