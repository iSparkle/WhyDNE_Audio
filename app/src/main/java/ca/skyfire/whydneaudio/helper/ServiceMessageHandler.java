package ca.skyfire.whydneaudio.helper;

import android.os.Handler;
import android.os.Message;

import ca.skyfire.whydneaudio.StandardScreen;


public class ServiceMessageHandler extends Handler {

	StandardScreen owner;

	public ServiceMessageHandler(StandardScreen parentScreen) {
		super();
		owner = parentScreen;
	}

	@Override
	public void handleMessage(Message msg) {

		/*switch(msg.what) {
			case Constants.MSG_MEDIA_DISCOVERED:
				ContentItemList list = new ContentItemList();
				ContentItem m;
				m = new ContentItem(); m.title = "Sample 1";
				list.add(m);
				m = new ContentItem(); m.title = "Sample 2";
				list.add(m);
				m = new ContentItem(); m.title = "Sample 3";
				list.add(m);
				owner.OnMediaDiscovered(list);
				break;
		}*/

	}

}
