package sk.upjs.ics.android.beachcoachapp.objects;

import java.util.ArrayList;

public class MainUserData {


    private String email;
    private String klub;
    private String meno;
    private String priezvisko;
    private ArrayList<Player>playersList = new ArrayList<>();
    private ArrayList<Fine>finesList = new ArrayList<>();
    private ArrayList<Exercice>exercicesList = new ArrayList<>();
    private ArrayList<LastPerformance>lastPerformancesLiist = new ArrayList<>();
    private ArrayList<Training>trainingsList = new ArrayList<>();

    public ArrayList<LastPerformance> getLastPerformancesLiist() {
        return lastPerformancesLiist;
    }

    public void setLastPerformancesLiist(ArrayList<LastPerformance> lastPerformancesLiist) {
        this.lastPerformancesLiist = lastPerformancesLiist;
    }

    public ArrayList<Exercice> getExercicesList() {
        return exercicesList;
    }

    public void setExercicesList(ArrayList<Exercice> exercicesList) {
        this.exercicesList = exercicesList;
    }

    public ArrayList<Training> getTrainingsList() {
        return trainingsList;
    }

    public void setTrainingsList(ArrayList<Training> trainingsList) {
        this.trainingsList = trainingsList;
    }

    //arrayList<Player> players
    //arrayList<Fine> fines
    //arrayList<Excercise> excercices


    public ArrayList<Fine> getFinesList() {
        return finesList;
    }

    public void setFinesList(ArrayList<Fine> finesList) {
        this.finesList = finesList;
    }

    public String getKlub() {
        return klub;
    }

    public void setKlub(String klub) {
        this.klub = klub;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addPlayer(Player p){
        playersList.add(p);
        System.out.println("pridany hrac");
    }
    public void addFine(Fine f){
        finesList.add(f);
        System.out.println("pridana pokuta");
    }
    public void addExercise(Exercice e){
        exercicesList.add(e);
        System.out.println("pridane cvicenie");
    }
    public void addLastPerformance(LastPerformance l){
        lastPerformancesLiist.add(l);
        System.out.println("pridany vykon");
    }
    public void addTraining(Training t){
        trainingsList.add(t);
        System.out.println("pridany trening");
    }




    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(ArrayList<Player> playersList) {
        this.playersList = playersList;
    }

    public void clearList(){
        playersList.clear();
    }
    public void clearFineList(){
        finesList.clear();
    }
    public void clearExerciseList(){
        exercicesList.clear();
    }
    public void clearLastPerformancesList(){lastPerformancesLiist.clear();}
    public void clearTraining(){trainingsList.clear();}

}
