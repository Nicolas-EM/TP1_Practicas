package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public abstract class GameObject implements Collider {
	protected int x, y;
	protected Game game;
	protected String symbol;
	private final String info;
	
	public GameObject(Game game, int x, int y, String symbol, String info) {
		this.x = x;
		this.y = y;
		this.game = game;
		this.symbol = symbol;
		this.info = info;
	}

	protected String getSymbol() {
		return this.symbol;
	}

	public String toString() {
		if (this.isAlive()) {
			return getSymbol();
		}
		return "";
	}

	public boolean isInPosition(int x, int y) {
		return this.x == x && this.y == y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean isAlive() {
		return true;
	}

	public String info() {
		return info;
	}
	
	public abstract void onEnter();
	public abstract void update();
	public abstract void onDelete();
	public String serialize() {
		return getSymbol() + " (" + this.getX() + ", " + this.getY() + ")";
	}
	
}
