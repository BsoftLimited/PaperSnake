package com.bsoft.papersnake.Objects.UIs.Buttons;

import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Utils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bsoft.papersnake.Objects.UIs.*;

public abstract class Button extends UI implements TouchHandler.OnTouchListener{
	public interface OnClickListener{
		void onClick(Button button);
	}
	
	private TouchHandler handler;
	private OnClickListener listener;
	private float dent;
	protected UI content;
	
	public Button(OnClickListener listener, UI content, float x, float y, float width, float height){
		super(x, y, width, height);
		this.listener = listener;
		this.content = content;
		handler = new TouchHandler(this, this.boundary);
		dent = width / 10.0f;;
	}
	
	public Button(OnClickListener listener, UI content, float x, float y){
		super(x, y);
		this.listener = listener;
		this.content = content;
		this.setSize(content.getActualBoundary().getWidth() + padding.getLeft() + padding.getRight(), content.getActualBoundary().getHeight() + padding.getDown() + padding.getUp());
		handler = new TouchHandler(this, this.boundary);
		dent = boundary.getWidth() / 10.0f;
	}
	
	public Button(OnClickListener listener, UI content){ this(listener, content, 0, 0);}
	public void setOnClickListener(OnClickListener listener){ this.listener = listener;}

	@Override
	public void onTouched(){ listener.onClick(this);}

	@Override
	public void setSize(float width, float height){
		super.setSize(width, height);
		dent = width / 10.0f;
	}

	@Override
	protected void render(SpriteBatch batch, ShapeRenderer renderer, float x, float y, float width, float height){
		content.setPosition(x, y);
		content.setSize(width, height);
		content.draw(batch, renderer);
	}

	@Override
	public void onTouchDown(){
		boundary.setX(boundary.getX() + dent);
		boundary.setY(boundary.getY() + dent);
		boundary.setWidth(boundary.getWidth() - dent * 2);
		boundary.setHeght(boundary.getHeight() - dent * 2);
	}

	@Override
	public void onTouchRelease(){
		boundary.setX(boundary.getX() - dent);
		boundary.setY(boundary.getY() - dent);
		boundary.setWidth(boundary.getWidth() + dent * 2);
		boundary.setHeght(boundary.getHeight() + dent * 2);
	}
	
	protected Float getDent(){ return dent; }

	@Override
	public void onTouchDraged(int deltaX, int deltaY){}

	@Override
	public void continusDrag(float x, float y)
	{
		// TODO: Implement this method
	}

	@Override
	public void enableTouch(){
		handler = new TouchHandler(this, this.boundary);
	}

	@Override
	public void disableTouch(){
		handler.destroy();
		handler = null;
	}

	@Override
	public String toString(){
		return content.toString();
	}
	

	@Override
	public void dispose(){ handler.destroy(); }
}
