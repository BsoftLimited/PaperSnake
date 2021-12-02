package com.bsoft.papersnake.Utils;

public class Position{
	int row, column;
	
	public Position(int row, int column){
		this.row = row;
		this.column = column;
	}
	
	public void moveRight(){this.column += 1;}
	public void moveLeft(){this.column -= 1;}
	public void moveUp(){this.row -= 1;}
	public void moveDown(){ this.row += 1; }
	public int getRow(){ return row; }
	public int getColumn(){ return column; }
	public void set(int row, int column){
		this.row = row;
		this.column = column;
	}
}
