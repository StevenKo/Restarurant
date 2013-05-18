package com.restaurant.collection.entity;

public class Category {
	int id;
	String floor;
	String name;
	String info;
	
	public Category(){
		this(1,"","","");
	}
	
	public Category(int id, String floor, String name, String info){
		this.id = id;
		this.floor = floor;
		this.name = name;
		this.info = info;
	}
	
	public int getId(){
		return id;
	}
	
	public String getFloor(){
		return floor;
	}
	
	public String getName(){
		return name;
	}
	
	public String getInfo(){
		return info;
	}
}
