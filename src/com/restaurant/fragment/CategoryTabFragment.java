package com.restaurant.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.restaurant.collection.MainActivity;
import com.restaurant.collection.R;

@SuppressLint("ValidFragment")
public class CategoryTabFragment extends Fragment {

    private FragmentTabHost mTabHost;

    public CategoryTabFragment() {

    }

    public static final CategoryTabFragment newInstance() {
        CategoryTabFragment f = new CategoryTabFragment();
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
        mTabHost.addTab(mTabHost.newTabSpec(nameSpec).setIndicator(tab), ccls, new Bundle());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}
