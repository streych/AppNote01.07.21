package com.example.note0107;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable{
    private final String id;
    private String title;
    private String date;
    private String description;

    public Note(String id, String title, String date, String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
    }

    protected Note(Parcel in) {
        id = in.readString();
        title = in.readString();
        date = in.readString();
        description = in.readString();
    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };



    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(description);
    }
}
