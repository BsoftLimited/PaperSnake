package com.bsoft.papersnake.Objects.UIs.Buttons;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.Objects.UIs.*;

public class ResumeButton extends ImageButton{
	public ResumeButton(OnClickListener listener, float widh, float height){
		super(listener, new Image(TextureLoader.resume()), 0, 0, widh, height);
	}
}
