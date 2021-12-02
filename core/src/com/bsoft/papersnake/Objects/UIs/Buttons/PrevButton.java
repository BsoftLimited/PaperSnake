package com.bsoft.papersnake.Objects.UIs.Buttons;

import com.bsoft.papersnake.Utils.*;
import com.bsoft.papersnake.Objects.UIs.*;

public class PrevButton extends ImageButton{
	public PrevButton(OnClickListener listener){
		super(listener, new Image(TextureLoader.prev()), 0, 0, 50, 50);
	}
}
