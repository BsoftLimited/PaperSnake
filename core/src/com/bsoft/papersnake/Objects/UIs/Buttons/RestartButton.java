package com.bsoft.papersnake.Objects.UIs.Buttons;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Utils.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bsoft.papersnake.Objects.UIs.*;

public class RestartButton extends Button{
	public RestartButton(OnClickListener listener){
		this(listener, 50, 50);
	}

	public RestartButton(OnClickListener listener, float width, float height){
		super(listener, new Image(TextureLoader.restart()), 0, 0, width, height);
	}
}
