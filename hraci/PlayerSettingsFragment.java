package sk.upjs.ics.android.beachcoachapp.hraci;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;


public class PlayerSettingsFragment extends Fragment {

    private EditText edMeno, edPriez, edNar, edMobile, edMail, edVyska, edVaha, edKlub, edPokuty, edVelkost;
    private String idDocX;
    private Player playerInfo;
    private FloatingActionButton saveC;
    NavController navController;

    public PlayerSettingsFragment() {
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
        return inflater.inflate(R.layout.fragment_player_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findPlayer();
        edMeno = (EditText)getActivity().findViewById(R.id.edTxMeSett);
        edPriez  = (EditText)getActivity().findViewById(R.id.edTxPriSett);
        edKlub = (EditText)getActivity().findViewById(R.id.edTxKlubSett);
        edMail = (EditText)getActivity().findViewById(R.id.edTxMailSett);
        edMobile = (EditText)getActivity().findViewById(R.id.edTxMobileSett);
        edNar = (EditText)getActivity().findViewById(R.id.edTxNarSett);
        edPokuty = (EditText)getActivity().findViewById(R.id.edTxPokutySett);
        edVaha = (EditText)getActivity().findViewById(R.id.edTxVahaSett);
        edVyska = (EditText)getActivity().findViewById(R.id.edTxVyskaSett);
        edVelkost = (EditText)getActivity().findViewById(R.id.edTxVelkostOblSett);
        saveC = (FloatingActionButton)getActivity().findViewById(R.id.fabSave);
        navController = Navigation.findNavController(view);
        edMeno.setText(playerInfo.getMenoPl());
        edPriez.setText(playerInfo.getPriezviskoPl());
        edKlub.setText(playerInfo.getKlub());
        edMail.setText(playerInfo.getMail());
        edMobile.setText(playerInfo.getTelC());
        edVyska.setText(String.valueOf(playerInfo.getVyskaPl()));
        edVaha.setText(String.valueOf(playerInfo.getVahaPl()));
        edVelkost.setText(playerInfo.getVelkostObleceniaPl());
        edPokuty.setText(String.valueOf(playerInfo.getPokuty()));
        edNar.setText(playerInfo.getDatumNarPl());
        listenOnClick(edKlub);
        listenOnClick(edMeno);
        listenOnClick(edPriez);
        listenOnClick(edNar);
        listenOnClick(edMobile);
        listenOnClick(edMail);
        listenOnClick(edVaha);

        saveC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeAndSavePlayer();
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });




    }





    public void findPlayer(){
        if (getArguments()!=null){
            idDocX = getArguments().getString("idDoc");
            System.out.println(idDocX+" --------------------------------------------------");
        }
        else {
            System.out.println("nebavi :(");
        }


        for (Player player: MainUser.getInstance().getData().getPlayersList()) {
            if (player.getStringUID().equals(idDocX)){
                playerInfo = player;
                System.out.println("nasiel som hihihi");
            }
            else {
                System.out.println("nenasiel som hladam dalej");
            }
        }
    }

    public void listenOnClick(final EditText eDx){
        eDx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edMeno.setTextColor(Color.GRAY);
                edPriez.setTextColor(Color.GRAY);
                edKlub.setTextColor(Color.GRAY);
                edMail.setTextColor(Color.GRAY);
                edMobile.setTextColor(Color.GRAY);
                edVyska.setTextColor(Color.GRAY);
                edVaha.setTextColor(Color.GRAY);
                edVelkost.setTextColor(Color.GRAY);
                edPokuty.setTextColor(Color.GRAY);
                edNar.setTextColor(Color.GRAY);
                eDx.setTextColor(Color.BLACK);
            }
        });
    }

    public void changeAndSavePlayer(){

        Player playerXD = new Player(
                playerInfo.getIdTren(),
                edMeno.getText().toString(),
                edPriez.getText().toString(),
                playerInfo.getPohlaviePl(),
                edNar.getText().toString(),
                Double.parseDouble(edVyska.getText().toString()),
                Double.parseDouble(edVaha.getText().toString()),
                edKlub.getText().toString(),
                edVelkost.getText().toString(),
                Double.parseDouble(edPokuty.getText().toString()),
                edMail.getText().toString(),
                edMobile.getText().toString(),
                playerInfo.getLastPerformanceX(),
                playerInfo.getFinalMovementTest(),
                playerInfo.getFinalSpecificTest(),
                playerInfo.getStartSpecificTest(),
                playerInfo.getStarMovementTest());

        DocumentReference documentReference = FirebaseFirestore.getInstance()
                .collection("Players")
                .document(playerInfo.getStringUID());
        documentReference.update(
                "menoPl",edMeno.getText().toString(),
                "priezviskoPl",edPriez.getText().toString(),
                "klub",edKlub.getText().toString(),
                "datumNarPl",edNar.getText().toString(),
                "telC",edMobile.getText().toString(),
                "mail",edMail.getText().toString(),
                "velkostObleceniaPl",edVelkost.getText().toString(),
                "pokuty",Double.parseDouble(edPokuty.getText().toString()),
                "vyskaPl",Double.parseDouble(edVyska.getText().toString()),
                "vahaPl",Double.parseDouble(edVaha.getText().toString())
        ).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(requireContext(), "Hráč uspešne zmenený!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(requireContext(), "Nepodarilo sa :(", Toast.LENGTH_SHORT).show();
                }

            }
        });

        MainUser.getInstance().getData().getPlayersList().remove(playerInfo);
        MainUser.getInstance().getData().getPlayersList().add(playerXD);
    }
}