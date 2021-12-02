package com.bsoft.papersnake.Sprites;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;

import com.badlogic.gdx.utils.*;
import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.Objects.*;

public abstract class SnakePath {
	protected Position position;
	protected Snake snake;
	protected SnakePath child;
	
	public SnakePath(Snake snake, Position position, SnakePath child){
		this.position = position;
		this.snake = snake;
		this.child = child;
		snake.setCell(position.getRow(), position.getColumn());
	}
	
	public void increase(){
		if(child instanceof Tail){
			Tail tail = ((Tail)child);
			Body init = new Body(snake, this, tail, new Position(position.getRow(), position.getColumn()));
			tail.setBody(init, true);
			this.child = init;
		}else{
			child.increase();
		}
	}
	
	public void decrease(){
		if(this instanceof Body && child instanceof Tail){
			SnakePath parent = ((Body)this).getParent();
			if(parent instanceof Body){
				Tail tail = ((Tail)child);
				tail.move();
				tail.setBody((Body)parent, false);
				parent.child = tail;
			}else{
				snake.setDead();
			}
		}else{
			child.decrease();
		}
	}
	
	public abstract void draw(SpriteBatch batch);
	public Position getPosition(){return position;}
	public abstract void  move();
}
