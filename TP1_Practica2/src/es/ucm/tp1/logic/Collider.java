package es.ucm.tp1.logic;

import es.ucm.tp1.logic.gameobjects.Player;

public interface Collider {
	boolean receiveCollision(Player player);
	void receiveShot();
	void receiveWave();
	void receiveExplosion();
	boolean receiveThunder();
}
