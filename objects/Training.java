package sk.upjs.ics.android.beachcoachapp.objects;

import com.google.firebase.firestore.DocumentId;

import java.util.ArrayList;

import sk.upjs.ics.android.beachcoachapp.dochadzka.UcastnikTr;

public class Training {

    private String stringUID;
    private String idTrener;
    private String datum;
    private String cas;
    private ArrayList<UcastnikTr> ucastnici;
    private String nazov;
    private Boolean koniec;

    @DocumentId
    public String getStringUID() {
        return stringUID;
    }

    public Training(){
    }

    public Training(String idTrener, String datum, String cas, ArrayList<UcastnikTr> ucastnici, String nazov, Boolean koniec) {
        this.idTrener = idTrener;
        this.datum = datum;
        this.cas = cas;
        this.ucastnici = ucastnici;
        this.nazov = nazov;
        this.koniec = koniec;
    }

    public Boolean getKoniec() {
        return koniec;
    }

    public void setKoniec(Boolean koniec) {
        this.koniec = koniec;
    }

    public void setStringUID(String stringUID) {
        this.stringUID = stringUID;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public String getIdTrener() {
        return idTrener;
    }

    public void setIdTrener(String idTrener) {
        this.idTrener = idTrener;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public ArrayList<UcastnikTr> getUcastnici() {
        return ucastnici;
    }

    public void setUcastnici(ArrayList<UcastnikTr> ucastnici) {
        this.ucastnici = ucastnici;
    }
}

