package com.bsoft.papersnake.Screens;
import com.bsoft.papersnake.*;
import com.bsoft.papersnake.Objects.UIs.Containers.*;
import com.bsoft.papersnake.Objects.UIs.*;
import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Utils.*;
import com.badlogic.gdx.*;
import java.util.*;
import com.bsoft.papersnake.Objects.LevelIconBoards.*;
import com.bsoft.papersnake.Objects.UIs.Buttons.*;
import com.bsoft.papersnake.Screens.Levels.*;

public class SelectScreen extends Screen implements TouchHandler.OnTouchListener, Button.OnClickListener{
	List<LevelIconBoard> boards;
	TouchHandler handler;
	LevelIconBoard current;
	boolean isMoving, isLeft, isReleased;
	Font stageLabel;
	Objective objective;
	
	public SelectScreen(PaperSnake game){
		super(game);
		this.addBoard( new StageOne(game));
		this.addBoard( new StageTwo(game));
		this.addBoard(new StageThree(game));
		this.addBoard( new StageFour(game));
		this.addBoard(new StageFive(game));
		this.isMoving = false;
		this.isLeft = false;
		this.isReleased = true;
		
		RelativeContainer buttons = new RelativeContainer(PaperSnake.GameWidth, 80);
		buttons.setPosition(0, LevelIconBoard.getHeight());
		
		PrevButton prev = new PrevButton(this);
		prev.setSize(70, 70);
		NextButton next = new NextButton(this);
		next.setSize(70, 70);
		buttons.addChildren(prev, RelativeContainer.Dock.Left);
		buttons.addChildren(next, RelativeContainer.Dock.Right);
		manager.addObject(buttons);
		handler = new TouchHandler(this, new Boundary(0, 0, PaperSnake.GameWidth, PaperSnake.GameHeight));
		this.current = boards.get(0);
		stageLabel = new Font("Stage " + Integer.toString(current.getN())).setSize(24);
		buttons.addChildren(stageLabel, RelativeContainer.Dock.Center);
		
		objective = new Objective("Tap on icon to select level");
		objective.setHeight(80);
		objective.setPosition(5, PaperSnake.GameHeight - objective.getHeight() - 5);
		manager.addObject(objective);
	}
	
	private void addBoard(LevelIconBoard board){
		if(boards == null){
			boards = new ArrayList<>();
		}
		boards.add(board);
		manager.addObject(board);
	}

	@Override
	public void update(){
		if(!isMoving && isReleased){
			if(current.getX() > 0){
				float init = current.getX();
				for(LevelIconBoard board : boards){
					board.setPosition(board.getX() - init * Gdx.graphics.getDeltaTime() * 5, board.getY());
				}
			}else if((current.getX() + current.getWidth()) < PaperSnake.GameWidth){
				float init = PaperSnake.GameWidth - (current.getX() + current.getWidth());
				for(LevelIconBoard board : boards){
					board.setPosition(board.getX() + init * Gdx.graphics.getDeltaTime() * 5, board.getY());
				}
			}
		}else if(isMoving && isReleased){
			int index = boards.indexOf(current);
			current = isLeft ? (index > 0 ? boards.get(index - 1) : current) : ((index < boards.size() - 1) ? boards.get(index + 1) : current);
			stageLabel.setString("Stage " + Integer.toString(current.getN()));
			isMoving = false;
		}
	}

	@Override
	public void onClick(Button button){
		if(button instanceof PrevButton){
			isLeft = true;
		}else if(button instanceof NextButton){
			isLeft = false;
		}
		int index = boards.indexOf(current);
		current = isLeft ? (index > 0 ? boards.get(index - 1) : current) : ((index < boards.size() - 1) ? boards.get(index + 1) : current);
		stageLabel.setString("Stage " + Integer.toString(current.getN()));
	}

	@Override
	public void onTouched(){
		// TODO: Implement this method
	}

	@Override
	public void onTouchDown(){
		isReleased = false;
	}

	@Override
	public void onTouchDraged(int deltaX, int deltaY){
		if(deltaX > 120 || deltaX < -120){
			isMoving = true;
			isLeft =  deltaX > 0;
		}
	}

	@Override
	public void continusDrag(float x, float y){
			for(LevelIconBoard board : boards){
				board.setPosition(board.getX() + x, board.getY());
			}
	}

	
	@Override
	public void onTouchRelease(){
		isReleased = true ;
	}

	@Override
	public void enable(){
		super.enable();
		handler = new TouchHandler(this, new Boundary(0, 0, PaperSnake.GameWidth, PaperSnake.GameHeight));
	}

	@Override
	public void disable(){
		super.disable();
		handler.destroy();
	}
	
	

	@Override
	public boolean back()
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public void dispose(){
		super.dispose();
		handler.destroy();
	}
}
