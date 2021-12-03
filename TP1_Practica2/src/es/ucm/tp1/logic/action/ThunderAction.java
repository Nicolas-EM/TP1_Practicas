package es.ucm.tp1.logic.action;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.InstantAction;

public class ThunderAction implements InstantAction {
	private int x, y;
	
	
	@Override
	public void execute(Game game) {
		this.x = game.getRandomCol();
		this.y = game.getRandomLane();
		Collider other = game.getColliderInPosition(this.x + game.getPlayerX(), this.y);
		if(this.x > 0) this.x--;
		
		if(other != null && other.receiveThunder()&&other.toString().trim()!="█") {
			System.out.println("Thunder hit position: (" + this.x + " , " + this.y + ") -> " + other.toString().trim());
		}
		else if(other != null &&other.toString().trim()=="█") {
			System.out.println("Thunder hit position: (" + this.x + " , " + this.y + ") -> " + "░");

		}
		else System.out.println("Thunder hit position: (" + this.x + " , " + this.y + ")");
	}

}
