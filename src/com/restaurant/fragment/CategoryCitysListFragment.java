package com.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.restaurant.adapter.ListAdapter;

public class CategoryCitysListFragment extends ListFragment {

    public CategoryCitysListFragment() {

    }

    public static final CategoryCitysListFragment newInstance() {
        CategoryCitysListFragment f = new CategoryCitysListFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] test = { "台北", "台中", "高雄" };
        ListAdapter adapter = new ListAdapter(getActivity(), test);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }

}
