package com.bsoft.papersnake.Utils;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.graphics.*;

public class TextureLoader implements Disposable{
	
	private static TextureRegion snakeImage,  levelIcon, levels, levelStatus;
	private static int snakeWidth, snakeHeight, startingY;
	private static Texture snake, paper, levelIconImage, statusImage, levelsImage, levelStatusImage;
	private static Texture upperCaseImage, lowerCaseImage, tornPaper, logoImage, launcher, snakeSad;
	private static TextureRegion upperCase, lowerCase, logo, lines, numbers;
	private static Texture linesImage, numbersImage;
	public TextureLoader(){
		snake = new Texture(Config.isColored() ? "snake_colored.png" : "snake.png");
		snakeWidth = snake.getWidth() / 5;
		snakeHeight = snake.getHeight() / 5;
		startingY = snakeHeight / 2;
		snakeImage =new TextureRegion(snake);
		paper = new Texture("paper.jpg");
		levelIconImage = new Texture("level_icons.png");
		levelIcon = new TextureRegion(levelIconImage);
		statusImage = new Texture("status.png");
		
		tornPaper = new Texture("torn_paper.png");
		
		this.upperCaseImage = new Texture("alphaUpper.png");
		this.upperCase = new TextureRegion(upperCaseImage);

		this.lowerCaseImage = new Texture("alphaLower.png");
		this.lowerCase = new TextureRegion(lowerCaseImage);
		
		this.logoImage = new Texture("logo.png");
		this.logo = new TextureRegion(logoImage);
		
		this.launcher = new Texture("launcher.png");
		this.snakeSad = new Texture("snake_sad.png");
		
		this.levelsImage = new Texture("levels.png");
		this.levels = new TextureRegion(this.levelsImage);
		
		this.levelStatusImage = new Texture("level_status.png");
		this.levelStatus = new TextureRegion(levelStatusImage);
		
		this.linesImage = new Texture("lines.png");
		this.lines = new TextureRegion(linesImage);
		
		this.numbersImage = new Texture("numbers.png");
		this.numbers = new TextureRegion(numbersImage);
	}
	
	public static TextureRegion snakeHeadUp(){
		snakeImage.setRegion(3 * snakeWidth + 2, startingY, snakeWidth - 2, snakeHeight);
		return snakeImage;
	}
	
	public static TextureRegion snakeHeadDown(){
		snakeImage.setRegion(4 * snakeWidth + 1,  snakeHeight + startingY, snakeWidth - 2, snakeHeight);
		return snakeImage;
	}
	
	public static TextureRegion snakeHeadRight(){
		snakeImage.setRegion(4 * snakeWidth + 2 , startingY + 1, snakeWidth, snakeHeight - 2);
		return snakeImage;
	}
	
	public static TextureRegion snakeHeadLeft(){
		snakeImage.setRegion(3 * snakeWidth,  snakeHeight + startingY + 1, snakeWidth, snakeHeight - 2);
		return snakeImage;
	}

	public static TextureRegion snakeTailUp(){
		snakeImage.setRegion(3 * snakeWidth + 2, 2 * snakeHeight + startingY, snakeWidth - 4, snakeHeight - 1);
		return snakeImage;
	}
	
	public static TextureRegion snakeTailDown(){
		snakeImage.setRegion(4 * snakeWidth, 3 * snakeHeight + startingY, snakeWidth, snakeHeight);
		return snakeImage;
	}
	
	public static TextureRegion snakeTailRight(){
		snakeImage.setRegion(4 * snakeWidth, 2 * snakeHeight + startingY, snakeWidth, snakeHeight);
		return snakeImage;
	 }
	 
	public static TextureRegion snakeTailLeft(){
		snakeImage.setRegion(3 * snakeWidth, 3 * snakeHeight + startingY, snakeWidth, snakeHeight);
		return snakeImage;
	}

	public static TextureRegion snakeBodyHorizontal(){
		snakeImage.setRegion(snakeWidth, startingY, snakeWidth, snakeHeight);
		return snakeImage;
	}
	
	public static TextureRegion snakeBodyVertical(){
		snakeImage.setRegion(2 * snakeWidth, snakeHeight + startingY, snakeWidth, snakeHeight);
		return snakeImage;
	}
	
	public static TextureRegion snakeDownLeft(){
		snakeImage.setRegion(2 * snakeWidth, startingY, snakeWidth, snakeHeight);
		return snakeImage;
	}
	
	public static TextureRegion snakeDownRight(){
		snakeImage.setRegion(0, startingY, snakeWidth, snakeHeight);
		return snakeImage;
	}
	
	public static TextureRegion snakeUpLeft(){
		snakeImage.setRegion(2 * snakeWidth, 2 * snakeHeight + startingY, snakeWidth, snakeHeight);
		return snakeImage;
	}
	
