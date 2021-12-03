package es.ucm.tp1.logic.gameobjects;

import java.util.Arrays;

public class CoinList {
	private Coin[] coins;
	private int coinsCount;
	
	// Constructor
	public CoinList() {
		this.coinsCount = 0;
		this.coins = new Coin[this.coinsCount];
		Coin.reset();
	}
	
	// Create a new Coin and add to array
	public void addCoin(Coin c) {
		this.coins = Arrays.copyOf(this.coins, this.coinsCount + 1);
		this.coins[this.coinsCount] = c;
		this.coinsCount++;
		Coin.addNumCoin();
	}
	
	// Set coin at x,y dead (when player collision)
	public void setCoinPositionDead(int x, int y) {
		Boolean killed = false;
		for(int i = 0; i < this.coinsCount && !killed; i++) {
			if(this.coins[i].getRow() == x && this.coins[i].getCol() == y) {
				this.coins[i].setDead();
				this.coins = Arrays.copyOfRange(this.coins, 1, this.coinsCount);
				this.coinsCount--;
				Coin.subNumCoin();
				killed = true;
			}
		}
	}
	
	// Return FALSE if there is a coin with position x,y
	public Boolean coinPositionEmpty(int x, int y) {
		Boolean empty = true;
		for(int i = 0; i < this.coinsCount; i++) {
			if(this.coins[i].getRow() == x && this.coins[i].getCol() == y) empty = false;
		}
		return empty;
	}
}
