package ca.skyfire.whydneaudio.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ContentItem implements Parcelable {

	//This is a media item (eg a song)
	//I didn't want to use "MediaItem" as that's used elsewhere and will cause import confusion

	public String title;

	public ContentItem() {super();}

	protected ContentItem(Parcel in) {
		super();
		title = in.readString();
	}

	public static final Creator<ContentItem> CREATOR = new Creator<ContentItem>() {
		@Override
		public ContentItem createFromParcel(Parcel in) {
			return new ContentItem(in);
		}

		@Override
		public ContentItem[] newArray(int size) {
			return new ContentItem[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel parcel, int i) {
		parcel.writeString(title);
	}
}
