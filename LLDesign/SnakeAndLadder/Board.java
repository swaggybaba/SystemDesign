package SnakeAndLadder;

public class Board {
	 Cell cells[][];
	 
	 public Board() {
		cells=new Cell[10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				cells[i][j]=new Cell();
			}
		}
	 }
}
