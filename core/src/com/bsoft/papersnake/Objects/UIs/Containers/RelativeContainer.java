package com.bsoft.papersnake.Objects.UIs.Containers;

import java.util.*;
import com.bsoft.papersnake.Objects.UIs.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Utils.*;
import com.badlogic.gdx.graphics.*;

public class RelativeContainer extends Container{
	public static enum Dock{ Left, TopLeft, Top, TopRight, Right, BottomRight, Bottom, BottomLeft, Center}
	
	private List<Dock> docks;
	
	public RelativeContainer(float width, float height){
		super( width, height);
		docks = new ArrayList<>();
	}

	@Override
	public void addChildren(UI... children){
		for(UI child : children){
			this.children.add(child);
			child.setParent(this);
			this.docks.add(Dock.Center);
			this.arrange(child);
		}
	}
	
	public void addChildren(UI child, Dock dock){
		this.children.add(child);
		child.setParent(this);
		this.docks.add(dock);
		this.arrange(child);
	}
	
	public static boolean setDock(RelativeContainer container, UI child, Dock dock){
		if(container.children.contains(child)){
			int index = container.children.indexOf(child);
			container.docks.remove(index);
			container.docks.set(index, dock);
			container.arrange(child);
			return true;
		}
		return false;
	}

	@Override
	public void removeChildren(UI... children){
		for(UI child : children){
			int index = this.children.indexOf(child);
			this.docks.remove(index);
			this.children.remove(index);
		}
	}
	
	private void arrange(UI child){
		float initX = this.getX() + padding.getLeft();
		float initY = this.getY() + padding.getDown();
		float initWidth = this.getWidth() - padding.getRight() - padding.getLeft();
		float initHeight = this.getHeight() - padding.getUp() - padding.getDown();
		Boundary initBound = child.getActualBoundary();
		int index = this.children.indexOf(child);
		float x, y;
		switch(docks.get(index)){
				case Left:
					x = initX;
					y = initHeight/2 - initBound.getHeight()/2 + initY;
					break;
				case TopLeft:
					x = initX;
					y = initHeight + initY - initBound.getHeight();
					break;
				case Top:
					x = initWidth/2 - initBound.getWidth()/2 + initX;
					y = initHeight + initY - initBound.getHeight();
					break;
				case TopRight:
					x = initWidth + initX - initBound.getWidth();
					y = initHeight + initY - initBound.getHeight();
					break;
				case Right:
					x = initWidth + initX - initBound.getWidth();
					y = initHeight/2 - initBound.getHeight()/2 + initY;
					break;
				case BottomRight:
					x = initWidth + initX - initBound.getWidth();
					y = initY;
					break;
				case Bottom:
					x = initWidth/2 - initBound.getWidth()/2 + initX;
					y = initY;
					break;
				case BottomLeft:
					x = initX;
					y = initY;
					break;
				default:
				    x = initWidth/2 - initBound.getWidth()/2 + initX;
				    y = initHeight/2 - initBound.getHeight()/2 + initY;
		}
		child.setPosition(x, y);
	}

	@Override
	public void setPadding(float up, float down, float right, float left){
		super.setPadding(up, down, right, left);
		for(UI child : children){
			this.arrange(child);
		}
	}

	@Override
	public void setPadding(Padding padding){
		super.setPadding(padding);
		for(UI child : children){
			this.arrange(child);
		}
	}

	@Override
	public void setMargin(Margin margin){
		super.setMargin(margin);
		for(UI child : children){
			this.arrange(child);
		}
	}

	@Override
	public void setMargin(float up, float down, float right, float left){
		super.setMargin(up, down, right, left);
		for(UI child : children){
			this.arrange(child);
		}
	}

	@Override
	public void setPosition(float x, float y)	{
		super.setPosition(x, y);
		for(UI child : children){
			this.arrange(child);
		}
	}

	@Override
	protected void render(SpriteBatch batch, ShapeRenderer renderer, float x, float y, float width, float height){
		for(UI child : children){
			child.draw(batch, renderer);
		}
	}
}
