package es.ucm.tp1.logic.action;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.InstantAction;

public class ShootAction implements InstantAction{

	@Override
	public void execute(Game game) {
		for(int i = 0; i < game.getVisibility(); i++) {
			Collider other = game.getColliderInPosition(game.getPlayerX() + i, game.getPlayerY());
			if (other != null) {
				other.receiveShot();
				break;
			}
		}
	}

}
