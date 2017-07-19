package com.ninjup.votosya.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import static com.ninjup.votosya.data.api.Constants.Serialized.PORCENT;
import static com.ninjup.votosya.data.api.Constants.Serialized.VOTES;
import static com.ninjup.votosya.data.api.Constants.Serialized.ID;
import static com.ninjup.votosya.data.api.Constants.Serialized.NAME;
import static com.ninjup.votosya.data.api.Constants.Serialized.PARTY;
import static com.ninjup.votosya.data.api.Constants.Serialized.PHOTO;

/**
 * Created by Njara on 11-07-2017.
 */

public class Candidate implements Parcelable {
    public static final Creator<Candidate> CREATOR = new Creator<Candidate>() {

        public Candidate createFromParcel(Parcel source) {
            return new Candidate(source);
        }

        public Candidate[] newArray(int size) {
            return new Candidate[size];
        }
    };
    @SerializedName(ID)
    public int id;

    @SerializedName(NAME)
    public String nombre;

    @SerializedName(PARTY)
    public String partido;

    @SerializedName(PHOTO)
    public String foto;

    @SerializedName(VOTES)
    public int votos;

    @SerializedName(PORCENT)
    public String porcentaje;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.partido);
        dest.writeString(this.foto);
        dest.writeInt(this.votos);
        dest.writeString(this.porcentaje);
    }

    protected Candidate(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
        this.partido = in.readString();
        this.foto = in.readString();
        this.votos = in.readInt();
        this.porcentaje = in.readString();
    }

}
