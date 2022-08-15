package sk.upjs.ics.android.beachcoachapp.testovania;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.R;


public class TestUtokRozdelenieFragment extends Fragment {

    private GridLayout gridSec;
    private CardView smecCard;
    private CardView lobCard;
    private CardView ciaraCard;
    private CardView digaCard;
    private TextView textPredmet;
    private TextView textMeno;
    private TextView textSmec;
    private TextView textLob;
    private TextView textCiara;
    private TextView textDiga;
    private CardView send;
    private LinearLayout ochWtf;
    private int coounterShow;
    private int counterGreen;
    private NavController navController;
    private TestHrac t;
    private String modeUder;
    private String smerUder;




    public TestUtokRozdelenieFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        coounterShow=0;
        counterGreen=0;

        return inflater.inflate(R.layout.fragment_test_utok_rozdelenie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        smecCard = (CardView)view.findViewById(R.id.smecCard);
        lobCard = (CardView)view.findViewById(R.id.lobCard);
        ciaraCard = (CardView)view.findViewById(R.id.ciaraCard);
        digaCard = (CardView)view.findViewById(R.id.digaCard);
        gridSec = (GridLayout)view.findViewById(R.id.gridSec);
        textCiara = (TextView)getActivity().findViewById(R.id.TextCiara);
        textDiga  = (TextView)getActivity().findViewById(R.id.TextDiga);
        textLob  = (TextView)getActivity().findViewById(R.id.TextLob);
        textSmec  = (TextView)getActivity().findViewById(R.id.TextSmec);
        send = (CardView)getActivity().findViewById(R.id.send);
        textMeno = (TextView)getActivity().findViewById(R.id.menoTest);
        textPredmet = (TextView)getActivity().findViewById(R.id.predmet);
        t= new TestHrac();
        setMenoAPredmet();







        smecCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                makeAnimGridSec(gridSec, textSmec);
                if (t.getTestovanyHrac().get(0).getCoTestujem()==1) {
                    modeUder = "smec";
                }
                else if (t.getTestovanyHrac().get(0).getCoTestujem()==4){
                    modeUder = "skakany";
                }

            }
        });

        lobCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeAnimGridSec(gridSec, textLob);
                if (t.getTestovanyHrac().get(0).getCoTestujem()==1) {
                    modeUder = "lob";
                }
                else if (t.getTestovanyHrac().get(0).getCoTestujem()==4){
                    modeUder = "plachta";
                }

            }
        });

        digaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeAnimSendCard(textDiga);
                    smerUder = "diga";

            }
        });
        ciaraCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeAnimSendCard(textCiara);
                smerUder = "ciara";
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestHrac t = new TestHrac();
                t.getTestovanyHrac().get(0).setModeUder(modeUder);
                t.getTestovanyHrac().get(0).setWayUder(smerUder);
                goOn();
            }
        });



    }

    public void makeAnimGridSec(GridLayout gris, TextView text){
        coounterShow++;

        if (coounterShow%2==0){
            send.setVisibility(View.INVISIBLE);
            text.setTextColor(Color.GRAY);
            gris.setVisibility(View.VISIBLE);
            System.out.println("etffffffffff-----------------------");
            YoYo.with(Techniques.FlipOutX)
                    .duration(400)
                    .playOn(ciaraCard);
            YoYo.with(Techniques.FlipOutX)
                    .duration(400)
                    .playOn(digaCard);
            counterGreen=0;
            textCiara.setTextColor(Color.GRAY);
            textDiga.setTextColor(Color.GRAY);
        }
        else {

            send.setVisibility(View.INVISIBLE);
            text.setTextColor(Color.GREEN);
            gris.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.FlipInX)
                    .duration(400)
                    .playOn(ciaraCard);
            YoYo.with(Techniques.FlipInX)
                    .duration(400)
                    .playOn(digaCard);
        }
    }

    public void makeAnimSendCard(TextView text){
        counterGreen++;
        if (counterGreen%2==0){
            text.setTextColor(Color.GRAY);
            YoYo.with(Techniques.FlipOutX)
                    .duration(400)
                    .playOn(send);
        }
        else {
            text.setTextColor(Color.GREEN);
            send.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.FlipInX)
                    .duration(400)
                    .playOn(send);
        }
    }

    public void goOn(){
        navController.navigate(R.id.action_testUtokRozdelenieFragment_to_testPrstyTestovanieFragment);
    }

    public void setMenoAPredmet(){
        textMeno.setText(""+t.getTestovanyHrac().get(0).getMenoTestovaneho());
        if (t.getTestovanyHrac().get(0).getCoTestujem()==1){
            textPredmet.setText("Útočný úder");

        }
        else if (t.getTestovanyHrac().get(0).getCoTestujem()==4){
            textPredmet.setText("Servis");
            textSmec.setText("Skákaný");
            textLob.setText("Plachta");
        }
        else if (t.getTestovanyHrac().get(0).getCoTestujem()==5){
            textPredmet.setText("Blok");
        }

    }
}