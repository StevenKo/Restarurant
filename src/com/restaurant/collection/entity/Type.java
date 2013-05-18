package com.restaurant.collection.entity;

public class Type {
	
	int id;
	String name;
	
	public Type(){
		this(1,"");
	}
	
	public Type(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
}
