package com.bsoft.papersnake.Screens;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.*;
import com.bsoft.papersnake.Objects.UIs.*;
import com.bsoft.papersnake.Objects.UIs.Containers.*;
import com.bsoft.papersnake.Objects.UIs.Buttons.*;
import com.bsoft.papersnake.Screens.Levels.*;

public class MenuScreen extends Screen implements Button.OnClickListener{
	
	public MenuScreen(PaperSnake game){
		super(game);
		Image logo = new Image(TextureLoader.logo());
		logo.setSize(420, 180);
		
		Image launcher = new Image(TextureLoader.launcher());
		launcher.setSize(PaperSnake.GameWidth, PaperSnake.GameHeight);
		
		LinearContainer menu = new LinearContainer(LinearContainer.Orientation.Vertical, true);
		menu.getPadding().setDown(165);
		this.addMenu(menu, "New Game", "Continue", "Time Attack", "Settings", "Exit");
		
		InfoButton info = new InfoButton(this);
		
		RelativeContainer root = new RelativeContainer(PaperSnake.GameWidth, PaperSnake.GameHeight);
		root.addChildren(logo, RelativeContainer.Dock.Top);
		root.addChildren(menu, RelativeContainer.Dock.Bottom);
		root.addChildren(info, RelativeContainer.Dock.TopRight);
		manager.addObject(launcher, root);
	}
	
	private void addMenu(LinearContainer parent, String... texts){
		TextButton[] init = new TextButton[texts.length];
		for(int i = 0; i < texts.length; i++){
			init[i] = new TextButton(this, new Font(texts[i]).setSize(24));
			init[i].setMargin(0);
			init[i].setPadding(0);
		}
		parent.addChildren(init);
	}

	@Override
	public void onClick(Button button){
		if(button instanceof TextButton){
			switch(button.toString()){
				case "New Game":
					game.setScreen(new LevelOne(game));
					break;
				case "Continue":
					game.setScreen(new SelectScreen(game));
					break;
				case "Settings":
					game.setScreen(new SettingsScreen(game));
					break;
				case "Exit":
					break;
			}
		}else if(button instanceof InfoButton){
			game.setScreen(new HelpScreen(game));
		}
	}

	@Override
	public void update(){
		
	}

	@Override
	public boolean back(){
		return false;
	}
}
