package sk.upjs.ics.android.beachcoachapp.pomocky.KOTC;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import sk.upjs.ics.android.beachcoachapp.pomocky.CiernyPetro.TimKOTC;
import sk.upjs.ics.android.beachcoachapp.R;

public class KOTCScore extends Fragment {


    private Button button;
    private Button button2;
    private Button button3;
    private TextView tim1TW;
    private TextView tim2TW;
    private TextView tim3TW;
    private ImageView winnerImage;

    private LinearLayout screenL;
    private LinearLayout bodyL;

    private int body1;
    private int body2;
    private int body3;
    private TextView winner;
    private TextView namewinner;
    TimKOTC timKOTC = new TimKOTC();

    public KOTCScore() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_k_o_t_c_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        namewinner = (TextView)getActivity().findViewById(R.id.namewinner);
        bodyL = (LinearLayout)getActivity().findViewById(R.id.bodyL);
        winnerImage = (ImageView) getActivity().findViewById(R.id.winnerImageX);

        namewinner.setVisibility(View.INVISIBLE);
        button =(Button)getActivity().findViewById(R.id.button);
        button2=(Button)getActivity().findViewById(R.id.button2);
        button3=(Button)getActivity().findViewById(R.id.button3);
        tim1TW = (TextView)getActivity().findViewById(R.id.tim1TW);
        tim2TW = (TextView)getActivity().findViewById(R.id.tim2TW);
        tim3TW = (TextView)getActivity().findViewById(R.id.tim3TW);

        screenL = (LinearLayout)getActivity().findViewById(R.id.screenL);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                body1++;
                setNameOfTeams();
                try {
                    endGame();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                body2++;
                setNameOfTeams();
                try {
                    endGame();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                body3++;
                setNameOfTeams();
                try {
                    endGame();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        setNameOfTeams();
    }
    public void setNameOfTeams(){
        tim1TW.setText(timKOTC.getTimyAL().get(0).getTim1nazov()+": "+body1);
        tim2TW.setText(timKOTC.getTimyAL().get(0).getTim2nazov()+": "+body2);
        tim3TW.setText(timKOTC.getTimyAL().get(0).getTim3nazov()+": "+body3);
    }

    public void setVisibleName(String winname) throws InterruptedException {


        Animation animation_fade_in = AnimationUtils.loadAnimation(getActivity(),R.anim.fade_in);
        namewinner.setVisibility(View.VISIBLE);
        winnerImage.setVisibility(View.VISIBLE);
        namewinner.setText(winname);
        namewinner.startAnimation(animation_fade_in);
        winnerImage.startAnimation(animation_fade_in);

    }

    public void animFadoOut(){
        Animation animation_fade_out = AnimationUtils.loadAnimation(getActivity(),R.anim.fade_out);
        namewinner.startAnimation(animation_fade_out);
        winnerImage.startAnimation(animation_fade_out);

    }    public void endGame() throws InterruptedException {
        if (body1==11){

            setVisibleName(timKOTC.getTimyAL().get(0).getTim1nazov());
            resetGame();

        }
        else if (body2==11){

            setVisibleName(timKOTC.getTimyAL().get(0).getTim2nazov());
            resetGame();
        }
        else if (body3==11){

            setVisibleName(timKOTC.getTimyAL().get(0).getTim3nazov());
            resetGame();
        }
    }

    public void resetGame(){
        body1 =0;
        body2 =0;
        body3=0;
    }

}