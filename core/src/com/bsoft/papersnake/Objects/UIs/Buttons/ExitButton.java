package com.bsoft.papersnake.Objects.UIs.Buttons;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.Objects.UIs.*;

public class ExitButton extends ImageButton
{
	public ExitButton(OnClickListener listener){
		this(listener, 50, 50);
	}
	
	public ExitButton(OnClickListener listener, float width, float height){
		super(listener, new Image(TextureLoader.exit()), 0, 0, width, height);
	}
}
