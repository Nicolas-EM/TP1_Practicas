package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Obstacle extends GameObject{
	public static final String INFO = "[Obstacle] hits car";
	private static final String symbol = "░";
	protected static int gameObstaclesCount = 0;
	
	
	public Obstacle(Game game, int x, int y) {
		super(game, x, y, symbol, INFO);
	}

	public Obstacle(Game game, int x, int y, String symbol, String info) {
		super(game, x, y, symbol, info);
	}

	public static int getObstaclesCount() {
		return Obstacle.gameObstaclesCount;
	}
			
	public static void reset() {
		Obstacle.gameObstaclesCount = 0;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.receiveCollision(player);
		return true;
	}

	@Override
	public void onEnter() {
		Obstacle.gameObstaclesCount++;
	}
	
	@Override
	public void onDelete() {
		this.game.removeGameObject(this);
		Obstacle.gameObstaclesCount--;
	}
	
	@Override
	public void receiveShot() {
		this.onDelete();
		
	}
	
	@Override
	public void receiveExplosion() {
		this.game.removeGameObject(this);
	}
	
	@Override
	public boolean receiveThunder() {
		this.onDelete();
		return true;
	}

	@Override
	public void receiveWave() {
		this.x++;
	}
	

	@Override
	public void update() {
//		if(this.x == this.game.getPlayerX() && this.y == this.game.getPlayerY()) this.game.doPlayerCollision();
	}
}
