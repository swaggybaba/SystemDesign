package SnakeAndLadder;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
	Board board;
	Dice dice;
	Deque<Player> players;
	
	public Game(int countOfSnake,int countOfLadder,int playersCount) {
		board=new Board();
		addJump(countOfSnake, countOfLadder);
		addPlayers(playersCount);
		dice=new Dice(1);
	}
	
	public void start() {
		Player winnerPlayer=null;
		while(winnerPlayer==null) {
			Player curPlayer=players.remove();
			System.out.println("Player turn is "+curPlayer.id+" current position is "+ curPlayer.currentPositon);
			int curPlayerScore=dice.rollDice();
			if(curPlayer.currentPositon+curPlayerScore<=100) {
				curPlayer.currentPositon+=curPlayerScore;
				if(curPlayer.currentPositon==100) {
					winnerPlayer=curPlayer;
					break;
				}
			}
			setPositionAfterJump(curPlayer);
			System.out.println("Player turn is "+curPlayer.id+" new position is "+ curPlayer.currentPositon);
			if(curPlayer.currentPositon==100) {
				winnerPlayer=curPlayer;
			}
			players.add(curPlayer);
		}
		System.out.println("Winner of Game is player" +" "+winnerPlayer.id);
	}
	
	private void setPositionAfterJump(Player curPlayer) {
		int pos[]=getPos(curPlayer.currentPositon);
		if(board.cells[pos[0]][pos[1]].jump!=null) {
			Jump jump=board.cells[pos[0]][pos[1]].jump;
			if(jump.start==curPlayer.currentPositon) {
				curPlayer.currentPositon=jump.end;
				System.out.println("jump done by "+(jump.end>jump.start?"ladder":"snake"));
			}
		}
	}

	private int[] getPos(int currentPositon) {
		return new int[]{currentPositon/10,currentPositon%10};
	}

	private void addPlayers(int playersCount) {
		players=new LinkedList<Player>(); 
		int playerId=0;
		while (playersCount-->0) {
			Player player=new Player(Integer.toString(playerId++));
			players.add(player);
		}
		
	}

	private void addJump(int countOfSnake,int countOfLadder) {
		//add snake
		while (countOfSnake>0) {
			int start=ThreadLocalRandom.current().nextInt(1, 99);
			int end=ThreadLocalRandom.current().nextInt(1, 99);
			if(start>end) {
				countOfSnake--;
				addJumpFromTo(start,end);
			}
		}
		//add ladder
		while (countOfLadder>0) {
			int start=ThreadLocalRandom.current().nextInt(1, 99);
			int end=ThreadLocalRandom.current().nextInt(1, 99);
			if(start<end) {
				countOfLadder--;
				addJumpFromTo(start,end);
			}
		}
	}
	private void addJumpFromTo(int start,int end) {
		int pos[]=getPos(start);
		board.cells[pos[0]][pos[1]].jump=new Jump();
		board.cells[pos[0]][pos[1]].jump.start=start;
		board.cells[pos[0]][pos[1]].jump.end=end;
	}
}
