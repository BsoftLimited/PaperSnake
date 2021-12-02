package com.bsoft.papersnake.Objects;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Utils.*;

public class Stars extends GameObject2D{
	int n;
	Boundary boundary;
	float space;
	
	public Stars(int n){
		this.n = n;
		this.space = 20;
		boundary = new Boundary(0, 0, 80, 280);
	}
	
	public void setPosition(float x, float y){
		boundary.setX(x); boundary.setY(y);
	}
	
	public void setSpacing(float space){ this.space = space; }

	public void setHeight(float height){
		boundary.setSize((height * 3) + (space * 2), height);
	}

	public float getWidth(){ return boundary.getWidth(); }
	public float getHeight(){ return boundary.getHeight(); }
	public float getX(){ return boundary.getX(); }
	public float getY(){ return boundary.getY(); }

	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer){
		batch.begin();
		float x = getX(), y = getY();
		batch.draw(n > 0 ? TextureLoader.star() : TextureLoader.emty_star(), x, y, getHeight(), getHeight());
		x += getHeight() + space;
		batch.draw(n > 1 ? TextureLoader.star() : TextureLoader.emty_star(), x, y, getHeight(), getHeight());
		x += getHeight() + space;
		batch.draw(n > 2 ? TextureLoader.star() : TextureLoader.emty_star(), x, y, getHeight(), getHeight());
		batch.end();
	}

	@Override
	public void enableTouch(){
		// TODO: Implement this method
	}

	@Override
	public void disableTouch(){
		// TODO: Implement this method
	}

	@Override
	public void dispose(){
		// TODO: Implement this method
	}
}
