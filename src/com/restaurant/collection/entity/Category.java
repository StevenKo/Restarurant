package com.restaurant.collection.entity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class Category {
	int id;
	String name;
	
    static final String message = "[{\"id\":1,\"name\":\"\u4e2d\u5f0f\u6599\u7406\"},{\"id\":2,\"name\":\"\u65e5\u5f0f\u6599\u7406\"},{\"id\":3,\"name\":\"\u4e9e\u6d32\u6599\u7406\"},{\"id\":4,\"name\":\"\u7570\u570b\u6599\u7406\"},{\"id\":5,\"name\":\"\u71d2\u70e4\u985e\"},{\"id\":6,\"name\":\"\u934b\u985e\"},{\"id\":7,\"name\":\"\u5496\u5561\u3001\u7c21\u9910\u3001\u8336\"},{\"id\":8,\"name\":\"\u7d20\u98df\"},{\"id\":9,\"name\":\"\u901f\u98df\u6599\u7406\"},{\"id\":10,\"name\":\"\u4e3b\u984c\u7279\u8272\u9910\u5ef3\"},{\"id\":11,\"name\":\"buffet\u81ea\u52a9\u9910\"}]";
	public Category(){
		this(1,"");
	}
	
	public Category(int id, String name){
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
	
//	public static ArrayList<Category> getAreaCategories(int area_id){
//		ArrayList<Category> newlist = new ArrayList<Category>();
//		JSONArray jArray;
//        try {
//            jArray = new JSONArray(Area.ship_message.toString());
//            for (int i = 0; i < jArray.length(); i++) {
//                int ship_area_id = jArray.getJSONObject(i).getInt("area_id");
//                int category_id = jArray.getJSONObject(i).getInt("category_id");
//                
//                if(ship_area_id == area_id)
//                {
//                	newlist.add(getCategory(category_id));
//                }
//                
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return newlist;
//	}

	
}
