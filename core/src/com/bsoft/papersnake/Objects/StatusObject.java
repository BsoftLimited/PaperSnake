package com.bsoft.papersnake.Objects;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Utils.*;
import com.badlogic.gdx.graphics.glutils.*;

public abstract class StatusObject extends GameObject2D
{

	protected float x, y;
	private boolean showBoundary;
	public StatusObject(){
		this.x = 0;
		this.y = 0;
		showBoundary = true;
	}
	
	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer){
		batch.begin();
		if(showBoundary){
			batch.draw(TextureLoader.statusBack(), x, y, getWidth(), getHeight());
		}
		this.print(batch);
		batch.end();
	}
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}

	public void showBoundary(boolean show){
		this.showBoundary = show;
	}
	public abstract int getWidth();
	public abstract int getHeight();
	protected abstract void print(SpriteBatch batch);
	protected void printCell(SpriteBatch batch, int n, float x, float y){
		batch.draw(TextureLoader.getNumber(n, false), x, y, 24, 24); 
	}
	
}
