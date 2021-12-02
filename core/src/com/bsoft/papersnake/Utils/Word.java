package com.bsoft.papersnake.Utils;

import java.util.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Word{
	private float x, y, width;
	private float charSize;
	List<Char> chars;
	String word;

	public Word(String word){
		this(word, 20);
	}
	
	public Word(String word, float size){
		this.chars = new ArrayList<>();
		this.word = word;
		this.charSize = size;
		this.process();
	}
	
	private void process(){
		width = 0;
		chars.clear();
		for(char c : word.toCharArray()){
			Char init = this.getCharacter(c);
			width += this.getCharWidth(init);
			chars.add(init);
		}
	}
	
	private Char getCharacter(int c){
		if(c >= 65 && c <= 90){
			return get(c, 65, 90, true);
		}else if( c >= 97 && c <= 122){
			return get(c, 97, 122, false);
		}else if(c >= 48 && c <= 57){
			return get((c - 47) + 90,  65, 121, true);
		}else if( c == 32){
			return new Char(5 , 5, false);
		}else if(c == 58){
			return new Char( 2 , 4 , false);
		}
		return null;
	}

	private Char get(int c, int min, int max, boolean isCapital){
		int x = (c - min) % 6,  y = (c - min) / 6;
		return new Char(x, y, isCapital);
	}
	
	public void setSize(float size){ this.charSize = size; this.process(); }
	public void setPosition(float x, float y){ this.x = x;  this.y = y; }

	private  float getCharWidth(Char c){
		if(c.getRegionX() == 2 && ( c.getRegionY() == 1 || c.getRegionY() == 5)){
			return charSize - (charSize * (0.6f));
		}
		return c.isCapital() ?  charSize  : charSize - (charSize * 0.4f);
	}

	public float getWidth(){ return width; }
	public float getHeight(){ return charSize * 1.5f; }
	public float getX(){ return x; }
	public float getY(){ return y; }
	
	public void draw(SpriteBatch batch){
		float x = this.x;
		for(Char init : chars){
			init.draw(batch, x, getY(), this.getCharWidth(init), getHeight());
			x += this.getCharWidth(init);
		}
	}
}
