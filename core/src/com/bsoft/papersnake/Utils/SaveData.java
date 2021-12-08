package com.bsoft.papersnake.Utils;

import com.bsoft.papersnake.Utils.JSONObject;

public class SaveData extends JSONObject{
	public static class LevelData extends JSONObject{
		
		public LevelData(String level, int stars, int points, boolean isLocked) throws JSONException{
			this.put("level", level);
			this.put("stars", stars);
			this.put("points", points);
			this.put("isLocked", isLocked);
		}
		
		public LevelData(String level, boolean isLocked) throws JSONException{
			this.put("level", level);
			this.put("stars", 0);
			this.put("points", 0);
			this.put("isLocked", isLocked);
		}
		
		/*public LevelData(String data)throws JSONException{
		}*/
		
		public String getLevel() throws JSONException{ return getString("level"); }
		public boolean isLocked() throws JSONException{ return getBoolean("isLocked"); }
		public int getPoints() throws JSONException{ return getInt("points"); }
		public int stars() throws JSONException{ return getInt("stars"); }
		
		public void unLock() throws JSONException{
			this.remove("isLocked");
			this.put("isLocked", false);
		}
		
		public void updatePoints(int points) throws JSONException{
			if(this.getPoints() < points){
				this.remove("points");
				this.put("points", points);
			}
		}
		
		public void updateStar(int stars) throws JSONException{
			if(this.stars() < stars){
				this.remove("stars");
				this.put("stars", stars);
			}
		}
	}
	
	public static class StageData extends JSONObject{
		public StageData(int stage) throws JSONException{
			this.put("stage", stage);
		}
		
		public int getStage() throws JSONException{ return getInt("stage"); }
		
		public void add(LevelData data) throws JSONException{
			if(has(data.getLevel())){
				this.remove(data.getLevel());
			}
			this.put(data.getLevel(), data);
		}
		
		public boolean hasLevelData(LevelData data) throws JSONException{
			return has(data.getLevel());
		}
		
		public LevelData getLevelData(String level) throws JSONException{
			if(has(level)){
				return (LevelData)getDataObject(level);
			}
			LevelData init = new LevelData(level, true);
			this.put(level, init);
			return init;
		}
	}
	
	private static SaveData saveData;
	
	private SaveData() throws JSONException{
		LevelData data = this.getLevelData(1, "1");
		data.unLock();
	}
	
	private SaveData(String data) throws JSONException{

	}
	
	public void add(StageData data) throws JSONException{
		if(has(Integer.toString(data.getStage()))){
			this.remove(Integer.toString(data.getStage()));
		}
		this.put(Integer.toString(data.getStage()), data);
	}
	
	public StageData getStageData(int stage) throws JSONException{
		if(has(Integer.toString(stage))){
			return (StageData)getDataObject(Integer.toString(stage));
		}
		StageData init = new StageData(stage);
		this.put(Integer.toString(stage), init);
		return init;
	}

	public LevelData getLevelData(int stage, String level) throws JSONException{
		StageData data = this.getStageData(stage);
		return data.getLevelData(level);
	}
	
	public static SaveData getData() throws JSONException{
		if(saveData == null){
			saveData = new SaveData();
		}
		return saveData;
	}
}
