package es.ucm.tp1.logic.action;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.InstantAction;

public class WaveAction implements InstantAction {

	@Override
	public void execute(Game game) {
		for(int y = 0; y < game.getRoadWidth(); y++) {
			for(int i = game.getVisibility() - 1; i >= 0; i--) {
				Collider other = game.getColliderInPosition(game.getCycle() + i, y);
				if (other != null && game.isPositionEmpty(game.getCycle() + i + 1, y)) {
					other.receiveWave();
				}
			}
		}
	}
}
