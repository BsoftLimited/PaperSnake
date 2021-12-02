package com.bsoft.papersnake.Objects.UIs.Buttons;

import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.Objects.UIs.*;

public class NextButton extends ImageButton{
	public NextButton(OnClickListener listener){
		super(listener, new Image(TextureLoader.next()), 0, 0, 50, 50);
	}
}
