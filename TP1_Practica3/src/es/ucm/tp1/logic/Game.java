package es.ucm.tp1.logic;

import java.util.Random;
import es.ucm.tp1.control.Level;
import es.ucm.tp1.control.Record;
import es.ucm.tp1.logic.gameobjects.GameObject;
import es.ucm.tp1.logic.gameobjects.Player;
import es.ucm.tp1.utils.StringUtils;
import es.ucm.tp1.view.GamePrinter;

public class Game {
	// attributes
	private Long seed;
	private Level level;
	private boolean endGame;
	private boolean testMode;
	private Player player;
	private Random rand;
	private long initialTime;
	private int cycle;
	private boolean isUserExit;
	private GameObjectContainer objectContainer;
	private Record record;
	
	private final String finishLine = "¦";

	// constructor
	public Game(Long seed, Level level) {
		this.resetLevel(level, seed);
	}

	// methods
	public void resetLevel(Level level, long seed) {
		this.seed = seed;
		this.level = level;
		if(this.level.equals(Level.TEST)) this.toggleTest();
		this.objectContainer = new GameObjectContainer();
		this.reset();
	}
	
	public void reset() {
		this.player = new Player(this, 0, this.level.getWidth() / 2);
		this.rand = new Random(this.seed);
		this.cycle = 0;
		this.isUserExit = false;
		this.objectContainer.reset();
		this.record = Record.load(this.level);
		
		// Generate game objects
		GameObjectGenerator.generateGameObjects(this, this.level);

		this.initialTime = System.currentTimeMillis();
	}

	public void movePlayer(int x, int y) {
		if(!this.player.doCollision()) this.player.move(x, y);
		this.player.doCollision();
	}
	
	public void update() {
		this.cycle++;
		this.objectContainer.update();
		GameObjectGenerator.generateRuntimeObjects(this);
	}
	
	public int getRandomLane() {
		return (int) (rand.nextDouble() * this.level.getWidth());
	}
	
	public int getRandomCol() {
		return (int) (rand.nextDouble() * this.level.getVisibility());
	}

	public void toggleTest() {
		this.testMode = true;
	}

	public int getCycle() {
		return this.cycle;
	}

	public boolean isFinished() {
		if (!this.player.isAlive() || this.hasArrived()) {
			this.endGame = true;
		}
		return this.endGame;
	}

	public void setUserExit() {
		this.endGame = true;
		this.isUserExit = true;
	}

	public String positionToString(int x, int y) {
		x += this.player.getX();
		StringBuilder buffer = new StringBuilder();
		if (this.player.getX() == x && this.player.getY() == y) buffer.append(this.player.toString() + " ");
		if (!this.isPositionEmpty(x, y)) buffer.append(this.objectContainer.getObjectSymbol(x, y) + " "); 
		if (x == this.level.getLength()) buffer.append(this.finishLine);
		return buffer.toString().trim();
	}
	
	public boolean isPositionEmpty(int x, int y) {
		return this.objectContainer.isPositionEmpty(x, y);
	}

	public int getVisibility() {
		return this.level.getVisibility();
	}

	public boolean isTestMode() {
		return this.testMode;
	}

	public long elapsedTime() {
		if (this.cycle == 0)
			return 0;
		else
			return (System.currentTimeMillis() - this.initialTime);
	}

	public int getRoadLength() {
		return this.level.getLength();
	}

	public int getRoadWidth() {
		return this.level.getWidth();
	}

	public int distanceToGoal() {
		return (this.level.getLength() - this.player.getX());
	}

	public Level getLevel() {
		return this.level;
	}

	public int playerCoins() {
		return this.player.getNumCoins();
	}

	public boolean isUserExit() {
		return this.isUserExit;
	}

	public boolean hasArrived() {
		return (this.level.getLength() - this.player.getX() < 0);
	}

	public boolean isNewRecord() {
		if(this.elapsedTime() < this.record.getRecord()) {
			this.record.updateRecord(this.elapsedTime());
			return true;
		}
		else return false;
	}

	public long getRecord() {
		return this.record.getRecord();
	}

	public void tryToAddObject(GameObject o, double f) {
		if (this.rand.nextDouble() < f && this.objectContainer.isPositionEmpty(o.getX(), o.getY()))
			this.objectContainer.addObject(o);
	}

	public Collider getColliderInPosition(int x, int y) {
		return this.objectContainer.getColliderInPosition(x, y);
	}

	public void forceAddObject(GameObject o) {
		this.objectContainer.addObject(o);
	}

	public void clear() {
		this.objectContainer.clear();
		
	}
	public void clearColumn() {
		this.objectContainer.clearColumn(this.getVisibility()+cycle);
		
	}

	public boolean buyCommand(int cost) {
		if(this.playerCoins() >= cost) {
			this.player.addCoin(-1*cost);
			return true;
		}
		else return false;
	}

	public void execute(InstantAction a) {
		a.execute(this);
	}

	public int getPlayerX() {
		return this.player.getX();
	}
	
	public int getPlayerY() {
		return this.player.getY();
	}

	public void removeGameObject(GameObject o) {
		this.objectContainer.removeObject(o);
	}

	public void awardCoinsToPlayer(int value) {
		this.player.addCoin(value);
	}

	public String getDescription() {
		return GamePrinter.description(this.level);
	}
	
	public String serialize() {
		StringBuilder str = new StringBuilder();
		
		str.append("Game Objects: " + StringUtils.LINE_SEPARATOR);
		for(int x = 0; x < this.level.getLength(); x++) {
			for(int y = 0; y < this.level.getWidth(); y++) {
				if(x == this.player.getX() && y == this.getPlayerY()) str.append(this.player.serialize() + StringUtils.LINE_SEPARATOR);
				if(!this.objectContainer.isPositionEmpty(x, y)) str.append(this.objectContainer.serialize(x,y));
			}
		}
		
		return str.toString();
	}
}
