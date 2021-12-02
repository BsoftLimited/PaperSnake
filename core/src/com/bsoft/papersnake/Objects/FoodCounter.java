package com.bsoft.papersnake.Objects;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Utils.*;

public class FoodCounter extends StatusObject{
	int apples, eggs;
	
	public FoodCounter(){
		apples = 0;
		eggs = 0;
		showBoundary(false);
	}
	
	public int getEggCount(){ return eggs; }
	public int getAppleCount(){ return apples; }
	public void increaseApple(){ apples++;}
	public void increaseEggs(){ eggs++; }
	
	@Override
	public int getWidth(){ return 181; }

	@Override
	public int getHeight(){ return 30; }

	@Override
	protected void print(SpriteBatch batch){
		batch.draw(TextureLoader.apple(), x, y, 32, 32);
		batch.draw(TextureLoader.column(), x + 26, y + 4, 14, 24);
		if(apples >= 10){
			printCell(batch, apples / 10, x + 36, y + 4);
			printCell(batch, apples % 10, x + 59, y + 4);
		}else{
			printCell(batch, apples , x + 36, y + 4);
		}
		batch.draw(TextureLoader.egg(), x + 95, y, 32, 32);
		batch.draw(TextureLoader.column(), x + 121, y + 4, 14, 24);
		if(eggs >= 10){
			printCell(batch, eggs / 10, x + 131, y + 4);
			printCell(batch, eggs % 10, x + 154, y + 4);
		}else{
			printCell(batch, eggs, x + 131, y + 4);
		}
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
	public void dispose(){
		// TODO: Implement this method
	}
}
