package sk.upjs.ics.android.beachcoachapp.dochadzka;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sk.upjs.ics.android.beachcoachapp.ExampleAdapter;
import sk.upjs.ics.android.beachcoachapp.ExampleItem;
import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;
import sk.upjs.ics.android.beachcoachapp.objects.Training;


public class DochadzkaShowTrening extends Fragment {




    private String UID;
    private Training trainingFinal;
    private TextView textNazov, textTime;
    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    NavController navController;
    final ArrayList<ExampleItem> exampleItemArrayList = new ArrayList<>();
    private ArrayList<UcastnikTr> ucastniksLocalArrayList = new ArrayList<>();
    private int indeXTrainingMainUser;

    public DochadzkaShowTrening() {
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
        return inflater.inflate(R.layout.fragment_dochadzka_show_trening, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getBundle();

        textNazov = (TextView)getActivity().findViewById(R.id.textTreningyShow);
        textTime = (TextView)getActivity().findViewById(R.id.textTreningyShowTime);
        recyclerView = (RecyclerView)view.findViewById(R.id.recTreningyShow);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new ExampleAdapter(exampleItemArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        setTexts();
        fillList();

        adapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                if (position==ucastniksLocalArrayList.size()){
                    setEndTraining(position);
                }
                else {
                    if (trainingFinal.getKoniec()){
                        Toast.makeText(getContext(), "Tréning už skončil", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        checkTF(position);
                    }
                }
            }

            @Override
            public void onDeleteClick(int position) {

            }
        });



    }

    public void changeItem(int pos, String text){
        exampleItemArrayList.get(pos).changeText(text);
        adapter.notifyItemChanged(pos);
    }

    public void changeImage(int pos, int imgX){
        exampleItemArrayList.get(pos).changeImage(imgX);
        adapter.notifyItemChanged(pos);
    }

    public void checkTF(int pos){

        if (ucastniksLocalArrayList.get(pos).isUcast()){
            changeImage(pos,R.drawable.thumbfalse);
            //todo fiebase update
            //updateVaulesFirebase(ucastniksLocalArrayList.get(pos).getHracID());
            ucastniksLocalArrayList.get(pos).setUcast(false);
            MainUser.getInstance().getData().getTrainingsList().get(indeXTrainingMainUser).getUcastnici().get(pos).setUcast(false);
            updateFirebase(false,pos);
        }
        else {
            //updateVaulesFirebase(ucastniksLocalArrayList.get(pos).getHracID());
            changeImage(pos,R.drawable.thumbtrue);
            ucastniksLocalArrayList.get(pos).setUcast(true);
            MainUser.getInstance().getData().getTrainingsList().get(indeXTrainingMainUser).getUcastnici().get(pos).setUcast(true);
            updateFirebase(true,pos);
        }
    }

    public void getBundle(){
        if (getArguments()!=null){
            UID = getArguments().getString("UID");
            int f = 0;
            for (Training t: MainUser.getInstance().getData().getTrainingsList()) {
                if (t.getStringUID().equals(UID)){
                    trainingFinal = t;
                    indeXTrainingMainUser = f;
                    break;
                }
                f++;
            }
        }
    }

