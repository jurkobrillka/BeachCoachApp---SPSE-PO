package sk.upjs.ics.android.beachcoachapp.objects;

import com.google.firebase.firestore.DocumentId;

public class Player {

    private String idTren;
    private String menoPl;
    private String priezviskoPl;
    private String pohlaviePl;
    private String datumNarPl;
    private double vyskaPl;
    private double vahaPl;
    private String klub;
    private String velkostObleceniaPl;
    private double pokuty;
    private String stringUID;
    private String mail;
    private String telC;
    private int treningyAll;
    private int treningyTrue;
    private double treningyPer;
    private LastPerformance lastPerformanceX;
    private FinalMovementTest finalMovementTest;
    private FinalSpecificTest finalSpecificTest;
    private FinalSpecificTest StartSpecificTest;
    private FinalMovementTest StarMovementTest;

    //arraylist<Fine> zoznam pokut
    //arraylist<Excercise> vykony



    @DocumentId
    public String getStringUID() {
        return stringUID;
    }

    @DocumentId
    public void setStringUID(String stringUID) {
        this.stringUID = stringUID;
    }

    public Player() {
    }

    public LastPerformance getLastPerformanceX() {
        return lastPerformanceX;
    }

    public void setLastPerformanceX(LastPerformance lastPerformanceX) {
        this.lastPerformanceX = lastPerformanceX;
    }

    public String getIdTren() {
        return idTren;
    }

    public void setIdTren(String idTren) {
        this.idTren = idTren;
    }

    public double getPokuty() {
        return pokuty;
    }

    public void setPokuty(double pokuty) {
        this.pokuty = pokuty;
    }




    public Player(String idTren, String menoPl, String priezviskoPl, String pohlaviePl, String datumNarPl, double vyskaPl, double vahaPl, String klub, String velkostObleceniaPl, double pokuty, String mail, String telC, LastPerformance lastPerformanceX, FinalMovementTest finalMovementTest, FinalSpecificTest finalSpecificTest, FinalSpecificTest startSpecificTest, FinalMovementTest starMovementTest) {
        this.idTren = idTren;
        this.menoPl = menoPl;
        this.priezviskoPl = priezviskoPl;
        this.pohlaviePl = pohlaviePl;
        this.datumNarPl = datumNarPl;
        this.vyskaPl = vyskaPl;
        this.vahaPl = vahaPl;
        this.klub = klub;
        this.velkostObleceniaPl = velkostObleceniaPl;
        this.pokuty = pokuty;
        this.mail = mail;
        this.telC = telC;
        this.treningyAll = 0;
        this.treningyTrue = 0;
        this.treningyPer = 0.0;
        this.lastPerformanceX = lastPerformanceX;
        this.finalMovementTest = finalMovementTest;
        this.finalSpecificTest = finalSpecificTest;
        this.StartSpecificTest = startSpecificTest;
        this.StarMovementTest = starMovementTest;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelC() {
        return telC;
    }

    public void setTelC(String telC) {
        this.telC = telC;
    }

    public String getMenoPl() {
        return menoPl;
    }

    public void setMenoPl(String menoPl) {
        this.menoPl = menoPl;
    }

    public String getPriezviskoPl() {
        return priezviskoPl;
    }

    public void setPriezviskoPl(String priezviskoPl) {
        this.priezviskoPl = priezviskoPl;
    }

    public String getPohlaviePl() {
        return pohlaviePl;
    }

    public void setPohlaviePl(String pohlaviePl) {
        this.pohlaviePl = pohlaviePl;
    }

    public String getDatumNarPl() {
        return datumNarPl;
    }

    public void setDatumNarPl(String datumNarPl) {
        this.datumNarPl = datumNarPl;
    }

    public double getVyskaPl() {
        return vyskaPl;
    }

    public void setVyskaPl(double vyskaPl) {
        this.vyskaPl = vyskaPl;
    }

    public double getVahaPl() {
        return vahaPl;
    }

    public void setVahaPl(double vahaPl) {
        this.vahaPl = vahaPl;
    }

    public String getKlub() {
        return klub;
    }

    public void setKlub(String klub) {
        this.klub = klub;
    }

    public String getVelkostObleceniaPl() {
        return velkostObleceniaPl;
    }

    public void setVelkostObleceniaPl(String velkostObleceniaPl) {
        this.velkostObleceniaPl = velkostObleceniaPl;
    }

    public FinalMovementTest getFinalMovementTest() {
        return finalMovementTest;
    }

    public void setFinalMovementTest(FinalMovementTest finalMovementTest) {
        this.finalMovementTest = finalMovementTest;
    }

    public FinalSpecificTest getFinalSpecificTest() {
        return finalSpecificTest;
    }

    public void setFinalSpecificTest(FinalSpecificTest finalSpecificTest) {
        this.finalSpecificTest = finalSpecificTest;
    }

    public FinalSpecificTest getStartSpecificTest() {
        return StartSpecificTest;
    }

    public void setStartSpecificTest(FinalSpecificTest startSpecificTest) {
        StartSpecificTest = startSpecificTest;
    }

    public FinalMovementTest getStarMovementTest() {
        return StarMovementTest;
    }

    public void setStarMovementTest(FinalMovementTest starMovementTest) {
        StarMovementTest = starMovementTest;
    }

    public int getTreningyAll() {
        return treningyAll;
    }

    public void setTreningyAll(int treningyAll) {
        this.treningyAll = treningyAll;
    }

    public int getTreningyTrue() {
        return treningyTrue;
    }

    public void setTreningyTrue(int treningyTrue) {
        this.treningyTrue = treningyTrue;
    }

    public double getTreningyPer() {
        return treningyPer;
    }

    public void setTreningyPer(double treningyPer) {
        this.treningyPer = treningyPer;
    }

    @Override
    public String toString() {
        return "Player{" +
                "idTren='" + idTren + '\'' +
                ", menoPl='" + menoPl + '\'' +
                ", priezviskoPl='" + priezviskoPl + '\'' +
                ", pohlaviePl='" + pohlaviePl + '\'' +
                ", datumNarPl='" + datumNarPl + '\'' +
                ", vyskaPl=" + vyskaPl +
                ", vahaPl=" + vahaPl +
                ", klub='" + klub + '\'' +
                ", velkostObleceniaPl='" + velkostObleceniaPl + '\'' +
                ", pokuty=" + pokuty +
                ", stringUID='" + stringUID + '\'' +
                ", mail='" + mail + '\'' +
                ", telC='" + telC + '\'' +
                ", lastPerformanceX=" + lastPerformanceX +
                ", finalMovementTest=" + finalMovementTest +
                ", finalSpecificTest=" + finalSpecificTest +
                ", StartSpecificTest=" + StartSpecificTest +
                ", StarMovementTest=" + StarMovementTest +
                '}';
    }
}
