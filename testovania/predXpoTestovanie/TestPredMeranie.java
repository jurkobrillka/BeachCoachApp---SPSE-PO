package sk.upjs.ics.android.beachcoachapp.testovania.predXpoTestovanie;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;


public class TestPredMeranie extends Fragment {


    private NavController navController;
    private TextView seekResult;
    private TextView seekLoremIpsum;
    private TextView seekTitle;
    private SeekBar seekMeter;
    private Player playerInfo;
    private String idDocXX;
    private String whatTitle;
    private String sX;
    private ImageButton seekSend;
    private String predXpo;

    public TestPredMeranie() {
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
        return inflater.inflate(R.layout.fragment_test_pred_meranie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        seekMeter = (SeekBar)getActivity().findViewById(R.id.seekMeter);
        seekResult = (TextView)getActivity().findViewById(R.id.seekResult);
        seekLoremIpsum = (TextView)getActivity().findViewById(R.id.seekLoremIpsum);
        seekTitle = (TextView)getActivity().findViewById(R.id.seekTitle);
        seekSend = (ImageButton)getActivity().findViewById(R.id.seekSend);
        predXpo = "s";
        getArgsBundle();

        findPlayer();
        setLoremIpsum();

        seekSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (predXpo.equals("pred")) {
                    sendToFirebase("starMovementTest");
                }
                else if (predXpo.equals("po")){
                    sendToFirebase("finalMovementTest");
                }
            }
        });


        seekMeter.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekResult.setText(String.valueOf(i)+" cm");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


    public void findPlayer(){

            idDocXX = "";
            whatTitle = "";
            if (getArguments()!=null){
                idDocXX = getArguments().getString("idDoc");
                whatTitle = getArguments().getString("whatTitle");
                sX = getArguments().getString("whatTest");
            }
            else {
                System.out.println("nebavi :(");
            }
            for (Player player: MainUser.getInstance().getData().getPlayersList()) {
                if (player.getStringUID().equals(idDocXX)){
                    playerInfo = player;
                }
                else {}
            }
        }

    public void setLoremIpsum(){
        seekTitle.setText(whatTitle);

        if (sX.equals("dosahStoj")){
            seekLoremIpsum.setText("Hr???? stoj?? v mieste pri stene, bez obuvi. Stoj?? bokom k stene, kedy je sme??iarska ??plne vytiahnut?? z ramien, trup sa bokom dot??ka steny. P??ty s?? na podlahe.");
        }
        else if (sX.equals("dosahVyskok")){
            seekLoremIpsum.setText("Hr???? vysko???? blok??rskym ??t??lom. Po????ta sa dosah oboch r??k. Hr???? m?? 3 pokusy. Medzi ka??d??m pokusom m?? pauzu 30 sek??nd. Sk????e sa na piesku. V????ka sa meria rovnako ako v????ka siete.");
        }
        else if (sX.equals("skokDoDlzky")){
            seekLoremIpsum.setText("Hr???? sa sna???? vysko??i?? na piesko ??o naj??alej do d????ky. P??ty s?? na zemi a za????na sa bez rozbehu z miesta. Hr???? m?? 3 pokusy. Medzi ka??d??m pokusom m?? pauzu 30 sek??nd.");
        }
    }

    public void sendToFirebase(String field){
       double XD = seekMeter.getProgress();
        DocumentReference documentReference = FirebaseFirestore.getInstance()
                .collection("Players")
                .document(idDocXX);
        documentReference.update(field+"."+sX,XD)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(requireContext(), "??daje ??spe??ne zap??san??!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(requireContext(), "Nepodarilo sa :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
    }

    public void getArgsBundle(){
        if (getArguments()!=null){
            predXpo = getArguments().getString("predXpo");
        }
        else {
            System.out.println("nebavi :(");
        }
    }
}