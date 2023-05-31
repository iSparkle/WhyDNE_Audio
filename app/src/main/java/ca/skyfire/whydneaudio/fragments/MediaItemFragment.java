package ca.skyfire.whydneaudio.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.skyfire.whydneaudio.R;
import ca.skyfire.whydneaudio.model.ContentItem;

public class MediaItemFragment extends Fragment {

	ContentItem contentItem;

	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	//private static final String ARG_PARAM1 = "param1";
	//private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	public MediaItemFragment() {
		// Required empty public constructor
	}

	public static MediaItemFragment newInstance(ContentItem item) {
		MediaItemFragment fragment = new MediaItemFragment();
		Bundle args = new Bundle();
		//args.putString(ARG_PARAM1, item.title);
		//args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			//mParam1 = getArguments().getString(ARG_PARAM1);
			//mParam2 = getArguments().getString(ARG_PARAM2);
			ContentItem m = getArguments().getParcelable("contentItem");
			contentItem = m;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
													 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_media_item, container, false);
		TextView titleField = v.findViewById(R.id.fragmentMediaItemTitle);
		titleField.setText(contentItem.title);
		return v;
	}

	public void setMediaItem(ContentItem m) {
		contentItem = m;
	}
}