package com.bsoft.papersnake.Screens;

import com.bsoft.papersnake.*;
import com.bsoft.papersnake.Screens.Levels.*;
import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Objects.UIs.*;
import com.bsoft.papersnake.Objects.UIs.Buttons.*;
import com.bsoft.papersnake.Objects.UIs.Containers.*;
import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.Sprites.*;

public class SuccessScreen extends Screen implements Button.OnClickListener{
	Level level;
	RelativeContainer container;
	Score score;
	Stars star;

	public SuccessScreen(PaperSnake game, Level level, String mes){
		super(game);
		this.level = level;

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
		sadSnake.setSize(320, 300);
		RelativeContainer messageLayout = new RelativeContainer(340, 340);
		messageLayout.setPadding(0);
		messageLayout.addChildren(sadSnake, RelativeContainer.Dock.Top);
		messageLayout.addChildren(message, RelativeContainer.Dock.Bottom);

		container = new RelativeContainer(PaperSnake.GameWidth, PaperSnake.GameHeight);
		container.setPadding(0, 10, 10, 10);
		container.addChildren(messageLayout, RelativeContainer.Dock.Top);
		container.addChildren(restartLayout, RelativeContainer.Dock.BottomLeft);
		container.addChildren(exitLayout, RelativeContainer.Dock.BottomRight);

		star = new Stars(2);
		star.setHeight(100);
		star.setPosition(PaperSnake.GameWidth/ 2 - star.getWidth()/2, PaperSnake.GameHeight/2 - star.getHeight() + 30);

		score = new Score(level.getScore());
		score.setHeight(100);
		score.setPosition(PaperSnake.GameWidth/ 2 - score.getWidth()/2, star.getY() - score.getHeight() - 20);

		Font high = new Font("High Score 2000").setSize(24);
		Boundary init = high.getActualBoundary();
		high.setPosition(PaperSnake.GameWidth/ 2 - init.getWidth()/2, score.getY() - init.getHeight() - 20);
		manager.addObject(star, score, container, high);
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
	public void update(){
		score.setPosition(PaperSnake.GameWidth/ 2 - score.getWidth()/2, star.getY() - score.getHeight() - 20);
	}

	@Override
	public boolean back(){
		level.back();
		return false;
	}
}
