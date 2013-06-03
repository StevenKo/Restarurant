package com.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.restaurant.collection.R;


public class CategoryTabFragment extends Fragment {

    private FragmentTabHost mTabHost;

    public CategoryTabFragment() {
    }

    public static final CategoryTabFragment newInstance(int area_id, int rank_category_id, int category_id, int type_id, boolean is_collection, boolean is_selected) {
        CategoryTabFragment f = new CategoryTabFragment();
        Bundle bdl = new Bundle();
        bdl.putInt("AreaId", area_id);
        bdl.putInt("RankCategoryId", rank_category_id);
        bdl.putInt("CategoryId", category_id);
        bdl.putInt("TypeId", type_id);
        bdl.putBoolean("IsCollection", is_collection);
        bdl.putBoolean("IsSelected", is_selected);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_tabhost);

        setupTab(GridRestaurantsFragment.class, "餐廳列表", "View1");
        setupTab(GridEatNoteFragment.class, "食記列表", "View2");

        return mTabHost;
    }
    
    

    private void setupTab(Class<?> ccls, String name, String nameSpec) {

        getActivity().getLayoutInflater();
        View tab = LayoutInflater.from(getActivity()).inflate(R.layout.item_tab, null);
        TextView text = (TextView) tab.findViewById(R.id.text);
        text.setText(name);
        
        Bundle arg1 = new Bundle();
		arg1.putInt("AreaId", getArguments().getInt("AreaId"));
		arg1.putInt("CategoryId", getArguments().getInt("CategoryId"));
		arg1.putInt("RankCategoryId", getArguments().getInt("RankCategoryId"));
		arg1.putInt("TypeId", getArguments().getInt("TypeId"));
		arg1.putBoolean("IsCollection", getArguments().getBoolean("IsCollection"));
		arg1.putBoolean("IsSelected", getArguments().getBoolean("IsSelected"));
		
        mTabHost.addTab(mTabHost.newTabSpec(nameSpec).setIndicator(tab), ccls, arg1);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}
