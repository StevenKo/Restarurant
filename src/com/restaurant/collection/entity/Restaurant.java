package com.restaurant.collection.entity;

public class Restaurant {
	int id;
	String  name;
    String  grade_food;
    String  grade_service;
    String  pic_url;
    String  address;
    String  open_time;
    String  official_link;
    String  price;
    String  traffic;
    String  introduction;
    public Restaurant(){
    	this(1, "", "", "","","","","","","","");
    }
    public Restaurant(int id, String name, String grade_food, String grade_service, String pic_url, String address, String open_time, String official_link, String price, String traffic, String introduction ){
    	this.name = name;
    	this.grade_food = grade_food;
    	this.grade_service = grade_service;
    	this.pic_url = pic_url;
    	this.address = address;
    	this.open_time = open_time;
    	this.official_link = official_link;
    	this.price = price;
    	this.traffic = traffic;
    	this.introduction = introduction;
    }
    
    public int getId(){
    	return id;
    }
    
    public String getName(){
    	return name;
    }
    
    public String getGradeFood(){
    	return grade_food;
    }
    
    public String getGradeService(){
    	return grade_service;
    }
    
    public String getPicUrl(){
    	return pic_url;
    }
        
    public String getAddress(){
    	return address;
    }
    
    public String getOpenTime(){
    	return open_time;
    }
    
    public String getOfficailLink(){
    	return official_link;
    }
    
    public String getPrice(){
    	return price;
    }
    
    public String getTraffic(){
    	return traffic;
    }
    
    public String getIntroduction(){
    	return introduction;
    }
    

}
