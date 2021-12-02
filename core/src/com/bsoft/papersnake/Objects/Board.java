package com.bsoft.papersnake.Objects;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.*;

public abstract class Board extends GameObject2D{
	public static final int CellSize = 32;
	private static Boundary boundary;
	private byte[][] cells;
	private int rLength, cLength;
	
	public Board(){
		boundary = new Boundary(0, 0, 480, 640);
		rLength = (int)boundary.getHeight() / CellSize;
		cLength = (int)boundary.getWidth() / CellSize;
		this.cells = new byte[rLength][cLength];
	}
	
	public void clear(){
		this.init(cells, rLength, cLength);
	}
	
	public abstract void init(byte[][] cells, int rLength, int cLength);
	public void slide(){ boundary.setX( boundary.getX() == 0 ? PaperSnake.GameWidth - boundary.getWidth() : 0); }
	public static int getX(){ return (int)boundary.getX(); }
	public static int getY(){ return (int)boundary.getY(); }
	public static Boundary getBoundary(){ return boundary; }
	public byte getValue(int row, int column){return cells[row][column];	}
	public void setValue(int row, int column, byte value){this.cells[row][column] = value;	}
	
	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer){
		batch.begin();
		for(int i = 0; i < boundary.getHeight()/CellSize; i++){
			for(int j = 0; j < boundary.getWidth()/CellSize; j ++){
				if( cells[i][j] == 1){
					float x = j * Board.CellSize + boundary.getX();
					float y = boundary.getHeight() - Board.CellSize - (i * Board.CellSize + boundary.getY());
					batch.draw(TextureLoader.crate(), x, y, Board.CellSize, Board.CellSize);
				}
			}
		}
		batch.end();
	}

	@Override
	public void enableTouch()
	{
		// TODO: Implement this method
	}

	@Override
	public void disableTouch()
	{
		// TODO: Implement this method
	}
	
	@Override
	public void dispose(){ }
}
