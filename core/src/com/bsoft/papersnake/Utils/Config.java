package com.bsoft.papersnake.Utils;

public class Config{
	private static boolean colored;
	
	public Config(){
		colored = false;
	}
	
	public void toggleColor(){ colored = colored ? false : true; }
	public static boolean isColored(){ return colored; }
}