    public void fillList(){
        for (Training t:MainUser.getInstance().getData().getTrainingsList()) {
            if (t.getStringUID().equals(UID)){
                for (UcastnikTr tr:t.getUcastnici()){
                    exampleItemArrayList.add(new ExampleItem(tr.isUcast()?R.drawable.thumbtrue:R.drawable.thumbfalse,tr.getMenoTr(),tr.getPriezviskoTr(),R.drawable.ic_baseline_clear));
                    ucastniksLocalArrayList.add(tr);
                }
            }
        }
        //TODO tu zmen ked je konec a zaciatok treningu moreeee
        if (trainingFinal.getKoniec()){
            exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_event,"","Tréning skončil",R.drawable.ic_baseline_clear));

        }
        else {
            exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_event_not_done,"(Po ukončení budú údaje uložené)","Ukonči tréning",R.drawable.ic_baseline_clear));
        }
    }

    public void setEndTraining(int pos){

        if (trainingFinal.getKoniec()){
            Toast.makeText(getContext(), "Tréning už skončil", Toast.LENGTH_SHORT).show();
        }
        else {
            for (UcastnikTr u:ucastniksLocalArrayList) {
                if (u.isUcast()){
                    //TODO
                    updateFieldTrue(u);
                }
                else {
                    updateFieldFalse(u);
                }
            }

            setTrainingDataAndExit();
        }
    }

    public void setTrainingDataAndExit(){
        trainingFinal.setKoniec(true);
        for (Training t:MainUser.getInstance().getData().getTrainingsList()) {
            if (t.getStringUID().equals(trainingFinal.getStringUID())){
                t.setKoniec(true);
                break;
            }
        }
        //firebase
        DocumentReference documentReference = FirebaseFirestore.getInstance()
                .collection("Trainings")
                .document(trainingFinal.getStringUID());
        documentReference.update("koniec",true).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(), "Tréning je úspešne ukončený", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "Niečo sa pokazilo, skús ešte raz :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //exit
        Intent i = new Intent(getActivity(),MainActivity.class);
        startActivity(i);

    }
    public void updateFieldFalse(UcastnikTr utr){
        for (Player p:MainUser.getInstance().getData().getPlayersList()) {
            if (p.getStringUID().equals(utr.getHracID())){

                System.out.println("nasiel som hraca "+p.getMenoPl()+" --------------------");

                p.setTreningyAll(p.getTreningyAll()+1); //nastavene vsetky treningy
                double per = (100*p.getTreningyTrue())/p.getTreningyAll();
                p.setTreningyPer(per);

                DocumentReference documentReference = FirebaseFirestore.getInstance()
                        .collection("Players")
                        .document(p.getStringUID());
                documentReference.update("treningyAll",p.getTreningyAll(),
                        "treningyPer",per,
                        "treningyTrue",p.getTreningyTrue())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getContext(), "idede", Toast.LENGTH_SHORT).show();
                                    System.out.println("boha lol bav XDD+-----------------------------------");
                                }
                                else {
                                    Toast.makeText(getContext(), "nejde", Toast.LENGTH_SHORT).show();
                                    System.out.println("boha lol nebavi :(((((((9+-----------------------------------");
                                    //System.out.println(task.getResult()+" ////////////////////////////////////");
                                }
                            }
                        });
            }
        }
    }


    public void updateFieldTrue(UcastnikTr utr){
        for (Player p:MainUser.getInstance().getData().getPlayersList()) {
            if (p.getStringUID().equals(utr.getHracID())){
                p.setTreningyAll(p.getTreningyAll()+1); //nastavene vsetky treningy
                p.setTreningyTrue(p.getTreningyTrue()+1); //nastavene uspesne treningy
                double per = (100*p.getTreningyTrue())/p.getTreningyAll();
                p.setTreningyPer(per);

                DocumentReference documentReference = FirebaseFirestore.getInstance()
                        .collection("Players")
                        .document(p.getStringUID());
                documentReference.update("treningyAll",p.getTreningyAll(),
                        "treningyPer",per,
                        "treningyTrue",p.getTreningyTrue())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    System.out.println("boha lol bav XDD+-----------------------------------");
                                }
                                else {
                                    System.out.println("boha lol nebavi :(((((((9+-----------------------------------");
                                }
                            }
                        });
            }
        }
    }

    /*public void updateVaulesFirebase(String UID, boolean b){
        if (b){
            for (Player p:MainUser.getInstance().getData().getPlayersList()) {
                if (p.getStringUID().equals(UID)){
                    p.setTreningyAll(p.getTreningyAll()+1); //nastavene vsetky treningy
                    p.setTreningyTrue(p.getTreningyTrue()+1); //nastavene uspesne treningy
                    double per = (100*p.getTreningyTrue())/p.getTreningyAll();
                    p.setTreningyPer(per);

                    DocumentReference documentReference = FirebaseFirestore.getInstance()
                            .collection("Players")
                            .document(UID);
                    documentReference.update("treningyAll",p.getTreningyAll(),
                            "treningyPer",per,
                            "treningyTrue",p.getTreningyTrue())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        System.out.println("boha lol bav XDD+-----------------------------------");
                                    }
                                    else {
                                        System.out.println("boha lol nebavi :(((((((9+-----------------------------------");
                                    }
                                }
                            });
                }
            }
        }
        //todo tu je druhy false boolean
        else {
            for (Player p:MainUser.getInstance().getData().getPlayersList()) {
                if (p.getStringUID().equals(UID)){
                    p.setTreningyAll(p.getTreningyAll()+1); //nastavene vsetky treningy
                    double per = (100*p.getTreningyTrue())/p.getTreningyAll();
                    p.setTreningyPer(per);

                    DocumentReference documentReference = FirebaseFirestore.getInstance()
                            .collection("Players")
                            .document(UID);
                    documentReference.update("treningyAll",p.getTreningyAll(),
                            "treningyPer",per,
                            "treningyTrue",p.getTreningyTrue())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        System.out.println("boha lol bav XDD+-----------------------------------");
                                    }
                                    else {
                                        System.out.println("boha lol nebavi :(((((((9+-----------------------------------");
                                    }
                                }
                            });
                }
            }
        }
    }*/

    //todo huuhuhu
    public void updateFirebase(boolean b, int posX){
        DocumentReference documentReference = FirebaseFirestore.getInstance()
                .collection("Trainings")
                .document(trainingFinal.getStringUID());
        Map<String, Object> data = new HashMap<>();
        data.put("ucastnici", ucastniksLocalArrayList);


        documentReference.set(data, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    System.out.println("baviiiiiiiiiiiiiiiiiiiiiiiiiiii");
                }
                else {
                    System.out.println("nebaviiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
                }

            }
        });
    }


    public void setTexts(){
        if (trainingFinal.getNazov().length()==0){
            textNazov.setText("Bez názvu");
        }
        else {
            textNazov.setText(trainingFinal.getNazov());
        }

        textTime.setText(trainingFinal.getDatum()+" / "+trainingFinal.getCas());
    }
}