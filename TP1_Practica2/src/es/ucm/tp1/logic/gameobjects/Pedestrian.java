package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Pedestrian extends Obstacle{
	public static final String INFO = "[PEDESTRIAN] person crossing the road up and down";
	private static final String symbol = "☺";
	private boolean goingDown = true;
	
	public Pedestrian(Game game, int x, int y) {
		super(game, x, y, symbol, INFO);
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.receiveCollision(player);
		player.addCoin(-1*player.getNumCoins());
		this.onDelete();
		return true;
	}
	
	@Override
	public void receiveShot() {
		this.game.awardCoinsToPlayer(-1*this.game.playerCoins());
		this.onDelete();
	}

	@Override
	public void update() {
		if(this.goingDown) {
			this.y++;
			if(this.y + 1 == game.getRoadWidth()) this.goingDown = false;
		}
		else {
			y--;
			if(this.y == 0) this.goingDown = true;
		}
	}
	
	@Override
	public void onDelete() {
		this.game.removeGameObject(this);
		Obstacle.gameObstaclesCount--;
	}
	
	@Override
	public void receiveExplosion() {
		this.onDelete();
	}
}
