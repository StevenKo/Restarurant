package com.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment; 
import com.restaurant.adapter.CategoryListAdapter;
import com.restaurant.collection.entity.Category;

public class SecondCategoryListFragment extends ListFragment {

    private int category_id;

	public SecondCategoryListFragment() {

    }

    public static final SecondCategoryListFragment newInstance(int category_id) {
        SecondCategoryListFragment f = new SecondCategoryListFragment();
        Bundle bdl = new Bundle();
        bdl.putInt("CategoryId", category_id);
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
        category_id = getArguments().getInt("CategoryId");
        setListAdapter(new CategoryListAdapter(getActivity(), Category.getCategory(category_id).getSecondCategories()));
        
    }

}
