package sk.upjs.ics.android.beachcoachapp.testovania;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.Random;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.objects.Quotes;
import sk.upjs.ics.android.beachcoachapp.R;


public class TestStart extends Fragment {

    private TextView citatWid;
    private TextView autorWid;
    Quotes quo = new Quotes();
    CardView utok;
    CardView prsty;
    CardView bager;
    CardView servis;
    CardView blok;
    CardView testPredCard;
    CardView testPoCard;
    private  static int co;
    NavController navController;


    public TestStart() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        citatWid = (TextView)view.findViewById(R.id.citat);
        autorWid = (TextView)view.findViewById(R.id.autor);
        quo.fillQuotes();
        makeQuote();
        testPoCard= (CardView)view.findViewById(R.id.poTestStart);
        bager = (CardView)view.findViewById(R.id.bager);
        prsty = (CardView)view.findViewById(R.id.prsty);
        utok = (CardView)view.findViewById(R.id.utok);
        servis = (CardView)view.findViewById(R.id.servis);
        blok = (CardView)view.findViewById(R.id.blok);
        testPredCard = (CardView)view.findViewById(R.id.predTestCard);


        navController = Navigation.findNavController(view);

        prsty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                co = 2;
                goOnPrsty();
            }
        });
        utok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                co = 1;
                goOnPrsty();
            }
        });
        bager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOnPrsty();
                co = 3;
            }
        });
        servis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOnPrsty();
                co=4;
            }
        });
        blok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOnPrsty();
                co=5;
            }
        });
        testPredCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("predXpo","pred");

                navController.navigate(R.id.action_testStart_to_testPredViewFragment,args);
            }
        });
        testPoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("predXpo","po");
                navController.navigate(R.id.action_testStart_to_testPredViewFragment,args);
            }
        });








    }

    public void goOnPrsty(){
        navController.navigate(R.id.action_testStart_to_testPrstyFragment);
    }

    public void makeQuote(){
        Random rn = new Random();
        int index = rn.nextInt(9);
        quo.setQuoteText(index,citatWid,autorWid);
        quo.show();
    }

    public static int getCo() {
        return co;
    }




}