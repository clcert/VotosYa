package com.ninjup.votosya.data.api;

import static com.ninjup.votosya.data.api.Constants.Params.TABLE_ID;

/**
 * Created by Njara on 11-07-2017.
 */

public class Constants {
  //  public static final String VOTOSYA_API = "http://encintadosrojas.ninjup.com";
    public static final String VOTOSYA_API = "http://34.223.255.19";

    public static final class Endpoint {

       // public static final String CANDIDATE_SEARCH = "/votacion/candidatos.json";
        public static final String CANDIDATE_SEARCH = "/api/candidatos/";
        public static final String PLACE_SEARCH = "/api/recintos/";
        public static final String VOTES_REPORT = "/api/reporte/";
        public static final String RESULTADOS = "/api/resultados/";
        public static final String TABLE_PLACES = "/v1/{" + TABLE_ID + "}search?type=artist";
    }

    public static final class Params {
        public static final String TABLE_ID = "artistId";
    }

    public static final class Serialized {

        public static final String NAME = "nombre";
        public static final String PHOTO = "foto";
        public static final String PARTY = "partido";
        public static final String ADDRESS = "direccion";
        public static final String ID = "id";
        public static final String TABLES = "mesas";
        public static final String NUMBER = "numero";
        public static final String VOTES = "votos";
        public static final String TABLE = "mesa";
        public static final String COUNTS = "conteos";
        public static final String CANDIDATE = "candidato";
        public static final String PORCENT = "porcentaje";
        public static final String MESAS_REPORTADAS = "mesas_reportadas";
        public static final String MESAS_TOTALES = "mesas_totales";
        public static final String PORCENTAJE_MESAS = "porcentaje_mesas";
        public static final String VOTOS_TOTALES = "votos_totales";
        public static final String CANDIDATOS = "candidatos";


    }

    public static final class Deserializer {

        public static final String CANDIDATES = "candidates";
        public static final String PARTYS = "partys";
        public static final String PLACES = "places";
        public static final String TABLES = "tables";
        public static final String VOTES = "votes";
        public static final String ITEMS = "items";
    }
}
