package com.restaurant.collection.entity;

public class Note {
	
	int id;
	int restaurant_id;
	String title;
    String intro;
    String pic_url;
    String link;
    
    public Note(){
    	this(1,1,"","","","http://www.wretch.cc/blog/yui888888888/7030513");
    }
    
    public Note(int id, int restaurant_id,String title, String intro, String pic_url, String link){
    	this.id = id;
    	this.restaurant_id = restaurant_id;
    	this.title = title;
    	this.intro = intro;
    	this.pic_url = pic_url;
    	this.link = link;
    }
    
    public int getId(){
    	return id;
    }
    
    public int getRestaurant(){
    	return restaurant_id;
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
