package com.bsoft.papersnake.Utils;

import java.util.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.*;
import com.bsoft.papersnake.*;

public class Time{
	public static interface SecondsListener{
		void onTick();
	}
	
	private List<SecondsListener> secondsListeners;
	float deltaTime;
	boolean isStarted, stoppped, isPaused;
	float seconds;
	
	public Time(float seconds){
		secondsListeners = new ArrayList<>();
		isStarted = false;
		stoppped = false;
		isPaused = false;
		this.seconds = seconds;
	}
	
	public Time(){ this(1.0f); }
	
	public void start(){
		if(!isStarted){
			deltaTime = 0;
			isStarted = true;
		}
	}
	
	public void stop(){ stoppped = true; }
	
	public void update(){
		if(isStarted && !stoppped && !isPaused){
			deltaTime += Gdx.graphics.getDeltaTime();
			if(deltaTime >= seconds){
				for(SecondsListener listener : secondsListeners){
					listener.onTick();
				}
				deltaTime -= seconds;
			}
		}
	}
	
	public boolean isPaused(){ return isPaused; }
	public boolean hasStoped(){ return stoppped; }
	public void pause(){ isPaused = true; }
	public void resume(){ isPaused = false; }
	
	public void addListener(SecondsListener... listeners){
		secondsListeners.addAll(Arrays.asList( listeners));
	}
	
	public void removeListener(SecondsListener... listeners){
		secondsListeners.removeAll(Arrays.asList( listeners));
	}
}
