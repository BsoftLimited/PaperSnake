package com.bsoft.papersnake.Objects;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import java.util.*;
import com.bsoft.papersnake.Utils.*;

public class Lines extends GameObject2D{
	private static class Line{
		int regionRow, regionColumn;
		float row, column;
		LevelIconBoard board;
		public Line(LevelIconBoard board, int regionRow, int regionColumn, float row, float column){
			this.regionRow = regionRow;
			this.regionColumn = regionColumn;
			this.row = row;
			this.column = column;
			this.board = board;
		}
		
		public void draw(SpriteBatch batch){
			float x = board.getX() + (column * LevelIconBoard.CellWidth);
			float y = board.getY() + board.getHeight() - ((row + 1) * LevelIconBoard.CellHeight);
			batch.draw(TextureLoader.line(regionRow, regionColumn), x, y, LevelIconBoard.CellWidth, LevelIconBoard.CellHeight);
		}
	}
	
	LevelIconBoard board;
	List<Line> lines;
	
	public Lines(LevelIconBoard board){
		this.board = board;
		this.lines = new ArrayList<>();
		for(int i = 0; i < board.getIcons().size() - 1; i ++){
			Position first = board.getIcons().get(i).getPosition();
			Position second = board.getIcons().get(i + 1).getPosition();
			Position init = new Position(first.getRow(), first.getColumn());
			boolean isFirst = true;
			do{
				if(!isFirst){
					if(first.getRow() == init.getRow() && second.getColumn() == init.getColumn()){
						if(first.getColumn() < second.getColumn()){
							if(first.getRow() < second.getRow()){
								// up left
								lines.add(new Line(board, 0, 2, init.getRow(), init.getColumn()));
							}else if(first.getRow() > second.getRow()){
								// down left
								lines.add(new Line(board, 1, 2, init.getRow(), init.getColumn()));
							}
						}else if(first.getColumn() > second.getColumn()){
							if(first.getRow() < second.getRow()){
								// up right
								lines.add(new Line(board, 0, 0, init.getRow(), init.getColumn()));
							}else if(first.getRow() > second.getRow()){
								// down right
								lines.add(new Line(board, 2, 0, init.getRow(), init.getColumn()));
							}
						}
					}else if(first.getColumn() == init.getColumn() && second.getRow() == init.getRow()){
						if(first.getColumn() > second.getColumn()){
							if(first.getRow() > second.getRow()){
								// up left
								lines.add(new Line(board, 0, 2, init.getRow(), init.getColumn()));
							}else if(first.getRow() < second.getRow()){
								// down left
								lines.add(new Line(board, 1, 2, init.getRow(), init.getColumn()));
							}
						}else if(first.getColumn() < second.getColumn()){
							if(first.getRow() > second.getRow()){
								// up right
								lines.add(new Line(board, 0, 0, init.getRow(), init.getColumn()));
							}else if(first.getRow() < second.getRow()){
								// down right
								lines.add(new Line(board, 2, 0, init.getRow(), init.getColumn()));
							}
						}
					}else if(first.getRow() == second.getRow() || first.getRow() == init.getRow() || second.getRow() == init.getRow()){
						//horizontal
						lines.add(new Line(board, 0, 1, init.getRow(), init.getColumn()));
					}else if(first.getColumn() == second.getColumn() || first.getColumn() == init.getColumn() || second.getColumn() == init.getColumn()){
						// vertical
						lines.add(new Line(board, 1, 0, init.getRow(), init.getColumn()));
					}
				}else{ isFirst = false;}
				
				if(second.getRow() > init.getRow()){
					init.moveDown();
				}else if(second.getRow() < init.getRow()){
					init.moveUp();
				}else{
					if(second.getColumn() > init.getColumn()){
						init.moveRight();
					}else{
						init.moveLeft();
					}
				}
			}while(init.getRow() != second.getRow() || init.getColumn() != second.getColumn());
		}
	}

	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer){
		for(Line line : lines){
			line.draw(batch);
		}
	}

	@Override
	public void enableTouch()
	{
		// TODO: Implement this method
	}

	@Override
	public void disableTouch()
	{
		// TODO: Implement this method
	}

	@Override
	public void dispose()
	{
		// TODO: Implement this method
	}
}
