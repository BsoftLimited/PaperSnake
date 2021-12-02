package com.bsoft.papersnake.Objects.Boards;
import com.bsoft.papersnake.Objects.*;

public class Crossed extends Board{

	@Override
	public void init(byte[][] cells, int rLength, int cLength){
		for(int i = 0; i < rLength; i++){
			for(int j = 0; j < cLength; j ++){
				if(i == 0 || i == rLength - 1 || j == 0 || j== cLength - 1){
					cells[i][j] = 1;
				}else if(i == rLength / 2){
					cells[i][j] = (byte) ((j >= cLength/2 - 2 && j <= cLength/2 + 2) ? 0 : 1);
				}else if(j == cLength / 2){
					cells[i][j] = (byte) ((i >= rLength/2 - 1 && i <= rLength/2 + 1) ? 0 : 1);
				}else{
					cells[i][j] = 0;
				}
			}
		}
	}
}
