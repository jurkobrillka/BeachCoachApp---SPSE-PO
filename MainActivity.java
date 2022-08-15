package sk.upjs.ics.android.beachcoachapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import sk.upjs.ics.android.beachcoachapp.dochadzka.AttendanceActivity;
import sk.upjs.ics.android.beachcoachapp.hraci.PlayerActivity;
import sk.upjs.ics.android.beachcoachapp.objects.Exercice;
import sk.upjs.ics.android.beachcoachapp.objects.Fine;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.MainUserData;
import sk.upjs.ics.android.beachcoachapp.objects.Player;
import sk.upjs.ics.android.beachcoachapp.objects.Quotes;
import sk.upjs.ics.android.beachcoachapp.objects.Training;
import sk.upjs.ics.android.beachcoachapp.pokuty.FineActivity;
import sk.upjs.ics.android.beachcoachapp.pomocky.ToolsActivity;
import sk.upjs.ics.android.beachcoachapp.promoter.PromoterActvity;
import sk.upjs.ics.android.beachcoachapp.testovania.TestsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private CardView pokutyCard;
    private CardView hraciCard;
    private CardView dochadzkaCard;
    private CardView testovaniaCard;
    private CardView cviceniaCard;
    private CardView pomockyCard;
    private TextView citatWid;
    private TextView autorWid;
    Quotes quo = new Quotes();


    @Override
    protected void onStart() {
        super.onStart();
        makeQuote();
        downloadPlayerData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokutyCard = (CardView)findViewById(R.id.pokutyCard);
        hraciCard = (CardView)findViewById(R.id.hraciCard);
        dochadzkaCard = (CardView)findViewById(R.id.dochadzkaCard);
        testovaniaCard = (CardView)findViewById(R.id.testovaniaCard);
        cviceniaCard = (CardView)findViewById(R.id.cviceniaCard);
        pomockyCard = (CardView)findViewById(R.id.pomockyCard);
        citatWid = (TextView)findViewById(R.id.citat);
        autorWid = (TextView)findViewById(R.id.autor);
        quo.fillQuotes();
        makeQuote();




        pokutyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadFineData();
            }
        });
        hraciCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOn(PlayerActivity.class);
            }
        });
        dochadzkaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadTrainingData();
            }
        });
        testovaniaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOn(TestsActivity.class);
            }
        });
        cviceniaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadExerciseData();
            }
        });
        pomockyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOn(ToolsActivity.class);
            }
        });


    }



    public void downloadPlayerData(){

        System.out.println("stiahol si Player data ----------------------------------------------");
        FirebaseFirestore.getInstance().collection("Players")
                .whereEqualTo("idTren", FirebaseAuth.getInstance().getCurrentUser().getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            if (MainUser.getInstance().getData().getPlayersList()!=null){
                                MainUser.getInstance().getData().clearList();
                            }
                            else {
                                System.out.println("arraylist je null nevjem preco XD");
                            }

                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                MainUser.getInstance().getData().addPlayer(document.toObject(Player.class));

                            }
                            for (Player player:MainUser.getInstance().getData().getPlayersList()) {
                                System.out.println(player);
                            }
                        }
                        else {
                            System.out.println("nebavi");
                        }
                    }
                });
    }


    public void downloadTrainingData(){
        FirebaseFirestore.getInstance().collection("Trainings")
                .whereEqualTo("idTrener", FirebaseAuth.getInstance().getCurrentUser().getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            System.out.println("stiahol si Trainings data ----------------------------------------------");
                            MainUser.getInstance().getData().clearTraining();
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                MainUser.getInstance().getData().addTraining(document.toObject(Training.class));
                            }
                            goOn(AttendanceActivity.class);
                        }
                        else {
                            System.out.println("nebavi");
                        }
                    }
                });

    }


    public void downloadExerciseData(){
        FirebaseFirestore.getInstance().collection("Exercices")
                .whereEqualTo("idTren", FirebaseAuth.getInstance().getCurrentUser().getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            System.out.println("stiahol si Excercices data ----------------------------------------------");
                            MainUser.getInstance().getData().clearExerciseList();
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                MainUser.getInstance().getData().addExercise(document.toObject(Exercice.class));
                            }
                            goOn(PromoterActvity.class);
                        }
                        else {
                            System.out.println("nebavi");
                        }
                    }
                });

    }

    public void downloadFineData(){

        System.out.println("stiahol si Fine data ----------------------------------------------");
        FirebaseFirestore.getInstance().collection("Fines")
                .whereEqualTo("idTren", FirebaseAuth.getInstance().getCurrentUser().getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            MainUser.getInstance().getData().clearFineList();
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                MainUser.getInstance().getData().addFine(document.toObject(Fine.class));
                            }
                            goOn(FineActivity.class);
                        }
                        else {
                            System.out.println("nebavi");
                        }
                    }
                });
    }
    public void goOn(Class destination){
        Intent intent= new Intent(this, destination);
        startActivity(intent);
    }

    public void makeQuote(){
        Random rn = new Random();
        int index = rn.nextInt(9);
        quo.setQuoteText(index,citatWid,autorWid);
        quo.show();
    }
}
