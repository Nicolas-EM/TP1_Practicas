package es.ucm.tp1.logic.action;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.InstantAction;

public class ThunderAction implements InstantAction {
	private int x, y;
	
	
	@Override
	public void execute(Game game) {
		this.x = game.getRandomCol()+1;
		this.y = game.getRandomLane();
		Collider other = game.getColliderInPosition(this.x-1 + game.getPlayerX(), this.y);
		if(this.x > 0) this.x--;
		
		if(other != null && other.receiveThunder()) {
			System.out.println("Thunder hit position: (" + this.x + " , " + this.y + ") -> " + other.toString().trim());
		}		
		else System.out.println("Thunder hit position: (" + this.x + " , " + this.y + ")");
	}

}
