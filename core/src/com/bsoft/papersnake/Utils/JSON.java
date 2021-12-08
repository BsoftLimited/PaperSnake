package com.bsoft.papersnake.Utils;

import java.io.Serializable;

public abstract class JSON<T> implements Serializable{
	private static enum TokenType{ String, Number, Boolean, OpenSquareBracket, OpenCurlyBracket, CloseSquareBracket, CloseCurlyBracket, DoubleQuote, SingleQuote, Coma, Colon, End}
	public static class JSONException extends Exception{
		public JSONException(String message){ super(message); }
	}
	private static class Token{
		TokenType type; Object value;
		public Token(TokenType type, Object value){
			this.type = type; this.value = value;
		}

		@Override
		public String toString(){ return "Type: " + type + " value: " + value; }
	}

	private static class StringToken extends Token{
		private Token open, close;
		public StringToken(Token open, Object value, Token cloase){
			super(TokenType.String, value);
			this.open = open;
			this.close = cloase;
		}

		public String getString(){ return value.toString(); }

		@Override
		public String toString(){
			return "Type: String value: " + open + " " + value + " " + close;
		}
	}

	public static class JSONLexer{
		String data;
		char current;
		int index;

		public JSONLexer(String data){
			this.data = data;
			this.index = 0;
		}

		public boolean hasNext(){
			while(index < this.data.length()){
				current = this.data.charAt(index);
				boolean passable = Character.isSpace(current) || current == '\n' || current == '\t';
				if(!passable){ return true; }
				index++;
			}
			return false;
		}

		private char pop(){
			return this.data.charAt(index++);
		}

		public Token getNextToken() throws JSONException{
			if(Character.isAlphabetic(current)){
				return this.getBooleanToken();
			}else if(Character.isDigit(current)){
				return this.getNumberToken();
			}else{
				switch(current){
					case '{':
						return new Token(TokenType.OpenCurlyBracket, this.pop());
					case '}':
						return new Token(TokenType.CloseCurlyBracket, this.pop());
					case '[':
						return new Token(TokenType.OpenSquareBracket, this.pop());
					case ']':
						return new Token(TokenType.CloseSquareBracket, this.pop());
					case '"':
					case '\'':
						return this.getStringToken();
					case ',':
						return new Token(TokenType.Coma, this.pop());
					case ':':
						return new Token(TokenType.Colon, this.pop());
				}
				throw new JSONException("Unexpected token: " + this.pop());
			}
		}

		private Token getStringToken() throws JSONException{
			Token init = new Token(current == '"' ? TokenType.DoubleQuote : TokenType.SingleQuote, this.pop());
			StringBuilder builder = new StringBuilder();
			while(index < data.length()){
				if( this.data.charAt(index) == '"' && init.type == TokenType.DoubleQuote){
					return new StringToken(init, builder.toString(), new Token(TokenType.DoubleQuote, this.pop()));
				}else if( this.data.charAt(index) == '\'' && init.type == TokenType.SingleQuote){
					return new StringToken(init, builder.toString(), new Token(TokenType.SingleQuote, this.pop()));
				}else{
					builder.append(this.pop());
				}
			}
			throw new JSONException("Unexpected end of token, expecting " + (init.type == TokenType.DoubleQuote ? '"' : '\'') + " after " + builder.toString());
		}

		private Token getNumberToken() throws JSONException{
			StringBuilder builder = new StringBuilder().append(this.pop());
			while(hasNext() && (Character.isDigit(current) || current == '.')){
				builder.append(this.pop());
			}
			try{
				return new Token( TokenType.Number,  Double.parseDouble(builder.toString()));
			}catch(Exception ex){
				throw new JSONException("Invalid token: " + builder.toString());
			}
		}

		private Token getBooleanToken() throws JSONException{
			StringBuilder builder = new StringBuilder().append(this.pop());
			while(hasNext() && Character.isAlphabetic(current)){
				builder.append(this.pop());
			}
			if(builder.toString().equals("true") || builder.toString().equals("false")){
				return new Token(TokenType.Boolean, builder.toString().equals("true") ? true : false );
			}
			throw new JSONException("Wrap token " + builder.toString() + " inside two \" or '");
		}
	}


	private static class JSONParser{
		private JSON json;
		private JSONLexer lexer;

		private JSONParser(String data) throws JSONException{
			lexer = new JSONLexer(data);
			Object init = this.check();
			if(init instanceof JSONObject || init instanceof JSONArray){
				this.json = (JSON)init;
			}else{
				throw new JSONException("Invalid start to a json object" + init);
			}
		}

