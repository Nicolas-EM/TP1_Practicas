package es.ucm.tp1.logic;


import es.ucm.tp1.control.Level;
import es.ucm.tp1.logic.action.ThunderAction;
import es.ucm.tp1.logic.gameobjects.*;

public class GameObjectGenerator {
	
	public static void generateGameObjects(Game game, Level level) {
		for (int x = game.getVisibility() / 2; x < game.getRoadLength(); x++) {
			game.tryToAddObject(new Obstacle(game, x, game.getRandomLane()), level.getObstacleFrequency());
			game.tryToAddObject(new Coin(game, x, game.getRandomLane()), level.getCoinFrequency());
			
			if(game.getLevel().hasAdvancedObjects()) {
				game.tryToAddObject(new Wall(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
				game.tryToAddObject(new Turbo(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
				if (!SuperCoin.hasSuperCoin()) {
					game.tryToAddObject(new SuperCoin(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
				}
				game.tryToAddObject(new Truck(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
				game.tryToAddObject(new Pedestrian(game, x, 0), level.advancedObjectsFrequency());
			}
		}
	}

	public static void generateRuntimeObjects(Game game) {
		if (game.getLevel().hasAdvancedObjects()) {
			game.execute(new ThunderAction());
		}
	}
	
	public static void forceAdvanceObject(Game game, int id, int x) {
		switch (id) {
			case 1:
				game.forceAddObject(new Wall(game, x, game.getRandomLane()));
				break;
			case 2:
				game.forceAddObject(new Turbo(game, x, game.getRandomLane()));
				break;
			case 3:
				game.forceAddObject(new SuperCoin(game, x, game.getRandomLane()));
				break;
			case 4:
				game.forceAddObject(new Truck(game, x, game.getRandomLane()));
				break;
			case 5:
				game.forceAddObject(new Pedestrian(game, x, 0));
				break;
		}
	}
}