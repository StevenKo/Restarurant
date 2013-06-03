package com.restaurant.collection.entity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class Category {
	int id;
	String name;
	ArrayList<Category> second_categories = new ArrayList<Category>();
	
    static final String message = "[{\"id\":1,\"name\":\"中式料理\",\"second_categories\":[{\"id\":1,\"name\":\"海鮮餐廳\"},{\"id\":2,\"name\":\"山產野菜餐廳\"},{\"id\":3,\"name\":\"北京菜\"},{\"id\":4,\"name\":\"客家菜\"},{\"id\":5,\"name\":\"四川菜\"},{\"id\":6,\"name\":\"湘菜(湖南菜)\"},{\"id\":7,\"name\":\"台菜餐廳\"},{\"id\":8,\"name\":\"上海菜(江浙菜)\"},{\"id\":9,\"name\":\"粵菜 港式飲茶\"},{\"id\":10,\"name\":\"麵食點心\"},{\"id\":11,\"name\":\"其它中式料理\"},{\"id\":12,\"name\":\"熱炒、快炒\"},{\"id\":13,\"name\":\"台灣原住民料理\"},{\"id\":14,\"name\":\"新疆菜\"},{\"id\":15,\"name\":\"西藏菜\"},{\"id\":16,\"name\":\"雲南菜\"},{\"id\":17,\"name\":\"眷村菜\"}]},{\"id\":2,\"name\":\"日式料理\",\"second_categories\":[{\"id\":18,\"name\":\"居酒屋\"},{\"id\":19,\"name\":\"綜合日式料理\"},{\"id\":20,\"name\":\"日式麵食專賣\"},{\"id\":21,\"name\":\"生魚片、壽司專賣\"},{\"id\":22,\"name\":\"日式豬排專賣\"},{\"id\":23,\"name\":\"文字燒、大阪燒\"},{\"id\":24,\"name\":\"懷石料理\"},{\"id\":25,\"name\":\"其他日式料理\"}]},{\"id\":3,\"name\":\"亞洲料理\",\"second_categories\":[{\"id\":26,\"name\":\"韓式料理\"},{\"id\":27,\"name\":\"泰式料理\"},{\"id\":28,\"name\":\"越南料理\"},{\"id\":29,\"name\":\"緬甸料理\"},{\"id\":30,\"name\":\"綜合南洋料理\"},{\"id\":31,\"name\":\"印尼料理\"},{\"id\":32,\"name\":\"土耳其料理\"},{\"id\":33,\"name\":\"澳門美食\"},{\"id\":34,\"name\":\"星馬料理\"},{\"id\":35,\"name\":\"印度料理\"},{\"id\":36,\"name\":\"中東料理\"},{\"id\":37,\"name\":\"其他亞洲料理\"}]},{\"id\":4,\"name\":\"異國料理\",\"second_categories\":[{\"id\":38,\"name\":\"法式料理\"},{\"id\":39,\"name\":\"美式料理\"},{\"id\":40,\"name\":\"義式料理\"},{\"id\":41,\"name\":\"德式料理\"},{\"id\":42,\"name\":\"其他異國料理\"},{\"id\":43,\"name\":\"非洲料理\"},{\"id\":44,\"name\":\"葡萄牙料理\"},{\"id\":45,\"name\":\"俄羅斯料理\"},{\"id\":46,\"name\":\"比利時料理\"},{\"id\":47,\"name\":\"愛爾蘭料理\"},{\"id\":48,\"name\":\"紐澳料理\"},{\"id\":49,\"name\":\"英式料理\"},{\"id\":50,\"name\":\"墨西哥料理\"},{\"id\":51,\"name\":\"西班牙料理\"},{\"id\":52,\"name\":\"北歐料理\"},{\"id\":53,\"name\":\"希臘料理\"}]},{\"id\":5,\"name\":\"燒烤類\",\"second_categories\":[{\"id\":54,\"name\":\"炭烤串燒\"},{\"id\":55,\"name\":\"鐵板燒\"},{\"id\":56,\"name\":\"蒙古烤肉\"},{\"id\":57,\"name\":\"日式燒肉\"},{\"id\":58,\"name\":\"韓式燒肉\"},{\"id\":59,\"name\":\"火烤兩吃\"}]},{\"id\":6,\"name\":\"鍋類\",\"second_categories\":[{\"id\":60,\"name\":\"麻辣火鍋\"},{\"id\":61,\"name\":\"涮涮鍋\"},{\"id\":62,\"name\":\"其他鍋類\"},{\"id\":63,\"name\":\"羊肉爐\"},{\"id\":64,\"name\":\"薑母鴨\"},{\"id\":65,\"name\":\"石頭火鍋\"},{\"id\":66,\"name\":\"壽喜燒\"}]},{\"id\":7,\"name\":\"咖啡、簡餐、茶\",\"second_categories\":[{\"id\":67,\"name\":\"咖啡專賣\"},{\"id\":68,\"name\":\"複合式\"},{\"id\":69,\"name\":\"茶專賣\"}]},{\"id\":8,\"name\":\"素食\",\"second_categories\":[{\"id\":70,\"name\":\"小吃\"},{\"id\":71,\"name\":\"中式\"},{\"id\":72,\"name\":\"西式\"}]},{\"id\":9,\"name\":\"速食料理\",\"second_categories\":[{\"id\":73,\"name\":\"漢堡、炸雞\"},{\"id\":74,\"name\":\"披薩\"},{\"id\":75,\"name\":\"其他速食\"}]},{\"id\":10,\"name\":\"主題特色餐廳\",\"second_categories\":[{\"id\":76,\"name\":\"酒吧 Lounge Bar\"},{\"id\":77,\"name\":\"啤酒屋\"},{\"id\":78,\"name\":\"現場演奏餐廳\"},{\"id\":79,\"name\":\"食補藥膳餐廳\"},{\"id\":80,\"name\":\"景觀餐廳\"},{\"id\":81,\"name\":\"寵物餐廳\"},{\"id\":82,\"name\":\"懷舊主題\"},{\"id\":83,\"name\":\"情境主題餐廳\"},{\"id\":84,\"name\":\"運動主題餐廳\"},{\"id\":85,\"name\":\"其它主題餐廳\"},{\"id\":86,\"name\":\"溫泉餐廳\"},{\"id\":87,\"name\":\"動漫主題餐廳\"},{\"id\":88,\"name\":\"養生蔬食餐廳\"}]},{\"id\":11,\"name\":\"buffet自助餐\",\"second_categories\":[{\"id\":89,\"name\":\"其他類型buffet自助餐\"},{\"id\":90,\"name\":\"中式\"},{\"id\":91,\"name\":\"日式\"},{\"id\":92,\"name\":\"西式\"},{\"id\":93,\"name\":\"複合式\"}]}]";
    static final String rake_message = "[{\"id\":1,\"name\":\"\u4e2d\u5f0f\u6599\u7406\"},{\"id\":2,\"name\":\"\u53f0\u5f0f\u6599\u7406\"},{\"id\":3,\"name\":\"\u65e5\u5f0f\u6599\u7406\"},{\"id\":4,\"name\":\"\u6b50\u5f0f\u6599\u7406\"},{\"id\":5,\"name\":\"\u5357\u6d0b\u6599\u7406\"},{\"id\":6,\"name\":\"\u934b\u985e\u6599\u7406\"},{\"id\":7,\"name\":\"\u71d2\u70e4\u6599\u7406\"},{\"id\":8,\"name\":\"\u4e0b\u5348\u8336\"},{\"id\":9,\"name\":\"\u4e3b\u984c\u9910\u5ef3\"}]";
    
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
	
	public void addSecondCategories(Category c){
		second_categories.add(c);
	}
	
	public ArrayList<Category> getSecondCategories(){
		return this.second_categories;
	}
	
	
	public static ArrayList<Category> getCategories() {
        ArrayList<Category> newlist = new ArrayList<Category>(10);

        JSONArray jArray;
        try {
            jArray = new JSONArray(message.toString());
            for (int i = 0; i < jArray.length(); i++) {
                int id = jArray.getJSONObject(i).getInt("id");
                String name = jArray.getJSONObject(i).getString("name");

                Category c = new Category(id, name);
                
                JSONArray jSecondCategories = jArray.getJSONObject(i).getJSONArray("second_categories");
                for (int j = 0; j < jSecondCategories.length(); j++) {
                	int subId = jSecondCategories.getJSONObject(j).getInt("id");
                    String subName = jSecondCategories.getJSONObject(j).getString("name");
                    c.addSecondCategories(new Category(subId,subName));
                }
                newlist.add(c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newlist;
    }
	
	public static ArrayList<Category> getRankCategories() {
        ArrayList<Category> newlist = new ArrayList<Category>(10);

        JSONArray jArray;
        try {
            jArray = new JSONArray(rake_message.toString());
            for (int i = 0; i < jArray.length(); i++) {
                int id = jArray.getJSONObject(i).getInt("id");
                String name = jArray.getJSONObject(i).getString("name");

                Category c = new Category(id, name);
                newlist.add(c);
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
	
	public static Category getSecondCategory(int id){
		ArrayList<Category> cs = getCategories();
		for (Category category : cs){
			for(Category subCategory : category.getSecondCategories()){
				if(subCategory.getId() == id)
					return subCategory;
			}
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
