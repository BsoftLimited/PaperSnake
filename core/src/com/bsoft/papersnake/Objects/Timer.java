package com.bsoft.papersnake.Objects;

import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Utils.*;

public class Timer extends StatusObject implements Time.SecondsListener{
	public static interface TimerListener{
		void onTimerStoped();
	}
	
	private int seconds, init, counter;
	private boolean countDown;
	private TimerListener listener;
	
	public Timer(int seconds, boolean countDown){
		this.seconds = seconds;
		this.countDown = countDown;
		this.counter = 0;
		if(!this.countDown){
			this.init = 0;
		}
	}
	
	public void setListener(TimerListener listener){
		this.listener = listener;
	}
	
	@Override
	public int getWidth(){ return 140; }
	
	@Override
	public int getHeight(){ return 80; }

	@Override
	public void onTick(){
		if(++counter >= 10){
			if(countDown && seconds > 0){
				if(--seconds <= 0 && listener != null){
					listener.onTimerStoped();
				}
			}else if(init < seconds){
				init += 1;
				if(++init >= seconds && listener != null){
					listener.onTimerStoped();
				}
			}
			counter = 0;
		}
		
	}
	
	@Override
	protected void print(SpriteBatch batch){
		int minute = countDown ?  this.seconds / 60 : this.init / 60;
		int seconds = countDown ?  this.seconds % 60 : this.init % 60;
		float x = this.x + 10;
		float y = this.y + 10;
		batch.draw(TextureLoader.time(), x + 10, y + 24, 100, 30);
		this.printCell(batch, minute / 10, x + 7, y );
		this.printCell(batch, minute % 10, x + 31, y );
		batch.draw(TextureLoader.column(), x + 55, y , 10, 24);
		this.printCell(batch, seconds / 10, x + 65, y);
		this.printCell(batch, seconds % 10, x + 89, y);
	}

	@Override
	public String toString(){
		int minute = countDown ?  this.seconds / 60 : this.init / 60;
		int seconds = countDown ?  this.seconds % 60 : this.init % 60;
		String minStr = minute < 10 ? ("0" + Integer.toString(minute)) : Integer.toString(minute);
		String secStr = seconds < 10 ? ("0" + Integer.toString(seconds)) : Integer.toString(seconds);
		return new StringBuilder().append("Time: ").append(minStr).append(" : ").append(secStr).toString();
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
	public void dispose(){}
}
