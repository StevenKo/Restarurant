package com.restaurant.collection.entity;

public class Area {
	
	int id;
	String name;
	
	public Area(){
		this(1,"");
	}
	
	public Area(int id, String name){
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
