package sk.upjs.ics.android.beachcoachapp.pomocky.KOTC;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.pomocky.CiernyPetro.TimKOTC;
import sk.upjs.ics.android.beachcoachapp.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ToolsSkoreKOTC extends Fragment {

    private EditText EditText;
    private EditText EditText2;
    private EditText EditText3;
    private Button Start;
    NavController navController;



    public ToolsSkoreKOTC() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tools_skore_k_o_t_c, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);


        EditText = (EditText)getActivity().findViewById(R.id.editText);
        EditText2 = (EditText)getActivity().findViewById(R.id.editText2);
        EditText3 = (EditText)getActivity().findViewById(R.id.editText3);
        Start = (Button)getActivity().findViewById(R.id.start);

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimKOTC timy = new TimKOTC(EditText.getText().toString(),EditText2.getText().toString(),EditText3.getText().toString());

                System.out.println(EditText.getText().toString());
                System.out.println(EditText2.getText().toString());
                System.out.println(EditText3.getText().toString());
                if(timy.getTim1nazov().length()==0){
                    timy.setTim1nazov("annonymous1");
                }
                if (timy.getTim2nazov().length()==0){
                    timy.setTim2nazov("annonymous2");
                }
                if (timy.getTim3nazov().length()==0){
                    timy.setTim3nazov("annonymous3");
                }

                timy.saveTim(timy);
                System.out.println(timy.getTimyAL().get(0).getTim1nazov()+" gigiiiiiiiiiiii");
                goNext();



            }
        });
    }
    public void goNext(){
        navController.navigate(R.id.action_toolsSkoreKOTC_to_KOTCScore);
    }

}