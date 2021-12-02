package com.bsoft.papersnake.Objects;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Utils.*;

public class ScoreCounter extends StatusObject{
	private int value;
	
	public ScoreCounter(){
		value = 0;
		this.x = 0;
		this.y = 0;
	}
	
	public void setValue(int value){ this.value = value; }
	public int getValue(){ return value; }
	public void add(int value){ this.value += value; }
	
	@Override
	protected void print(SpriteBatch batch){
		float y = this.y + 10;
		float x = this.x + 10;
		batch.draw(TextureLoader.score(), x + 10 , y + 24, 100, 36);
		if(value > 999){
			this.printCell(batch, value / 1000, x + 12, y );
			this.printCell(batch, (value % 1000) / 100, x + 36, y);
			this.printCell(batch, (value % 100) / 10, x + 60, y );
			this.printCell(batch, value % 10, x + 84, y );
		}else if(value > 99){
			this.printCell(batch, value / 100, x + 24, y );
			this.printCell(batch, (value % 100) / 10, x + 48, y);
			this.printCell(batch, value % 10, x + 72, y );
		}else{
			this.printCell(batch, value / 10, x + 36, y );
			this.printCell(batch, value % 10, x + 60 , y);
		}
	}

	@Override
	public int getWidth(){ return 140;}

	@Override
	public int getHeight()
	{ return 80; }

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
	public void dispose(){
		// TODO: Implement this method
	}
}
