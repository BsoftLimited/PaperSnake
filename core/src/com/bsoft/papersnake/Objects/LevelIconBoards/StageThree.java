package com.bsoft.papersnake.Objects.LevelIconBoards;

import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Objects.LevelIconBoard.*;
import com.bsoft.papersnake.*;

public class StageThree extends LevelIconBoard{
	public StageThree(PaperSnake game){ super(game); }

	@Override
	public int setNumber(){
		return 3;
	}

	@Override
	public Grid setIcons(Grid grid){
		grid.addValue(
		    new GridValue(7, 4, 1), new GridValue( 6, 2, 2), new GridValue(7, 0,  3),
		    new GridValue(5, 0, 4), new GridValue( 4, 2, 5), new GridValue(5, 4, 6),
			new GridValue(4, 5, 7),  new GridValue(3, 3, 8), new  GridValue(2, 5, 9),
			new GridValue(1, 3, 10),  new GridValue(0, 0, 11), new GridValue(2, 1, 12));
		return grid;
	}

	@Override
	protected void onIconClick(LevelIcon icon)
	{
		// TODO: Implement this method
	}
}
