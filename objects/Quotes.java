package sk.upjs.ics.android.beachcoachapp.objects;

import android.widget.TextView;

import java.util.ArrayList;

public class Quotes {

    private String citat;
    private String autor;
    private ArrayList<Quotes> quotesList = new ArrayList<>();

    public Quotes(String citat, String autor) {
        this.citat = citat;
        this.autor = autor;
    }

    public Quotes() {
    }

    public String getCitat() {
        return citat;
    }

    public void setCitat(String citat) {
        this.citat = citat;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    public void setQuoteText(int index,TextView citatWidget, TextView autorWidget){
        System.out.println(index+" ----------------------------------");
        citatWidget.setText("\""+quotesList.get(index).getCitat()+"\"");
        autorWidget.setText("- "+quotesList.get(index).getAutor()+"");

    }

    public void show(){
        for (int i = 0; i <quotesList.size() ; i++) {
            System.out.println(quotesList.get(i).getCitat()+" -------------------------------");
        }
    }

    public void fillQuotes(){
        Quotes quotes = new Quotes("Neuspel som a potom som neuspel zas a zas. To je dôvod, prečo som nakoniec uspel.","Michael Jordan");
        quotesList.add(quotes);
        Quotes quotes1 = new Quotes("Ak zlyháš pri príprave, si pripravený zlyhať.","Mark Spitz");
        quotesList.add(quotes1);
        Quotes quotes2 = new Quotes("Nepremeníš sto percent striel, ktoré nevystrelíš.","Wayne Gretzky");
        quotesList.add(quotes2);
        Quotes quotes3 = new Quotes("Nie je to o tom, či spadneš na zem, ale o tom, či sa dokážeš postaviť.","Vince Lombardi");
        quotesList.add(quotes3);
        Quotes quotes4 = new Quotes("Bez sebadisciplíny je úspech nereálny. Bodka.","Lou Holtz");
        quotesList.add(quotes4);
        Quotes quotes5 = new Quotes("Ak máš šancu jedno percento, zvyšných 99 percent musí byť viera.","Neymar");
        quotesList.add(quotes5);
        Quotes quotes6 = new Quotes("Niekedy musíš ísť poriadne vysoko, aby si si uvedomil, aký si v skutočnosti malý.","Felix Baumgartner");
        quotesList.add(quotes6);
        Quotes quotes7 = new Quotes("Ak neveríš v to, že môžeš byť najlepší, tak sa ti nikdy nepodarí dosiahnuť to, čoho si schopný.","Cristiano Ronaldo");
        quotesList.add(quotes7);
        Quotes quotes8 = new Quotes("Je dôležité inšpirovať ľudí, nech môžu byť úžasní v čomkoľvek, čo robia.","Kobe Bryant");
        quotesList.add(quotes8);
        Quotes quotes9 = new Quotes("Snívaj vo veľkom, ale nebuď sklamaný, ak to nevyjde","Mikaela Shiffrin");
        quotesList.add(quotes9);

        //zdroje https://www.redbull.com/sk-sk/slova-ktore-inspiruju-vyroky-sportovych-legiend

    }
}
