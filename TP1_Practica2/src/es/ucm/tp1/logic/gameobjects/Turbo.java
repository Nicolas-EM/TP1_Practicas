package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Turbo extends GameObject{
	public static final String INFO = "[TURBO] pushes the car: 3 columns";
	private static final String symbol = ">>>";
	
	public Turbo(Game game, int x, int y) {
		super(game, x, y, symbol, INFO);
	}

	@Override
	public boolean receiveCollision(Player player) {
		this.onDelete();
		player.move(3,0);
		return false;
	}
	
	@Override
	public void receiveWave() {
		this.x++;
	}
	
	@Override
	public void onDelete() {
		this.game.removeGameObject(this);
	}
	
	// No hace nada
	@Override
	public void receiveShot() {}
	@Override
	public void receiveExplosion() {}
	@Override
	public void onEnter() {}
	@Override
	public void update() {}
	@Override
	public boolean receiveThunder() {return false;}
}
