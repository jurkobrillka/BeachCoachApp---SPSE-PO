package sk.upjs.ics.android.beachcoachapp.testovania;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;

import static android.content.ContentValues.TAG;

public class TestVyhodnotenie extends Fragment {


    private TextView gg;
    private TestHrac t;
    private FloatingActionButton fabHome;
    private TestHrac testHracX;
    private String category;
    private Player testovanyPlayerX;
    private TextView ggXD;


    public TestVyhodnotenie() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_vyhodnotenie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //chooseCategory();
        //testovanyPlayerX = new Player();
        testHracX = new TestHrac();
        testovanyPlayerX = returnPlayer();
        ggXD = (TextView)view.findViewById(R.id.ggXD);
        gg = (TextView)view.findViewById(R.id.gg);
        fabHome = (FloatingActionButton)view.findViewById(R.id.fabHomeT);


        double perUspesnost = (100.0*(double)testHracX.getTestovanyHrac().get(0).getLikeDislike())/(double) testHracX.getTestovanyHrac().get(0).getPocetOpakovani();
        chooseCategory(perUspesnost);
        zapisFireBase(chooseCategory(perUspesnost),perUspesnost);



        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

    }



    public String chooseCategory(double perUspAktualne){

        testHracX = new TestHrac();
        if(testHracX.getTestovanyHrac().get(0).getCoTestujem()==1){
            //smec
            Log.d(TAG, "chooseCategory: "+testHracX.getTestovanyHrac().get(0).toString()+" -*- co testujem");
            System.out.println(testHracX.getTestovanyHrac().get(0).toString());
            if (testHracX.getTestovanyHrac().get(0).getModeUder().equals("lob")){
                if (testHracX.getTestovanyHrac().get(0).getWayUder().equals("ciara")){
                    //lobCiara
                    Log.d(TAG, "chooseCategory: "+testHracX.getTestovanyHrac().get(0).toString()+" -*- co testujem");
                    category = "lobCiarPer";
                    CompareResults(testovanyPlayerX.getLastPerformanceX().getLobCiarPer(),perUspAktualne);

                    return category;

                }
                else {
                    //lobDiga
                    Log.d(TAG, "chooseCategory: "+testHracX.getTestovanyHrac().get(0).toString()+" -*- co testujem");
                    category = "lobDigPer";
                    CompareResults(testovanyPlayerX.getLastPerformanceX().getLobDigPer(),perUspAktualne);
                    return category;
                }

            }
            else if (testHracX.getTestovanyHrac().get(0).getModeUder().equals("smec")){
                if (testHracX.getTestovanyHrac().get(0).getWayUder().equals("ciara")){
                    //smecCiara
                    Log.d(TAG, "chooseCategory: "+testHracX.getTestovanyHrac().get(0).toString()+" -*- co testujem");
                    category = "smecCiarPer";
                    CompareResults(testovanyPlayerX.getLastPerformanceX().getSmecCiarPer(),perUspAktualne);
                    return category;
                }
                else {
                    //smecDiga
                    Log.d(TAG, "chooseCategory: "+testHracX.getTestovanyHrac().get(0).toString()+" -*- co testujem");
                    category = "smecDigPer";
                    CompareResults(testovanyPlayerX.getLastPerformanceX().getSmecDigPer(),perUspAktualne);
                    return category;
                }
            }
        }
        else if (testHracX.getTestovanyHrac().get(0).getCoTestujem()==2){
            //prsty
            category = "prstyPer";
            CompareResults(testovanyPlayerX.getLastPerformanceX().getPrstyPer(),perUspAktualne);
            return category;

        }

        else if (testHracX.getTestovanyHrac().get(0).getCoTestujem()==3){
            //bager
            CompareResults(testovanyPlayerX.getLastPerformanceX().getBagertPer(),perUspAktualne);
            category = "bagertPer";
            return category;
        }

        else if (testHracX.getTestovanyHrac().get(0).getCoTestujem()==4){
            //servis
            if (testHracX.getTestovanyHrac().get(0).getModeUder().equals("plachta")){
                if (testHracX.getTestovanyHrac().get(0).getWayUder().equals("ciara")){
                    //plachtaCiara
                    Log.d(TAG, "chooseCategory: "+testHracX.getTestovanyHrac().get(0).toString()+" -*- co testujem");
                    category = "plachtaCiarPer";
                    CompareResults(testovanyPlayerX.getLastPerformanceX().getPlachtaCiarPer(),perUspAktualne);
                    return category;

                }
                else {
                    //palchtaDiga
                    Log.d(TAG, "chooseCategory: "+testHracX.getTestovanyHrac().get(0).toString()+" -*- co testujem");
                    category = "plactaDigPer";
                    CompareResults(testovanyPlayerX.getLastPerformanceX().getPlachtaDigPer(),perUspAktualne);
                    return category;
                }

            }
            else if (testHracX.getTestovanyHrac().get(0).getModeUder().equals("skakany")){
                if (testHracX.getTestovanyHrac().get(0).getWayUder().equals("ciara")){
                    //smecCiara
                    Log.d(TAG, "chooseCategory: "+testHracX.getTestovanyHrac().get(0).toString()+" -*- co testujem");
                    category = "skakanyCiarPer";
                    CompareResults(testovanyPlayerX.getLastPerformanceX().getSkakanyCiaraPer(),perUspAktualne);
                    return category;

                }
                else {
                    //smecDiga
                    Log.d(TAG, "chooseCategory: "+testHracX.getTestovanyHrac().get(0).toString()+" -*- co testujem");
                    category = "skakanyDigPer";
                    CompareResults(testovanyPlayerX.getLastPerformanceX().getSkakanyDigaPer(),perUspAktualne);
                    return category;
                }
            }

        }

        else if (testHracX.getTestovanyHrac().get(0).getCoTestujem()==5){
            CompareResults(testovanyPlayerX.getLastPerformanceX().getBlokPer(),perUspAktualne);
            return "blokPer";

        }
        else {
            return "ta co";
        }
    return category;
    }

    public void CompareResults(double stareRes, double aktualneRes){
        if (stareRes>aktualneRes){
            gg.setText("Zoslabol si");

            aktualneRes = Math.round(aktualneRes);
            stareRes = Math.round(stareRes);
            ggXD.setText("Posledné testovanie: "+stareRes+"%\nAktuálne testovnie: "+aktualneRes+"%");
            Log.d(TAG, "CompareResults: zoslabol si");
        }
        else if (stareRes<aktualneRes){
            gg.setText("Zlepšil si sa!");

            aktualneRes = Math.round(aktualneRes);
            stareRes = Math.round(stareRes);
            ggXD.setText("Posledné testovanie: "+stareRes+"%\nAktuálne testovnie: "+aktualneRes+"%");
            Log.d(TAG, "CompareResults: zlepsil si sa");
        }
        else if (stareRes==aktualneRes){
            gg.setText("Stagnuješ");
            aktualneRes = Math.round(aktualneRes);
            stareRes = Math.round(stareRes);
            ggXD.setText("Posledné testovanie: "+stareRes+"%\nAktuálne testovnie: "+aktualneRes+"%");
            Log.d(TAG, "CompareResults: stagnujes");
        }
        else {
            gg.setText("ta so XD");
            Log.d(TAG, "CompareResults: nebavi");
        }

    }

    public Player returnPlayer(){
        Player xyz = new Player();

        for (Player p: MainUser.getInstance().getData().getPlayersList()) {
            String menopriezviskoPX = p.getMenoPl()+" "+p.getPriezviskoPl();
            if (menopriezviskoPX.equals(testHracX.getTestovanyHrac().get(0).getMenoTestovaneho())){
                xyz = p;
                break;
            }
        }
        return xyz;
    }

    public void zapisFireBase(String poleKtoreUpdatujem, double finalPerUspesnost){
        DocumentReference documentReference = FirebaseFirestore.getInstance()
                .collection("Players")
                .document(testovanyPlayerX.getStringUID());
        documentReference.update("lastPerformanceX."+poleKtoreUpdatujem,finalPerUspesnost)
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
}