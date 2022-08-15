package sk.upjs.ics.android.beachcoachapp.testovania.predXpoTestovanie;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;

public class TestPredCas extends Fragment {

    private String idPlayerX;
    private String predXpo;
    private Player playerInfo;
    private NavController navController;
    private EditText casClnkovy;
    private EditText casPlank;
    private EditText casKilometer;
    private EditText casSedLah;
    private ImageButton casSend;
    private TextView casMeno;


    public TestPredCas() {
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
        return inflater.inflate(R.layout.fragment_test_pred_cas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        casMeno = (TextView)getActivity().findViewById(R.id.testCasMeno);
        casSedLah = (EditText)getActivity().findViewById(R.id.casMinuta);
        casPlank = (EditText)getActivity().findViewById(R.id.casPlank);
        casKilometer = (EditText)getActivity().findViewById(R.id.casKilometer);
        casClnkovy = (EditText)getActivity().findViewById(R.id.casClnkovy);
        casSend = (ImageButton)getActivity().findViewById(R.id.testSend);

        getArgsBundle();
        if (predXpo.equals("pred")){
            checkDone();
        }
        else {
            checkDonePo();
        }
        casMeno.setText(playerInfo.getMenoPl()+" "+playerInfo.getPriezviskoPl());
        casSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (predXpo.equals("pred")){
                    sendToFirebasePred();
                }
                else {
                    sendToFirebasePo();
                }
            }
        });
    }
    public void sendToFirebasePo(){
        if (casClnkovy.getText().toString().length()==0){
            //nerob nic
        }
        else {
            useFirebase("finalMovementTest","clnkovyBeh",casClnkovy.getText().toString());
        }

        if (casKilometer.getText().toString().length()==0){
            //nerob nic
        }
        else {
            useFirebase("finalMovementTest","kilometerBeh",casKilometer.getText().toString());
        }
        if (casPlank.getText().toString().length()==0){

        }
        else {
            useFirebase("finalMovementTest","plankVydrz",casPlank.getText().toString());
        }
        if (casSedLah.getText().toString().length()==0){

        }
        else {
            DocumentReference documentReference = FirebaseFirestore.getInstance()
                    .collection("Players")
                    .document(playerInfo.getStringUID());
            documentReference.update("finalMovementTest"+"."+"sedLahMinuta",Integer.parseInt(casSedLah.getText().toString()))
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(requireContext(), "Údaje úspešne zapísané!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(requireContext(), "Nepodarilo sa :(", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);
    }

    public void sendToFirebasePred(){
        if (casClnkovy.getText().toString().length()==0){
            //nerob nic
        }
        else {
            useFirebase("starMovementTest","clnkovyBeh",casClnkovy.getText().toString());
        }

        if (casKilometer.getText().toString().length()==0){
            //nerob nic
        }
        else {
            useFirebase("starMovementTest","kilometerBeh",casKilometer.getText().toString());
        }
        if (casPlank.getText().toString().length()==0){

        }
        else {
            useFirebase("starMovementTest","plankVydrz",casPlank.getText().toString());
        }
        if (casSedLah.getText().toString().length()==0){

        }
        else {
            DocumentReference documentReference = FirebaseFirestore.getInstance()
                    .collection("Players")
                    .document(playerInfo.getStringUID());
            documentReference.update("starMovementTest"+"."+"sedLahMinuta",Integer.parseInt(casSedLah.getText().toString()))
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(requireContext(), "Údaje úspešne zapísané!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(requireContext(), "Nepodarilo sa :(", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);


    }

    public void useFirebase(String predXpo, String poleKtoreUpdatujem, String timeFB){
        DocumentReference documentReference = FirebaseFirestore.getInstance()
                .collection("Players")
                .document(playerInfo.getStringUID());
        documentReference.update(predXpo+"."+poleKtoreUpdatujem,timeFB)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(requireContext(), "Údaje úspešne zapísané!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(requireContext(), "Nepodarilo sa :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void checkDone(){
        if (!playerInfo.getStarMovementTest().getClnkovyBeh().equals("0")){
            casClnkovy.setHintTextColor(Color.rgb(0,255,0));
            casClnkovy.setHint("Hotovo: "+playerInfo.getStarMovementTest().getClnkovyBeh());
        }
        if (!playerInfo.getStarMovementTest().getKilometerBeh().equals("0")){
            casKilometer.setHintTextColor(Color.rgb(0,255,0));
            casKilometer.setHint("Hotovo: "+playerInfo.getStarMovementTest().getKilometerBeh());
        }
        if (!playerInfo.getStarMovementTest().getPlankVydrz().equals("0")){
            casPlank.setHint("Hotovo: "+playerInfo.getStarMovementTest().getPlankVydrz());
            casPlank.setHintTextColor(Color.rgb(0,255,0));
        }
        if (playerInfo.getStarMovementTest().getSedLahMinuta()>0){
            casSedLah.setHintTextColor(Color.rgb(0,255,0));
            casSedLah.setHint("Hotovo: "+playerInfo.getStarMovementTest().getPlankVydrz());
        }
    }

    public void checkDonePo(){
        if (!playerInfo.getFinalMovementTest().getClnkovyBeh().equals("0")){
            casClnkovy.setHintTextColor(Color.rgb(0,255,0));
            casClnkovy.setHint("Hotovo: "+playerInfo.getFinalMovementTest().getClnkovyBeh());
        }
        if (!playerInfo.getFinalMovementTest().getKilometerBeh().equals("0")){
            casKilometer.setHintTextColor(Color.rgb(0,255,0));
            casKilometer.setHint("Hotovo: "+playerInfo.getFinalMovementTest().getKilometerBeh());
        }
        if (!playerInfo.getFinalMovementTest().getPlankVydrz().equals("0")){
            casPlank.setHint("Hotovo: "+playerInfo.getFinalMovementTest().getPlankVydrz());
            casPlank.setHintTextColor(Color.rgb(0,255,0));
        }
        if (playerInfo.getFinalMovementTest().getSedLahMinuta()>0){
            casSedLah.setHintTextColor(Color.rgb(0,255,0));
            casSedLah.setHint("Hotovo: "+playerInfo.getFinalMovementTest().getPlankVydrz());
        }
    }

    public void getArgsBundle(){
        if (getArguments()!=null){
            idPlayerX = getArguments().getString("idDoc");
            predXpo = getArguments().getString("predXpo");
            for (Player pX: MainUser.getInstance().getData().getPlayersList()) {
                if (pX.getStringUID().equals(idPlayerX)){
                    playerInfo = pX;
                }
            }
        }
        else {
            System.out.println("nebavi :(");
        }
    }
}