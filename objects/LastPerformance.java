package sk.upjs.ics.android.beachcoachapp.objects;

public class LastPerformance {
    private double lobDigPer;
    private double lobCiarPer;
    private double smecDigPer;
    private double smecCiarPer;
    private double prstyPer;
    private double bagertPer;
    private double plachtaDigPer;
    private double plachtaCiarPer;
    private double skakanyCiaraPer;
    private double skakanyDigaPer;
    private double blokPer;


    /*
    *
2-prsty, 3-bager, 5-blok
6-lobCiara, 7-lobDiga, 8-smecCiara, 9-smecDiga
10-skakanyCiara, 11-skakanyDiga, 12-plachtaCiara, 13-plachtaDiga
    * */
    public double getLobDigPer() {
        return lobDigPer;
    }

    public LastPerformance(double lobDigPer, double lobCiarPer, double smecDigPer, double smecCiarPer, double prstyPer, double bagertPer, double plachtaDigPer, double plachtaCiarPer, double skakanyCiaraPer, double skakanyDigaPer, double blokPer) {
        this.lobDigPer = lobDigPer;
        this.lobCiarPer = lobCiarPer;
        this.smecDigPer = smecDigPer;
        this.smecCiarPer = smecCiarPer;
        this.prstyPer = prstyPer;
        this.bagertPer = bagertPer;
        this.plachtaDigPer = plachtaDigPer;
        this.plachtaCiarPer = plachtaCiarPer;
        this.skakanyCiaraPer = skakanyCiaraPer;
        this.skakanyDigaPer = skakanyDigaPer;
        this.blokPer = blokPer;
    }

    public LastPerformance(){

    }
    public double getPrstyPer() {
        return prstyPer;
    }

    public void setPrstyPer(double prstyPer) {
        this.prstyPer = prstyPer;
    }

    public double getBagertPer() {
        return bagertPer;
    }

    public void setBagertPer(double bagertPer) {
        this.bagertPer = bagertPer;
    }

    public double getBlokPer() {
        return blokPer;
    }

    public void setBlokPer(double blokPer) {
        this.blokPer = blokPer;
    }

    public void setLobDigPer(double lobDigPer) {
        this.lobDigPer = lobDigPer;
    }

    public double getLobCiarPer() {
        return lobCiarPer;
    }

    public void setLobCiarPer(double lobCiarPer) {
        this.lobCiarPer = lobCiarPer;
    }

    public double getSmecDigPer() {
        return smecDigPer;
    }

    public void setSmecDigPer(double smecDigPer) {
        this.smecDigPer = smecDigPer;
    }

    public double getSmecCiarPer() {
        return smecCiarPer;
    }

    public void setSmecCiarPer(double smecCiarPer) {
        this.smecCiarPer = smecCiarPer;
    }


    public double getPlachtaDigPer() {
        return plachtaDigPer;
    }

    public void setPlachtaDigPer(double plachtaDigPer) {
        this.plachtaDigPer = plachtaDigPer;
    }

    public double getPlachtaCiarPer() {
        return plachtaCiarPer;
    }

    public void setPlachtaCiarPer(double plachtaCiarPer) {
        this.plachtaCiarPer = plachtaCiarPer;
    }

    public double getSkakanyCiaraPer() {
        return skakanyCiaraPer;
    }

    public void setSkakanyCiaraPer(double skakanyCiaraPer) {
        this.skakanyCiaraPer = skakanyCiaraPer;
    }

    public double getSkakanyDigaPer() {
        return skakanyDigaPer;
    }

    public void setSkakanyDigaPer(double skakanyDigaPer) {
        this.skakanyDigaPer = skakanyDigaPer;
    }


    //TODO dokonci gigi

}
