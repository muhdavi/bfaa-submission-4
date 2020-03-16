package com.onesoul.moviecataloguels.tvshow;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class Tvshow implements Parcelable {
    private int mId;
    private String mType;
    private String mTitle;
    private String mRelease;
    private String mOverview;
    private String mPhoto;
    private String mPopularity;
    private String mVote;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mName) {
        this.mTitle = mTitle;
    }

    public String getmRelease() {
        return mRelease;
    }

    public void setmRelease(String mRelease) {
        this.mRelease = mRelease;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mDesc) {
        this.mOverview = mOverview;
    }

    public String getmPhoto() {
        return mPhoto;
    }

    public void setmPhoto(String mPhoto) {
        this.mPhoto = mPhoto;
    }

    public String getmPopularity() {
        return mPopularity;
    }

    public void setmPopularity(String mPopularity) {
        this.mPopularity = mPopularity;
    }

    public String getmVote() {
        return mVote;
    }

    public void setmVote(String mVote) {
        this.mVote = mVote;
    }

    public Tvshow() {

    }

    // Object Parcelable
    public Tvshow(JSONObject object) {
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd, mm, yyyy");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            int id = object.getInt("id");
            String description = object.getString("overview");
            String popularity = object.getString("popularity");
            String release = object.getString("first_air_date");
            String title = object.getString("name");
            String url_image = object.getString("poster_path");
            String vote = object.getString("vote_average");

            this.mId = id;
            this.mOverview = description;
            this.mPopularity = popularity;
            this.mRelease = formatter.format(dateFormat.parse(release));
            this.mTitle = title;
            this.mPhoto = url_image;
            this.mVote = vote;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mType);
        dest.writeString(this.mTitle);
        dest.writeString(this.mRelease);
        dest.writeString(this.mOverview);
        dest.writeString(this.mPhoto);
        dest.writeString(this.mPopularity);
        dest.writeString(this.mVote);
    }

    private Tvshow(Parcel in) {
        this.mId = in.readInt();
        this.mType = in.readString();
        this.mTitle = in.readString();
        this.mRelease = in.readString();
        this.mOverview = in.readString();
        this.mPhoto = in.readString();
        this.mPopularity = in.readString();
        this.mVote = in.readString();
    }

    public static final Creator<Tvshow> CREATOR = new Creator<Tvshow>() {
        @Override
        public Tvshow createFromParcel(Parcel source) {
            return new Tvshow(source);
        }

        @Override
        public Tvshow[] newArray(int size) {
            return new Tvshow[size];
        }
    };
}
