package com.bsoft.papersnake.Sprites;

import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Utils.*;

public class Body extends SnakePath{
	private SnakePath parent;
	public Body(Snake snake, SnakePath parent, SnakePath child, Position position){
		super(snake, position, child);
		this.parent = parent;
	}
	
	@Override
	public void draw(SpriteBatch batch){
			float x = position.getColumn() * Board.CellSize + Board.getX();
			float y = Board.getBoundary().getHeight() - Board.CellSize - (position.getRow() * Board.CellSize + Board.getY());
			if (parent.getPosition().getRow() == child.getPosition().getRow()){
				batch.draw(TextureLoader.snakeBodyHorizontal(), x, y, Board.CellSize, Board.CellSize);
			}else if(parent.getPosition().getColumn() == child.getPosition().getColumn()){
				batch.draw(TextureLoader.snakeBodyVertical(), x, y, Board.CellSize, Board.CellSize);
			}else if ( (parent.getPosition().getRow() > child.getPosition().getRow() && parent.getPosition().getColumn() > child.getPosition().getColumn())
					  || (parent.getPosition().getRow() < child.getPosition().getRow() && parent.getPosition().getColumn() < child.getPosition().getColumn())){
				if((parent.getPosition().getRow() > position.getRow()) || (child.getPosition().getRow() > position.getRow()) ){
					batch.draw(TextureLoader.snakeDownLeft(),  x, y, Board.CellSize, Board.CellSize);
				}else{
					batch.draw(TextureLoader.snakeUpRight(), x, y, Board.CellSize, Board.CellSize);
				}
			}else if ( (parent.getPosition().getRow() < child.getPosition().getRow() && parent.getPosition().getColumn() > child.getPosition().getColumn())
					  || (parent.getPosition().getRow() > child.getPosition().getRow() && parent.getPosition().getColumn() < child.getPosition().getColumn())){
				if((parent.getPosition().getRow() < position.getRow()) || child.getPosition().getRow() < position.getRow()){
					batch.draw(TextureLoader.snakeUpLeft(),  x, y, Board.CellSize, Board.CellSize);
				}else{
					batch.draw(TextureLoader.snakeDownRight(), x, y, Board.CellSize, Board.CellSize);
				}
			}
		
		child.draw(batch);
	}

	@Override
	public void move(){
		child.move();
		if(parent.position.getRow() == position.getRow()){
			if(parent.position.getColumn() < position.getColumn()){
				position.moveLeft();
			}else if(parent.position.getColumn() >  position.getColumn()){
				position.moveRight();
			}
		}else if(parent.position.getColumn() == position.getColumn()){
			if(parent.position.getRow() > position.getRow()){
				position.moveDown();
			}else if(parent.position.getRow() < position.getRow()){
				position.moveUp();
			}
		}
	}
	
	public SnakePath getParent(){ return parent; }
}
