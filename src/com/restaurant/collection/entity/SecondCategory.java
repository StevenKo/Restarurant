package com.restaurant.collection.entity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class SecondCategory {
	int id;
	int category_id;
	String name;
	
    static final String message = "[{\"category_id\":1,\"id\":1,\"name\":\"\u6d77\u9bae\u9910\u5ef3\"},{\"category_id\":1,\"id\":2,\"name\":\"\u5c71\u7522\u91ce\u83dc\u9910\u5ef3\"},{\"category_id\":1,\"id\":3,\"name\":\"\u5317\u4eac\u83dc\"},{\"category_id\":1,\"id\":4,\"name\":\"\u5ba2\u5bb6\u83dc\"},{\"category_id\":1,\"id\":5,\"name\":\"\u56db\u5ddd\u83dc\"},{\"category_id\":1,\"id\":6,\"name\":\"\u6e58\u83dc(\u6e56\u5357\u83dc)\"},{\"category_id\":1,\"id\":7,\"name\":\"\u53f0\u83dc\u9910\u5ef3\"},{\"category_id\":1,\"id\":8,\"name\":\"\u4e0a\u6d77\u83dc(\u6c5f\u6d59\u83dc)\"},{\"category_id\":1,\"id\":9,\"name\":\"\u7cb5\u83dc \u6e2f\u5f0f\u98f2\u8336\"},{\"category_id\":1,\"id\":10,\"name\":\"\u9eb5\u98df\u9ede\u5fc3\"},{\"category_id\":1,\"id\":11,\"name\":\"\u5176\u5b83\u4e2d\u5f0f\u6599\u7406\"},{\"category_id\":1,\"id\":12,\"name\":\"\u71b1\u7092\u3001\u5feb\u7092\"},{\"category_id\":1,\"id\":13,\"name\":\"\u53f0\u7063\u539f\u4f4f\u6c11\u6599\u7406\"},{\"category_id\":1,\"id\":14,\"name\":\"\u65b0\u7586\u83dc\"},{\"category_id\":1,\"id\":15,\"name\":\"\u897f\u85cf\u83dc\"},{\"category_id\":1,\"id\":16,\"name\":\"\u96f2\u5357\u83dc\"},{\"category_id\":1,\"id\":17,\"name\":\"\u7737\u6751\u83dc\"},{\"category_id\":2,\"id\":18,\"name\":\"\u5c45\u9152\u5c4b\"},{\"category_id\":2,\"id\":19,\"name\":\"\u7d9c\u5408\u65e5\u5f0f\u6599\u7406\"},{\"category_id\":2,\"id\":20,\"name\":\"\u65e5\u5f0f\u9eb5\u98df\u5c08\u8ce3\"},{\"category_id\":2,\"id\":21,\"name\":\"\u751f\u9b5a\u7247\u3001\u58fd\u53f8\u5c08\u8ce3\"},{\"category_id\":2,\"id\":22,\"name\":\"\u65e5\u5f0f\u8c6c\u6392\u5c08\u8ce3\"},{\"category_id\":2,\"id\":23,\"name\":\"\u6587\u5b57\u71d2\u3001\u5927\u962a\u71d2\"},{\"category_id\":2,\"id\":24,\"name\":\"\u61f7\u77f3\u6599\u7406\"},{\"category_id\":2,\"id\":25,\"name\":\"\u5176\u4ed6\u65e5\u5f0f\u6599\u7406\"},{\"category_id\":3,\"id\":26,\"name\":\"\u97d3\u5f0f\u6599\u7406\"},{\"category_id\":3,\"id\":27,\"name\":\"\u6cf0\u5f0f\u6599\u7406\"},{\"category_id\":3,\"id\":28,\"name\":\"\u8d8a\u5357\u6599\u7406\"},{\"category_id\":3,\"id\":29,\"name\":\"\u7dec\u7538\u6599\u7406\"},{\"category_id\":3,\"id\":30,\"name\":\"\u7d9c\u5408\u5357\u6d0b\u6599\u7406\"},{\"category_id\":3,\"id\":31,\"name\":\"\u5370\u5c3c\u6599\u7406\"},{\"category_id\":3,\"id\":32,\"name\":\"\u571f\u8033\u5176\u6599\u7406\"},{\"category_id\":3,\"id\":33,\"name\":\"\u6fb3\u9580\u7f8e\u98df\"},{\"category_id\":3,\"id\":34,\"name\":\"\u661f\u99ac\u6599\u7406\"},{\"category_id\":3,\"id\":35,\"name\":\"\u5370\u5ea6\u6599\u7406\"},{\"category_id\":3,\"id\":36,\"name\":\"\u4e2d\u6771\u6599\u7406\"},{\"category_id\":3,\"id\":37,\"name\":\"\u5176\u4ed6\u4e9e\u6d32\u6599\u7406\"},{\"category_id\":4,\"id\":38,\"name\":\"\u6cd5\u5f0f\u6599\u7406\"},{\"category_id\":4,\"id\":39,\"name\":\"\u7f8e\u5f0f\u6599\u7406\"},{\"category_id\":4,\"id\":40,\"name\":\"\u7fa9\u5f0f\u6599\u7406\"},{\"category_id\":4,\"id\":41,\"name\":\"\u5fb7\u5f0f\u6599\u7406\"},{\"category_id\":4,\"id\":42,\"name\":\"\u5176\u4ed6\u7570\u570b\u6599\u7406\"},{\"category_id\":4,\"id\":43,\"name\":\"\u975e\u6d32\u6599\u7406\"},{\"category_id\":4,\"id\":44,\"name\":\"\u8461\u8404\u7259\u6599\u7406\"},{\"category_id\":4,\"id\":45,\"name\":\"\u4fc4\u7f85\u65af\u6599\u7406\"},{\"category_id\":4,\"id\":46,\"name\":\"\u6bd4\u5229\u6642\u6599\u7406\"},{\"category_id\":4,\"id\":47,\"name\":\"\u611b\u723e\u862d\u6599\u7406\"},{\"category_id\":4,\"id\":48,\"name\":\"\u7d10\u6fb3\u6599\u7406\"},{\"category_id\":4,\"id\":49,\"name\":\"\u82f1\u5f0f\u6599\u7406\"},{\"category_id\":4,\"id\":50,\"name\":\"\u58a8\u897f\u54e5\u6599\u7406\"},{\"category_id\":4,\"id\":51,\"name\":\"\u897f\u73ed\u7259\u6599\u7406\"},{\"category_id\":4,\"id\":52,\"name\":\"\u5317\u6b50\u6599\u7406\"},{\"category_id\":4,\"id\":53,\"name\":\"\u5e0c\u81d8\u6599\u7406\"},{\"category_id\":5,\"id\":54,\"name\":\"\u70ad\u70e4\u4e32\u71d2\"},{\"category_id\":5,\"id\":55,\"name\":\"\u9435\u677f\u71d2\"},{\"category_id\":5,\"id\":56,\"name\":\"\u8499\u53e4\u70e4\u8089\"},{\"category_id\":5,\"id\":57,\"name\":\"\u65e5\u5f0f\u71d2\u8089\"},{\"category_id\":5,\"id\":58,\"name\":\"\u97d3\u5f0f\u71d2\u8089\"},{\"category_id\":5,\"id\":59,\"name\":\"\u706b\u70e4\u5169\u5403\"},{\"category_id\":6,\"id\":60,\"name\":\"\u9ebb\u8fa3\u706b\u934b\"},{\"category_id\":6,\"id\":61,\"name\":\"\u6dae\u6dae\u934b\"},{\"category_id\":6,\"id\":62,\"name\":\"\u5176\u4ed6\u934b\u985e\"},{\"category_id\":6,\"id\":63,\"name\":\"\u7f8a\u8089\u7210\"},{\"category_id\":6,\"id\":64,\"name\":\"\u8591\u6bcd\u9d28\"},{\"category_id\":6,\"id\":65,\"name\":\"\u77f3\u982d\u706b\u934b\"},{\"category_id\":6,\"id\":66,\"name\":\"\u58fd\u559c\u71d2\"},{\"category_id\":7,\"id\":67,\"name\":\"\u5496\u5561\u5c08\u8ce3\"},{\"category_id\":7,\"id\":68,\"name\":\"\u8907\u5408\u5f0f\"},{\"category_id\":7,\"id\":69,\"name\":\"\u8336\u5c08\u8ce3\"},{\"category_id\":8,\"id\":70,\"name\":\"\u5c0f\u5403\"},{\"category_id\":8,\"id\":71,\"name\":\"\u4e2d\u5f0f\"},{\"category_id\":8,\"id\":72,\"name\":\"\u897f\u5f0f\"},{\"category_id\":9,\"id\":73,\"name\":\"\u6f22\u5821\u3001\u70b8\u96de\"},{\"category_id\":9,\"id\":74,\"name\":\"\u62ab\u85a9\"},{\"category_id\":9,\"id\":75,\"name\":\"\u5176\u4ed6\u901f\u98df\"},{\"category_id\":10,\"id\":76,\"name\":\"\u9152\u5427 Lounge Bar\"},{\"category_id\":10,\"id\":77,\"name\":\"\u5564\u9152\u5c4b\"},{\"category_id\":10,\"id\":78,\"name\":\"\u73fe\u5834\u6f14\u594f\u9910\u5ef3\"},{\"category_id\":10,\"id\":79,\"name\":\"\u98df\u88dc\u85e5\u81b3\u9910\u5ef3\"},{\"category_id\":10,\"id\":80,\"name\":\"\u666f\u89c0\u9910\u5ef3\"},{\"category_id\":10,\"id\":81,\"name\":\"\u5bf5\u7269\u9910\u5ef3\"},{\"category_id\":10,\"id\":82,\"name\":\"\u61f7\u820a\u4e3b\u984c\"},{\"category_id\":10,\"id\":83,\"name\":\"\u60c5\u5883\u4e3b\u984c\u9910\u5ef3\"},{\"category_id\":10,\"id\":84,\"name\":\"\u904b\u52d5\u4e3b\u984c\u9910\u5ef3\"},{\"category_id\":10,\"id\":85,\"name\":\"\u5176\u5b83\u4e3b\u984c\u9910\u5ef3\"},{\"category_id\":10,\"id\":86,\"name\":\"\u6eab\u6cc9\u9910\u5ef3\"},{\"category_id\":10,\"id\":87,\"name\":\"\u52d5\u6f2b\u4e3b\u984c\u9910\u5ef3\"},{\"category_id\":10,\"id\":88,\"name\":\"\u990a\u751f\u852c\u98df\u9910\u5ef3\"},{\"category_id\":11,\"id\":89,\"name\":\"\u5176\u4ed6\u985e\u578bbuffet\u81ea\u52a9\u9910\"},{\"category_id\":11,\"id\":90,\"name\":\"\u4e2d\u5f0f\"},{\"category_id\":11,\"id\":91,\"name\":\"\u65e5\u5f0f\"},{\"category_id\":11,\"id\":92,\"name\":\"\u897f\u5f0f\"},{\"category_id\":11,\"id\":93,\"name\":\"\u8907\u5408\u5f0f\"}]";
	public SecondCategory(){
		this(1,1,"");
	}
	
	public SecondCategory(int id, int category_id,String name){
		this.id = id;
		this.category_id = category_id;
		this.name = name;
	}
	
	public int getId(){
		return id;
	}
	
	public int getCategoryId(){
		return category_id;
	}
	
	public String getName(){
		return name;
	}
	
	
	public static ArrayList<SecondCategory> getCategories(int category_id) {
        ArrayList<SecondCategory> newlist = new ArrayList<SecondCategory>(20);

        JSONArray jArray;
        try {
            jArray = new JSONArray(message.toString());
            for (int i = 0; i < jArray.length(); i++) {
                int id = jArray.getJSONObject(i).getInt("id");
                String name = jArray.getJSONObject(i).getString("name");
                int c_id = jArray.getJSONObject(i).getInt("category_id");
                if (c_id == category_id){
                	SecondCategory area = new SecondCategory(id, c_id,name);
                	newlist.add(area);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newlist;
    }
	
//	public static Category getCategory(int id){
//		ArrayList<Category> cs = getCategories();
//		for (Category category : cs){
//			if (category.getId() == id)
//				return category;
//		}
//		return null;
//	}
}
