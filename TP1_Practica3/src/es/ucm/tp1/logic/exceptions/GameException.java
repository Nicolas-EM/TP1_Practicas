package es.ucm.tp1.logic.exceptions;

@SuppressWarnings("serial")
public abstract class GameException extends Exception {
	public GameException(String errorMessage) {
		super(errorMessage);
	}
}
