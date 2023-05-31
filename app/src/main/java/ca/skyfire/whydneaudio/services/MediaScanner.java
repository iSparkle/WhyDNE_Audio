package ca.skyfire.whydneaudio.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.StrictMode;

import ca.skyfire.whydneaudio.helper.Constants;
import ca.skyfire.whydneaudio.model.ContentItem;
import ca.skyfire.whydneaudio.model.ContentItemList;

public class MediaScanner extends Service {

	private boolean needsMediaCheck = true;

	public MediaScanner() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		//throw new UnsupportedOperationException("Not yet implemented");
		return null;
	}

	public void onCreate() {

		super.onCreate();

		//Debug
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		//Main
		HandlerThread main = new HandlerThread("MediaScanner Thread");
		main.start();
		Looper looper = main.getLooper();
		Handler handler = new Handler(looper);

		IntentFilter intentFilter;

		intentFilter = new IntentFilter(Constants.MSG_MEDIA_INITSCAN);
		registerReceiver(onInitMediaScan, intentFilter, null, handler);

	}

	@Override
	public void onDestroy() {

		unregisterReceiver(onInitMediaScan);

		super.onDestroy();

	}

	private BroadcastReceiver onInitMediaScan = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {

			ContentItemList list = new ContentItemList();
			ContentItem m;
			m = new ContentItem(); m.title = "Sample 1";
			list.add(m);
			m = new ContentItem(); m.title = "Sample 2";
			list.add(m);
			m = new ContentItem(); m.title = "Sample 3";
			list.add(m);

			Bundle bundle = new Bundle();
			bundle.putParcelableArrayList("mediaItems", list);

			Intent newIntent = new Intent();
			newIntent.putExtras(bundle);
			newIntent.setAction(Constants.MSG_MEDIA_DISCOVERED);
			sendBroadcast(newIntent);

		}

	};

}