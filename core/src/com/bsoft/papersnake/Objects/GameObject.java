package com.bsoft.papersnake.Objects;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.graphics.g2d.*;

public abstract class GameObject<T> implements Disposable{
	public abstract void enableTouch();
	public abstract void disableTouch();
}
