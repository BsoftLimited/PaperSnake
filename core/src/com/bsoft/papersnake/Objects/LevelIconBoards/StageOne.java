package com.bsoft.papersnake.Objects.LevelIconBoards;

import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Objects.LevelIconBoard.*;
import com.bsoft.papersnake.*;
import com.bsoft.papersnake.Screens.Levels.*;

public class StageOne extends LevelIconBoard
{
	
	public StageOne(PaperSnake game){ super(game); }

	@Override
	public int setNumber(){
		return 1;
	}

	@Override
	public Grid setIcons(Grid grid){
		grid.addValue(
		    new GridValue(7, 5, 1), new GridValue( 5, 4, 2), new GridValue(7, 3,  3),
		    new GridValue(5, 2, 4), new GridValue( 7, 1, 5), new GridValue(5, 0, 6),
			new GridValue(3, 1, 7),  new GridValue(1, 2, 8), new  GridValue(3, 3, 9),
			new GridValue(1, 4, 10),  new GridValue(3, 5, 11), new GridValue(0, 0, 12));
		return grid;
	}

	@Override
	protected void onIconClick(LevelIcon icon)
	{
		if(!icon.isLocked()){
			game.setScreen(new LevelOne(game));
		}
	}
}
