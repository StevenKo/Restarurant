package com.restaurant.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.restaurant.collection.MyCollectionActivity;
import com.restaurant.collection.R;
import com.restaurant.collection.RestaurantIntroActivity;
import com.restaurant.collection.RestaurantNotesActivity;
import com.restaurant.collection.MainActivity;;


public final class IndexFragment extends Fragment {
	
	public interface OnButtonClickedListener {
	    public void onCategorySearchClicked(); 
	    public void onLocationSearchClicked(); 
	    public void onFoodtypeSearchClicked(); 
	}

    public static IndexFragment newInstance() {
        IndexFragment fragment = new IndexFragment();
        return fragment;
    }

    private View myFragmentView;
	private LinearLayout myCollectionLayout;
	private LinearLayout category_search;
	private LinearLayout location_search;
	private LinearLayout foodtype_search;
	private LinearLayout my_recommend;
	private LinearLayout near_food;
	private OnButtonClickedListener mListener;
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnButtonClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnButtonClickedListener ");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.layout_index_restaurant, container, false);
        findViews();
        setViews();
        return myFragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void setViews() {
    	myCollectionLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyCollectionActivity.class);
                startActivity(intent);
            }
        });
    	category_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	mListener.onCategorySearchClicked();
            }
        });
    	location_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	mListener.onLocationSearchClicked();
            }
        });
    	foodtype_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	mListener.onFoodtypeSearchClicked();
            }
        });
    }

    private void findViews() {
    	myCollectionLayout = (LinearLayout)myFragmentView.findViewById(R.id.my_collection);
    	category_search = (LinearLayout)myFragmentView.findViewById(R.id.category_search);
    	location_search = (LinearLayout)myFragmentView.findViewById(R.id.location_search);
    	foodtype_search = (LinearLayout)myFragmentView.findViewById(R.id.foodtype_search);
    	my_recommend = (LinearLayout)myFragmentView.findViewById(R.id.my_recommend);
    	near_food = (LinearLayout)myFragmentView.findViewById(R.id.near_food);
    	
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
