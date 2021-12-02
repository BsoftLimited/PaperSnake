package com.bsoft.papersnake.Screens.Levels;

import com.bsoft.papersnake.Screens.*;
import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Objects.Food.*;
import com.bsoft.papersnake.Utils.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.graphics.*;
import com.bsoft.papersnake.*;
import com.bsoft.papersnake.Objects.UIs.Buttons.*;
import com.bsoft.papersnake.Objects.UIs.PopUps.*;
import com.bsoft.papersnake.Objects.UIs.*;

public abstract class Level extends Screen implements Snake.SnakeDeadListener, OnFoodEatenListener, Time.SecondsListener, Timer.TimerListener, Button.OnClickListener{
	private Board board;
	private Snake snake;
	private Food food;
	private int startCount, counter;
	private Time time;
	private Timer timer;
	private ScoreCounter score;
	private Font start;
	
	private FoodCounter foodCounter;
	private PopUp popUp;
	
	protected Level(PaperSnake game){
		super(game);
		board = initBoard();
		Stage stage = initStage();
		stage.setPosition(5, Board.getBoundary().getHeight() + 5);
		
		Button info = new InfoButton(this);
		info.setPosition(PaperSnake.GameWidth - info.getWidth() - 5, PaperSnake.GameHeight - info.getHeight() - 5);
		Button pause = new PauseButton(this);
		pause.setPosition(5, PaperSnake.GameHeight - pause.getHeight() - 5);
		manager.addObject( board, stage, info, pause);
		
		start = new Font("Start").setSize(42);
		start.setPosition(board.getX() + (board.getBoundary().getWidth()  / 2) - (start.getActualBoundary().getWidth() / 2),
		(board.getBoundary().getHeight()/2) + board.getY() - (start.getActualBoundary().getHeight()/2));
		this.init();
	}
	
	private void init(){
		board.clear();
		
		startCount = 0;
		counter = 0;
		snake = new Snake(this, board);
		food = new Food(board, this);
		
		score = new ScoreCounter();
		score.setPosition(PaperSnake.GameWidth - score.getWidth() - 5, Board.getBoundary().getHeight() + 5);

		timer = initTimer();
		timer.setListener(this);
		timer.setPosition(PaperSnake.GameWidth / 2 - timer.getWidth() / 2, PaperSnake.GameHeight - timer.getHeight() - 5);
		
		foodCounter = new FoodCounter();
		foodCounter.setPosition(PaperSnake.GameWidth/2 - foodCounter.getWidth()/2, Board.getBoundary().getHeight() + 5);
		
		manager.addObject(food, snake, timer, score, foodCounter);
		time = new Time(0.1f);
		time.addListener(this); 
	}
	
	public void reset(){
		time.removeListener(this);
		foodCounter.dispose();
		timer.dispose();
		food.dispose();
		snake.dispose();
		score.dispose();
		manager.removeObject(timer, foodCounter, snake, food, score);
		init();
	}

	@Override
	public void onSnakeDied(){
		time.stop();
		game.setScreen(new DeadScreen(game, this, "Oops you Died"));
	}
	
	public int getScore(){ return score.getValue(); }

	@Override
	public void onAppleEaten(Spawn food){
		switch(food.getType()){
			case Apple:
				score.add( food.getLiveSpan() * 5);
				foodCounter.increaseApple();
				break;
			case Egg:
				score.add( 100);
				foodCounter.increaseEggs();
		}
	}

	@Override
	public void onClick(Button button){
		if(button instanceof InfoButton && popUp == null){
			this.pause();
			popUp = new HelpPopUp(this,  getObjective());
			this.showPopUp();
		}else if(button instanceof PauseButton && popUp == null){
			if(time.isPaused()){
				this.resume();
			}else{
				this.pause();
			}
		}else if(button instanceof ResumeButton){
			this.remoePopUp();
			this.resume();
		}else if(button instanceof RestartButton){
			this.remoePopUp();
			this.reset();
		}else if(button instanceof ExitButton){
			remoePopUp();
			game.back();
		}
	}
	
	private void remoePopUp(){
		manager.removeObject(popUp);
		popUp.dispose();
		popUp = null;
		snake.enableTouch();
	}
	
	private void showPopUp(){
		snake.disableTouch();
		manager.addObject(popUp);
	}

	@Override
	public boolean back(){
		if(popUp != null && !(popUp instanceof DeadPopUp)){
			this.remoePopUp();
			this.resume();
			return true;
		}
		return false;
	}
	
	public void pause(){
		time.pause();
		snake.pause();
	}
	
	public void resume(){
		time.resume();
		snake.start();
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer renderer){
		time.start();
		super.render(batch, renderer);
		if(!(time.isPaused() || time.hasStoped()) && startCount < 5){
			batch.begin();
			switch(startCount){
				case 1:
					this.printThree(batch);
					break;
				case 2:
					this.printTwo(batch);
					break;
				case 3:
					this.printOne(batch);
					break;
				case 4:
					this.printStart(batch);
			}
			batch.end();
		}
	}

	@Override
	public void update(){ time.update(); }

	@Override
	public void onTick(){
		if(++counter >= 10 && startCount < 5){
			startCount += 1;
			counter = 0;
		}else if(startCount >= 5){
			time.removeListener(this);
			time.addListener(snake, food, timer);
			snake.start();
		}
	}
	
	private void printStart(SpriteBatch batch){
		batch.end();
		start.draw(batch, null);
		batch.begin();
	}

	private void printOne(SpriteBatch batch){
		batch.draw(TextureLoader.one(), (board.getBoundary().getWidth() + board.getX()) / 2 - 60, (board.getBoundary().getHeight() + board.getY()) / 2 - 60, 120, 120);
	}

	private void printTwo(SpriteBatch batch){
		batch.draw(TextureLoader.two(), (board.getBoundary().getWidth() + board.getX()) / 2 - 60, (board.getBoundary().getHeight() + board.getY()) / 2 - 60, 120, 120);
	}

	private void printThree(SpriteBatch batch){
		batch.draw(TextureLoader.three(), (board.getBoundary().getWidth() + board.getX()) / 2 - 60, (board.getBoundary().getHeight() + board.getY()) / 2 - 60, 120, 120);
	}
	
	protected abstract Board initBoard();
	protected abstract Timer initTimer();
	protected abstract Stage initStage();
	public abstract String getObjective();
	protected abstract boolean condition();
}
