package ca.skyfire.whydneaudio;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ca.skyfire.whydneaudio.fragments.MediaItemFragment;
import ca.skyfire.whydneaudio.model.ContentItem;
import ca.skyfire.whydneaudio.model.ContentItemList;

public class AllSongsActivity extends StandardScreen {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allsongs);
	}

	public void OnMediaDiscovered(ContentItemList list) {

		FragmentManager fm = getSupportFragmentManager();

		FragmentTransaction ft = fm.beginTransaction();

		//list.stream().map(MediaItemFragment::newInstance).forEach(mediaFragment -> ft.add(R.id.songList, mediaFragment));
		for (ContentItem m : list) {
			MediaItemFragment mediaFragment = new MediaItemFragment();
			//mediaFragment.requireArguments().putParcelable("mediaItem", m);
			mediaFragment.setMediaItem(m);
			ft.add(R.id.songList, mediaFragment);
		}

		ft.commitAllowingStateLoss();

	}

}