package es.ucm.tp1.control;

import es.ucm.tp1.logic.Game;

public interface Buyable {
	static final String INSUFFICIENT_COINS = "Not enough coins";
	public int cost();
	public default boolean buy(Game game){
		return game.buyCommand(this.cost());
	};
}
