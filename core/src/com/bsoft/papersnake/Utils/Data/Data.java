package com.bsoft.papersnake.Utils.Data;

import java.io.Serializable;

public abstract class Data<T> implements Serializable {
    public static class DataException extends Exception{
        public DataException(String message){ super(message);}
    }

    public abstract Object get(T value) throws DataException;
    public abstract boolean remove(T value);
    public abstract boolean has(T value);
    public abstract int size();
    public abstract void clear();
    public abstract boolean isEmpty();

    public Boolean getBoolean(T key) throws DataException {
        Object init = this.get(key);
        if(init instanceof Boolean) {
            return (Boolean) init;
        }
        throw new DataException("the value is not of type Boolean");
    }

    public Integer getInt(T key) throws DataException {
        Object init = this.get(key);
        if(init instanceof Integer) {
            return (Integer) init;
        }
        throw new DataException("the value is not of type Integer");
    }

    public Float getFloat(T key) throws DataException {
        Object init = this.get(key);
        if(init instanceof Float) {
            return (Float) init;
        }
        throw new DataException("the value is not of type Float");
    }

    public Double getDouble(T key) throws DataException {
        Object init = this.get(key);
        if(init instanceof Double) {
            return (Double) init;
        }
        throw new DataException("the value is not of type Double");
    }

    public String getString(T key) throws DataException {
        Object init = this.get(key);
        if(init instanceof String) {
            return (String) init;
        }
        throw new DataException("the value is not of type String");
    }

    public DataObject getDataObject(T key) throws DataException {
        Object init = this.get(key);
        if(init instanceof DataObject) {
            return (DataObject) init;
        }
        throw new DataException("the value is not of type Data Object");
    }

    public DataArray getDataArray(T key) throws DataException {
        Object init = this.get(key);
        if(init instanceof DataArray) {
            return (DataArray) init;
        }
        throw new DataException("the value is not of type Data Array");
    }
}
