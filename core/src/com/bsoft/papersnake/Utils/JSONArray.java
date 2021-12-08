package com.bsoft.papersnake.Utils;

public class JSONArray extends JSON<Integer>{
    Object[] data;
    int size;
    public JSONArray(){ this.size = 0;}

    public JSONArray(String data) throws JSONException{
        this.size = 0;
        JSON.parse(this, data);
    }

    public void put(Object value){
        if(data == null){
            data = new Object[]{value};
        }else{
            Object[] init = new Object[data.length + 1];
            for(int i = 0; i < data.length; i++){
                init[i] = data[i];
            }
            init[data.length] = value;
            this.data = init;
        }
        size++;
    }

    public void clear(){
        this.data = null;  this.size = 0;
    }

    @Override
    public boolean isEmpty() { return this.size == 0; }

    @Override
    public Object get(Integer value) throws JSONException {
        if(value >= size){ throw new JSONException("Index out of bounds, Array if size " + size + " but " + value + " given"); }
        return data[value];
    }

    @Override
    public boolean remove(Integer value) {
        if(value < data.length){
            Object[] init = new Object[data.length - 1];
            for(int i = 0; i < init.length; i++){
                init[i] = i < value ? data[i] : data[i + 1];
            }
            this.data = init;
            this.size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean has(Integer value) {
        return value < size;
    }

    @Override
    public int size() { return size; }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder().append("[");
        for(int i = 0; i < this.data.length; i++){
            if(data[i] instanceof String){
                builder.append("'").append(this.data[i]).append("'");
            }else{
                builder.append(this.data[i].toString());
            }
            if(i < this.data.length - 1){ builder.append(", "); }
        }
        return builder.append("]").toString();
    }
}
