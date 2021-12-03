package es.ucm.tp1.logic.gameobjects;
import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public class Grenade extends GameObject{
	public static final String INFO = "[GRENADE] Explodes in 3 cycles, harming everyone around";
	private static final String symbol = "ð";
	private int lives = 3;
	
	public Grenade(Game game, int x, int y) {
		super(game, x, y, symbol, INFO);
	}
	
	protected String getSymbol() {
		return Grenade.symbol + "[" + this.lives + "]";
	}

	@Override
	public boolean receiveCollision(Player player) {
		return false;
	}

	@Override
	public void onEnter() {
		this.lives = 4;
	}

	@Override
	public void update() {
		if(--this.lives == 0) {
			this.onDelete();
		}
	}

	@Override
	public void onDelete() {
		for(int x = -1; x < 2; x++) {
			for(int y = -1; y < 2; y++) {
				if(!(x == 0 && y == 0)) {
					Collider other = this.game.getColliderInPosition(x + this.x, y + this.y);
					if (other != null) other.receiveExplosion();
				}
			}
		}
		this.game.removeGameObject(this);
	}
	
	@Override
	public void receiveWave() {
		this.x++;
	}

	// No hace nada
	@Override
	public void receiveShot() {}
	@Override
	public void receiveExplosion() {}
	@Override
	public boolean receiveThunder() {return false;}
}
