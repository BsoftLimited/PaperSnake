package com.bsoft.papersnake.Objects.LevelIconBoards;

import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Objects.LevelIconBoard.*;
import com.bsoft.papersnake.*;

public class StageFour extends LevelIconBoard{
	public StageFour(PaperSnake game){ super(game); }
	
	@Override
	public int setNumber(){
		return 4;
	}

	@Override
	public Grid setIcons(Grid grid){
		grid.addValue(
		    new GridValue(7, 5, 1), new GridValue( 6, 3, 2), new GridValue(7, 1,  3),
		    new GridValue(5, 0, 4), new GridValue( 4, 2, 5), new GridValue(5, 4, 6),
			new GridValue(3, 5, 7),  new GridValue(1, 4, 8), new  GridValue(2, 2, 9),
			new GridValue(1, 0, 10),  new GridValue(3, 1, 11), new GridValue(0, 2, 12));
		return grid;
	}

	@Override
	protected void onIconClick(LevelIcon icon){
		// TODO: Implement this method
	}
}
