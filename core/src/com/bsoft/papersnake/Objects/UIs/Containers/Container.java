package com.bsoft.papersnake.Objects.UIs.Containers;
import java.util.*;
import com.bsoft.papersnake.Objects.UIs.*;

public abstract class Container extends UI{
	protected List<UI> children;
	
	public Container(float width, float height){
		super(0, 0, width, height);
		children = new ArrayList<>();
	}
	
	public Container(){
		super(0, 0);
		children = new ArrayList<>();
	}
	
	public abstract void addChildren(UI... children);
	public abstract void removeChildren(UI... children);

	@Override
	public void enableTouch(){
		for(UI child : children){
			child.enableTouch();
		}
	}

	@Override
	public void disableTouch(){
		for(UI child : children){
			child.disableTouch();
		}
	}
	
	@Override
	public void dispose(){
		for(UI child : children){
			child.dispose();
		}
	}
}
