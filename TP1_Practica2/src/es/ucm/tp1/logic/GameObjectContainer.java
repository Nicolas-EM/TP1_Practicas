package es.ucm.tp1.logic;

import java.util.ArrayList;
import java.util.List;

import es.ucm.tp1.logic.gameobjects.Coin;
import es.ucm.tp1.logic.gameobjects.GameObject;
import es.ucm.tp1.logic.gameobjects.Obstacle;

public class GameObjectContainer {
	private List<GameObject> gameObjects;
	
	public GameObjectContainer() {
		this.gameObjects = new ArrayList<>();
	}
	
	private GameObject getObjectInPosition(int x, int y) {
		for (GameObject go : gameObjects)
			if (go.isInPosition(x, y)) return go;
		return null;
	}
	
	public boolean isPositionEmpty(int x, int y) {
		return getObjectInPosition(x, y) == null;
	}
	
	public void addObject(GameObject o) {
		this.gameObjects.add(o);
		o.onEnter();
	}
	
	public String getObjectSymbol(int x, int y) {
		StringBuilder buffer = new StringBuilder();
		for (GameObject go : gameObjects) {
			if(go.isInPosition(x, y)) buffer.append(go + " ");
		}
		return buffer.toString();
	}
	
	public Collider getColliderInPosition(int x, int y) {
		return this.getObjectInPosition(x, y);
	}

	public void removeObject(GameObject o) {
		List<GameObject> temp = new ArrayList<>();
		for (GameObject go : gameObjects) {
			if(!go.equals(o)) temp.add(go);
		}
		this.gameObjects = temp;
	}

	public void update() {
		for (GameObject go : gameObjects) {
			go.update();
		}
	}

	public void reset() {
		for (GameObject go : gameObjects) {
			go.onDelete();
		}
		Coin.reset();
		Obstacle.reset();
	
	}
	public void clearColumn(int column) {		
		for (GameObject go : gameObjects) {
				if(go.getX()==column-1) go.onDelete();
		}
		
	}
}
		
		
	
	
