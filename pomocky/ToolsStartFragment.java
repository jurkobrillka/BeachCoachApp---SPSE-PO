package sk.upjs.ics.android.beachcoachapp.pomocky;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;


public class ToolsStartFragment extends Fragment {


    CardView stopkyCard;
            CardView timerCard;
    CardView kotcCard;
            CardView ciernypetroCard;
    CardView skoreCard;
            CardView idkCard;

    GridLayout myGrid;
    NavController navController;

    TextView citat;
    TextView autor;
    String citaty[][] = {{"Neuspel som a potom som neuspel zas a zas. To je dôvod, prečo som nakoniec uspel.","Nepremeníš sto percent striel, ktoré nevystrelíš","Nie je to o tom, či spadneš na zem, ale o tom, či sa dokážeš postaviť.","Bez sebadisciplíny je úspech nereálny. Bodka.","Ak neveríš v to, že môžeš byť najlepší, tak sa ti nikdy nepodarí dosiahnuť to, čoho si schopný.","Je dôležité inšpirovať ľudí, nech môžu byť úžasní v čomkoľvek, čo robia."},{"Michael Jordan","Wayne Gretzky","Vince Lombardi","Lou Holtz","Cristiano Ronaldo","Kobe Bryant"}};




    public ToolsStartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tools_start, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        autor = (TextView)view.findViewById(R.id.autor);
        citat = (TextView)view.findViewById(R.id.citat);
        myGrid = (GridLayout)view.findViewById(R.id.myGrid);
        skoreCard = (CardView)view.findViewById(R.id.skoreCard);
        stopkyCard = (CardView)view.findViewById(R.id.stopkyCard);
        timerCard = (CardView)view.findViewById(R.id.timerCard);
        kotcCard = (CardView)view.findViewById(R.id.kotcCard);
        idkCard = (CardView)view.findViewById(R.id.idkCard);
        ciernypetroCard = (CardView)view.findViewById(R.id.ciernypetroCard);
        generateQuote();



        skoreCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOnSkore();
            }
        });
        stopkyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOnStopky();
            }
        });
        timerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOnTimer();
            }
        });
        kotcCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOnSkorKOTC();
            }
        });
        ciernypetroCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOnCiernyPetro();
            }
        });
        idkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOnMojUcet();
            }
        });
    }




    public void generateQuote(){
        Random rn = new Random();
        int index = rn.nextInt(5);
        if (index == 1){
            citat.setText("\""+citaty[0][0]+"\"");
            autor.setText("- "+citaty[1][0]+"");
        }
        else if (index == 2){
            citat.setText("\""+citaty[0][1]+"\"");
            autor.setText("- "+citaty[1][1]+"");
        }
        else if (index == 3){
            citat.setText("\""+citaty[0][2]+"\"");
            autor.setText("- "+citaty[1][2]+"");
        }
        else if (index == 4){
            citat.setText("\""+citaty[0][3]+"\"");
            autor.setText("- "+citaty[1][3]+"");
        }
        else if (index == 5){
            citat.setText("\""+citaty[0][4]+"\"");
            autor.setText("- "+citaty[1][4]+"");
        }
        else{
            citat.setText("\""+citaty[0][5]+"\"");
            autor.setText("- "+citaty[1][5]+"");
        }

    }
    public void goOnStopky(){
        navController.navigate(R.id.action_toolsStartFragment_to_toolsStopkyFragment);
    }
    public void goOnTimer(){
        navController.navigate(R.id.action_toolsStartFragment_to_toolsTimerFragment);
    }
    public void goOnSkore(){
        navController.navigate(R.id.action_toolsStartFragment_to_toolsSkoreFragment);
        //navController.navigate(R.id.action_toolsStartFragment_to_toolsSkoreFragment);
    }
    public void goOnCiernyPetro(){
        navController.navigate(R.id.action_toolsStartFragment_to_toolsSkoreKOTC);
    }
    public void goOnSkorKOTC(){
        navController.navigate(R.id.action_toolsStartFragment_to_toolsSkoreCiernyPetro);
    }
    public void goOnMojUcet(){
        navController.navigate(R.id.action_toolsStartFragment_to_mojUcetFragment);
    }



}