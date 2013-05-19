package com.restaurant.collection.entity;

public class Note {
	
	int id;
	String title;
    String intro;
    String pic_url;
    String link;
    
    public Note(){
    	this(1,"","","","");
    }
    
    public Note(int id, String title, String intro, String pic_url, String link){
    	this.id = id;
    	this.title = title;
    	this.intro = intro;
    	this.pic_url = pic_url;
    	this.link = link;
    }
    
    public int getId(){
    	return id;
    }
    
    public String getTitle(){
    	return title;
    }
    
    public String getIntro(){
    	return intro;
    }
    
    public String getLink(){
    	return link;
    }
}
