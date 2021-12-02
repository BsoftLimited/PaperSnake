package com.bsoft.papersnake.Sprites;

import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Utils.*;

public class Tail extends SnakePath{
	private Body body;
	private boolean skipOne;
	public Tail(Snake snake, Position position, Body body){
		super(snake, position, null);
		this.body = body;
		skipOne = false;
	}
	@Override
	public void draw(SpriteBatch batch){
		float x = position.getColumn() * Board.CellSize + Board.getX();
		float y = Board.getBoundary().getHeight() - Board.CellSize - (position.getRow() * Board.CellSize + Board.getY());
		if(body.getPosition().getRow() == position.getRow()){
			if(body.getPosition().getColumn() < position.getColumn()){
				batch.draw(TextureLoader.snakeTailLeft(), x, y, Board.CellSize, Board.CellSize);
			}else{
				batch.draw(TextureLoader.snakeTailRight(), x, y, Board.CellSize, Board.CellSize);
			}
		}else{
			if(body.getPosition().getRow() < position.getRow()){
				batch.draw(TextureLoader.snakeTailUp(), x, y, Board.CellSize, Board.CellSize);
			}else{
				batch.draw(TextureLoader.snakeTailDown(), x, y, Board.CellSize, Board.CellSize);
			}
		}
	}
	
	public SnakePath getBody(){ return body; }
	public void setBody(Body body, boolean skip){
		this.body = body;
		this.skipOne = skip;
	}

	@Override
	public void move(){
		if(skipOne){
			skipOne = false;
		}else{
			snake.clearCell(position.getRow(), position.getColumn());
			if(body.position.getRow() == position.getRow()){
				if(body.position.getColumn() < position.getColumn()){
					position.moveLeft();
				}else if(body.position.getColumn() > position.getColumn()){
					position.moveRight();
				}
			}else if(body.position.getColumn() == position.getColumn()){
				if(body.position.getRow()  < position.getRow()){
					position.moveUp();
				}else if(body.position.getRow()  > position.getRow()){
					position.moveDown();
				}
			}
		}
	}
}
