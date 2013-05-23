package com.restaurant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.restaurant.collection.MyCollectionActivity;
import com.restaurant.collection.R;
import com.restaurant.collection.RestaurantNoteActivity;
import com.tool.imageload.ImageLoader;

public final class RestaurantNotesFragment extends Fragment {


    private View myFragmentView;
	private ImageView noteImage;
	private TextView noteText;
	private int noteId;
	private String picUrl;
	private String title;
	private ImageLoader imageLoader;
	private LinearLayout noteLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	noteId = getArguments().getInt("ID");
    	picUrl = getArguments().getString("PicUrl");
    	title = getArguments().getString("Title");
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.item_restaurant_note, container, false);
        findViews();
        setViews();
        return myFragmentView;
    }

	private void setViews() {
		noteText.setText(title);
		imageLoader = new ImageLoader(getActivity().getApplicationContext(), 150);
		if (picUrl == null || picUrl.equals("null") ) {
			noteImage.setImageResource(R.drawable.icon);
        } else {
            imageLoader.DisplayImage(picUrl, noteImage);
        }
		
		noteLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), RestaurantNoteActivity.class);
                startActivity(intent);
            }
        });
		
	}

	private void findViews() {
		 noteImage =(ImageView)myFragmentView.findViewById(R.id.note_image);
		 noteText = (TextView)myFragmentView.findViewById(R.id.note_title);
		 noteLayout = (LinearLayout)myFragmentView.findViewById(R.id.note_fragment);
	}

	public static Fragment newInstance(int id, String picUrl, String title) {
		RestaurantNotesFragment fragment = new RestaurantNotesFragment();
        Bundle bdl = new Bundle();
        bdl.putInt("ID", id);
        bdl.putString("PicUrl", picUrl);
        bdl.putString("Title", title);
        fragment.setArguments(bdl);
        return fragment;
	}


}
