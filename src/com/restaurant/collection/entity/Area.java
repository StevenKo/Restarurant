package com.restaurant.collection.entity;

import java.util.ArrayList;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;


public class Area {
	
	int id;
	String name;
	
	
//	static final String ship_message = "[{\"id\":2,\"name\":\"\u57fa\u9686\u5e02\"},{\"id\":3,\"name\":\"\u53f0\u5317\u5e02\"},{\"id\":4,\"name\":\"\u65b0\u5317\u5e02\"},{\"id\":5,\"name\":\"\u6843\u5712\u7e23\"},{\"id\":6,\"name\":\"\u65b0\u7af9\u5e02\"},{\"id\":7,\"name\":\"\u65b0\u7af9\u7e23\"},{\"id\":8,\"name\":\"\u82d7\u6817\u7e23\"},{\"id\":9,\"name\":\"\u53f0\u4e2d\u5e02\"},{\"id\":10,\"name\":\"\u5357\u6295\u7e23\"},{\"id\":11,\"name\":\"\u5f70\u5316\u7e23\"},{\"id\":12,\"name\":\"\u96f2\u6797\u7e23\"},{\"id\":13,\"name\":\"\u5609\u7fa9\u5e02\"},{\"id\":14,\"name\":\"\u5609\u7fa9\u7e23\"},{\"id\":15,\"name\":\"\u53f0\u5357\u5e02\"},{\"id\":16,\"name\":\"\u9ad8\u96c4\u5e02\"},{\"id\":18,\"name\":\"\u5b9c\u862d\u7e23\"},{\"id\":19,\"name\":\"\u82b1\u84ee\u7e23\"},{\"id\":20,\"name\":\"\u53f0\u6771\u7e23\"},{\"id\":21,\"name\":\"\u6f8e\u6e56\u7e23\"},{\"id\":22,\"name\":\"\u9023\u6c5f\u7e23\"},{\"id\":23,\"name\":\"\u91d1\u9580\u7e23\"},{\"id\":17,\"name\":\"\u5c4f\u6771\u7e23\"}]";
    static final String message = "[{\"id\":2,\"name\":\"\u57fa\u9686\u5e02\"},{\"id\":3,\"name\":\"\u53f0\u5317\u5e02\"},{\"id\":4,\"name\":\"\u65b0\u5317\u5e02\"},{\"id\":5,\"name\":\"\u6843\u5712\u7e23\"},{\"id\":6,\"name\":\"\u65b0\u7af9\u5e02\"},{\"id\":7,\"name\":\"\u65b0\u7af9\u7e23\"},{\"id\":8,\"name\":\"\u82d7\u6817\u7e23\"},{\"id\":9,\"name\":\"\u53f0\u4e2d\u5e02\"},{\"id\":10,\"name\":\"\u5357\u6295\u7e23\"},{\"id\":11,\"name\":\"\u5f70\u5316\u7e23\"},{\"id\":12,\"name\":\"\u96f2\u6797\u7e23\"},{\"id\":13,\"name\":\"\u5609\u7fa9\u5e02\"},{\"id\":14,\"name\":\"\u5609\u7fa9\u7e23\"},{\"id\":15,\"name\":\"\u53f0\u5357\u5e02\"},{\"id\":16,\"name\":\"\u9ad8\u96c4\u5e02\"},{\"id\":18,\"name\":\"\u5b9c\u862d\u7e23\"},{\"id\":19,\"name\":\"\u82b1\u84ee\u7e23\"},{\"id\":20,\"name\":\"\u53f0\u6771\u7e23\"},{\"id\":21,\"name\":\"\u6f8e\u6e56\u7e23\"},{\"id\":22,\"name\":\"\u9023\u6c5f\u7e23\"},{\"id\":23,\"name\":\"\u91d1\u9580\u7e23\"},{\"id\":17,\"name\":\"\u5c4f\u6771\u7e23\"}]";
	
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
	
	public static Area getArea(int id){
		ArrayList<Area> areas = getAreas();
		for (Area area : areas){
			if (area.getId() == id)
				return area;
		}
		return null;
	}
	
//	public static ArrayList<Area> getCategoryAreas(int category_id){
//		ArrayList<Area> newlist = new ArrayList<Area>();
//		JSONArray jArray;
//        try {
//            jArray = new JSONArray(ship_message.toString());
//            for (int i = 0; i < jArray.length(); i++) {
//                int area_id = jArray.getJSONObject(i).getInt("area_id");
//                int ship_category_id = jArray.getJSONObject(i).getInt("category_id");
//                
//                if(ship_category_id == category_id)
//                {
//                	newlist.add(getArea(area_id));
//                }
//                
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return newlist;
//	}
	
//	public static ArrayList<Area> getTypeAreas(int type_id){
//		TreeMap map = new TreeMap<Integer, Boolean>();
//		ArrayList<Area> newlist = new ArrayList<Area>();
//		JSONArray jArray;
//        try {
//            jArray = new JSONArray(Type.ship_message.toString());
//            for (int i = 0; i < jArray.length(); i++) {
//                int ship_area_id = jArray.getJSONObject(i).getInt("area_id");
//                int ship_type_id = jArray.getJSONObject(i).getInt("type_id");
//                
//                if(ship_type_id == type_id && !map.containsKey(ship_area_id))
//                {
//                	newlist.add(getArea(ship_area_id));
//                	map.put(ship_area_id, true);
//                }
//                
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return newlist;
//	}
	
}
