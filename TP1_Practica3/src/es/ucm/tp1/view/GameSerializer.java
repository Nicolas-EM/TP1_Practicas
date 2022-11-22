package es.ucm.tp1.view;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.utils.StringUtils;

public class GameSerializer {
	private Game game;
	
	public GameSerializer(Game game) {
		this.game = game;
	}
	
	private String getInfo() {
		StringBuilder str = new StringBuilder();
		
		str.append("Level: " + this.game.getLevel() + StringUtils.LINE_SEPARATOR);
		str.append("Cycles: "+ this.game.getCycle() + StringUtils.LINE_SEPARATOR);
		str.append("Coins: "+ this.game.playerCoins() + StringUtils.LINE_SEPARATOR);
		if(!this.game.isTestMode()) str.append("EllapsedTime: "+ this.game.elapsedTime() + StringUtils.LINE_SEPARATOR);
		
		return str.toString();
	}
	
	public String serializedObjects() {
		StringBuilder str = new StringBuilder();
		
		str.append(this.game.serialize());
		
		return str.toString();
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("---- ROAD FIGHTER SERIALIZED ----" + StringUtils.LINE_SEPARATOR);
		
		str.append(this.getInfo());
		str.append(this.serializedObjects());
		
		return str.toString();
	}
}
