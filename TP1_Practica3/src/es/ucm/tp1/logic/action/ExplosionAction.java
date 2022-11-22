package es.ucm.tp1.logic.action;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.InstantAction;

public class ExplosionAction implements InstantAction {
	private int x;
	private int y;
	
	public ExplosionAction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void execute(Game game) {
		for(int x = -1; x < 2; x++) {
			for(int y = -1; y < 2; y++) {
				if(!(x == 0 && y == 0)) {
					Collider other = game.getColliderInPosition(x + this.x, y + this.y);
					if (other != null) other.receiveExplosion();
				}
			}
		}
	}

}
