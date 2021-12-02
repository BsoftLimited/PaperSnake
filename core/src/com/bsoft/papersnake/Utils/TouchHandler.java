package com.bsoft.papersnake.Utils;

import com.badlogic.gdx.*;
import com.bsoft.papersnake.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.*;

public class TouchHandler implements InputProcessor{
	public static interface OnTouchListener{
		void onTouchDraged(int deltaX, int deltaY);
		void continusDrag(float x, float y);
		void onTouched();
		void onTouchRelease();
		void onTouchDown();
	}
	
	private static InputMultiplexer inputHandler;
	private float xScale, yScale;
	OnTouchListener listener;
	boolean isDragged, inBound;
	Boundary boundary;
	int initX, initY;
	public TouchHandler(OnTouchListener listener, Boundary boundary){
		this.listener = listener;
		this.boundary = boundary;
		xScale = ((float)Gdx.graphics.getWidth())/PaperSnake.GameWidth;
		yScale = ((float)Gdx.graphics.getHeight())/PaperSnake.GameHeight;
		if(inputHandler == null){
			inputHandler = new InputMultiplexer();
			Gdx.input.setInputProcessor(inputHandler);
		}
		this.reset();
		inputHandler.addProcessor(this);
	}
	
	public void setBoundary(Boundary boundary){ this.boundary = boundary;}
	public void destroy(){
		if(inputHandler != null){ inputHandler.removeProcessor(this);}
	}
	private boolean reset(){initX = 0; initY = 0; inBound = false; isDragged = false;  return true; }
	public static void dispose(){ inputHandler = null; }

	@Override
	public boolean touchDown(int screenX, int screenY, int p3, int p4){
		if(boundary.inside(screenX / xScale, PaperSnake.GameHeight - (screenY / yScale))){
			initX = screenX;
			initY = screenY;
			inBound = true;
			listener.onTouchDown();
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer){
		if(inBound){
			listener.continusDrag((Gdx.input.getDeltaX()) / xScale,  - (Gdx.input.getDeltaY()) /yScale);
			isDragged = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pionter, int button){
		if(inBound){
			listener.onTouchRelease();
			if(isDragged){
				listener.onTouchDraged(screenX - initX, screenY - initY);
			}
			listener.onTouched();
			return reset();
		}
		return false;
	}

	@Override
	public boolean keyTyped(char p1){
		return false;
	}

	@Override
	public boolean keyUp(int p1){
		return false;
	}

	@Override
	public boolean keyDown(int p1)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean mouseMoved(int p1, int p2)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) { return false; }
	
	public static void draw(ShapeRenderer renderer){
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.setColor(Color.BLUE);
		for(InputProcessor processor : inputHandler.getProcessors()){
			Boundary init = ((TouchHandler)processor).boundary;
			renderer.rect(init.getX(), init.getY(), init.getWidth(), init.getHeight());
		}
		renderer.end();
	}
}
