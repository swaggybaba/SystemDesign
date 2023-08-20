package SnakeAndLadder;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
	int diceCount;
	
	public Dice(int diceCount) {
		this.diceCount=diceCount;
	}
	
	public int rollDice() {
		int count=0;
		int score=0;
		while(count++<diceCount)
			score+=ThreadLocalRandom.current().nextInt(1, 6);
		return score;
	}
}
