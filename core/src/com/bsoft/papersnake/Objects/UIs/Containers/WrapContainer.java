package com.bsoft.papersnake.Objects.UIs.Containers;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bsoft.papersnake.Objects.UIs.*;
import com.bsoft.papersnake.Utils.*;

public class WrapContainer extends Container{
	private float gap;
	
	public WrapContainer(float width, float gap){
		super(width, 0);
		this.gap = gap;
	}
	
	private void arrange(){
		float initX = this.getX() + padding.getLeft(), initY = this.getY() + padding.getDown();
		float holder = getHeight();
		for(UI child : children){
			child.setPosition(initX, initY);
			Boundary init = child.getActualBoundary();
			initX += init.getWidth() + gap;
			if(init.getHeight() > boundary.getHeight()){
				holder = init.getHeight();
			}
			if( initX - this.getX() > this.getWidth() - padding.getRight() - padding.getLeft()){
				initY += holder;
				holder = init.getHeight();
			}
			this.setSize(initX, initY + holder);
		}
	}

	@Override
	public void addChildren(UI... children){
		for(UI child : children){
			this.children.add(child);
			child.setParent(this);
		}
		this.arrange();
	}

	@Override
	public void removeChildren(UI... children){
		for(UI child : children){
			this.children.remove(child);
			child.setParent(null);
		}
		this.arrange();
	}

	@Override
	public void setMargin(Margin margin){
		super.setMargin(margin);
		this.arrange();
	}

	@Override
	public void setMargin(float up, float down, float right, float left){
		super.setMargin(up, down, right, left);
		this.arrange();
	}

	@Override
	public void setPadding(float up, float down, float right, float left){
		super.setPadding(up, down, right, left);
		this.arrange();
	}

	@Override
	public void setPadding(Padding padding){
		super.setPadding(padding);
		this.arrange();
	}

	@Override
	public void setPosition(float x, float y){
		super.setPosition(x, y);
		this.arrange();
	}

	@Override
	protected void render(SpriteBatch batch, ShapeRenderer renderer, float x, float y, float width, float height){
		for(UI child : children){
			child.draw(batch, renderer);
		}
	}

	@Override
	public void dispose(){
		for(UI child : children){
			child.dispose();
		}
	}
}