	public static TextureRegion snakeUpRight(){
		snakeImage.setRegion(0,  snakeHeight + startingY, snakeWidth, snakeHeight);
		return snakeImage;
	}

	public static TextureRegion apple(){
		snakeImage.setRegion(0, 3 * snakeHeight + startingY, snakeWidth, snakeHeight);
		return snakeImage;
	}
	
	public static TextureRegion crate(){
		snakeImage.setRegion(snakeWidth, 3 * snakeHeight + startingY, snakeWidth, snakeHeight);
		return snakeImage;
	}
	
	public static TextureRegion egg(){
		snakeImage.setRegion(snakeWidth * 2, 3 * snakeHeight + startingY, snakeWidth, snakeHeight);
		return snakeImage;
	}
	
	public static TextureRegion rottenEgg(){
		snakeImage.setRegion(snakeWidth , 2 * snakeHeight + startingY, snakeWidth, snakeHeight);
		return snakeImage;
	}
	
	public static TextureRegion one(){
		return getNumber(1, true);
	}
	
	public static TextureRegion two(){
		return getNumber(2, true);
	}
	
	public static TextureRegion three(){
		return getNumber(3, true);
	}
	
	public static TextureRegion time(){
		int cell = levelIconImage.getHeight() / 4;
		levelIcon.setRegion(0 , cell, cell * 2, cell);
		return levelIcon;
	}
	
	public static TextureRegion score(){
		int cell = levelIconImage.getHeight() / 4;
		levelIcon.setRegion(cell * 2 , cell, cell * 2, cell);
		return levelIcon;
	}
	
	public static TextureRegion stage(){
		int cell = levelIconImage.getHeight() / 4;
		levelIcon.setRegion(cell * 2 , cell * 2, cell * 2, cell);
		return levelIcon;
	}
	
	public static TextureRegion resume(){
		int cell = levelIconImage.getHeight() / 4;
		levelIcon.setRegion(cell * 3 , cell * 3, cell, cell);
		return levelIcon;
	}
	
	public static TextureRegion exit(){
		int cell = levelIconImage.getHeight() / 4;
		levelIcon.setRegion(cell * 3 , 0, cell, cell);
		return levelIcon;
	}
	
	public static TextureRegion emty_star(){
		levelStatus.setRegion(0, (levelStatusImage.getHeight()/4) * 3, levelStatusImage.getWidth()/4, levelStatusImage.getHeight()/4);
		return levelStatus;
	}
	
	public static TextureRegion star(){
		levelStatus.setRegion(0, levelStatusImage.getHeight()/2, levelStatusImage.getWidth()/4, levelStatusImage.getHeight()/4);
		return levelStatus;
	}
	
	public static TextureRegion getNumber(int n, boolean is3D){
		switch(n){
			case 0:
				return is3D ? number(0, 0) : alpha(2, 4, true, true);
			case 1:
				return is3D ? number(0, 1) : alpha(3, 4, true, true);
			case 2:
				return  is3D ? number(0, 2) : alpha(4, 4, true, true);
			case 3:
				return is3D ? number(0, 3) : alpha(5, 4, true, true);
			case 4:
				return is3D ? number(1, 0) :  alpha(0, 5, true, true);
			case 5:
				return is3D ? number(1, 1) :  alpha(1, 5, true, true);
			case 6:
				return is3D ? number(1, 2) :  alpha(2, 5, true, true);
			case 7:
				return is3D ? number(1, 3) :  alpha(3, 5, true, true);
			case 8:
				return is3D ? number(2, 0) :  alpha(4, 5, true, true);
			case 9:
				return is3D ? number(2, 1) :  alpha(5, 5, true, true);
		}
		return dash();
	}
	
	public static Texture paper(){ return paper; }
	public static Texture statusBack(){ return statusImage; }
	
	public static TextureRegion info(){
		int cell = levelIconImage.getHeight() / 4;
		levelIcon.setRegion(0 , 0, cell , cell);
		return levelIcon;
	}
	
	public static TextureRegion pause(){
		int cell = levelIconImage.getHeight() / 4;
		levelIcon.setRegion(cell , 0, cell, cell);
		return levelIcon;
	}
	
	public static TextureRegion restart(){
		int cell = levelIconImage.getHeight() / 4;
		levelIcon.setRegion(cell * 2 , 0, cell, cell);
		return levelIcon;
	}
	
	public static TextureRegion icon(int x, int y, int width, int height){
		levelIcon.setRegion(x , y, width, height);
		return levelIcon;
	}
	
	public static TextureRegion dash(){
		lowerCase.setRegion(3 * 88, 4 * 128, 80, 84);
		return lowerCase; 
	}
	
	public static TextureRegion column(){
		lowerCase.setRegion(2 * 84 + 20, 4 * 128, 64, 86);
		return lowerCase; 
	}
	
