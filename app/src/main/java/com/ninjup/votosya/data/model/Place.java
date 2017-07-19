package com.ninjup.votosya.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import static com.ninjup.votosya.data.api.Constants.Serialized.ADDRESS;
import static com.ninjup.votosya.data.api.Constants.Serialized.ID;
import static com.ninjup.votosya.data.api.Constants.Serialized.NAME;
import static com.ninjup.votosya.data.api.Constants.Serialized.TABLES;

/**
 * Created by Njara on 11-07-2017.
 */

public class Place implements Parcelable {
    public static final Creator<Place> CREATOR = new Creator<Place>() {

        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
    @SerializedName(ID)
    public int id;

    @SerializedName(NAME)
    public String nombre;

    @SerializedName(ADDRESS)
    public String direccion;

    @SerializedName(TABLES)
    public ArrayList<Table> mesas;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.direccion);
        dest.writeSerializable(this.mesas);
    }

    protected Place(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
        this.direccion = in.readString();
        this.mesas = in.createTypedArrayList(Table.CREATOR);
    }

}
