package com.bsoft.papersnake.Objects.Boards;
import com.bsoft.papersnake.Objects.*;

public class Rooms extends Board{

	@Override
	public void init(byte[][] cells, int rLength, int cLength){
		for(int i = 0; i < rLength; i++){
			for(int j = 0; j < cLength; j ++){
				if(i == 0 || i == rLength - 1 || j == 0 || j== cLength - 1){
					cells[i][j] = 1;
				}else if(i == rLength / 2){
					cells[i][j] = (byte) ((j >= cLength/2 - 2 && j <= cLength/2 + 2) || (j >= 1 && j <= 3) || ( j>= cLength - 4 && j <= cLength - 2) ? 1 : 0);
				}else if(j == cLength / 2){
					cells[i][j] = (byte) ((i >= rLength/2 - 2 && i <= rLength/2 + 2) || (i >= 1 && i <= 2) || ( i>= rLength - 3 && i <= rLength - 2) ? 1 : 0);
				}else{
					cells[i][j] = 0;
				}
			}
		}
	}
}
