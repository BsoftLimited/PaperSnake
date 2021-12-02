package com.bsoft.papersnake.Screens;

import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.Objects.*;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.graphics.*;
import com.bsoft.papersnake.*;

public abstract class Screen implements Disposable{
	protected GameObjectManager manager;
	
	protected PaperSnake game;
	
	public Screen(PaperSnake game){
		this.game = game;
		manager = new GameObjectManager();
		manager.addObject(new Background());
		
	}
	
	public void render(SpriteBatch batch, ShapeRenderer renderer){
		manager.draw(batch, renderer);
	}
	
	public abstract void update();
	
	public void enable(){
		manager.enableTouch();
	}
	
	public void disable(){
		manager.disableTouch();
	}
	
	public abstract boolean back();

	@Override
	public void dispose(){
		manager.dispose();
	}
}
