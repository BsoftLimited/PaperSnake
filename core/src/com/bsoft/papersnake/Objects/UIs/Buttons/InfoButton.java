package com.bsoft.papersnake.Objects.UIs.Buttons;

import com.bsoft.papersnake.Utils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bsoft.papersnake.Objects.UIs.*;

public class InfoButton extends Button{
	public InfoButton(OnClickListener listener){
		super(listener, new Image(TextureLoader.info()), 0, 0, 50, 50);
	}
}
