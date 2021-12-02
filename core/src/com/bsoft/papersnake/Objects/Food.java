package com.bsoft.papersnake.Objects;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.*;
import java.util.*;
import com.bsoft.papersnake.Utils.*;

public class Food extends GameObject2D implements Time.SecondsListener{
	public static enum FoodType{ Apple, Egg, RottenEgg };
	
	public static class Spawn{
		Position position; int liveSpan;
		FoodType type;
		public Spawn(FoodType type, int row, int column, int livespan){
			this.position = new Position(row, column);
			this.liveSpan = livespan;
			this.type = type;
		}
		
		public FoodType getType(){ return type; }
		public int getLiveSpan(){ return liveSpan; }
	}
	
	public static interface OnFoodEatenListener{
		void onAppleEaten(Spawn food);
	}
	
	Board board;
	List<Spawn> spawns;
	Random rand;
	OnFoodEatenListener listener;
	int count, scoreInterval, eggInterval;
	BitmapFont font;
	
	public Food(Board board, OnFoodEatenListener listener){
		this.board = board;
		this.listener = listener;
		this.spawns = new ArrayList<>();
		rand = new Random();
		count = 0;
		scoreInterval = 0;
		eggInterval = 0;
		font = new BitmapFont();
		font.setColor(Color.BLACK);
	}
	
	public void addFood(FoodType type){
		if(spawns.size() < 8){
			int row, column;
			do{
				row = rand.nextInt((int)Board.getBoundary().getHeight() / Board.CellSize);
				column = rand.nextInt((int)Board.getBoundary().getWidth() / Board.CellSize);
			}while(board.getValue(row, column) != 0);
			
			spawns.add(new Spawn(type,  row, column, type == FoodType.Egg ? 3 :  rand.nextInt(8) + 3));
			board.setValue(row, column, (byte)3);
		}
		
		int i = 0;
		while(i < spawns.size()){
			if(spawns.get(i).liveSpan == 0 ||  board.getValue(spawns.get(i).position.getRow(), spawns.get(i).position.getColumn()) == 2){
				if(spawns.get(i).liveSpan != 0){
					if(spawns.get(i).type == FoodType.Apple){ scoreInterval += spawns.get(i).liveSpan * 5;; }
					listener.onAppleEaten(spawns.get(i));
					spawns.remove(i);
				}else {
					if(spawns.get(i).type == FoodType.Egg){
						spawns.get(i).type = FoodType.RottenEgg;
						spawns.get(i).liveSpan = -1;
						board.setValue(spawns.get(i).position.getRow(), spawns.get(i).position.getColumn(), (byte)4);
						i += 1;
					}else if(spawns.get(i).type != FoodType.RottenEgg){
						spawns.remove(i);
					}else{
						i++;
					}
				}
			}else{
				spawns.get(i++).liveSpan -= 1; 
			}
		}
	}

	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer){
		batch.begin();
		for(Spawn spawn : spawns){
			float x = spawn.position.getColumn() * Board.CellSize + Board.getX();
			float y = Board.getBoundary().getHeight() - Board.CellSize - (spawn.position.getRow() * Board.CellSize + Board.getY());
			switch(spawn.type){
				case Egg:
					batch.draw(TextureLoader.egg(),  x, y, Board.CellSize, Board.CellSize);
					break;
				case Apple:
					batch.draw(TextureLoader.apple(),  x, y, Board.CellSize, Board.CellSize);
					font.draw(batch, Integer.toString( spawn.liveSpan * 5), x ,  y + Board.CellSize);
					break;
				case RottenEgg:
					batch.draw(TextureLoader.rottenEgg(), x, y, Board.CellSize, Board.CellSize);
					break;
			}
		}
		batch.end(); 
	}
	
	public void check(){
		int i = 0;
		while(i < spawns.size()){
			if(spawns.get(i).liveSpan == 0 ||  board.getValue(spawns.get(i).position.getRow(), spawns.get(i).position.getColumn()) == 2){
				if(spawns.get(i).liveSpan != 0){
					if(spawns.get(i).type == FoodType.Apple){ scoreInterval += spawns.get(i).liveSpan * 5;; }
					listener.onAppleEaten(spawns.get(i));
					spawns.remove(i);
				}else {
					if(spawns.get(i).type == FoodType.Egg){
						spawns.get(i).type = FoodType.RottenEgg;
						spawns.get(i).liveSpan = -1;
						board.setValue(spawns.get(i).position.getRow(), spawns.get(i).position.getColumn(), (byte)4);
						i += 1;
					}else if(spawns.get(i).type != FoodType.RottenEgg){ spawns.remove(i);
					}else{ i++; }
				}
			}else{ i++;}
		}
	}
	
	@Override
	public void onTick(){
		if(scoreInterval >= 100 || eggInterval >= 7){
			this.addFood(FoodType.Egg);
			scoreInterval -= 100;
			eggInterval = 0;
		}else if(count >= 30 || spawns.size() == 0){
			this.addFood(FoodType.Apple);
			eggInterval += 1;
			count = 0;
		}else{
			check();
			count ++;
		}
	}

	@Override
	public void enableTouch()
	{
		// TODO: Implement this method
	}

	@Override
	public void disableTouch()
	{
		// TODO: Implement this method
	}

	@Override
	public void dispose(){
		font.dispose();
	}
}
