package com.bsoft.papersnake.Objects;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.*;
import com.bsoft.papersnake.Utils.*;

public class Score extends GameObject2D{
	private float initValue, finalValue;
	private Boundary boundary;
	private boolean isStarted;
	
	public Score(float finalValue){
		this.finalValue = finalValue;
		initValue = 0;
		isStarted = false;
		boundary = new Boundary();
	}
	
	public void setPosition(float x, float y){
		boundary.setX(x); boundary.setY(y);
	}
	
	public void setHeight(float height){
		int factor = 1;
		if(initValue >= 1000){
			factor = 4;
		}else if(initValue >= 100){
			factor = 3;
		}else if(initValue >= 10){
			factor = 2;
		}
		boundary.setSize(height * factor, height);
	}
	
	public void start(){
		if(!isStarted){ isStarted = true; }
	}
	
	public float getWidth(){ return boundary.getWidth(); }
	public float getHeight(){ return boundary.getHeight(); }
	public float getX(){ return boundary.getX(); }
	public float getY(){ return boundary.getY(); }

	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer){
		if(isStarted){
			initValue += initValue == finalValue ? 0 : ((finalValue - initValue) * Gdx.graphics.getDeltaTime() * 2);
			if(initValue > finalValue){ initValue = finalValue; }

			int factor = 1;
			if(initValue >= 1000){
				factor = 4;
			}else if(initValue >= 100){
				factor = 3;
			}else if(initValue >= 10){
				factor = 2;
			}
			boundary.setWidth(getHeight() * factor);
		}
		
		batch.begin();
		int init =  ((int)initValue) + (initValue > 0 ? 1 : 0);
		if(initValue > 999){
			batch.draw(TextureLoader.getNumber(init / 1000,  true), boundary.getX(), boundary.getY(), boundary.getHeight(), boundary.getHeight());
			batch.draw(TextureLoader.getNumber((init % 1000) / 100,  true), boundary.getX() + boundary.getHeight() , boundary.getY(), boundary.getHeight() , boundary.getHeight());
			batch.draw(TextureLoader.getNumber((init % 100) / 10,  true), boundary.getX() + boundary.getHeight() * 2, boundary.getY(), boundary.getHeight() , boundary.getHeight());
			batch.draw(TextureLoader.getNumber(init % 10,  true), boundary.getX() + boundary.getHeight() * 3, boundary.getY(), boundary.getHeight() , boundary.getHeight());
		}else if(initValue > 99){
			batch.draw(TextureLoader.getNumber((init % 1000) / 100,  true), boundary.getX(), boundary.getY(), boundary.getHeight() , boundary.getHeight());
			batch.draw(TextureLoader.getNumber((init % 100) / 10,  true), boundary.getX() + boundary.getHeight(), boundary.getY(), boundary.getHeight() , boundary.getHeight());
			batch.draw(TextureLoader.getNumber(init % 10,  true), boundary.getX() + boundary.getHeight() * 2, boundary.getY(), boundary.getHeight() , boundary.getHeight());
		}else if(initValue > 9){
			batch.draw(TextureLoader.getNumber((init % 100) / 10,  true), boundary.getX(), boundary.getY(), boundary.getHeight() , boundary.getHeight());
			batch.draw(TextureLoader.getNumber(init % 10,  true), boundary.getX() + boundary.getHeight(), boundary.getY(), boundary.getHeight() , boundary.getHeight());
		}else{
			batch.draw(TextureLoader.getNumber(init  % 10,  true), boundary.getX(), boundary.getY(), boundary.getHeight() , boundary.getHeight());
		}
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
