package com.restaurant.collection.entity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;


public class Area {
	
	int id;
	String name;
	
    static final String message = "[{\"id\":1,\"name\":\"\u57fa\u9686\u5e02\"},{\"id\":2,\"name\":\"\u53f0\u5317\u5e02\"},{\"id\":3,\"name\":\"\u65b0\u5317\u5e02\"},{\"id\":4,\"name\":\"\u6843\u5712\u7e23\"},{\"id\":5,\"name\":\"\u65b0\u7af9\u5e02\"},{\"id\":6,\"name\":\"\u65b0\u7af9\u7e23\"},{\"id\":7,\"name\":\"\u82d7\u6817\u7e23\"},{\"id\":8,\"name\":\"\u53f0\u4e2d\u5e02\"},{\"id\":9,\"name\":\"\u5357\u6295\u7e23\"},{\"id\":10,\"name\":\"\u96f2\u6797\u7e23\"},{\"id\":11,\"name\":\"\u5609\u7fa9\u5e02\"},{\"id\":12,\"name\":\"\u53f0\u5357\u5e02\"},{\"id\":13,\"name\":\"\u9ad8\u96c4\u5e02\"},{\"id\":14,\"name\":\"\u5c4f\u6771\u7e23\"},{\"id\":15,\"name\":\"\u82b1\u84ee\u7e23\"},{\"id\":16,\"name\":\"\u5b9c\u862d\u7e23\"},{\"id\":17,\"name\":\"\u4e0a\u6d77\u5e02\"}]";
	
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
	
	public static ArrayList<Area> getAreas() {
        ArrayList<Area> newlist = new ArrayList<Area>(10);

        JSONArray jArray;
        try {
            jArray = new JSONArray(message.toString());
            for (int i = 0; i < jArray.length(); i++) {
                int id = jArray.getJSONObject(i).getInt("id");
                String name = jArray.getJSONObject(i).getString("name");

                Area area = new Area(id, name);
                newlist.add(area);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newlist;
    }
	
}
