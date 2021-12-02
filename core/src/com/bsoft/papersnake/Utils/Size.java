package com.bsoft.papersnake.Utils;

public class Size{
	private float width, height;
	
	public Size(float width, float height){
		this.width = width;
		this.height = height;
	}
	
	public Size(){
		this.width = 0;
		this.height = 0;
	}
	
	public float getWidth(){ return width;}
	public float getHeight(){ return height; }
	public void setWidth(float width){ this.width = width; }
	public void setHeght(float height){ this.height = height; }
}
