package sk.upjs.ics.android.beachcoachapp.dochadzka;

public class UcastnikTr {
    private String hracID;
    private boolean ucast;
    private String menoTr;
    private String priezviskoTr;

    public UcastnikTr(String hracID, boolean ucast, String meno, String priezvisko) {
        this.hracID = hracID;
        this.ucast = ucast;
        this.menoTr = meno;
        this.priezviskoTr = priezvisko;
    }

    public UcastnikTr(){

    }

    public String getMenoTr() {
        return menoTr;
    }

    public void setMenoTr(String menoTr) {
        this.menoTr = menoTr;
    }

    public String getPriezviskoTr() {
        return priezviskoTr;
    }

    public void setPriezviskoTr(String priezviskoTr) {
        this.priezviskoTr = priezviskoTr;
    }

    public String getHracID() {
        return hracID;
    }


    public void setHracID(String hracID) {
        this.hracID = hracID;
    }

    public boolean isUcast() {
        return ucast;
    }

    public void setUcast(boolean ucast) {
        this.ucast = ucast;
    }
}



