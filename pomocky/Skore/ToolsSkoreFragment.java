package sk.upjs.ics.android.beachcoachapp.pomocky.Skore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import sk.upjs.ics.android.beachcoachapp.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


public class ToolsSkoreFragment extends Fragment {


    private Button red;
    private Button blue;
    private int blueP;
    private int redP;
    private Animation scaleUp;


    public ToolsSkoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tools_skore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        red = (Button)view.findViewById(R.id.red);
        blue = (Button)view.findViewById(R.id.blue);
        scaleUp = AnimationUtils.loadAnimation(getActivity(),R.anim.scale_up);
        /*blue.setHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
        red.setHeight(view.displayMetrics.heightPixels);*/

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                red.startAnimation(scaleUp);
                redP++;
                red.setText(redP+"");
                if (redP>=21 && blueP<=redP-2){
                    setZeroPoint();
                }
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blue.startAnimation(scaleUp);
                blueP++;
                blue.setText(blueP+"");
                if (blueP>=21&&redP<=blueP-2){
                    setZeroPoint();
                }
            }
        });


    }
    public void setZeroPoint(){
        redP=0;
        red.setText(redP+"");
        blueP=0;
        blue.setText(blueP+"");
    }
}