package com.bsoft.papersnake.Objects.LevelIconBoards;

import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Objects.LevelIconBoard.*;
import com.bsoft.papersnake.*;

public class StageFive extends LevelIconBoard{
	public StageFive(PaperSnake game){ super(game); }

	@Override
	public int setNumber(){
		return 5;
	}

	@Override
	public Grid setIcons(Grid grid){
		grid.addValue(
		    new GridValue(3, 5, 1), new GridValue( 4, 3, 2), new GridValue(2, 3,  3),
		    new GridValue(1, 5, 4), new GridValue( 0, 3, 5), new GridValue(0, 0, 6),
			new GridValue(2, 0, 7),  new GridValue(3, 2, 8), new  GridValue(5, 1, 9),
			new GridValue(6, 3, 10),  new GridValue(5, 5, 11), new GridValue(7, 0, 12));
		return grid;
	}

	@Override
	protected void onIconClick(LevelIcon icon)
	{
		// TODO: Implement this method
	}
}
