package sk.upjs.ics.android.beachcoachapp.objects;

import com.google.firebase.firestore.DocumentId;

public class Fine {

    private String idTren;
    private double cenaFi;
    private String popisFi;
    private String stringUID;

    @DocumentId
    public String getStringUID() {
        return stringUID;
    }

    @DocumentId
    public void setStringUID(String stringUID) {
        this.stringUID = stringUID;
    }

    public Fine(String idTren , double cenaFi, String popisFi) {
        this.idTren = idTren;
        this.cenaFi = cenaFi;
        this.popisFi = popisFi;
    }

    public Fine(){

    }

    public double getCenaFi() {
        return cenaFi;
    }

    public void setCenaFi(double cenaFi) {
        this.cenaFi = cenaFi;
    }

    public String getPopisFi() {
        return popisFi;
    }

    public void setPopisFi(String popisFi) {
        this.popisFi = popisFi;
    }

    public String getIdTren() {
        return idTren;
    }

    public void setIdTren(String idTren) {
        this.idTren = idTren;
    }

    @Override
    public String toString() {
        return "Fine{" +
                "idTren='" + idTren + '\'' +
                ", cenaFi=" + cenaFi +
                ", popisFi='" + popisFi + '\'' +
                '}';
    }
}