		private JSONParser(JSON json, String data) throws JSONException{
			lexer = new JSONLexer(data);
			if(lexer.hasNext()){
				Token token = lexer.getNextToken();
				boolean isOk = false;
				if(token.type == TokenType.OpenCurlyBracket && json instanceof JSONObject){
					JSONObject initObject = (JSONObject)json;
					while(this.lexer.hasNext()){
						Token init = this.match(TokenType.CloseCurlyBracket, TokenType.String, TokenType.Coma);
						if(init.type == TokenType.CloseCurlyBracket){
							isOk = true;
							break;
						}else if((init.type == TokenType.String && initObject.size()  == 0) || (initObject.size() > 0 && init.type == TokenType.Coma)){
							StringToken key = (StringToken) (init.type == TokenType.Coma ? this.match(TokenType.String): init);
							this.match(TokenType.Colon);
							initObject.put(key.getString(), this.check());
						}else{
							throw new JSONException("Unexpected end of object definition, expecting }");
						}
					}
					if(!isOk){ throw new JSONException("Unexpected end of object definition, expecting }"); }
				}else if(token.type == TokenType.OpenSquareBracket && json instanceof JSONArray){
					JSONArray initArray = (JSONArray)json;
					while(this.lexer.hasNext()){
						Token init = this.lexer.getNextToken();
						if(init.type == TokenType.CloseSquareBracket){
							isOk = true;
							break;
						}else if(init.type == TokenType.Coma && initArray.size() > 0){
							initArray.put(this.check());
						}else if(initArray.size() == 0){
							initArray.put(this.check(init));
						}else{
							throw new JSONException("Unexpected end of object definition, expecting ]");
						}
					}
					if(!isOk){ throw new JSONException("Unexpected end of object definition, expecting ]"); }
				}else{
					throw new JSONException("wrong string format");
				}
			}
		}

		private Object check(Token current)throws JSONException{
			switch(current.type){
				case OpenSquareBracket:
					return this.getArray();
				case OpenCurlyBracket:
					return this.getObject();
				case String:
					return ((StringToken)current).getString();
				case Number:
				case Boolean:
					return current.value;
				default:
					throw new JSONException("Unexpected token found: " + current.value);
			}

		}

		private Object check()throws JSONException{
			if(lexer.hasNext()){
				return this.check(lexer.getNextToken());
			}
			throw new JSONException("Unexpected end of JSON object");
		}

		private Token match(TokenType... types) throws JSONException{
			if(this.lexer.hasNext()){
				Token init = this.lexer.getNextToken();
				for(TokenType type : types){
					if(init.type == type){ return init; }
				}
				throw new JSONException("encountered an unexpect token " + init.type + " with value " + init.value);
			}
			throw new JSONException("unexpected end of JSON definition");
		}

		private JSONObject getObject()throws JSONException{
			JSONObject initObject = new JSONObject();
			while(this.lexer.hasNext()){
				Token init = this.match(TokenType.CloseCurlyBracket, TokenType.String, TokenType.Coma);
				if(init.type == TokenType.CloseCurlyBracket){
					return initObject;
				}else if((init.type == TokenType.String && initObject.size()  == 0) || (initObject.size() > 0 && init.type == TokenType.Coma)){
					StringToken key = (StringToken) (init.type == TokenType.Coma ? this.match(TokenType.String): init);
					this.match(TokenType.Colon);
					initObject.put(key.getString(), this.check());
				}else{
					throw new JSONException("Unexpected end of object definition, expecting }");
				}
			}
			throw new JSONException("Unexpected end of object definition, expecting }");
		}

		private JSONArray getArray()throws JSONException{
			JSONArray initArray = new JSONArray();
			while(this.lexer.hasNext()){
				Token init = this.lexer.getNextToken();
				if(init.type == TokenType.CloseSquareBracket){
					return initArray;
				}else if(init.type == TokenType.Coma && initArray.size() > 0){
					initArray.put(this.check());
				}else if(initArray.size() == 0){
					initArray.put(this.check(init));
				}else{
					throw new JSONException("Unexpected end of object definition, expecting ]");
				}
			}
			throw new JSONException("Unexpected end of array definition, expecting ]");
		}
	}

	public abstract Object get(T value) throws JSONException;
	public abstract boolean remove(T value);
	public abstract boolean has(T value);
	public abstract int size();
	public abstract void clear();
	public abstract boolean isEmpty();

	public Boolean getBoolean(T key) throws JSONException {
		Object init = this.get(key);
		if(init instanceof Boolean) {
			return (Boolean) init;
		}
		throw new JSONException("the value is not of type Boolean");
	}

	public Integer getInt(T key) throws JSONException{
		Object init = this.get(key);
		if(init instanceof Double) {
			String value = init.toString();
			return Integer.parseInt(value.substring(0, value.indexOf('0')));
		}
		throw new JSONException("the value is not of type Integer");
	}

	public Float getFloat(T key) throws JSONException{
		Object init = this.get(key);
		if(init instanceof Double) {
			return Float.parseFloat(init.toString());
		}
		throw new JSONException("the value is not of type Float");
	}

	public Double getDouble(T key) throws JSONException {
		Object init = this.get(key);
		if(init instanceof Double) {
			return (Double) init;
		}
		throw new JSONException("the value is not of type Double");
	}

	public String getString(T key) throws JSONException{
		Object init = this.get(key);
		if(init instanceof String) {
			return (String) init;
		}
		throw new JSONException("the value is not of type String");
	}

	public JSONObject getDataObject(T key) throws JSONException {
		Object init = this.get(key);
		if(init instanceof JSONObject) {
			return (JSONObject) init;
		}
		throw new JSONException("the value is not of type JSON Object");
	}

	public JSONArray getDataArray(T key) throws JSONException {
		Object init = this.get(key);
		if(init instanceof JSONArray) {
			return (JSONArray) init;
		}
		throw new JSONException("the value is not of type JSON Array");
	}

	public static JSON parse(String data)throws JSONException{
		return new JSONParser(data).json;
	}

	protected static void parse(JSON json, String data)throws JSONException{
		new JSONParser(json, data);
	}
}