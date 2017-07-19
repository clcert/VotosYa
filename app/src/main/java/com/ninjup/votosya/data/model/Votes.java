package com.ninjup.votosya.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import static com.ninjup.votosya.data.api.Constants.Serialized.CANDIDATE;
import static com.ninjup.votosya.data.api.Constants.Serialized.VOTES;

/**
 * Created by Njara on 11-07-2017.
 */

public class Votes implements Parcelable {
    public static final Creator<Votes> CREATOR = new Creator<Votes>() {

        public Votes createFromParcel(Parcel source) {
            return new Votes(source);
        }

        public Votes[] newArray(int size) {
            return new Votes[size];
        }
    };
    @SerializedName(CANDIDATE)
    public int id;

    @SerializedName(VOTES)
    public int votos;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.votos);
    }

    protected Votes(Parcel in) {
        this.id = in.readInt();
        this.votos = in.readInt();
    }

    public Votes(int candidate, int votes) {
        id = candidate;
        votos = votes;
    }

}
