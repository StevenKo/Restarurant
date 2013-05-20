package com.restaurant.collection.entity;

import java.util.ArrayList;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;


public class Type {
	
	int id;
	String name;
	
    static final String message = "[{\"id\":1,\"name\":\"\u5403\u5230\u98fd\u00a0\u00a0\"},{\"id\":2,\"name\":\"\u4e0b\u5348\u8336\u00a0\u00a0\"},{\"id\":3,\"name\":\"\u53f0\u83dc\u6d77\u9bae\u00a0\u00a0\"},{\"id\":4,\"name\":\"\u4e2d\u5f0f\u6599\u7406\u00a0\u00a0\"},{\"id\":5,\"name\":\"\u7f8e\u5f0f\u6599\u7406\u00a0\u00a0\"},{\"id\":6,\"name\":\"\u725b\u6392\u00a0\u00a0\"},{\"id\":7,\"name\":\"\u5176\u4ed6\u897f\u5f0f\u00a0\u00a0\"},{\"id\":8,\"name\":\"\u7fa9\u5f0f\u6599\u7406\u00a0\u00a0\"},{\"id\":9,\"name\":\"\u5176\u5b83\u65e5\u5f0f\u00a0\u00a0\"},{\"id\":10,\"name\":\"\u5275\u610f\u6599\u7406\u00a0\u00a0\"},{\"id\":11,\"name\":\"\u9435\u677f\u71d2\u00a0\u00a0\"},{\"id\":12,\"name\":\"\u6cf0\u7dec\u6599\u7406\u00a0\u00a0\"},{\"id\":13,\"name\":\"\u6cd5\u5f0f\u6599\u7406\u00a0\u00a0\"},{\"id\":14,\"name\":\"\u8907\u5408\u5f0f\u7c21\u9910\u00a0\u00a0\"},{\"id\":15,\"name\":\"\u706b\u934b\u00a0\u00a0\"},{\"id\":16,\"name\":\"\u7fa9\u5927\u5229\u9eb5\u00a0\u00a0\"},{\"id\":17,\"name\":\"Lounge Bar \uff06 \u8abf\u9152\u00a0\u00a0\"},{\"id\":18,\"name\":\"\u6e2f\u5f0f\u6599\u7406\u00a0\u00a0\"},{\"id\":19,\"name\":\"\u71d2\u70e4\u00a0\u00a0\"},{\"id\":20,\"name\":\"\u58fd\u53f8/\u5c45\u9152\u5c4b\u00a0\u00a0\"},{\"id\":21,\"name\":\"\u7d20\u98df\u00a0\u00a0\"},{\"id\":22,\"name\":\"\u5fb7\u5f0f\u6599\u7406\u00a0\u00a0\"},{\"id\":23,\"name\":\"\u65e5\u5f0f\u8c6c\u6392/\u5496\u54e9\u00a0\u00a0\"},{\"id\":24,\"name\":\"\u97d3\u5f0f\u6599\u7406\u00a0\u00a0\"},{\"id\":25,\"name\":\"\u897f\u73ed\u7259\u6599\u7406\u00a0\u00a0\"},{\"id\":26,\"name\":\"\u65e5\u5f0f\u62c9\u9eb5\u00a0\u00a0\"},{\"id\":27,\"name\":\"\u9ad8\u96c4\u98ef\u5e97\"}]";
	static final String ship_message = "[{\"area_id\":1,\"type_id\":1},{\"area_id\":1,\"type_id\":2},{\"area_id\":1,\"type_id\":2},{\"area_id\":1,\"type_id\":3},{\"area_id\":1,\"type_id\":4},{\"area_id\":2,\"type_id\":1},{\"area_id\":2,\"type_id\":2},{\"area_id\":2,\"type_id\":5},{\"area_id\":2,\"type_id\":6},{\"area_id\":2,\"type_id\":7},{\"area_id\":2,\"type_id\":8},{\"area_id\":2,\"type_id\":6},{\"area_id\":2,\"type_id\":7},{\"area_id\":2,\"type_id\":1},{\"area_id\":2,\"type_id\":7},{\"area_id\":2,\"type_id\":2},{\"area_id\":2,\"type_id\":9},{\"area_id\":2,\"type_id\":2},{\"area_id\":2,\"type_id\":9},{\"area_id\":2,\"type_id\":1},{\"area_id\":2,\"type_id\":3},{\"area_id\":2,\"type_id\":9},{\"area_id\":2,\"type_id\":10},{\"area_id\":2,\"type_id\":3},{\"area_id\":2,\"type_id\":4},{\"area_id\":2,\"type_id\":1},{\"area_id\":2,\"type_id\":10},{\"area_id\":2,\"type_id\":2},{\"area_id\":2,\"type_id\":11},{\"area_id\":2,\"type_id\":12},{\"area_id\":2,\"type_id\":11},{\"area_id\":2,\"type_id\":6},{\"area_id\":2,\"type_id\":7},{\"area_id\":2,\"type_id\":2},{\"area_id\":2,\"type_id\":8},{\"area_id\":2,\"type_id\":6},{\"area_id\":2,\"type_id\":7},{\"area_id\":2,\"type_id\":13},{\"area_id\":2,\"type_id\":14},{\"area_id\":2,\"type_id\":2},{\"area_id\":2,\"type_id\":9},{\"area_id\":2,\"type_id\":1},{\"area_id\":2,\"type_id\":7},{\"area_id\":2,\"type_id\":2},{\"area_id\":2,\"type_id\":4},{\"area_id\":2,\"type_id\":15},{\"area_id\":2,\"type_id\":1},{\"area_id\":2,\"type_id\":8},{\"area_id\":2,\"type_id\":16},{\"area_id\":2,\"type_id\":2}]";
    public Type(){
		this(1,"");
	}
	
	public Type(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public static ArrayList<Type> getTypes() {
        ArrayList<Type> newlist = new ArrayList<Type>(10);

        JSONArray jArray;
        try {
            jArray = new JSONArray(message.toString());
            for (int i = 0; i < jArray.length(); i++) {
                int id = jArray.getJSONObject(i).getInt("id");
                String name = jArray.getJSONObject(i).getString("name");

                Type type = new Type(id, name);
                newlist.add(type);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newlist;
    }
	
	public static Type getType(int id){
		ArrayList<Type> types = getTypes();
		for (Type type : types){
			if (type.getId() == id)
				return type;
		}
		return null;
	}
	
	public static ArrayList<Type> getAreaTypes(int area_id){
		TreeMap map = new TreeMap<Integer, Boolean>();
		ArrayList<Type> newlist = new ArrayList<Type>();
		JSONArray jArray;
        try {
            jArray = new JSONArray(ship_message.toString());
            for (int i = 0; i < jArray.length(); i++) {
                int ship_area_id = jArray.getJSONObject(i).getInt("area_id");
                int ship_type_id = jArray.getJSONObject(i).getInt("type_id");
                
                if(ship_area_id == area_id && !map.containsKey(ship_type_id))
                {
                	newlist.add(getType(ship_type_id));
                	map.put(ship_type_id, true);
                }
                
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newlist;
	}
}
