package com.bsoft.papersnake.Objects.Boards;
import com.bsoft.papersnake.Objects.*;

public class Pilars extends Board{

	@Override
	public void init(byte[][] cells, int rLength, int cLength){
		for(int i = 0; i < rLength; i++){
			for(int j = 0; j < cLength; j ++){
				if(i == 0 || i == rLength - 1 || j == 0 || j== cLength - 1){
					cells[i][j] = 1;
				}else if( (i == 3 || i == 4 || i == rLength - 4 || i == rLength - 5) && (j == 3 ||  j == 4 || j == cLength -4 || j == cLength - 5)){
					cells[i][j] =(byte) 1;
				}else{
					cells[i][j] = 0;
				}
			}
		}
	}
}
