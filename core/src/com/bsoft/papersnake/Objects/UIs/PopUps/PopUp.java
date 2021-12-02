package com.bsoft.papersnake.Objects.UIs.PopUps;

import com.bsoft.papersnake.Objects.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.*;
import com.bsoft.papersnake.Objects.UIs.*;
import com.badlogic.gdx.*;

public abstract class PopUp extends UI{
	
	public PopUp(float x, float y){
		super(x, y, 450, 450);
	}
	
	public PopUp(){
		this(PaperSnake.GameWidth / 2 - 225, PaperSnake.GameHeight/2 - 225);
	}
	

	@Override
	protected void render(SpriteBatch batch, ShapeRenderer renderer, float x, float y, float width, float height){
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(new Color(0, 0, 0, 0.6f));
		renderer.rect(0, 0, PaperSnake.GameWidth, PaperSnake.GameHeight);
		renderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
		batch.begin();
		batch.draw(TextureLoader.tornPaper(), x, y, width, height);
		batch.end();
	}
}
