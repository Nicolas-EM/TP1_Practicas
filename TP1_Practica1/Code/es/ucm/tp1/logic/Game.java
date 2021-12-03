package es.ucm.tp1.logic;

import java.util.Random;
import es.ucm.tp1.control.Level;
import es.ucm.tp1.logic.gameobjects.*;
import es.ucm.tp1.view.GamePrinter;

public class Game {
	// attributes
	private Long seed;
	private Level level;
	private boolean endGame;
	private boolean testMode;
	private GamePrinter gamePrinter;
	private Player player;
	private CoinList coins;
	private ObstacleList obstacles;
	private Random rand;
	private long initialTime;
	private float elapsedTime;
	private int cycle;
	
	// constructor
	public Game(Long seed, Level level) {
		this.seed = seed;
		this.level = level;
		this.gamePrinter = new GamePrinter(this, level.getVisibility(), level.getWidth());
		this.reset();
	}
	
	// methods
	public void goUp() {
		if(this.player.getRow() > 0) this.player.goUp();
	}

	public void goDown() {
		if(this.player.getRow() < this.level.getWidth() - 1) this.player.goDown();
	}

	public void moveForward() {
		this.player.update();
		this.cycle++;
	}
	
	public void reset() {
		this.coins = new CoinList();
		this.obstacles = new ObstacleList();
		this.player = new Player(this, level.getWidth()/2, 0);
		this.rand = new Random(this.seed);
		this.cycle = 0;
		for(int x = this.level.getVisibility()/2; x < this.level.getLength(); x++) {
			this.tryToAddObject(new Obstacle(this, x, this.getRandomWidth()), this.level.getObstacleFrequency());
			this.tryToAddObject(new Coin(this, x, this.getRandomWidth()), this.level.getCoinFrequency()); 
		}
		this.initialTime = System.currentTimeMillis();
	}
	
	public int getRandomWidth() {
		return (int) (rand.nextDouble() * this.level.getWidth());
	}
	
	// tryAddObstacle
	public void tryToAddObject(Obstacle o, double f) {
		if(this.rand.nextDouble() < f) this.obstacles.addObstacle(o);
	}
	
	// tryAddCoin
	public void tryToAddObject(Coin c, double f) {
		if(this.rand.nextDouble() < f & this.obstaclePositionEmpty(c.getRow(), c.getCol())) this.coins.addCoin(c);
	}
	
	// Returns FALSE if there is a Coin at (x,y)
	public Boolean coinPositionEmpty(int x, int y) {
		return this.coins.coinPositionEmpty(x,y);
	}

	// Returns FALSE if there is an Obstacle at (x,y)
	public boolean obstaclePositionEmpty(int x, int y) {
		return this.obstacles.obstaclePositionEmpty(x, y);
	}

	public boolean isTest() {
		return this.testMode;
	}
	
	public void toggleTest() {
		this.testMode = true;
	}
	
	public String getInfo() {
		if(this.cycle == 0) this.elapsedTime = 0;
		else this.elapsedTime =  (System.currentTimeMillis() - this.initialTime)/ 1000F;
		StringBuilder s = new StringBuilder();
		s.append("Distance: " + (this.level.getLength() - this.player.getCol()) + System.lineSeparator());
		s.append("Coins: " + this.player.getCoins() + System.lineSeparator());
		s.append("Cycle: " + this.cycle + System.lineSeparator());
		s.append("Total obstacles: " + Obstacle.getNumObstacle() + System.lineSeparator());
		s.append("Total coins: " + Coin.getNumCoin());
		if(!this.testMode) s.append(System.lineSeparator() + "Elapsed Time: " + String.format("%.02f", this.elapsedTime) + " s");
		return s.toString();
	}
	
	public int getCycle() {
		return this.cycle;
	}
	
	public boolean getEndGame() {
		if(this.playerHasCrashed() || this.playerHasWon()) {
			this.endGame = true;
		}
		return this.endGame;
	}

	public void setUserExit() {
		this.endGame = true;
	}
	
	public void setCoinPositionDead(int x, int y) {
		this.coins.setCoinPositionDead(x,y);
	}
	
	public boolean playerHasCrashed() {
		return this.player.getStatus();
	}
	
	public boolean playerHasWon() {
		return (this.level.getLength() - this.player.getCol() < 0);
	}
	
	public String positionToString(int x, int y) {
		y += this.cycle;
		if (x == this.player.getRow() && y == this.player.getCol()) return this.player.toString(); 
		else if(!this.coinPositionEmpty(x, y)) return Coin.getSymbol();
		else if(!this.obstaclePositionEmpty(x,y)) return Obstacle.getSymbol();
		else if(y == this.level.getLength()) return "¦";
		else return "";
	}
	
	public String toString() {
		return this.gamePrinter.toString();
	}
}