	public static TextureRegion alpha(int x, int y, boolean isUpper){
		if(isUpper){
			if(x == 2 && ( y == 1 || y == 5)){
				upperCase.setRegion(x * 84 + 16, y * 128, 84 - 32, 128);
			}else{
				upperCase.setRegion(x * 84, y * 128, 84, 128);
			}
			return upperCase;
		}

		if(x == 2 && ( y == 1 || y == 5)){
			lowerCase.setRegion(x * 84 + 16, y * 128, 84 - 32, 128);
		}else{
			lowerCase.setRegion(x * 84 + 8, y * 128, 84 - 16, 128);
		}
		return lowerCase; 
	}
	
	private static TextureRegion number(int row, int column){
		int cellWidth = numbersImage.getWidth()/4;
		int cellHeight = numbersImage.getHeight()/4;
		numbers.setRegion(column * cellWidth, row * cellHeight, cellWidth, cellHeight);
		return numbers;
	}
	
	public static TextureRegion objective(){
		int cellHeight = numbersImage.getHeight()/4;
		numbers.setRegion(0, 3 * cellHeight, numbersImage.getWidth(), cellHeight);
		return numbers;
	}
	
	public static TextureRegion alpha(int x, int y, boolean isUpper, boolean notText){
		int height = notText ? 90 : 128;
		if(isUpper){
			if(x == 2 && ( y == 1 || y == 5)){
				upperCase.setRegion(x * 84 + 16, y * 128, 84 - 32, height);
			}else{
				upperCase.setRegion(x * 84, y * 128, 84, height);
			}
			return upperCase;
		}
		
		if(x == 2 && ( y == 1 || y == 5)){
			lowerCase.setRegion(x * 84 + 16, y * 128, 84 - 32, height);
		}else{
			lowerCase.setRegion(x * 84 + 8, y * 128, 84 - 16, height);
		}
		return lowerCase; 
	}
	
	public static Texture tornPaper(){ return tornPaper; }
	public static TextureRegion logo(){ return logo; }
	public static TextureRegion launcher(){ return new TextureRegion(launcher); }
	public static TextureRegion snakeSad(){
		TextureRegion init = new TextureRegion(snakeSad);
		init.setRegion(8, 0, snakeSad.getWidth() - 16, snakeSad.getHeight());
		return init;
	}
	
	public static TextureRegion levelZeroStar(){
		levels.setRegion(0, 0, levelsImage.getWidth()/2, levelsImage.getHeight()/2);
		return levels;
	}
	
	public static TextureRegion levelOneStar(){
		levels.setRegion(levelsImage.getWidth()/2, 0, levelsImage.getWidth()/2, levelsImage.getHeight()/2);
		return levels;
	}
	
	public static TextureRegion levelTwoStar(){
		levels.setRegion(0, levelsImage.getHeight()/2, levelsImage.getWidth()/2, levelsImage.getHeight()/2);
		return levels;
	}
	
	public static TextureRegion levelThreeStar(){
		levels.setRegion(levelsImage.getWidth()/2, levelsImage.getHeight()/2, levelsImage.getWidth()/2, levelsImage.getHeight()/2);
		return levels;
	}
	
	public static TextureRegion levelLocked(){
		levelStatus.setRegion(levelStatusImage.getWidth()/4, levelStatusImage.getHeight()/2, levelStatusImage.getWidth()/2, levelStatusImage.getHeight()/2);
		return levelStatus;
	}
	
	public static TextureRegion prev(){
		levelStatus.setRegion((levelStatusImage.getWidth()/4) * 3, (levelStatusImage.getHeight()/4) * 3, levelStatusImage.getWidth()/4, levelStatusImage.getHeight()/4);
		return levelStatus;
	}
	
	public static TextureRegion next(){
		levelStatus.setRegion((levelStatusImage.getWidth()/4) * 3, levelStatusImage.getHeight()/2, levelStatusImage.getWidth()/4, levelStatusImage.getHeight()/4);
		return levelStatus;
	}
	
	public static TextureRegion line(int row, int column){
		int cellWidth = linesImage.getWidth()/4;
		int cellHeight = linesImage.getHeight()/4;
		lines.setRegion(cellWidth * column, cellHeight * row, cellWidth, cellHeight);
		return lines;
	}
	
	@Override
	public void dispose(){
		snake.dispose();
		paper.dispose();
		levelIconImage.dispose();
		statusImage.dispose();
		upperCaseImage.dispose();
		lowerCaseImage.dispose();
		tornPaper.dispose();
		logoImage.dispose();
		snakeSad.dispose();
		launcher.dispose();
		levelsImage.dispose();
		levelStatusImage.dispose();
		linesImage.dispose();
	}
}
