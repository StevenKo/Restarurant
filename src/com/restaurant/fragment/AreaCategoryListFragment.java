package com.restaurant.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.restaurant.adapter.AreaCategoryListAdapter;
import com.restaurant.adapter.AreaTypeListAdapter;
import com.restaurant.adapter.SeparatedListAdapter;
import com.restaurant.collection.api.RestaurantAPI;
import com.restaurant.collection.entity.Category;
import com.restaurant.collection.entity.Type;

public class AreaCategoryListFragment extends ListFragment {

    private int area_id;

	public AreaCategoryListFragment() {

    }

    public static final AreaCategoryListFragment newInstance(int area_id) {
        AreaCategoryListFragment f = new AreaCategoryListFragment();
        Bundle bdl = new Bundle();
        bdl.putInt("AreaId", area_id);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        area_id = getArguments().getInt("AreaId");
        
        SeparatedListAdapter adapter = new SeparatedListAdapter(getActivity());
        ArrayList<Category> catetgories = Category.getCategories();
        for(int i=0; i < catetgories.size(); i++){
        	AreaCategoryListAdapter cadapter = new AreaCategoryListAdapter(getActivity(), catetgories.get(i).getSecondCategories(),area_id);
        	adapter.addSection(catetgories.get(i).getName(), cadapter);
        }
        AreaTypeListAdapter tadapter = new AreaTypeListAdapter(getActivity(), Type.getTypes(),area_id);
        adapter.addSection("類型分類", tadapter);
        setListAdapter(adapter);
        
    }

}
