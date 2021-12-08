package com.bsoft.papersnake.Utils;

import java.util.HashMap;

public class JSONObject extends JSON<String>{
    HashMap<String, Object> data;

    public JSONObject(){ data = new HashMap<>(); }

    public JSONObject(String data) throws JSONException{
        this.data = new HashMap<>();
        JSON.parse(this, data);
    }

    public void put(String key, Object value){ data.put(key, value); }

    @Override
    public Object get(String value) throws JSONException {
        Object init =  data.get(value);
        if(init == null){ throw  new JSONException("Error: value not found"); }
        return init;
    }

    @Override
    public boolean remove(String value) {
        if(data.containsKey(value)){
            data.remove(value);
            return true;
        }
        return false;
    }

    @Override
    public boolean has(String value) {
        return data.containsKey(value);
    }

    @Override
    public int size() { return data.size(); }

    @Override
    public void clear() { data.clear(); }

    @Override
    public boolean isEmpty() { return data.isEmpty(); }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder().append("{");
        try{
            Object[] keys = this.data.keySet().toArray();
            for(int i = 0; i < keys.length; i++){
                builder.append("'").append(keys[i]).append("' : ");
                if(this.get(keys[i].toString()) instanceof String){
                    builder.append("'").append(this.get(keys[i].toString())).append("'");
                }else{
                    builder.append(this.get(keys[i].toString()).toString());
                }
                if(i < keys.length - 1){ builder.append(", "); }
            }
        }catch(JSONException ex){ ex.printStackTrace(); }
        return builder.append("}").toString();
    }
}
