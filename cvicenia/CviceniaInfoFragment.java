package sk.upjs.ics.android.beachcoachapp.cvicenia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.Exercice;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;


public class CviceniaInfoFragment extends Fragment {


    private TextView textNazofInfo, textPopisInfo, textCasInfo;
    private String idDocX;
    private Exercice exerciceInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cvicenia_info, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textNazofInfo = (TextView)getActivity().findViewById(R.id.textNazovInfo);
        textPopisInfo = (TextView)getActivity().findViewById(R.id.textPopisInfo);
        textCasInfo = (TextView)getActivity().findViewById(R.id.textCasInfo);

        if (getArguments()!=null){
            idDocX = getArguments().getString("idDoc");
            System.out.println(idDocX+" --------------------------------------------------");
        }
        else {
            System.out.println("nebavi :(");
        }

        for (Exercice exercice: MainUser.getInstance().getData().getExercicesList()) {
            if (exercice.getStringUID().equals(idDocX)){
                exerciceInfo = exercice;
                System.out.println("nasiel som hihihi");
            }
            else {
                System.out.println("nenasiel som hladam dalej");
            }
        }

        textNazofInfo.setText(exerciceInfo.getNazovEx());
        textCasInfo.setText(exerciceInfo.getCasEx()+" min");
        textPopisInfo.setText(exerciceInfo.getPopisEx());


    }
}