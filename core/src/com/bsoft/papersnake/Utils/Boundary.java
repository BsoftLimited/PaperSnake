package com.bsoft.papersnake.Utils;

public class Boundary extends Size{
	float x, y;
	
	public Boundary(){
		this.x = 0;
		this.y = 0;
	}
	
	public Boundary(float x, float y, float width, float height){
		super(width, height);
		this.x = x;
		this.y = y;
	}
	
	public float getX(){ return x; }
	public float getY(){ return y; }
	public void setX( float x){ this.x = x; }
	public void setY( float y){ this.y = y; }
	public boolean inside(float x, float y){
		if(Boundary.inRange(x, this.x, this.x + this.getWidth())){
			if(Boundary.inRange(y, this.y, this.y + this.getHeight())){
				return true;
			}
		}
		return false;
	}
	
	public void setSize(float width, float height){
		this.setWidth(width);
		this.setHeght(height);
	}
	
	private static boolean inRange(float value, float min, float max){
		return value >= min && value <= max;
	}
}
