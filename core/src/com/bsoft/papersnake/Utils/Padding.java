package com.bsoft.papersnake.Utils;

public class Padding{
	private float up, down, left, right;

	public Padding(float up, float down, float left, float right){
		this.down = down;
		this.up = up;
		this.left = left;
		this.right = right;
	}

	public Padding(float padding){
		this(padding, padding, padding, padding);
	}

	public Padding(){ this(2); }
	
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
