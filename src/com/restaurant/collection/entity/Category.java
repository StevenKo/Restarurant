package com.restaurant.collection.entity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class Category {
	int id;
	String floor;
	String name;
	String info;
	
    static final String message = "[{\"id\":1,\"info\":\"Buffet\ufe52\u706b\u934b\u71d2\u8089\",\"name\":\"\u6b61\u805a\u540c\u6a02\"},{\"id\":2,\"info\":\"\u7c73\u5176\u6797\u9910\u5ef3\ufe52\u570b\u969b\u9910\u98f2\u96c6\u5718\",\"name\":\"\u570b\u969b\u7cbe\u54c1\"},{\"id\":3,\"info\":\"\u852c\u98df\u6599\u7406\ufe52\u8cb4\u5a66\u4e0b\u5348\u8336\",\"name\":\"\u90fd\u6703\u540d\u5a9b\"},{\"id\":4,\"info\":\"\u65e5\u672c\u6599\u7406\ufe52\u5357\u6d0b\u6599\u7406\",\"name\":\"\u6771\u65b9\u98a8\u60c5\"},{\"id\":5,\"info\":\"\u7fa9\u6cd5\u6599\u7406\ufe52\u7f8e\u5f0f\u6599\u7406\",\"name\":\"\u897f\u5f0f\u6d6a\u6f2b\"},{\"id\":6,\"info\":\"\u4e2d\u5f0f\u4f73\u991a\ufe52\u53f0\u83dc\u6d77\u9bae\",\"name\":\"\u50b3\u7d71\u7d93\u5178\"},{\"id\":7,\"info\":\"\u751f\u65e5\u512a\u60e0\ufe52\u4fe1\u7528\u5361\u512a\u60e0\ufe52\u7bc0\u65e5\u512a\u60e0\",\"name\":\"\u512a\u60e0\u63a8\u85a6\"},{\"id\":8,\"info\":\"\",\"name\":\"\u65b0\u9032\u9910\u5ef3\"},{\"id\":9,\"info\":\"Lounge Bar\",\"name\":\"\u591c\u5e97\u5962\u83ef\"},{\"id\":10,\"info\":\"\u666f\u89c0\u9910\u5ef3\",\"name\":\"\u666f\u89c0\u7f8e\u994c\"}]";
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
	
	public static ArrayList<Category> getCategories() {
        ArrayList<Category> newlist = new ArrayList<Category>(10);

        JSONArray jArray;
        try {
            jArray = new JSONArray(message.toString());
            for (int i = 0; i < jArray.length(); i++) {
                int id = jArray.getJSONObject(i).getInt("id");
                String name = jArray.getJSONObject(i).getString("name");
                String info = jArray.getJSONObject(i).getString("info");

                Category area = new Category(id, "",name,info);
                newlist.add(area);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newlist;
    }
}
