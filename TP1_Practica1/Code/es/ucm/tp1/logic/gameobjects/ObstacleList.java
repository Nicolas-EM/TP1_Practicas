package es.ucm.tp1.logic.gameobjects;

import java.util.Arrays;

public class ObstacleList {
		private Obstacle[] obstacles;
		private int obstaclesCount;
		
		// Constructor
		public ObstacleList() {
			this.obstaclesCount = 0;
			this.obstacles = new Obstacle[this.obstaclesCount];
			Obstacle.reset();
		}
		
		// Create a new Obstacle and add to array
		public void addObstacle(Obstacle o) {
			this.obstacles = Arrays.copyOf(this.obstacles, this.obstaclesCount + 1);
			this.obstacles[this.obstaclesCount] = o;
			this.obstaclesCount++;
			Obstacle.addNumObstacle();
		}
		
		// Return FALSE if there is a obstacle with position x,y
		public Boolean obstaclePositionEmpty(int x, int y) {
			Boolean empty = true;
			for(int i = 0; i < this.obstaclesCount; i++) {
				if(obstacles[i].getRow() == x && obstacles[i].getCol() == y) empty = false;
			}
			return empty;
		}
}
