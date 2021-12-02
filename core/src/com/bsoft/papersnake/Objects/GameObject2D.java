package com.bsoft.papersnake.Objects;

import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;

public abstract class GameObject2D extends GameObject{
	public abstract void draw(SpriteBatch batch, ShapeRenderer renderer);
}
