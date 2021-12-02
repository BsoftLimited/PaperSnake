package com.bsoft.papersnake.Objects;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.*;

public class Background extends GameObject2D{

	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer){
		batch.begin();
		batch.draw(TextureLoader.paper(), 0, 0, PaperSnake.GameWidth, PaperSnake.GameHeight);
		batch.end();
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
