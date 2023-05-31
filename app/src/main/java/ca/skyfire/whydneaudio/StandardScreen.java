package ca.skyfire.whydneaudio;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import ca.skyfire.whydneaudio.model.ContentItem;
import ca.skyfire.whydneaudio.model.ContentItemList;
import ca.skyfire.whydneaudio.services.MediaScanner;
import ca.skyfire.whydneaudio.helper.Constants;
import ca.skyfire.whydneaudio.helper.ServiceIntent;
import ca.skyfire.whydneaudio.helper.ServiceMessageHandler;

public class StandardScreen extends AppCompatActivity {

	ServiceIntent mediaScanner;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		StandardScreen.onActivityCreateSetTheme(this);

		IntentFilter intentFilter;

		//All standard screens should be ready for MediaScan results
		intentFilter = new IntentFilter(Constants.MSG_MEDIA_DISCOVERED);
		registerReceiver(OnMediaDiscoveredInternal, intentFilter);

		//Make sure media service is running

		//Make sure media-scanner-service is running
		mediaScanner = new ServiceIntent(this, MediaScanner.class);
		startService(mediaScanner);
		bindService(MediaScanner.class, mediaScanner, serviceConnectionMedia);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				//Trigger a media scan. MediaService will decide if a scan is actually required
				//If a scan is not required, Media Service needs to reply to this Activity with cached data
				Intent intent = new Intent(Constants.MSG_MEDIA_INITSCAN);
				sendBroadcast(intent);
				//finish();
			}
		}, 3000);

	}

	@Override
	protected void onDestroy() {
		unbindService(mediaScanner);
		unregisterReceiver(OnMediaDiscoveredInternal);
		super.onDestroy();
	}

	public void bindService(Class<?> cls, ServiceIntent svci, ServiceConnection idl) {
		super.bindService(new Intent(this, cls), idl, Context.BIND_NOT_FOREGROUND);
		svci.idl = idl;
		svci.connected = true;
	}

	public void btnAllAlbumsClick(View v) {

		if (this.getClass() != AllAlbumsActivity.class) {
			Intent intent = new Intent(this, AllAlbumsActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);
		}

	}

	public void btnAllArtistsClick(View v) {

		if (this.getClass() != AllArtistsActivity.class) {
			Intent intent = new Intent(this, AllArtistsActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);
		}

	}

	public void btnAllSongsClick(View v) {

		if (this.getClass() != AllSongsActivity.class) {
			Intent intent = new Intent(this, AllSongsActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);
		}

	}

	public void btnAllTagsClick(View v) {

		if (this.getClass() != AllTagsActivity.class) {
			Intent intent = new Intent(this, AllTagsActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);
		}

	}

	public void btnAllFoldersClick(View v) {

		if (this.getClass() != AllFoldersActivity.class) {
			Intent intent = new Intent(this, AllFoldersActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);
		}

	}

	public void btnFAQClick(View v) {

		if (this.getClass() != FAQActivity.class) {
			Intent intent = new Intent(this, FAQActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);
		}

	}

	public void btnPrivacyClick(View v) {

		if (this.getClass() != PrivacyActivity.class) {
			Intent intent = new Intent(this, PrivacyActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);
		}

	}

	public void btnSettingsClick(View v) {

		if (this.getClass() != SettingsActivity.class) {
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
		}

	}

	public static void onActivityCreateSetTheme(Activity activity) {
		//Log.i("WHYDNE", sTheme);
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(activity);
		String sTheme = pref.getString("list_preference_theme", "settings_theme_winter_day");
		Log.i("WHYDNE", sTheme);
		switch (sTheme) {
			default:
			case "settings_theme_winter_day":
				activity.setTheme(R.style.Theme_WhyDNEAudio);
				Log.i("WHYDNE", "1");
				break;
			case "settings_theme_summer_day":
				activity.setTheme(R.style.Theme_WhyDNESummerDay);
				Log.i("WHYDNE", "2");
				break;
		}
	}

	private final BroadcastReceiver OnMediaDiscoveredInternal = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {

			Bundle bundle = intent.getExtras();
			if (bundle == null) return;

			ArrayList<ContentItem> m;
			m = bundle.<ContentItem>getParcelableArrayList("mediaItems");
			if (m == null) return;

			ContentItemList mList = new ContentItemList();
			for(ContentItem mm : m)
				mList.add(mm);

			OnMediaDiscovered(mList);
		}
	};

	public void OnMediaDiscovered(ContentItemList list) {

	}

	Messenger mediaSeviceMessenger = null;
	final Messenger mediaSeviceMessengerClient = new Messenger(new ServiceMessageHandler(this));
	private final ServiceConnection serviceConnectionMedia = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
			mediaSeviceMessenger = new Messenger(iBinder);
			try {
				Message msg = Message.obtain(null, Constants.MSG_REGISTER_CLIENT);
				msg.replyTo = mediaSeviceMessengerClient;
				mediaSeviceMessenger.send(msg);
			} catch (RemoteException e) {
				throw new RuntimeException(e);
			}

		}

		@Override
		public void onServiceDisconnected(ComponentName componentName) {
			mediaSeviceMessenger = null;
		}

	};

	void unbindService(ServiceIntent svci) {

		if (svci.connected) {

			if (svci.idl != null) {
				Message msg = Message.obtain(null, Constants.MSG_UNREGISTER_CLIENT);
				//msg.replyTo = ???
				try {
					mediaSeviceMessenger.send(msg);
				} catch (RemoteException e) {
					throw new RuntimeException(e);
				}
			}

			super.unbindService(svci.idl);

		}

	}

}
