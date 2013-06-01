package com.restaurant.collection.entity;

import java.util.ArrayList;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;


public class Type {
	
	int id;
	String name;
	
    static final String message = "[{\"id\":1,\"name\":\"\u7bc0\u65e5\u805a\u9910\"},{\"id\":2,\"name\":\"\u60c5\u4fb6\u7d04\u6703\"},{\"id\":3,\"name\":\"\u5bb6\u5ead\u805a\u6703\"},{\"id\":4,\"name\":\"\u670b\u53cb\u805a\u6703\"},{\"id\":5,\"name\":\"\u5718\u9ad4\u805a\u6703\"},{\"id\":6,\"name\":\"\u6700\u4f73\u53e3\u5473\"},{\"id\":7,\"name\":\"\u6700\u4f73\u670d\u52d9\"},{\"id\":8,\"name\":\"\u6700\u4f73\u74b0\u5883\"}]";
//	static final String ship_message = "[{\"area_id\":1,\"type_id\":1},{\"area_id\":1,\"type_id\":2},{\"area_id\":1,\"type_id\":2},{\"area_id\":1,\"type_id\":3},{\"area_id\":1,\"type_id\":4},{\"area_id\":2,\"type_id\":1},{\"area_id\":2,\"type_id\":2},{\"area_id\":2,\"type_id\":5},{\"area_id\":2,\"type_id\":6},{\"area_id\":2,\"type_id\":7},{\"area_id\":2,\"type_id\":8},{\"area_id\":2,\"type_id\":6},{\"area_id\":2,\"type_id\":7},{\"area_id\":2,\"type_id\":1},{\"area_id\":2,\"type_id\":7},{\"area_id\":2,\"type_id\":2},{\"area_id\":2,\"type_id\":9},{\"area_id\":2,\"type_id\":2},{\"area_id\":2,\"type_id\":9},{\"area_id\":2,\"type_id\":1},{\"area_id\":2,\"type_id\":3},{\"area_id\":2,\"type_id\":9},{\"area_id\":2,\"type_id\":10},{\"area_id\":2,\"type_id\":3},{\"area_id\":2,\"type_id\":4},{\"area_id\":2,\"type_id\":1},{\"area_id\":2,\"type_id\":10},{\"area_id\":2,\"type_id\":2},{\"area_id\":2,\"type_id\":11},{\"area_id\":2,\"type_id\":12},{\"area_id\":2,\"type_id\":11},{\"area_id\":2,\"type_id\":6},{\"area_id\":2,\"type_id\":7},{\"area_id\":2,\"type_id\":2},{\"area_id\":2,\"type_id\":8},{\"area_id\":2,\"type_id\":6},{\"area_id\":2,\"type_id\":7},{\"area_id\":2,\"type_id\":13},{\"area_id\":2,\"type_id\":14},{\"area_id\":2,\"type_id\":2},{\"area_id\":2,\"type_id\":9},{\"area_id\":2,\"type_id\":1},{\"area_id\":2,\"type_id\":7},{\"area_id\":2,\"type_id\":2},{\"area_id\":2,\"type_id\":4},{\"area_id\":2,\"type_id\":15},{\"area_id\":2,\"type_id\":1},{\"area_id\":2,\"type_id\":8},{\"area_id\":2,\"type_id\":16},{\"area_id\":2,\"type_id\":2}]";
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
	
//	public static ArrayList<Type> getAreaTypes(int area_id){
//		TreeMap map = new TreeMap<Integer, Boolean>();
//		ArrayList<Type> newlist = new ArrayList<Type>();
//		JSONArray jArray;
//        try {
//            jArray = new JSONArray(ship_message.toString());
//            for (int i = 0; i < jArray.length(); i++) {
//                int ship_area_id = jArray.getJSONObject(i).getInt("area_id");
//                int ship_type_id = jArray.getJSONObject(i).getInt("type_id");
//                
//                if(ship_area_id == area_id && !map.containsKey(ship_type_id))
//                {
//                	newlist.add(getType(ship_type_id));
//                	map.put(ship_type_id, true);
//                }
//                
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return newlist;
//	}
}
