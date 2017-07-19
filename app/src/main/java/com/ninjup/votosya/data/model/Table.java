package com.ninjup.votosya.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import static com.ninjup.votosya.data.api.Constants.Serialized.ID;
import static com.ninjup.votosya.data.api.Constants.Serialized.NUMBER;

/**
 * Created by Njara on 11-07-2017.
 */

public class Table implements Parcelable {
    public static final Creator<Table> CREATOR = new Creator<Table>() {

        public Table createFromParcel(Parcel source) {
            return new Table(source);
        }

        public Table[] newArray(int size) {
            return new Table[size];
        }
    };
    @SerializedName(ID)
    public int id;

    @SerializedName(NUMBER)
    public int numero;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.numero);
    }

    protected Table(Parcel in) {
        this.id = in.readInt();
        this.numero = in.readInt();
    }

}
