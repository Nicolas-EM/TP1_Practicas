package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Obstacle {
	private int row, col;
	private Game game;
	private static final String SYMBOL = "░";
	private static int gameObstaclesCount = 0;
	
	// Constructor
	public Obstacle(Game game, int col, int row) {
		this.game = game;
		this.row = row;
		this.col = col;
	}

	// Methods
	// Returns TRUE if obstacle is still on the board
	public boolean isAlive() {
		if(this.game.getCycle() >= this.col) return false;
		else return true;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public static String getSymbol() {
		return Obstacle.SYMBOL;
	}
	
	public static int getNumObstacle() {
		return Obstacle.gameObstaclesCount;
	}
	
	public static void addNumObstacle() {
		Obstacle.gameObstaclesCount++;
	}

	public static void subNumObstacle() {
		Obstacle.gameObstaclesCount--;
	}
	
	public static void reset() {
		Obstacle.gameObstaclesCount = 0;
	}
}

