package com.bsoft.papersnake;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.Screens.Levels.*;
import com.bsoft.papersnake.Screens.*;

public class PaperSnake implements ApplicationListener{
	public static final int GameWidth = 480;
	public static final int GameHeight = 800;

	private SpriteBatch batch;
	private ShapeRenderer renderer;
	private TextureLoader textureLoader;
	private ScreenManager screenManager;
	private Config config;
	private StretchViewport viewport;

	@Override
	public void create(){
		config = new Config();
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		OrthographicCamera camera = new OrthographicCamera(PaperSnake.GameWidth, PaperSnake.GameHeight);
		viewport = new StretchViewport(PaperSnake.GameWidth, PaperSnake.GameHeight, camera);
		camera.translate(PaperSnake.GameWidth /2, PaperSnake.GameHeight / 2);
		textureLoader = new TextureLoader();
		screenManager = new ScreenManager( new MenuScreen(this));
	}

	@Override
	public void render(){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(viewport.getCamera().combined);
		renderer.setProjectionMatrix( viewport.getCamera().combined);
		try{
			screenManager.get().render(batch, renderer);
			//TouchHandler.draw(renderer);
			screenManager.get().update();
		}catch(NullPointerException ex){
			ex.printStackTrace();
		}
	}

	public void setScreen( com.bsoft.papersnake.Screens.Screen screen){
		this.screenManager.pushScreen(screen);
	}

	public void restart(){	}

	@Override
	public void dispose()	{
		TouchHandler.dispose();
		screenManager.dispose();
		renderer.dispose();
		batch.dispose();
		textureLoader.dispose();
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
	}

	public void toMenu(){ screenManager.toSelectScreen(); }


	@Override
	public void pause(){}

	@Override
	public void resume(){}

	public boolean back(){
		try{
			return screenManager.back();
		}catch(NullPointerException exp){
			exp.printStackTrace();
		}
		return false;
	}
}
