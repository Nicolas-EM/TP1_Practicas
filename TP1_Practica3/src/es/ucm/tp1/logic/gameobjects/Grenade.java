package es.ucm.tp1.logic.gameobjects;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.action.ExplosionAction;

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
		this.game.execute(new ExplosionAction(this.x, this.y));
		this.game.removeGameObject(this);
	}
	
	@Override
	public void receiveWave() {
		this.x++;
	}
	
	@Override
	public String serialize(){
		String nombre=super.serialize();
		return nombre+" "+this.lives;
		
	}

	// No hace nada
	@Override
	public boolean receiveShot() { return false;}
	@Override
	public void receiveExplosion() {}
	@Override
	public boolean receiveThunder() {return false;}
}
