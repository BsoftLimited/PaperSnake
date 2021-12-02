package com.bsoft.papersnake.Objects;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.glutils.*;

public abstract class GameObject3D extends GameObject
{
	protected Environment environment;
	public GameObject3D(Environment environment){
		this.environment = environment;
	}
	public abstract void draw(ModelBatch batch, ShapeRenderer renderer);
}
