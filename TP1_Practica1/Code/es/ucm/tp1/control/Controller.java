package es.ucm.tp1.control;

import java.util.Scanner;
import es.ucm.tp1.logic.Game;

public class Controller {
	// Prompt for input
	private static final String PROMPT = "Command > ";
	// Available commands
	private static final String[] HELP = new String[] {
		"Available commands:",
		"[h]elp: show this help",
		"[i]nfo: prints gameobject info",
		"[n]one | []: update",
		"[q]: go up",
		"[a]: go down",
		"[e]xit: exit game",
		"[r]eset: reset game",
		"[t]est: enables test mode",	
	};
	
	// Game info
	private static final String[] INFO = new String[] {
		"Available objects:",
		"[Car] the racing car",
		"[Coin] gives 1 coin to the player",
		"[Obstacle] hits car",	
	};
	private Game game;
	private Scanner scanner;
	
	// Constructor
	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
	}

	public void printGame() {
		System.out.println(game);
	}

	public void run() {
		this.printGame();
		while(!game.getEndGame()) {
			System.out.print(PROMPT);
			
			// User input
			String s = scanner.nextLine();
			String[] words = s.trim().split(" ");
			System.out.println(System.lineSeparator() + "[DEBUG] Executing: " + words[0]);
			if(words[0] == "") words[0] = "n";
			switch(words[0].substring(0,1)) {
			case "i":
			case "I":
				this.printStringArr(INFO);
				break;
			case "":
				game.moveForward();
				this.printGame();
				break;
			case "n":
				game.moveForward();
				this.printGame();
				break;
			case "q":
				game.goUp();
				game.moveForward();
				this.printGame();
				break;
			case "a":
				game.goDown();
				game.moveForward();
				this.printGame();
				break;
			case "e":
				game.setUserExit();
				break;
			case "r":
				this.game.reset();
				this.printGame();
				break;
			case "t":
				game.toggleTest();
				this.printGame();
				break;
			case "h":
			case "H":
				this.printStringArr(HELP);
				break;
			default:
				System.out.println("[ERROR]: Unknown command" + System.lineSeparator());
			}
			
			if(this.game.getEndGame()) {
				if(this.game.playerHasCrashed()) System.out.println("[GAME OVER] Player crashed!" + System.lineSeparator());
				else if(this.game.playerHasWon()) System.out.println("[GAME OVER] Player wins!" + System.lineSeparator());
				else System.out.println("[GAME OVER] Player leaves the game");
			}
		}
	}
	
	// Method to print String Arrays (for HELP and INFO)
	private void printStringArr(String[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}
