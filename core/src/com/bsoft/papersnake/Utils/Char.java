package com.bsoft.papersnake.Utils;

import com.badlogic.gdx.graphics.g2d.*;

public class Char{
	
	private int regionX, regionY;
	private float regionWidth, regionHeight;
	private boolean isCapital;
	public Char(int regionX, int regionY, float regionWidth, float regionHeight, boolean isCapital){
		this.isCapital = isCapital;
		this.regionX = regionX;
		this.regionY = regionY;
		this.regionWidth = regionWidth;
		this.regionHeight = regionHeight;
	}
	
	public Char(int regionX, int regionY, boolean isCapital){
		this(regionX, regionY, 84, 128, isCapital);
	}
	
	public boolean isCapital(){ return isCapital; }
	public int getRegionX(){ return regionX; }
	public int getRegionY(){ return regionY; }

	public void draw(SpriteBatch batch,  float x, float y, float width, float height){
		if(regionX == 2 && ( regionY == 1 || regionY == 5)){
			batch.draw(TextureLoader.alpha(regionX, regionY, isCapital), x - (width * 0.4f), y, width , height);
		}else{
			batch.draw(TextureLoader.alpha(regionX, regionY, isCapital), x, y, width, height);
		}
	}
}
