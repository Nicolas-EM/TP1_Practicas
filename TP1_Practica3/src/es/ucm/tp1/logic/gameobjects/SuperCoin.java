package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class SuperCoin extends Coin{
	private static final int value = 1000;
	public static final String INFO = "[SUPERCOIN] gives 1000 coins";
	private static final String symbol = "$";
	private static boolean alive;
	
	public SuperCoin(Game game, int x, int y) {
		super(game, x, y, symbol, INFO);
	}

	@Override
	public void onEnter() {
		SuperCoin.alive = true;
	}

	@Override
	public void onDelete() {
		SuperCoin.alive = false;
		this.game.removeGameObject(this);
	}

	public static boolean hasSuperCoin() {
		return SuperCoin.alive;
	}
	
	public static void reset() {
		SuperCoin.alive = false;
	}
	
	@Override
	public boolean receiveCollision(Player player) {
		player.addCoin(SuperCoin.value);
		this.onDelete();
		return false;
	}
}
