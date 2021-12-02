package com.bsoft.papersnake.Objects.UIs.Containers;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bsoft.papersnake.Objects.UIs.*;
import com.bsoft.papersnake.Utils.*;
import java.util.*;

public class LinearContainer extends Container{
	public static enum Orientation{ Vertical, Horizontal }
	
	private Orientation orientation;
	private float gap;
	private boolean isCentered;
	
	public LinearContainer(Orientation orientation, float gap, boolean isCentered){
		this.orientation = orientation;
		this.gap = gap;
		this.isCentered = isCentered;
	}
	
	public LinearContainer(Orientation orientation, float gap){this(orientation, gap, false);}
	public LinearContainer(Orientation orientation, boolean isCentered){ this(orientation, 5, isCentered);}
	public LinearContainer(float gap){this(Orientation.Horizontal, gap); }
	public LinearContainer(Orientation orientation){ this(orientation, 5);}
	public LinearContainer(){ this(Orientation.Horizontal, 5);}
	
	public void setOreintation(Orientation orientation){this.orientation = orientation; }
	public Orientation getOrientation(){ return orientation; }
	public void setGap(float gap){ this.gap = gap; }
	public float getGap(){ return gap; }
	
	private void arrange(){
		float initX = this.getX() + padding.getLeft(), initY = this.getY() + padding.getDown();
		float holderWidth = 0, holderHeight = 0;
		for(UI child : children){
			Boundary init = child.getActualBoundary();
			if(orientation == Orientation.Horizontal){
				holderWidth += init.getWidth() + gap;
				if(holderHeight < init.getHeight() + gap){
					holderHeight = init.getHeight() + gap;
				}
			}else{
				holderHeight += init.getHeight() + gap;
				if(holderWidth < init.getWidth() + gap){
					holderWidth = init.getWidth() + gap;
				}
			}
		}
		this.setSize(holderWidth, holderHeight);
		for(UI child : children){
			Boundary init = child.getActualBoundary();
			if(orientation == Orientation.Horizontal){
				child.setPosition(initX,   initY + (isCentered ? (holderHeight/2 - init.getHeight()/2) : 0));
				initX += init.getWidth() + gap;
			}else{
				holderHeight -= init.getHeight() + gap;
				child.setPosition(initX + (isCentered ? (holderWidth/2 - init.getWidth()/2) : 0),  initY + holderHeight);
			}
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
}
