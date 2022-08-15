package sk.upjs.ics.android.beachcoachapp.objects;

import com.google.firebase.firestore.DocumentId;

public class Exercice {

    private String idTren;
    private double casEx;
    private String popisEx;
    private String nazovEx;
    private String stringUID;

    public Exercice(){

    }


    @DocumentId
    public String getStringUID() {
        return stringUID;
    }

    @DocumentId
    public void setStringUID(String stringUID) {
        this.stringUID = stringUID;
    }

    public Exercice(String idTren, double casEx, String popisEx, String nazovEx) {
        this.idTren = idTren;
        this.casEx = casEx;
        this.popisEx = popisEx;
        this.nazovEx = nazovEx;
    }



    public String getNazovEx() {
        return nazovEx;
    }

    public void setNazovEx(String nazovEx) {
        this.nazovEx = nazovEx;
    }

    public String getIdTren() {
        return idTren;
    }

    public void setIdTren(String idTren) {
        this.idTren = idTren;
    }

    public double getCasEx() {
        return casEx;
    }

    public void setCasEx(double casEx) {
        this.casEx = casEx;
    }

    public String getPopisEx() {
        return popisEx;
    }

    public void setPopisEx(String popisEx) {
        this.popisEx = popisEx;
    }
}
