package com.bsoft.papersnake.Objects.UIs.PopUps;

import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.Objects.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Objects.UIs.Buttons.*;
import com.bsoft.papersnake.Objects.UIs.*;
import com.bsoft.papersnake.Objects.UIs.Containers.*;
import com.bsoft.papersnake.*;

public class DeadPopUp extends PopUp {
	private RelativeContainer container;
	private Score score;

	public DeadPopUp(Button.OnClickListener listener,  String mes){
		Font message = new Font(mes).setSize(25); 
		message.setMargin(0);
		message.setPadding(0);
		
		RestartButton restart = new RestartButton(listener, 80, 80);
		restart.setMargin(0);
		LinearContainer restartLayout = new LinearContainer(LinearContainer.Orientation.Vertical, true);
		restartLayout.addChildren(restart, new Font("Restart").setSize(22));
		
		ExitButton exit = new ExitButton(listener, 80, 80);
		exit.setMargin(0);
		LinearContainer exitLayout = new LinearContainer(LinearContainer.Orientation.Vertical, true);
		exitLayout.addChildren(exit, new Font(" Exit").setSize(22));
		
		Image sadSnake = new Image(TextureLoader.snakeSad());
		sadSnake.setMargin(0);
		sadSnake.setPadding(0);
		sadSnake.setSize(280, 280);
		RelativeContainer messageLayout = new RelativeContainer(300, 300);
		messageLayout.setPadding(0);
		messageLayout.addChildren(sadSnake);
		messageLayout.addChildren(message, RelativeContainer.Dock.Bottom);
		
		container = new RelativeContainer(this.getWidth() - padding.getRight() - padding.getLeft(), this.getHeight() - padding.getUp() - padding.getDown());
		container.setPadding(0, 10, 10, 10);
		container.setPosition(this.getX() - padding.getLeft(), this.getY() - padding.getDown());
		container.addChildren(messageLayout, RelativeContainer.Dock.Top);
		container.addChildren(restartLayout, RelativeContainer.Dock.BottomLeft);
		container.addChildren(exitLayout, RelativeContainer.Dock.BottomRight);
		
		score = new Score(1245);
		score.setHeight(80);
		score.setPosition(PaperSnake.GameWidth/ 2 - score.getWidth()/2, PaperSnake.GameHeight/2 - score.getHeight()/2);
	}

	@Override
	protected void render(SpriteBatch batch, ShapeRenderer renderer, float x, float y, float width, float height){
		super.render(batch, renderer, x, y, width, height);
		container.draw(batch, renderer);
		score.draw(batch, renderer);
	}

	@Override
	public void enableTouch(){
		container.enableTouch();
	}

	@Override
	public void disableTouch(){
		container.disableTouch();
	}
	
	
	@Override
	public void dispose(){
		container.dispose();
	}
}
