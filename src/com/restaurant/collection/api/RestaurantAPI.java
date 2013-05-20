package com.restaurant.collection.api;

import java.util.ArrayList;

import com.restaurant.collection.entity.Area;
import com.restaurant.collection.entity.Category;;

public class RestaurantAPI {
	final static String         HOST  = "http://106.187.100.252";
    public static final String  TAG   = "RESTAURANT_API";
    public static final boolean DEBUG = true;
    
    public static ArrayList<Area> getAreas() {
        return Area.getAreas();
    }
    
    public static ArrayList<Category> getCategories() {
        return Category.getCategories();
    }
}
