package com.bsoft.papersnake.Utils;

import com.bsoft.papersnake.Utils.Data.DataObject;

import java.io.Serializable;

public class SaveData extends DataObject{
	public static class LevelData extends DataObject{
		
		public LevelData(String level, int stars, int points, boolean isLocked) throws DataException{
			this.put("level", level);
			this.put("stars", stars);
			this.put("points", points);
			this.put("isLocked", isLocked);
		}
		
		public LevelData(String level, boolean isLocked) throws DataException{
			this.put("level", level);
			this.put("stars", 0);
			this.put("points", 0);
			this.put("isLocked", isLocked);
		}
		
		public LevelData(String data)throws DataException{ super(data); }
		
		public String getLevel() throws DataException{ return getString("level"); }
		public boolean isLocked() throws DataException{ return getBoolean("isLocked"); }
		public int getPoints() throws DataException{ return getInt("points"); }
		public int stars() throws DataException{ return getInt("stars"); }
		
		public void unLock() throws DataException{
			this.remove("isLocked");
			this.put("isLocked", false);
		}
		
		public void updatePoints(int points) throws DataException{
			if(this.getPoints() < points){
				this.remove("points");
				this.put("points", points);
			}
		}
		
		public void updateStar(int stars) throws DataException{
			if(this.stars() < stars){
				this.remove("stars");
				this.put("stars", stars);
			}
		}
	}
	
	public static class StageData extends DataObject{
		public StageData(int stage) throws DataException{
			this.put("stage", stage);
		}
		
		public int getStage() throws DataException{ return getInt("stage"); }
		
		public void add(LevelData data) throws DataException{
			if(has(data.getLevel())){
				this.remove(data.getLevel());
			}
			this.put(data.getLevel(), data);
		}
		
		public boolean hasLevelData(LevelData data) throws DataException{
			return has(data.getLevel());
		}
		
		public LevelData getLevelData(String level) throws DataException{
			if(has(level)){
				return (LevelData)getDataObject(level);
			}
			LevelData init = new LevelData(level, true);
			this.put(level, init);
			return init;
		}
	}
	
	private static SaveData saveData;
	
	private SaveData() throws DataException{
		LevelData data = this.getLevelData(1, "1");
		data.unLock();
	}
	
	private SaveData(String data) throws DataException{
		super(data);
	}
	
	public void add(StageData data) throws DataException{
		if(has(Integer.toString(data.getStage()))){
			this.remove(Integer.toString(data.getStage()));
		}
		this.put(Integer.toString(data.getStage()), data);
	}
	
	public StageData getStageData(int stage) throws DataException{
		if(has(Integer.toString(stage))){
			return (StageData)getDataObject(Integer.toString(stage));
		}
		StageData init = new StageData(stage);
		this.put(Integer.toString(stage), init);
		return init;
	}

	public LevelData getLevelData(int stage, String level) throws DataException{
		StageData data = this.getStageData(stage);
		return data.getLevelData(level);
	}
	
	public static SaveData getData() throws DataException{
		if(saveData == null){
			saveData = new SaveData();
		}
		return saveData;
	}
}
