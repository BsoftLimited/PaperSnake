package com.bsoft.papersnake.Sprites;

import com.bsoft.papersnake.Utils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Objects.Snake.Direction;
import com.bsoft.papersnake.Objects.*;

public class Head extends SnakePath{
	
	public Head(Snake snake, Position position){
		super(snake, position, null);
		child = new Body(snake, this, null, new Position(position.getRow(), position.getColumn() - 1));
		child.child = new Tail(snake, new Position(position.getRow(), position.getColumn() - 2), (Body)child);
	}

	@Override
	public void draw(SpriteBatch batch){
		float x = position.getColumn() * Board.CellSize + Board.getX();
		float y = Board.getBoundary().getHeight() - Board.CellSize - (position.getRow() * Board.CellSize + Board.getY());
		switch(snake.getDirection()){
			case Up:
				batch.draw(TextureLoader.snakeHeadUp(), x, y, Board.CellSize, Board.CellSize);
				break;
			case Down:
				batch.draw(TextureLoader.snakeHeadDown(), x, y, Board.CellSize, Board.CellSize);
				break;
			case Left:
				batch.draw(TextureLoader.snakeHeadLeft(), x, y, Board.CellSize, Board.CellSize);
				break;
			case Right:
				batch.draw(TextureLoader.snakeHeadRight(), x, y, Board.CellSize, Board.CellSize);
				break;
		}
		child.draw(batch);
	}

	@Override
	public void move(){
		check();
		if(snake.isAlive()){
			child.move();
			switch(snake.getDirection()){
				case Up:
					position.moveUp();
					break;
				case Down:
					position.moveDown();
					break;
				case Left:
					position.moveLeft();
					break;
				case Right:
					position.moveRight();
					break;
			}
			snake.setCell(position.getRow(), position.getColumn());
		}
	}
	
	private void check(){
		switch(snake.getDirection()){
			case Up:
				snake.check( position.getRow() - 1, position.getColumn());
				break;
			case Down:
				snake.check( position.getRow() + 1, position.getColumn());
				break;
			case Left:
				snake.check( position.getRow() , position.getColumn() - 1);
				break;
			case Right:
				snake.check( position.getRow() , position.getColumn() + 1);
				break;
		}
	}
}
