package com.bsoft.papersnake.Objects.UIs;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Objects.UIs.Containers.*;

public class ProgressBar extends UI{
	int value, min, max;
	String message;
	LinearContainer container;
	public ProgressBar(float x, float y, float width, float height){
		super(x, y, width, height);
		container = new LinearContainer(LinearContainer.Orientation.Vertical, true);
		container.setMargin(0);
		container.setPadding(0);
		
		
	}

	@Override
	protected void render(SpriteBatch batch, ShapeRenderer renderer, float x, float y, float width, float height){
		// TODO: Implement this method
	}

	@Override
	public void enableTouch(){
		container.enableTouch();
	}

	@Override
	public void disableTouch(){
		container.disableTouch();
	}

	@Override
	public void dispose(){
		container.dispose();
	}
}
