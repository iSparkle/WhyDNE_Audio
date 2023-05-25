package ca.skyfire.whydneaudio;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class StandardScreen extends AppCompatActivity {

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		StandardScreen.onActivityCreateSetTheme(this);

		//Make sure media service is running

		//Make sure media-scanner-service is running

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

}
