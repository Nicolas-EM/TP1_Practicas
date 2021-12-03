package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Wall extends Obstacle{
	public static final String INFO = "[WALL] hard obstacle";
	private String symbol = "█";
	private int lives;
	private final int value = 5;
	
	public Wall(Game game, int x, int y) {
		super(game, x, y, "█", INFO);
		this.symbol = "█";
	}
	
	@Override
	public String toString() {
		return this.symbol;
	}

	@Override
	public void onEnter() {
		this.lives = 3;
		super.onEnter();
	}

	@Override
	public void onDelete() {		
		this.game.removeGameObject(this);	
		Obstacle.gameObstaclesCount--;	
			this.game.awardCoinsToPlayer(this.value);
	}

	@Override
	public void receiveShot() {
		switch(--this.lives) {
		case 2:
			this.symbol = "▒";
			break;
		case 1:
			this.symbol = "░";
			break;
		case 0:
			
			this.onDelete();
			
			break;
		}
	}
	
	@Override
	public void receiveExplosion() {
		this.game.removeGameObject(this);	
	}
}
