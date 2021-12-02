package com.bsoft.papersnake.Objects.Boards;
import com.bsoft.papersnake.Objects.*;

public class Dungeon extends Board
{

	@Override
	public void init(byte[][] cells, int rLength, int cLength)
	{
		for(int i = 0; i < rLength; i++){
			for(int j = 0; j < cLength; j ++){
				if(i == 0 || i == rLength - 1 || j == 0 || j== cLength - 1){
					cells[i][j] = 1;
				}else if( (i == 3 || i == rLength - 4) && (j >= 3 && j <= cLength - 4)){
					cells[i][j] =(byte) (( j >= cLength/2 -1 && j <= cLength/2 + 1) ? 0 : 1);
				}else if((j == 3 || j == cLength - 4) && (i >= 3 && i <= rLength - 4)){
					cells[i][j] =(byte) (( i >= rLength/2 -1 && i <= rLength/2 + 1) ? 0 : 1);
				}else{
					cells[i][j] = 0;
				}
			}
		}
	}
}
