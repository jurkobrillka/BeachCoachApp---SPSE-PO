package sk.upjs.ics.android.beachcoachapp.objects;

public class FinalMovementTest {


    private String clnkovyBeh;
    private String plankVydrz;
    private Double dosahVyskok;
    private Double dosahStoj;
    private Double vyskok;
    private Double skokDoDlzky;
    private int sedLahMinuta;
    private String kilometerBeh;


    public FinalMovementTest(String clnkovyBeh, String plankVydrz, Double dosahVyskok, Double dosahStoj, Double vyskok, Double skokDoDlzky, int sedLahMinuta, String kilometerBeh) {
        this.clnkovyBeh = clnkovyBeh;
        this.plankVydrz = plankVydrz;
        this.dosahVyskok = dosahVyskok;
        this.dosahStoj = dosahStoj;
        this.vyskok = vyskok;
        this.skokDoDlzky = skokDoDlzky;
        this.sedLahMinuta = sedLahMinuta;
        this.kilometerBeh = kilometerBeh;
    }

    public FinalMovementTest(){

    }


    public String getClnkovyBeh() {
        return clnkovyBeh;
    }

    public void setClnkovyBeh(String clnkovyBeh) {
        this.clnkovyBeh = clnkovyBeh;
    }

    public String getPlankVydrz() {
        return plankVydrz;
    }

    public void setPlankVydrz(String plankVydrz) {
        this.plankVydrz = plankVydrz;
    }

    public String getKilometerBeh() {
        return kilometerBeh;
    }

    public void setKilometerBeh(String kilometerBeh) {
        this.kilometerBeh = kilometerBeh;
    }

    public Double getDosahVyskok() {
        return dosahVyskok;
    }

    public void setDosahVyskok(Double dosahVyskok) {
        this.dosahVyskok = dosahVyskok;
    }

    public Double getDosahStoj() {
        return dosahStoj;
    }

    public void setDosahStoj(Double dosahStoj) {
        this.dosahStoj = dosahStoj;
    }

    public Double getVyskok() {
        return vyskok;
    }

    public void setVyskok(Double vyskok) {
        this.vyskok = vyskok;
    }

    public Double getSkokDoDlzky() {
        return skokDoDlzky;
    }

    public void setSkokDoDlzky(Double skokDoDlzky) {
        this.skokDoDlzky = skokDoDlzky;
    }

    public int getSedLahMinuta() {
        return sedLahMinuta;
    }

    public void setSedLahMinuta(int sedLahMinuta) {
        this.sedLahMinuta = sedLahMinuta;
    }

    public FinalMovementTest(String s, String s1, double v, double v1, double v2, int i, String s2) {
    }
}
