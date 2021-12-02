package com.bsoft.papersnake.Utils;
import com.bsoft.papersnake.Screens.*;
import java.util.*;
import com.badlogic.gdx.utils.*;

public class ScreenManager implements Disposable{
	List<Screen> screens;
	public ScreenManager(Screen screen){
		screens = new ArrayList<>();
		screens.add(screen);
	}
	
	public void pushScreen(Screen screen){
		screens.get(screens.size() - 1).disable();
		screens.add(screen);
	}
	
	public void popScreen(){
		if(screens.size() > 1){
			screens.get(screens.size() - 1).dispose();
			screens.remove(screens.size() - 1);
			screens.get(screens.size() - 1).enable();
		}
	}
	
	public void toSelectScreen(){
		while(!(get() instanceof MenuScreen) && screens.size() > 1){
			this.popScreen();
		}
	}
	
	public Screen get() throws NullPointerException{
		if(screens.size() > 0){
			return screens.get(screens.size() - 1);
		}
		return null;
	}
	
	public boolean back() throws NullPointerException{
		boolean init = get().back();
		if(init ){
			return true;
		}else if(screens.size() > 1){
			this.popScreen();
			return true;
		}
		return false;
	}

	@Override
	public void dispose(){
		for(Screen screen : screens){
			screen.dispose();
		}
	}
}
