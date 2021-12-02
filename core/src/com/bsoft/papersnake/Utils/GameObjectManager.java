package com.bsoft.papersnake.Utils;

import java.util.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bsoft.papersnake.Objects.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.*;

public class GameObjectManager  implements Disposable{
	List<GameObject2D> objects2D;
	List<GameObject3D> objects3D;
	Camera camera;
	
	public GameObjectManager(){
		objects3D = new ArrayList<>();
		objects2D = new ArrayList<>();
	}
	
	public GameObjectManager(Camera camera){
		this.camera = camera;
	}
	
	public void setCamera(PerspectiveCamera camera){
		this.camera = camera;
	}
	
	public void addObject(GameObject... objects){
		for(GameObject object : objects){
			if(object instanceof GameObject3D){
				this.objects3D.add((GameObject3D)object);
			}else{
				this.objects2D.add((GameObject2D)object);
			}
		}
	}
	
	public void removeObject(GameObject... objects){
		for(GameObject object : objects){
			if(object instanceof GameObject3D){
				this.objects3D.remove((GameObject3D)object);
			}else{
				this.objects2D.remove((GameObject2D)object);
			}
		}
	}
	
	public void draw(SpriteBatch batch, ShapeRenderer renderer){
		for(GameObject2D object : objects2D){
				object.draw(batch, renderer);
		}
	}
	
	public void draw(ModelBatch batch, ShapeRenderer renderer){
		for(GameObject3D object : objects3D){
				object.draw(batch, renderer);
		}
	}
	
	public void enableTouch(){
		for(GameObject object : objects3D){
			object.enableTouch();
		}
		for(GameObject object : objects2D){
			object.enableTouch();
		}
	}

	public void disableTouch(){
		for(GameObject object : objects2D){
			object.disableTouch();
		}
		for(GameObject object : objects3D){
			object.disableTouch();
		}
	}

	@Override
	public void dispose(){
		for(GameObject object : objects2D){
			object.dispose();
		}
		for(GameObject object : objects3D){
			object.dispose();
		}
	}
}
