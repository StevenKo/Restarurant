package com.restaurant.collection.entity;

public class Restaurant {
	int id;
	String  name;
	String  pic_url;
    String  grade_food;
    String  grade_service;
    String  grade_ambiance;
    String  price;
    String  open_time;
    String  rest_date;
    String  address;
    String  phone;
    int  rate_num;
    String  introduction;
    String  official_link;
    String  recommand_dish;
    
    double	x_lat;
    double	y_long;
    String  dis;
    public Restaurant(){
    	this(1, "", "", "","","","","","","","", 0,"","","", 0, 0,"");
    }
    public Restaurant(int id, String name, String pic_url,String grade_food, String grade_service,  String grade_ambiance, String price,String open_time,
    		String rest_date, String address, String phone, int rate_num, String introduction,
    		 String official_link,  String recommand_dish,  double x_lan, double y_long,String dis){
    	this.id = id;
    	this.name = name;
    	this.pic_url = pic_url;
    	this.grade_food = grade_food;
    	this.grade_service = grade_service;
    	this.grade_ambiance = grade_ambiance;
    	this.price = price;
    	this.open_time = open_time;
    	this.rest_date = rest_date;
    	this.address = address;
    	this.phone = phone;
    	this.rate_num = rate_num;
    	this.introduction = introduction;
    	this.official_link = official_link;
    	this.recommand_dish = recommand_dish;
    	
    	this.x_lat = x_lan;
    	this.y_long = y_long;
    	this.dis = dis;
    }
    
    public int getId(){
    	return id;
    }
    
    public String getName(){
    	return name;
    }
    
    public String getPicUrl(){
    	return pic_url;
    }
    
    public String getGradeFood(){
    	return grade_food;
    }
    
    public String getGradeService(){
    	return grade_service;
    }
    
    public String getGradeAmbiance(){
    	return grade_ambiance;
    }
        
      
    public String getPrice(){
    	return price;
    }
    
    public String getOpenTime(){
    	return open_time;
    }
     
    public String getRestDate(){
    	return rest_date;
    } 
        
    public String getAddress(){
    	return address;
    }
    
    public String getPhone(){
    	return phone;
    }
    
    public int getRateNum(){
    	return rate_num;
    }
    
    public String getIntroduction(){
    	return introduction;
    }
    
    public String getOfficialLink(){
    	return official_link;
    }
    
    public String getRecommandDish(){
    	return recommand_dish;
    }
    
    public double getX(){
    	return x_lat;
    }
    
    public double getY(){
    	return y_long;
    }
    
    public void setDis(String dis){
    	this.dis = dis;
    }
    
    public String getDis(){
    	return dis;
    }
}
