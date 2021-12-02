package com.bsoft.papersnake.Objects.LevelIconBoards;

import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Objects.LevelIconBoard.*;
import com.bsoft.papersnake.*;

public class StageTwo extends LevelIconBoard{

	public StageTwo(PaperSnake game){ super(game); }
	
	@Override
	public int setNumber(){
		return 2;
	}

	@Override
	public Grid setIcons(Grid grid){
		grid.addValue(
		    new GridValue(7, 5, 1), new GridValue( 7, 3, 2), new GridValue(7, 1,  3),
		    new GridValue(6, 0, 4), new GridValue( 5, 2, 5), new GridValue(5, 4, 6),
			new GridValue(3, 5, 7),  new GridValue(1, 5, 8), new  GridValue(1, 3, 9),
			new GridValue(3, 2, 10),  new GridValue(2, 0, 11), new GridValue(0, 1, 12));
		return grid;
	}

	@Override
	protected void onIconClick(LevelIcon icon)
	{
		// TODO: Implement this method
	}
}
