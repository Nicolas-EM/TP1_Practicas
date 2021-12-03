package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Truck extends Obstacle{
	public static final String INFO = "[TRUCK] moves towards the player";
	private static final String symbol = "←";
	
	public Truck(Game game, int x, int y) {
		super(game, x, y, symbol, INFO);
	}

	@Override
	public void update() {
		this.x-=1;
	}
	
	@Override
	public void receiveExplosion() {
		Obstacle.gameObstaclesCount--;
		this.game.removeGameObject(this);
	}
}
