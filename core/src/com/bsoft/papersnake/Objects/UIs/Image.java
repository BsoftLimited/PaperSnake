package com.bsoft.papersnake.Objects.UIs;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bsoft.papersnake.Utils.*;

public class Image extends UI{
	private TextureRegion region;
	private int regionX, regionY, regionWidth, regionHeight;
	public Image(TextureRegion region){
		super(0, 0, 100, 100);
		this.region = region;
		this.regionX = region.getRegionX();
		this.regionY = region.getRegionY();
		this.regionWidth = region.getRegionWidth();
		this.regionHeight = region.getRegionHeight();
	}

	@Override
	protected void render(SpriteBatch batch, ShapeRenderer renderer, float x, float y, float width, float height){
		batch.begin();
		region.setRegion(regionX, regionY, regionWidth, regionHeight);
		batch.draw(region, x, y, width, height);
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
	}
}
