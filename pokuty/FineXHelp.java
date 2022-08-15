package sk.upjs.ics.android.beachcoachapp.pokuty;

import java.util.ArrayList;

public class FineXHelp {
    private double cenaXHelp;
    static ArrayList<FineXHelp>xList = new ArrayList<>();

    public FineXHelp(){

    }

    public ArrayList<FineXHelp> getxList() {
        return xList;
    }

    public void setxList(ArrayList<FineXHelp> xList) {
        this.xList = xList;
    }

    public FineXHelp(double cenaXHelp) {
        this.cenaXHelp = cenaXHelp;
    }

    public double getCenaXHelp() {
        return cenaXHelp;
    }

    public void setCenaXHelp(double cenaXHelp) {
        this.cenaXHelp = cenaXHelp;
    }

    public void clearList(){
        xList.clear();
    }
    public void addList(FineXHelp f){
        xList.add(f);
    }


}
