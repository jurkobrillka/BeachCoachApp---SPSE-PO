package sk.upjs.ics.android.beachcoachapp.pomocky.CiernyPetro;

import java.util.ArrayList;

public class TimKOTC {
    private String tim1nazov;
    private String tim2nazov;
    private String tim3nazov;
    private String CP1;
    private String CP2;
    private String CP3;
    private String CP4;
    private String CP5;
    private static ArrayList<TimKOTC> hraciCP = new ArrayList<>();
    private static ArrayList<TimKOTC> timyAL = new ArrayList<>();

    public TimKOTC() {

    }

    public TimKOTC(String CP1, String CP2, String CP3, String CP4, String CP5) {
        this.CP1 = CP1;
        this.CP2 = CP2;
        this.CP3 = CP3;
        this.CP4 = CP4;
        this.CP5 = CP5;
    }

    public String getCP1() {
        return CP1;
    }

    public void setCP1(String CP1) {
        this.CP1 = CP1;
    }

    public String getCP2() {
        return CP2;
    }

    public void setCP2(String CP2) {
        this.CP2 = CP2;
    }

    public String getCP3() {
        return CP3;
    }

    public void setCP3(String CP3) {
        this.CP3 = CP3;
    }

    public String getCP4() {
        return CP4;
    }

    public void setCP4(String CP4) {
        this.CP4 = CP4;
    }

    public String getCP5() {
        return CP5;
    }

    public void setCP5(String CP5) {
        this.CP5 = CP5;
    }

    public static ArrayList<TimKOTC> getHraciCP() {
        return hraciCP;
    }

    public static void setHraciCP(ArrayList<TimKOTC> hraciCP) {
        TimKOTC.hraciCP = hraciCP;
    }

    public TimKOTC(String tim1nazov, String tim2nazov, String tim3nazov) {
        this.tim1nazov = tim1nazov;
        this.tim2nazov = tim2nazov;
        this.tim3nazov = tim3nazov;
    }

    public ArrayList<TimKOTC> getTimyAL() {
        return timyAL;
    }

    public void setTimyAL(ArrayList<TimKOTC> timyAL) {
        this.timyAL = timyAL;
    }

    public String getTim1nazov() {
        return tim1nazov;
    }

    public void setTim1nazov(String tim1nazov) {
        this.tim1nazov = tim1nazov;
    }

    public String getTim2nazov() {
        return tim2nazov;
    }

    public void setTim2nazov(String tim2nazov) {
        this.tim2nazov = tim2nazov;
    }

    public String getTim3nazov() {
        return tim3nazov;
    }

    public void setTim3nazov(String tim3nazov) {
        this.tim3nazov = tim3nazov;
    }


    public void saveTim(TimKOTC tim){
        timyAL.clear();
        timyAL.add(tim);
    }
    public void saveTimCP(TimKOTC tim){
        hraciCP.clear();
        hraciCP.add(tim);
    }



}
