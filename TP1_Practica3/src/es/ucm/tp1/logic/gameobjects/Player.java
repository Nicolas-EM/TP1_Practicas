package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public class Player extends GameObject {
	public static final String INFO = "[Car] the racing car";
	private int numCoins;
	private String symbol = ">";
	
	public Player(Game game, int x, int y) {
		super(game, x, y, ">", INFO);
		this.symbol = ">";
		this.numCoins = 5;
	}
	
	public void move(int x, int y) {
		if(this.y + y >= 0 && this.y + y < game.getRoadWidth()) this.y += y;
		this.x += x;
	}

	public int getNumCoins(){
		return this.numCoins;
	}
	
	public void addCoin(int n) {
		this.numCoins += n;
	}
	
	public String toString() {
		return this.symbol;
	}
	
	public boolean doCollision() {
		Collider other = this.game.getColliderInPosition(x, y);
		if (other != null) {
			return other.receiveCollision(this);
		}
		return false;
	}
	
	@Override
	public boolean isAlive() {
		return this.symbol == ">";
	}

	@Override
	public boolean receiveCollision(Player player) {
		this.symbol = "@";
		return false;
	}

	// No hace nada
	@Override
	public void update() {}
	@Override
	public void onEnter() {}
	@Override
	public void onDelete() {}
	@Override
	public boolean receiveShot() { return false;}
	@Override
	public void receiveExplosion() {}
	@Override
	public void receiveWave() {}
	@Override
	public boolean receiveThunder() {return false;}
}
