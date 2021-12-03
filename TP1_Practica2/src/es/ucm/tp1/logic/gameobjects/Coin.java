package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Coin extends GameObject{
	public static final String INFO = "[Coin] gives 1 coin to the player";
	private static final String symbol = "¢";
	private static int gameCoinsCount = 0;
	private int value = 1;
	
	
	public Coin(Game game, int x, int y) {
		super(game, x, y, symbol, INFO);
	}

	public Coin(Game game, int x, int y, String SYMBOL, String INFO) {
		super(game, x, y, SYMBOL, INFO);
	}

	public static int getCoinsCount() {
		return Coin.gameCoinsCount;
	}
		
	public static void reset() {
		Coin.gameCoinsCount = 0;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.addCoin(this.value);
		this.onDelete();
		
		return false;
	}

	@Override
	public void onEnter() {
		Coin.gameCoinsCount++;
	}

	@Override
	public void onDelete() {
		this.game.removeGameObject(this);
		Coin.gameCoinsCount--;
        
	}
	
	@Override
	public void receiveWave() {
		this.x++;
	}
	
	@Override
	public void update() {
		
	}
	
	// No hace nada
	@Override
	public void receiveShot() {}
	@Override
	public void receiveExplosion() {}
	@Override
	public boolean receiveThunder() {return false;}
}
