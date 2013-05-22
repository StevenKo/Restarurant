package com.restaurant.collection.entity;

public class Note {
	
	int id;
	int restaurant_id;
	String title;
    String intro;
    String pic_url;
    String link;
    double	x_lan;
    double	y_long;
    
    public Note(){
    	this(1,1,"","","","http://www.wretch.cc/blog/yui888888888/7030513",0,0);
    }
    
    public Note(int id, int restaurant_id,String title, String intro, String pic_url, String link, double x_lan, double y_long){
    	this.id = id;
    	this.restaurant_id = restaurant_id;
    	this.title = title;
    	this.intro = intro;
    	this.pic_url = pic_url;
    	this.link = link;
    	this.x_lan = x_lan;
    	this.y_long = y_long;
    }
    
    public int getId(){
    	return id;
    }
    
    public int getRestaurantId(){
    	return restaurant_id;
    }
    public String getPicUrl(){
    	return pic_url;
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
    
    public Double getX(){
    	return x_lan;
    }
    
    public Double getY(){
    	return y_long;
    }
}
