package com.bsoft.papersnake.Screens;

import com.bsoft.papersnake.*;
import com.bsoft.papersnake.Screens.Levels.*;
import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Objects.UIs.*;
import com.bsoft.papersnake.Objects.UIs.Buttons.*;
import com.bsoft.papersnake.Objects.UIs.Containers.*;
import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.Sprites.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;

public class DeadScreen extends Screen implements Button.OnClickListener{
	Level level;
	RelativeContainer container;
	Score score;
	Objective objective;
	Font high;
	float y;
	
	public DeadScreen(PaperSnake game, Level level, String mes){
		super(game);
		this.level = level;
		y = PaperSnake.GameHeight;
	
		Font message = new Font(mes).setSize(25); 
		message.setMargin(0);
		message.setPadding(0);

		RestartButton restart = new RestartButton(this, 80, 80);
		restart.setMargin(0);
		LinearContainer restartLayout = new LinearContainer(LinearContainer.Orientation.Vertical, true);
		restartLayout.addChildren(restart, new Font("Restart").setSize(22));

		ExitButton exit = new ExitButton(this, 80, 80);
		exit.setMargin(0);
		LinearContainer exitLayout = new LinearContainer(LinearContainer.Orientation.Vertical, true);
		exitLayout.addChildren(exit, new Font(" Exit").setSize(22));

		Image sadSnake = new Image(TextureLoader.snakeSad());
		sadSnake.setMargin(0);
		sadSnake.setPadding(0);
		sadSnake.setSize(360, 360);
		RelativeContainer messageLayout = new RelativeContainer(400, 400);
		messageLayout.setPadding(0);
		messageLayout.addChildren(sadSnake, RelativeContainer.Dock.Center);
		messageLayout.addChildren(message, RelativeContainer.Dock.Bottom);

		container = new RelativeContainer(PaperSnake.GameWidth, PaperSnake.GameHeight);
		container.setPadding(0, 10, 10, 10);
		container.addChildren(messageLayout, RelativeContainer.Dock.Top);
		container.addChildren(restartLayout, RelativeContainer.Dock.BottomLeft);
		container.addChildren(exitLayout, RelativeContainer.Dock.BottomRight);
		
		score = new Score(level.getScore());
		score.setHeight(120);
		score.setPosition(PaperSnake.GameWidth/ 2 - score.getWidth()/2, PaperSnake.GameHeight/2 - score.getHeight() + 30);
		
		high = new Font("High Score 2000").setSize(24);
		Boundary init = high.getActualBoundary();
		high.setPosition(PaperSnake.GameWidth/ 2 - init.getWidth()/2, score.getY() - init.getHeight() - 80);
		
		objective = new Objective(level.getObjective());
		objective.setHeight(80);
		objective.setPosition(5, PaperSnake.GameHeight - objective.getHeight() - 5 + y);
		manager.addObject(score, container, high, objective);
	}
	
	public void rearrange(){
		container.setPosition(0, y);
		score.setPosition(PaperSnake.GameWidth/ 2 - score.getWidth()/2, PaperSnake.GameHeight/2 - score.getHeight() - 50 + y);
		Boundary init = high.getActualBoundary();
		high.setPosition(PaperSnake.GameWidth/ 2 - init.getWidth()/2, score.getY() - init.getHeight() - 20);
		objective.setPosition(5, PaperSnake.GameHeight - objective.getHeight() - 5 + y);
	}

	@Override
	public void onClick(Button button){
		if(button instanceof ExitButton){
			game.toMenu();
		}else if( button instanceof RestartButton){
			game.back();
			level.reset();
		}
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer renderer){
		if( y > 0){
			y -= y  * Gdx.graphics.getDeltaTime() * 4;
			rearrange();
		}else if( y < 0){
			y += (0 - y) * Gdx.graphics.getDeltaTime() * 4;
			rearrange();
		}
		if(y < 10){score.start();}
		super.render(batch, renderer);
	}

	@Override
	public void update(){
		score.setPosition(PaperSnake.GameWidth/ 2 - score.getWidth()/2, PaperSnake.GameHeight/2 - score.getHeight() - 20);
	}

	@Override
	public boolean back(){
		return false;
	}
}
