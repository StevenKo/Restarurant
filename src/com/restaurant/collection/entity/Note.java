package com.restaurant.collection.entity;

public class Note {
	
	int id;
	int restaurant_id;
	String title;
    String author;
    String pic_url;
    String pub_date;
    String link;
    
    double	x_lat;
    double	y_long;
    
    public Note(){
    	this(1,1,"","","","","http://www.wretch.cc/blog/yui888888888/7030513",0,0);
    }
    
    public Note(int id, int restaurant_id,String title, String author, String pic_url, String pub_date, String link, double x_lat, double y_long){
    	this.id = id;
    	this.restaurant_id = restaurant_id;
    	this.title = title;
    	this.author = author;
    	this.pic_url = pic_url;
    	this.pub_date = pub_date;
    	this.link = link;
    	this.x_lat = x_lat;
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
    
    public String getAuthor(){
    	return author;
    }
    
    public String getPubDate(){
    	return pub_date;
    }
    
    public String getLink(){
    	return link;
    }
    
    public Double getX(){
    	return x_lat;
    }
    
    public Double getY(){
    	return y_long;
    }
}
