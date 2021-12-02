package com.bsoft.papersnake.Objects;
import java.util.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.*;
import com.bsoft.papersnake.Sprites.*;
import com.bsoft.papersnake.Utils.*;

public class Snake extends GameObject2D implements TouchHandler.OnTouchListener, Time.SecondsListener {
	public static enum Direction {Up, Down, Left, Right}
	public interface SnakeDeadListener{
		void onSnakeDied();
	}
	
	Head head;
	private Direction direction, newDirection;
	private boolean isAlive, started;
	private Board board;
	private SnakeDeadListener listener;
	private int timeStamp, count;
	private TouchHandler touchHandler;
	
	public Snake(SnakeDeadListener listener, Board board){
		this.listener = listener;
		direction = Direction.Right;
		newDirection = Direction.Right;
		this.board = board;
		head = new  Head(this, new Position(1,  3));
		isAlive = true;
		timeStamp = 1;
		count = 0;
		this.enableTouch();
	}
	
	public Direction getDirection(){ return direction; }
	
	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer){
		batch.begin();
		head.draw(batch);
		batch.end();
	}
	
	public void disableTouch(){
		touchHandler.destroy();
		touchHandler = null;
	}
	
	public void enableTouch(){
		touchHandler = new TouchHandler(this, board.getBoundary());
	}
	
	public void setTimeStamp(int timeStamp){	this.timeStamp = timeStamp;}
	
	public boolean isAlive(){ return isAlive; }
	
	public void setCell(int row, int column){
		board.setValue(row, column, (byte)2);
	}
	
	public void clearCell(int row, int column){
		board.setValue(row, column, (byte)0);
	}
	
	public void check(int row, int column){
		switch(board.getValue(row, column)){
			case 1:
			case 2:
				isAlive = false;
				listener.onSnakeDied();
				break;
			case 3:
				head.increase();
				break;
			case 4:
				head.decrease();
				break;
		}
	}
	
	public void setDead(){ isAlive = false; }

	@Override
	public void onTick(){
		if (isAlive && count == timeStamp){
			direction = newDirection;
			head.move();
			count = 0;
		}else{
			count += 1;
		}
	}

	@Override
	public void onTouchDraged(int deltaX, int deltaY){
		if(started){
			int x = deltaX < 0 ? deltaX * -1 : deltaX;
			int y = deltaY < 0 ? deltaY * -1 : deltaY;
			if(x > y && (direction == Direction.Up || direction == Direction.Down)){
				newDirection = deltaX > 0 ?  Direction.Right : Direction.Left;
			}if(x  < y && (direction == Direction.Right || direction == Direction.Left)){
				newDirection = deltaY > 0 ? Direction.Down : Direction.Up;
			}
		}
	}

	@Override
	public void continusDrag(float x, float y)
	{
		// TODO: Implement this method
	}
	
	public void start(){
		started = true;
	}
	
	public void pause(){
		started = false;
	}

	@Override
	public void onTouched(){}

	@Override
	public void onTouchDown(){}

	@Override
	public void onTouchRelease(){}

	@Override
	public void dispose(){
		touchHandler.destroy();
	}
}
