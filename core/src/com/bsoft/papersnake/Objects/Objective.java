package com.bsoft.papersnake.Objects;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bsoft.papersnake.*;
import com.bsoft.papersnake.Utils.*;
import java.util.*;
import com.bsoft.papersnake.Objects.UIs.*;

public class Objective extends GameObject2D{
	Boundary boundary;
	private Font message;
	String text;
	
	public Objective(String text){
		boundary = new Boundary();
		this.text = text;
		this.setHeight(80);
	}
	
	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer){
		batch.begin();
		batch.draw(TextureLoader.objective(), getX(), getY(), getWidth(), getHeight());
		batch.end();
		message.draw(batch, renderer);
	}
	
	public void setPosition(float x, float y){
		boundary.setX(x); boundary.setY(y);
		message.setPosition(x + (getHeight() * 1.2f), y);
	}

	public void setHeight(float height){
		boundary.setSize(height * 5, height);
		if(message == null){
			message = new Font(text, boundary.getX() + (getHeight() * 1.2f) , boundary.getY() , height * 3.8f );
		}else{
			message.setPosition(boundary.getX() + (getHeight() * 1.2f), boundary.getY());
		}
	}

	public float getWidth(){ return boundary.getWidth(); }
	public float getHeight(){ return boundary.getHeight(); }
	public float getX(){ return boundary.getX(); }
	public float getY(){ return boundary.getY(); }

	@Override
	public void enableTouch(){
	}

	@Override
	public void disableTouch(){
	}

	@Override
	public void dispose(){
	}
}
