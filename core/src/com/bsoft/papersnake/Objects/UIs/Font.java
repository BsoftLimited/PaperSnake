package com.bsoft.papersnake.Objects.UIs;

import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
import com.bsoft.papersnake.Utils.*;
import com.badlogic.gdx.graphics.*;
import com.bsoft.papersnake.Objects.UIs.*;

public class Font extends UI{
	private String[][] texts;
	private String original;
	private float size;
	private List<Word> words;
	private boolean hasFixedSize;
	
	public Font(String text, float x, float y, float width){
		super(x, y, 0, 0);
		this.original = text;
		this.texts = this.split(text);
		this.size = 20;
		this.setMargin(0);
		this.words = new ArrayList<>();
		super.setSize(width, 0);
		hasFixedSize = width > 0;
		this.init();
	}
	
	public Font(String text, float x, float y){
		this(text, x, y, -1);
	}
	
	public Font(String words){
		this(words, 0, 0, -1);
	}
	
	public Font setSize(float size){
		this.size = size;
		this.init();
		return this;
	}
	
	public float getSize(){ return size; }
	
	public Font setString(String words){
		this.texts = this.split(words);
		this.init();
		this.original = words;
		return this;
	}
	
	private String[][] split(String text){
		String[] init = text.split("\n");
		String[][] output = new String[init.length][];
		for(int i = 0; i < init.length; i++){
			output[i] = init[i].split(" ");
		}
		return output;
	}
	
	private void init(){
		words.clear();
		float x = this.boundary.getX() + this.padding.getLeft();
		float y = this.boundary.getY()  + this.padding.getDown();
		if(hasFixedSize){
			initFixed(x, y);
		}else{
			initExpand(x, y);
		}
	}
	
	private void initFixed(float startingX, float startingY){
		int totalInes = 0;
		for(int i = 0; i < texts.length; i++){
			float lineWidth = 0;
			for(int j = 0; j < texts[i].length; j++){
				Word word = new Word(texts[i][j], size);
				words.add(word);
				float init =  word.getWidth() + (j < texts[i].length - 1 ? (size * 0.6f) : 0);
				if(lineWidth + init > (boundary.getWidth() - padding.getRight() - padding.getLeft())){
					lineWidth = 0;
					totalInes += 1;
					word.setPosition(startingX + lineWidth, startingY - (totalInes * size * 1.5f));
					lineWidth += init;
				}else{
					word.setPosition(startingX + lineWidth, startingY - (totalInes * size * 1.5f));
					lineWidth += init;
				}
			}
			totalInes ++;
		}
		
		float height = totalInes * size * 1.5f;
		if(totalInes > 1){
			for(Word word : words){
				word.setPosition(word.getX(), word.getY() + (totalInes - 1) * size * 1.5f);
			}
		}
		super.setSize(this.getWidth(), height + padding.getDown() + padding.getUp());
	}
	
	
	private void initExpand(float startingX, float startingY){
		float height = texts.length * size * 1.5f, width = 0;
		for(int i = 0; i < texts.length; i++){
			float lineWidth = 0;
			for(int j = 0; j < texts[i].length; j++){
				Word word = new Word(texts[i][j], size);
				words.add(word);
				word.setPosition(startingX + lineWidth, startingY + height - ((i + 1) * size * 1.5f));
				lineWidth +=  word.getWidth() + (j < texts[i].length - 1 ? (size * 0.6f) : 0);
			}
			width = lineWidth > width ? lineWidth : width;
		}
		super.setSize(width + padding.getRight() + padding.getLeft(), height + padding.getDown() + padding.getUp());
	}

	@Override
	public void setPadding(Padding padding){
		super.setPadding(padding);
		this.init();
	}

	@Override
	public void setPadding(float up, float down, float right, float left){
		super.setPadding(up, down, right, left);
		this.init();
	}

	@Override
	public void setPosition(float x, float y){
		if(boundary.getX() != x || boundary.getY() != y){
			super.setPosition(x, y);
			this.init();
		}
	}
	
	public void setWidth(float width){
		boundary.setWidth(width);
		hasFixedSize = true;
		this.init();
	}

	@Override
	protected void render(SpriteBatch batch, ShapeRenderer renderer, float x, float y, float width, float height){
		batch.begin();
		for(Word init : words){
			init.draw(batch);
		}
		batch.end();
	}

	@Override
	public void enableTouch(){
		// TODO: Implement this method
	}

	@Override
	public void disableTouch(){
		// TODO: Implement this method
	}

	@Override
	public String toString(){ return original; }
	
	@Override
	public void dispose(){}
}
