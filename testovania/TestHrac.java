package sk.upjs.ics.android.beachcoachapp.testovania;

import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class TestHrac {

    private static ArrayList<TestHrac> testovanyHrac= new ArrayList<>();

    private String menoTestovaneho;
    private int pocetOpakovani;
    private int likeDislike;
    private int okX;
    private int coTestujem; //1-smec, 2-prsty, 3-bager, 4-servis, 5-blok
    private String modeUder;
    private String wayUder;

    public String getModeUder() {
        return modeUder;
    }

    public void setModeUder(String modeUder) {
        this.modeUder = modeUder;
    }

    public String getWayUder() {
        return wayUder;
    }

    public void setWayUder(String wayUder) {
        this.wayUder = wayUder;
    }

    public int getCoTestujem() {
        return coTestujem;
    }

    public void setCoTestujem(int coTestujem) {
        this.coTestujem = coTestujem;
    }

    public int getLikeDislike() {
        return likeDislike;
    }

    public void setLikeDislike(int likeDislike) {
        this.likeDislike = likeDislike;
    }

    public int getOkX() {
        return okX;
    }

    public void setOkX(int okX) {
        this.okX = okX;
    }

    public TestHrac(String menoTestovaneho, int pocetOpakovani, int presne, int ciste) {
        this.menoTestovaneho = menoTestovaneho;
        this.pocetOpakovani = pocetOpakovani;
        this.likeDislike = presne;
        this.okX = ciste;
    }

    public TestHrac(String menoTestovaneho, int pocetOpakovani, int coTestujem) {
        this.menoTestovaneho = menoTestovaneho;
        this.pocetOpakovani = pocetOpakovani;
        this.coTestujem = coTestujem;
    }

    public TestHrac() {
    }

    public ArrayList<TestHrac> getTestovanyHrac() {
        return testovanyHrac;
    }

    public void setTestovanyHrac(ArrayList<TestHrac> testovanyHrac) {
        this.testovanyHrac = testovanyHrac;
    }

    public String getMenoTestovaneho() {
        return menoTestovaneho;
    }

    public void setMenoTestovaneho(String menoTestovaneho) {
        this.menoTestovaneho = menoTestovaneho;
    }

    public int getPocetOpakovani() {
        return pocetOpakovani;
    }

    public void setPocetOpakovani(int pocetOpakovani) {
        this.pocetOpakovani = pocetOpakovani;
    }

    public void clearList(){
        testovanyHrac.clear();
    }

    public void addToList(TestHrac t){
        clearList();
        testovanyHrac.add(t);
        Log.d(TAG, "addToList: "+testovanyHrac.get(0).getMenoTestovaneho()+" "+testovanyHrac.get(0).getPocetOpakovani()+" "+testovanyHrac.get(0).getCoTestujem());
        System.out.println(testovanyHrac.get(0).getMenoTestovaneho()+" "+testovanyHrac.get(0).getPocetOpakovani()+" -----------------------------------");


    }

    @Override
    public String toString() {
        return "TestHrac{" +
                "menoTestovaneho='" + menoTestovaneho + '\'' +
                ", pocetOpakovani=" + pocetOpakovani +
                ", likeDislike=" + likeDislike +
                ", okX=" + okX +
                ", coTestujem=" + coTestujem +
                ", modeUder='" + modeUder + '\'' +
                ", wayUder='" + wayUder + '\'' +
                '}';
    }
}
