package com.bsoft.papersnake.Objects.UIs;
import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.Objects.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;

public abstract class UI extends GameObject2D{
	protected Boundary boundary;
	protected Margin margin;
	protected Padding padding;
	private UI parent;
	
	public UI(float x, float y, float width, float height){
		boundary = new Boundary(x, y, width, height);
		margin = new Margin();
		padding = new Padding();
	}
	
	public void setParent(UI parent){this.parent = parent; }
	protected boolean hasParent(){ return parent != null; }
	protected Boundary getParentBoundary(){ return parent.boundary; }
	
	public UI(float width, float height){
		this(0, 0, width, height);
	}
	
	public void setPosition(float x, float y){
		boundary.setX(x);
		boundary.setY(y);
	}

	public void setSize(float width, float height){
		boundary.setWidth(width);
		boundary.setHeght(height);
	}

	public float getX(){ return boundary.getX(); }
	public float getY(){ return boundary.getY(); }
	public float getWidth(){ return boundary.getWidth(); }
	public float getHeight(){ return boundary.getHeight(); }
	public Boundary getActualBoundary(){
		return new Boundary(
		    boundary.getX() - margin.getLeft(),
			boundary.getY() - margin.getDown(),
			boundary.getWidth() + margin.getRight() + margin.getLeft(),
			boundary.getHeight() + margin.getUp() + margin.getDown());
	}
	
	public void setMargin(Margin margin){
		this.margin = margin;
	}
	
	public void setMargin(float margin){
		this.margin.set(margin, margin, margin, margin);
	}
	
	public void setMargin(float up, float down, float left, float right){
		this.margin.set(up, down, left, right);
	}
	
	public Margin getMargin(){ return margin; }
	public void setPadding(Padding padding){this.padding = padding; }

	public void setPadding(float padding){
		this.padding.set(padding, padding, padding, padding);
	}

	public void setPadding(float up, float down, float left, float right){
		this.padding.set(up, down, left, right);
	}

	public Padding getPadding(){ return padding; }

	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer){
		float x = boundary.getX() + padding.getLeft();
		float y = boundary.getY() + padding.getDown();
		float width = boundary.getWidth() - padding.getRight() - padding.getLeft();
		float height = boundary.getHeight() - padding.getUp() - padding.getDown();
		this.render(batch, renderer, x, y, width, height);
	}
	
	protected abstract void render(SpriteBatch batch, ShapeRenderer renderer, float x, float y, float width, float height);
}
