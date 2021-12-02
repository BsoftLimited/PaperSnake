package com.bsoft.papersnake.Utils.Data;

import java.util.HashMap;

public class DataObject extends Data<String>{
    HashMap<String, Object> data;

    public DataObject(){ data = new HashMap<>(); }
    public DataObject(String value){
        data = new HashMap<>();
    }

    public void put(String key, Object value){ data.put(key, value); }

    @Override
    public Object get(String value) throws DataException {
        Object init =  data.get(value);
        if(init == null){ throw  new DataException("Error: value not found"); }
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
}
