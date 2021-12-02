package com.bsoft.papersnake.Objects.UIs.PopUps;

import com.bsoft.papersnake.Objects.*;
import com.bsoft.papersnake.Utils.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Objects.UIs.Buttons.*;
import com.bsoft.papersnake.Objects.UIs.*;
import com.bsoft.papersnake.Objects.UIs.PopUps.*;
import com.bsoft.papersnake.Objects.UIs.Containers.*;

public class HelpPopUp extends PopUp {
	private RelativeContainer container;
	
	public HelpPopUp(Button.OnClickListener listener,  String object){
		ResumeButton pause = new ResumeButton(listener, 80, 80);
		pause.setMargin(0);
		LinearContainer pauseLayout = new LinearContainer(LinearContainer.Orientation.Vertical, true);
		pauseLayout.addChildren(pause, new Font("Resume").setSize(22));
		
		RestartButton restart = new RestartButton(listener, 80, 80);
		restart.setMargin(0);
		LinearContainer restartLayout = new LinearContainer(LinearContainer.Orientation.Vertical, true);
		restartLayout.addChildren(restart, new Font("Restart").setSize(22));
		
		Font message = new Font(object).setSize(18);
		message.setPadding(5);
		
		container = new RelativeContainer(this.getWidth() - padding.getRight() - padding.getLeft(), this.getHeight() - padding.getUp() - padding.getDown());
		container.setPosition(this.getX() - padding.getLeft(), this.getY() - padding.getDown());
		container.setPadding(10);
		container.addChildren(new Font("OBJECTIVE").setSize(26), RelativeContainer.Dock.Top);
		container.addChildren(message, RelativeContainer.Dock.Center);
		container.addChildren(pauseLayout, RelativeContainer.Dock.BottomLeft);
		container.addChildren(restartLayout, RelativeContainer.Dock.BottomRight);
	}
	

	@Override
	protected void render(SpriteBatch batch, ShapeRenderer renderer, float x, float y, float width, float height){
		super.render(batch, renderer, x, y, width, height);
		container.draw(batch, renderer);
	}
	
	@Override
	public void enableTouch()
	{
		container.enableTouch();
	}

	@Override
	public void disableTouch()
	{
		container.disableTouch();
	}

	@Override
	public void dispose(){
		this.container.dispose();
	}
}
