package com.ninjup.votosya.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import static com.ninjup.votosya.data.api.Constants.Serialized.CANDIDATOS;
import static com.ninjup.votosya.data.api.Constants.Serialized.MESAS_REPORTADAS;
import static com.ninjup.votosya.data.api.Constants.Serialized.MESAS_TOTALES;
import static com.ninjup.votosya.data.api.Constants.Serialized.PORCENTAJE_MESAS;
import static com.ninjup.votosya.data.api.Constants.Serialized.VOTOS_TOTALES;

/**
 * Created by Njara on 13-07-2017.
 */

public class Result implements Parcelable {
    public static final Creator<Result> CREATOR = new Creator<Result>() {

        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
    @SerializedName(MESAS_REPORTADAS)
    public int mesas_reportadas;

    @SerializedName(MESAS_TOTALES)
    public int mesas_totales;

    @SerializedName(PORCENTAJE_MESAS)
    public String porcentaje_mesas;

    @SerializedName(VOTOS_TOTALES)
    public int votos_totales;

    @SerializedName(CANDIDATOS)
    public ArrayList<Candidate> candidatos;



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mesas_reportadas);
        dest.writeInt(this.mesas_totales);
        dest.writeString(this.porcentaje_mesas);
        dest.writeInt(this.votos_totales);
        dest.writeSerializable(this.candidatos);
    }

    protected Result(Parcel in) {
        this.mesas_reportadas = in.readInt();
        this.mesas_totales = in.readInt();
        this.porcentaje_mesas = in.readString();
        this.votos_totales = in.readInt();
        this.candidatos = in.createTypedArrayList(Candidate.CREATOR);

    }

}