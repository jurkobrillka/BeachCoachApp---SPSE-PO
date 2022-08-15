package sk.upjs.ics.android.beachcoachapp.pomocky.CiernyPetro;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ToolsSkoreCiernyPetro extends Fragment {


    private EditText hrac1;
    private EditText hrac2;
    private EditText hrac3;
    private EditText hrac4;
    private EditText hrac5;
    private Button Start;
    TimKOTC timKOTC;
    NavController navController;

    public ToolsSkoreCiernyPetro() {
           }
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tools_skore_cierny_petro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        Start = (Button)getActivity().findViewById(R.id.startCP);
        hrac1 = (EditText)getActivity().findViewById(R.id.hrac1);
        hrac2 = (EditText)getActivity().findViewById(R.id.hrac2);
        hrac3 = (EditText)getActivity().findViewById(R.id.hrac3);
        hrac4 = (EditText)getActivity().findViewById(R.id.hrac4);
        hrac5 = (EditText)getActivity().findViewById(R.id.hrac5);


        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timKOTC = new TimKOTC(hrac1.getText().toString(),hrac2.getText().toString(),hrac3.getText().toString(),hrac4.getText().toString(),hrac5.getText().toString());

                timKOTC.saveTimCP(timKOTC);
                if (timKOTC.getHraciCP().get(0).getCP1().length()==0){
                    timKOTC.getHraciCP().get(0).setCP1("annonymous1");
                }
                if (timKOTC.getHraciCP().get(0).getCP2().length()==0){
                    timKOTC.getHraciCP().get(0).setCP2("annonymous2");
                }
                if (timKOTC.getHraciCP().get(0).getCP3().length()==0){
                    timKOTC.getHraciCP().get(0).setCP3("annonymous3");
                }
                if (timKOTC.getHraciCP().get(0).getCP4().length()==0){
                    timKOTC.getHraciCP().get(0).setCP4("annonymous4");
                }
                if (timKOTC.getHraciCP().get(0).getCP5().length()==0){
                    timKOTC.getHraciCP().get(0).setCP5("annonymous5");
                }


                goNext();
            }
        });
    }
    public void goNext(){


        //TODO route to CPskore fragment
        navController.navigate(R.id.action_toolsSkoreCiernyPetro_to_CPskore);

    }
}