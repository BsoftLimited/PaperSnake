package com.bsoft.papersnake.Objects;

import com.bsoft.papersnake.Objects.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Utils.*;

public class Stage extends StatusObject{
	private String level;
	public Stage(String level){
		this.level = level;
	}

	@Override
	protected void print(SpriteBatch batch){
		float x = this.x + 10;
		float y = this.y + 10;
		batch.draw(TextureLoader.stage(), x + 10 , y + 24, 100, 30);
		this.printCell(batch, Integer.parseInt(level.substring(0, 1)), x + 24, y );
		batch.draw(TextureLoader.dash(), x + 48, y , 14, 24);
		this.printCell(batch, Integer.parseInt(level.substring(2, 3)), x + 72, y);
	}

	@Override
	public int getWidth(){ return 140; }

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
	public void dispose()
	{
		// TODO: Implement this method
	}
}
