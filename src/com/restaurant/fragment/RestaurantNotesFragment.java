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
	private ImageLoader imageLoader;
	private LinearLayout noteLayout;
	private int noteId;
	private int rId;
	private String noteTitle;
	private String noteLink;
	private double noteY;
	private double noteX;
	private String notePic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	 noteId = getArguments().getInt("NoteId");
         rId = getArguments().getInt("RestaurantId");
         noteTitle = getArguments().getString("NoteTitle");
         noteLink = getArguments().getString("NoteLink");
         noteX = getArguments().getDouble("NoteX");
         noteY = getArguments().getDouble("NoteY");
         notePic = getArguments().getString("NotePic");
        
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
		noteText.setText(noteTitle);
		imageLoader = new ImageLoader(getActivity().getApplicationContext(), 150);
		if (notePic == null || notePic.equals("null") ) {
			noteImage.setImageResource(R.drawable.icon);
        } else {
            imageLoader.DisplayImage(notePic, noteImage);
        }
		
		noteLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), RestaurantNoteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("NoteId", noteId);
            	bundle.putInt("RestaurantId",rId);
            	bundle.putString("NoteTitle", noteTitle);
            	bundle.putString("NoteLink", noteLink);
            	bundle.putDouble("NoteX", noteX);
            	bundle.putDouble("NoteY", noteY);
            	bundle.putString("NotePic", notePic);
            	intent.putExtras(bundle);
                startActivity(intent);
            }
        });
		
	}

	private void findViews() {
		 noteImage =(ImageView)myFragmentView.findViewById(R.id.note_image);
		 noteText = (TextView)myFragmentView.findViewById(R.id.note_title);
		 noteLayout = (LinearLayout)myFragmentView.findViewById(R.id.note_fragment);
	}


	public static Fragment newInstance(int id, int restaurantId, String title, String link, Double x, Double y, String picUrl) {
		RestaurantNotesFragment fragment = new RestaurantNotesFragment();
        Bundle bdl = new Bundle();
        
        bdl.putInt("NoteId", id);
        bdl.putInt("RestaurantId", restaurantId);
        bdl.putString("NoteTitle", title);
        bdl.putString("NoteLink", link);
        bdl.putDouble("NoteX", x);
        bdl.putDouble("NoteY", y);
        bdl.putString("NotePic", picUrl);
        
        fragment.setArguments(bdl);
        return fragment;
	}


}
