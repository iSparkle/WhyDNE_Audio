package ca.skyfire.whydneaudio.helper;

import android.content.Intent;
import android.content.ServiceConnection;

import ca.skyfire.whydneaudio.StandardScreen;

public class ServiceIntent extends Intent {

	public boolean connected = false;
	public ServiceConnection idl = null;

	public ServiceIntent(StandardScreen standardScreen, Class<?> serviceClass) {
		super (standardScreen, serviceClass);
	}

}
