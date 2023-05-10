package ca.skyfire.whydneaudio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StandardScreen extends AppCompatActivity {

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		//Make sure media service is running

		//Make sure media-scanner-service is running

	}

	public void btnAllSongsClick(View v) {

		if (this.getClass() != AllSongsActivity.class) {
			Intent intent = new Intent(this, AllSongsActivity.class);
			startActivity(intent);
		}

	}

	public void btnAllTagsClick(View v) {

		if (this.getClass() != AllTagsActivity.class) {
			Intent intent = new Intent(this, AllTagsActivity.class);
			startActivity(intent);
		}

	}

	public void btnAllFoldersClick(View v) {

		if (this.getClass() != AllFoldersActivity.class) {
			Intent intent = new Intent(this, AllFoldersActivity.class);
			startActivity(intent);
		}

	}

}
