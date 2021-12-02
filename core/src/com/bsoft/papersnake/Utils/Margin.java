package com.bsoft.papersnake.Utils;

public class Margin{
	private float up, down, left, right;
	
	public Margin(float up, float down, float left, float right){
		this.down = down;
		this.up = up;
		this.left = left;
		this.right = right;
	}
	
	public Margin(float margin){
		this(margin, margin, margin, margin);
	}
	
	public Margin(){
		this(5);
	}
	
	public float getUp(){ return up; }
	public float getDown(){ return down; }
	public float getRight(){ return right; }
	public float getLeft(){ return left; }
	
	public void setUp(float up){ this.up = up; }
	public void setDown(float down){ this.down = down; }
	public void setRight(float right){ this.right = right; }
	public void setLeft(float left){ this.left = left; }
	public void set(float up, float down, float left, float right){
		this.down = down;
		this.up = up;
		this.left = left;
		this.right = right;
	}
}
