package com.bsoft.papersnake.Screens.Levels;
import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Objects.Boards.*;
import com.bsoft.papersnake.*;
import com.bsoft.papersnake.Objects.Food.*;

public class LevelOne extends Level{
	int appleCount;
	public LevelOne(PaperSnake game){
		super(game);
		appleCount = 0;
	}

	@Override
	protected Board initBoard(){
		return new Box();
	}

	@Override
	protected Timer initTimer(){
		return new Timer(300, false);
	}

	@Override
	public void onTimerStoped(){
		// TODO: Implement this method
	}

	@Override
	protected Stage initStage(){
		return new Stage("1-1");
	}

	@Override
	protected boolean condition(){
		return appleCount >= 5;
	}

	@Override
	public void onAppleEaten(Spawn food){
		super.onAppleEaten(food);
		if(food.getType() == FoodType.Egg){
			appleCount+= 1;
		}
	}

	@Override
	public String getObjective(){
		return "Eat five Eggs before the time runs out";
	}

	@Override
	public void dispose(){
		super.dispose();
	}
}
