package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Coin {
	private int row, col;
	private Game game;
	private static final String SYMBOL = "¢";
	private static int gameCoinsCount = 0;
	private Boolean isDead;
	
	// Constructor
	public Coin(Game game, int col, int row) {
		this.game = game;
		this.row = row;
		this.col = col;
		this.isDead = false;
	}

	// Methods
	// Used when player collisions with coin
	public void setDead() {
		this.isDead = true;
	}
		
	// Return TRUE if no collision and is still on the board
	public Boolean isAlive() {
		return (!this.isDead && col > game.getCycle());
	}
	
	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}
	
	public static String getSymbol() {
		return Coin.SYMBOL;
	}
	
	public static int getNumCoin() {
		return Coin.gameCoinsCount;
	}
	
	public static void addNumCoin() {
		Coin.gameCoinsCount++;
	}
	
	public static void subNumCoin() {
		Coin.gameCoinsCount--;
	}
	
	public static void reset() {
		Coin.gameCoinsCount = 0;
	}
}
