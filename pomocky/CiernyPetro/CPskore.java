package sk.upjs.ics.android.beachcoachapp.pomocky.CiernyPetro;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import sk.upjs.ics.android.beachcoachapp.R;


public class CPskore extends Fragment {

    private TextView tim1PC;
    private TextView tim5PC;
    private TextView tim4PC;
    private TextView tim3PC;
    private TextView tim2PC;
    private Button button1PC;
    private Button button2PC;
    private Button button3PC;
    private Button button4PC;
    private Button button5PC;
    private Button button2PCCH;
    private Button button1PCCH;
    private Button button3PCCH;
    private Button button4PCCH;
    private Button button5PCCH;
    private ImageView winnerPictureCP;
    private TextView winnerNameCP;
    TimKOTC timKOTC;
    private int timbody1[]=new int[2];
    private int timbody2[]=new int[2];
    private int timbody3[]=new int[2];
    private int timbody4[]=new int[2];
    private int timbody5[]=new int[2];


    public CPskore() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_c_pskore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        defineComponents();
        declareArrayOfPoint();
        setNameOfTeams();
        button1PC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timbody1[0]++;
                setNameOfTeams();
                checkLoser();
            }
        });
        button2PC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timbody2[0]++;
                setNameOfTeams();
                checkLoser();
            }
        });
        button3PC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timbody3[0]++;
                setNameOfTeams();
                checkLoser();
            }
        });
        button4PC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timbody4[0]++;
                setNameOfTeams();
                checkLoser();
            }
        });
        button5PC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timbody5[0]++;
                setNameOfTeams();
                checkLoser();
            }
        });
        button1PCCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timbody1[1]++;
                setNameOfTeams();
                checkLoser();
            }
        });
        button2PCCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timbody2[1]++;
                setNameOfTeams();
                checkLoser();
            }
        });
        button3PCCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timbody3[1]++;
                setNameOfTeams();
                checkLoser();
            }
        });
        button4PCCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timbody4[1]++;
                setNameOfTeams();
                checkLoser();
            }
        });
        button5PCCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timbody5[1]++;
                setNameOfTeams();
                checkLoser();
            }
        });
    }

    public void defineComponents(){
        button2PCCH = (Button)getActivity().findViewById(R.id.button2PCCH);
        button1PCCH = (Button)getActivity().findViewById(R.id.button1PCCH);
        button3PCCH = (Button)getActivity().findViewById(R.id.button3PCCH);
        button4PCCH = (Button)getActivity().findViewById(R.id.button4PCCH);
        button5PCCH = (Button)getActivity().findViewById(R.id.button5PCCH);
        tim1PC = (TextView) getActivity().findViewById(R.id.tim1PC);
        tim2PC = (TextView) getActivity().findViewById(R.id.tim2PC);
        tim3PC = (TextView) getActivity().findViewById(R.id.tim3PC);
        tim4PC = (TextView)getActivity().findViewById(R.id.tim4PC);
        tim5PC = (TextView) getActivity().findViewById(R.id.tim5PC);
        button1PC = (Button) getActivity().findViewById(R.id.button1PC);
        button2PC = (Button) getActivity().findViewById(R.id.button2PC);
        button3PC = (Button) getActivity().findViewById(R.id.button3PC);
        button4PC = (Button) getActivity().findViewById(R.id.button4PC);
        button5PC = (Button) getActivity().findViewById(R.id.button5PC);
        winnerNameCP = (TextView)getActivity().findViewById(R.id.winnerNameCP);
        winnerPictureCP = (ImageView)getActivity().findViewById(R.id.winnerImageCP);

    }
    public void setVisibleNameWinner(String winnerName){

        Animation animation_fade_in = AnimationUtils.loadAnimation(getActivity(),R.anim.fade_in);
        winnerNameCP.setVisibility(View.VISIBLE);
        winnerPictureCP.setVisibility(View.VISIBLE);
        winnerNameCP.setText(winnerName);
        winnerPictureCP.startAnimation(animation_fade_in);
        winnerNameCP.startAnimation(animation_fade_in);
    }

    public void checkLoser(){
        if (timbody1[1]==11){

            setVisibleNameWinner(timKOTC.getHraciCP().get(0).getCP1());
            resetGame();

        }
        else if (timbody2[1]==11){

            setVisibleNameWinner(timKOTC.getHraciCP().get(0).getCP2());
            resetGame();
        }
        else if (timbody3[1]==11){

            setVisibleNameWinner(timKOTC.getHraciCP().get(0).getCP3());
            resetGame();
        }
        else if (timbody4[1]==11){

            setVisibleNameWinner(timKOTC.getHraciCP().get(0).getCP4());
            resetGame();
        }
        else if (timbody5[1]==11){

            setVisibleNameWinner(timKOTC.getHraciCP().get(0).getCP5());
            resetGame();
        }
    }
    public void resetGame(){
        timbody5[0]=0;
        timbody5[1]=0;
        timbody4[0]=0;
        timbody4[1]=0;
        timbody3[0]=0;
        timbody3[1]=0;
        timbody2[1]=0;
        timbody2[0]=0;
        timbody1[0]=0;
        timbody1[1]=0;
    }


    public void setNameOfTeams(){

        setTextColorFul(tim1PC,timKOTC.getHraciCP().get(0).getCP1()+": <font color='red'>"+timbody1[1]+"</font>/<font color='green'>"+timbody1[0]+"</font>");
        setTextColorFul(tim2PC,timKOTC.getHraciCP().get(0).getCP2()+": <font color='red'>"+timbody2[1]+"</font>/<font color='green'>"+timbody2[0]+"</font>");
        setTextColorFul(tim3PC,timKOTC.getHraciCP().get(0).getCP3()+": <font color='red'>"+timbody3[1]+"</font>/<font color='green'>"+timbody3[0]+"</font>");
        setTextColorFul(tim4PC,timKOTC.getHraciCP().get(0).getCP4()+": <font color='red'>"+timbody4[1]+"</font>/<font color='green'>"+timbody4[0]+"</font>");
        setTextColorFul(tim5PC,timKOTC.getHraciCP().get(0).getCP5()+": <font color='red'>"+timbody5[1]+"</font>/<font color='green'>"+timbody5[0]+"</font>");

    }
    public void declareArrayOfPoint(){
        timbody1[0]=0;
        timbody1[1]=0;
        timbody2[1]=0;
        timbody3[1]=0;
        timbody4[1]=0;
        timbody5[1]=0;
        timbody2[0]=0;
        timbody5[0]=0;
        timbody4[0]=0;
        timbody3[0]=0;
    }
    public void setTextColorFul(TextView tw, String text){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tw.setText(Html.fromHtml(text,  Html.FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
        } else {
            tw.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
        }
    }
}