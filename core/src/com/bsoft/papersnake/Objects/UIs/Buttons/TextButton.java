package com.bsoft.papersnake.Objects.UIs.Buttons;
import com.bsoft.papersnake.Objects.UIs.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Utils.*;

public class TextButton extends Button
{
	private boolean hasShink;
	private float fontSize;
	public TextButton(OnClickListener listener, String text, float x, float y, float width, float height){
		super(listener, new Font(text), x, y, width, height);
		hasShink = false;
		fontSize = ((Font)content).getSize();
	}
	
	public TextButton(OnClickListener listener, Font text, float x, float y){
		super(listener, text, x, y);
		hasShink = false;
		fontSize = text.getSize();
	}
	
	public TextButton(OnClickListener listener, Font text){
		super(listener, text);
		fontSize = text.getSize();
		hasShink = false;
	}
	
	public TextButton(OnClickListener listener, String text){
		super(listener, new Font(text));
		fontSize = ((Font)content).getSize();
		hasShink = false;
	}

	@Override
	public void onTouchDown(){
		if(!hasShink){
			((Font)content).setSize(fontSize - fontSize/10);
			hasShink = true;
		}
	}

	@Override
	public void onTouchRelease(){
		if(hasShink){
			((Font)content).setSize(fontSize);
			hasShink = false;
		}
	}
	
	@Override
	protected void render(SpriteBatch batch, ShapeRenderer renderer, float x, float y, float width, float height){
		Boundary init = content.getActualBoundary();
		content.setPosition(x + width/2 - init.getWidth()/2, y + height/2 - init.getHeight()/2);
		content.draw(batch, renderer);
	}
	
}
