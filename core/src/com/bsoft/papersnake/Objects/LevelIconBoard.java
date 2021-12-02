package com.bsoft.papersnake.Objects;

import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.io.*;
import com.bsoft.papersnake.Utils.*;
import java.util.*;
import com.bsoft.papersnake.*;

public abstract class LevelIconBoard extends GameObject2D implements Serializable{
	protected static class GridValue{
		private int row, column, value;
		public GridValue(int row, int column, int value){
			this.row = row;
			this.column = column;
			this.value = value;
		}
	}
	
	protected static class Grid{
		List<GridValue> values;
		
		public Grid(){ values = new ArrayList<GridValue>(); }
		public void addValue(GridValue... values){
			this.values.addAll(Arrays.asList(values));
		}
		
		public List<GridValue> getValues(){
			return values;
		}
	}
	private Grid grid;
	private int n;
	public static final int CellWidth = 80, CellHeight = 80;
	public static final int RowCount = 8, ColumnCount = 6;
	private List<LevelIcon> icons;
	private Boundary boundary;
	private Lines lines;
	protected PaperSnake game;
	
	public LevelIconBoard(PaperSnake game){
		this.game = game;
		this.n = this.setNumber();
		boundary = new Boundary((n - 1) * getWidth(), 0, getWidth(), getHeight());
		this.icons = new ArrayList<>();
		this.grid = this.setIcons(new Grid());
		
		try{
			SaveData data = SaveData.getData();
			for(GridValue value : grid.getValues()){
				SaveData.LevelData levelData = data.getLevelData(n, Integer.toString(value.value));
				LevelIcon init = new LevelIcon(this,  value.value,  levelData.stars(), levelData.isLocked());
				init.setPosition(value.row, value.column);
				this.icons.add(init);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		lines = new Lines(this);
	}
	
	public abstract Grid setIcons(Grid grid);
	public abstract int setNumber();
	
	public static int getWidth(){ return ColumnCount * CellWidth; }
	public static int getHeight(){ return RowCount * CellHeight; }
	public int getN(){ return n; }
	public float getX(){ return boundary.getX(); }
	public float getY(){ return boundary.getY(); }
	public void setPosition(float x, float y){
		boundary.setX(x); boundary.setY(y);
		for(LevelIcon icon : icons){
			icon.init();
		}
	}
	
	protected abstract void onIconClick(LevelIcon icon);

	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer){
		batch.begin();
		for(LevelIcon icon : icons){
			icon.draw(batch);
		}
		lines.draw(batch, renderer);
		batch.end();
	}

	@Override
	public void enableTouch(){
		for(LevelIcon icon : icons){
			icon.enableTouch();
		}
	}

	@Override
	public void disableTouch(){
		for(LevelIcon icon : icons){
			icon.disableTouch();
		}
	}
	
	public List<LevelIcon> getIcons(){ return icons; }

	@Override
	public void dispose(){
		for(LevelIcon icon : icons){
			icon.dispose();
		}
	}
}
