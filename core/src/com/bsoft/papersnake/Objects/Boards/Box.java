package com.bsoft.papersnake.Objects.Boards;
import com.bsoft.papersnake.Objects.*;

public class Box extends Board
{

	@Override
	public void init(byte[][] cells, int rLength, int cLength)
	{
		for(int i = 0; i < rLength; i++){
			for(int j = 0; j < cLength; j ++){
				if(i == 0 || i == rLength - 1 || j == 0 || j== cLength - 1){
					cells[i][j] = 1;
				}else{
					cells[i][j] = 0;
				}
			}
		}
	}
}
