package com.bsoft.papersnake.Objects;

import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Utils.*;
import java.io.*;
import com.badlogic.gdx.utils.*;
import java.util.logging.*;
import com.bsoft.papersnake.Objects.UIs.*;

public class LevelIcon  implements Serializable, Disposable, TouchHandler.OnTouchListener{
	private int value,  nStars;
	private boolean isLocked;
	private Position position;
	private String objective;
	private LevelIconBoard board;
	private Boundary boundary;
	private TouchHandler handler;
	private Font digit;
	private float dent;
	
	public LevelIcon(LevelIconBoard board, int value, int nStars, boolean isLocked){
		this.value = value;
		this.nStars = nStars;
		this.isLocked = isLocked;
		this.board = board;
		this.digit = new Font(Integer.toString(this.value)).setSize(16);
		this.position = new Position(0, 0);
		this.boundary = new Boundary();
		this.boundary.setSize(LevelIconBoard.CellWidth, LevelIconBoard.CellHeight);
		this.handler = new TouchHandler(this, boundary);
		this.init();
	}
	
	public LevelIcon(LevelIconBoard board, int value, int nStar){ this(board, value, nStar, false); }
	public LevelIcon(LevelIconBoard board, int value, boolean isLocked){ this(board, value, 0, isLocked);}
	public LevelIcon(LevelIconBoard board, int value){ this(board, value, 0, true); }
	
	public void unLock(){ isLocked = true; }
	public Position getPosition(){ return position; }
	public int getStars(){ return nStars; }
	public int getValue(){ return value; }
	public boolean isLocked(){ return isLocked; }
	public void setObjective(String objective){ this.objective = objective; }
	public String getObjective(){ return objective; }
	public void setPosition(int row, int column){
		position.set(row, column);
		this.init();
	}
	public void setStar(int nStar){ this.nStars = nStar; }
	
	public void init(){
		float y = (board.getY() + board.getHeight()) - LevelIconBoard.CellHeight * (position.getRow() + 1) ;
		float x = LevelIconBoard.CellWidth * position.getColumn();
		
		this.boundary.setY(y);
		this.boundary.setX(x + board.getX());
		dent = boundary.getWidth() / 10.0f;
		this.digit.setPosition(board.getX() + x + (boundary.getWidth()/2) - (this.digit.getWidth()/2),  y + (boundary.getHeight()/2) - (this.digit.getHeight()/2) + 10);
	}

	public void draw(SpriteBatch batch){
		if(isLocked){
			batch.draw(TextureLoader.levelLocked(), boundary.getX(), boundary.getY(), boundary.getWidth(), boundary.getHeight());
		}else{
			batch.end();
			digit.draw(batch, null);
			batch.begin();
			switch(nStars){
				case 0:
					batch.draw(TextureLoader.levelZeroStar(), boundary.getX(), boundary.getY(), boundary.getWidth(), boundary.getHeight());
					break;
				case 1:
					batch.draw(TextureLoader.levelOneStar(), boundary.getX(), boundary.getY(), boundary.getWidth(), boundary.getHeight());
					break;
				case 2:
					batch.draw(TextureLoader.levelTwoStar(), boundary.getX(), boundary.getY(), boundary.getWidth(), boundary.getHeight());
					break;
				case 3:
					batch.draw(TextureLoader.levelThreeStar(), boundary.getX(), boundary.getY(), boundary.getWidth(), boundary.getHeight());
					break;
			}
		}
	}
	
	public void enableTouch(){
		this.handler = new TouchHandler(this, boundary);
	}
	
	public void disableTouch(){ this.handler.destroy(); }

	@Override
	public void onTouched()
	{
		// TODO: Implement this method
	}

	@Override
	public void onTouchDown(){
		boundary.setX(boundary.getX() + dent);
		boundary.setY(boundary.getY() + dent);
		boundary.setWidth(boundary.getWidth() - dent * 2);
		boundary.setHeght(boundary.getHeight() - dent * 2);
	}

	@Override
	public void onTouchDraged(int deltaX, int deltaY){
	}

	@Override
	public void continusDrag(float x, float y)
	{
		// TODO: Implement this method
	}

	@Override
	public void onTouchRelease(){
		boundary.setX(boundary.getX() - dent);
		boundary.setY(boundary.getY() - dent);
		boundary.setWidth(boundary.getWidth() + dent * 2);
		boundary.setHeght(boundary.getHeight() + dent * 2);
		board.onIconClick( this);
	}

	@Override
	public void dispose(){
		this.handler.destroy();
		this.digit.dispose();
	}
}
