package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Player {
	private int row, col, numCoins;
	private static String symbol = ">";
	private Game game;
	private boolean hasCrashed;
	
	public Player(Game game, int row, int col) {
		this.game = game;
		this.row = row;
		this.col = col;
		this.numCoins = 5;		// init_coins
		Player.symbol = ">";
	}
	
	public void update() {
		this.col++;
		if(!this.game.coinPositionEmpty(row, col)) {	// Collision with coin
			this.numCoins++;
			this.game.setCoinPositionDead(row, col);
		}
		else if(!this.game.obstaclePositionEmpty(row,col)) {	// Collision with obstacle
			Player.symbol = "@";
			this.hasCrashed = true;
		}
	}
	
	public void goUp() {
		this.row--;
	}
	
	public void goDown() {
		this.row++;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public int getCoins(){
		return this.numCoins;
	}
	
	public boolean getStatus() {
		return this.hasCrashed;
	}

	public String toString() {
		return Player.symbol;
	}
}
