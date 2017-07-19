package com.ninjup.votosya.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import static com.ninjup.votosya.data.api.Constants.Serialized.COUNTS;
import static com.ninjup.votosya.data.api.Constants.Serialized.TABLE;

/**
 * Created by Njara on 12-07-2017.
 */

public class Reporte implements Parcelable {
    public static final Creator<Reporte> CREATOR = new Creator<Reporte>() {

        public Reporte createFromParcel(Parcel source) {
            return new Reporte(source);
        }

        public Reporte[] newArray(int size) {
            return new Reporte[size];
        }
    };
    @SerializedName(TABLE)
    public int mesa;

    @SerializedName(COUNTS)
    public ArrayList<Votes> conteos;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mesa);
        dest.writeSerializable(this.conteos);
    }

    protected Reporte(Parcel in) {
        this.mesa = in.readInt();
        this.conteos = in.createTypedArrayList(Votes.CREATOR);
    }

    public Reporte() {
        mesa = 0;
        conteos = new ArrayList<Votes>();
    }

    public JsonObject toJson() {
        JsonObject reporte = new JsonObject();
        reporte.addProperty("mesa", mesa);
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < conteos.size(); i++) {
            JsonObject voto = new JsonObject();
            voto.addProperty("candidato", conteos.get(i).id);
            voto.addProperty("votos", conteos.get(i).votos);
            jsonArray.add(voto);
        }
        reporte.add("conteos", jsonArray);
        return reporte;
    }

}
