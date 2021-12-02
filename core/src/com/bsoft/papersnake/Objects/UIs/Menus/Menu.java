package com.bsoft.papersnake.Objects.UIs.Menus;

import com.bsoft.papersnake.Objects.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
import com.bsoft.papersnake.*;
import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.Objects.UIs.*;

public abstract class Menu extends GameObject2D{
	public static abstract class MenuItem extends GameObject2D{
		private int id;
		private String tag;
		
		public MenuItem(String tag){
			this.id = UUID.randomUUID().clockSequence();
			this.tag = tag;
		}
		
		public int getId(){ return id; }
		public String getTag()
		{ return tag; }

		@Override
		public void draw(SpriteBatch batch, ShapeRenderer renderer) {
			// TODO: Implement this method
		}
	}
	
	public static interface OnMenuClickListener{
		void onMenyClick(MenuItem item);
	}
	
	List<MenuItem> items;
	private Boundary boundary;
	public Menu(){
		items = new ArrayList<>();
		boundary = new Boundary(PaperSnake.GameWidth - 100, 0, 100, 150);
	}

	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer)	{
		// TODO: Implement this method
	}

	@Override
	public void dispose(){
		// TODO: Implement this method
	}
}
