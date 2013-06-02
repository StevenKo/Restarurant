package com.restaurant.collection.entity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class RankCategory {
	int id;
	String name;
	
    static final String message = "[{\"id\":1,\"name\":\"\u4e2d\u5f0f\u6599\u7406\"},{\"id\":2,\"name\":\"\u53f0\u5f0f\u6599\u7406\"},{\"id\":3,\"name\":\"\u65e5\u5f0f\u6599\u7406\"},{\"id\":4,\"name\":\"\u6b50\u5f0f\u6599\u7406\"},{\"id\":5,\"name\":\"\u5357\u6d0b\u6599\u7406\"},{\"id\":6,\"name\":\"\u934b\u985e\u6599\u7406\"},{\"id\":7,\"name\":\"\u71d2\u70e4\u6599\u7406\"},{\"id\":8,\"name\":\"\u4e0b\u5348\u8336\"},{\"id\":9,\"name\":\"\u4e3b\u984c\u9910\u5ef3\"}]";
	public RankCategory(){
		this(1,"");
	}
	
	public RankCategory(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public int getId(){
		return id;
	}
	
	
	public String getName(){
		return name;
	}
	
	
	public static ArrayList<Category> getCategories() {
        ArrayList<Category> newlist = new ArrayList<Category>(10);

        JSONArray jArray;
        try {
            jArray = new JSONArray(message.toString());
            for (int i = 0; i < jArray.length(); i++) {
                int id = jArray.getJSONObject(i).getInt("id");
                String name = jArray.getJSONObject(i).getString("name");

                Category area = new Category(id, name);
                newlist.add(area);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newlist;
    }
	
	public static Category getCategory(int id){
		ArrayList<Category> cs = getCategories();
		for (Category category : cs){
			if (category.getId() == id)
				return category;
		}
		return null;
	}
}
