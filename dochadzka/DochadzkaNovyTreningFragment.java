package sk.upjs.ics.android.beachcoachapp.dochadzka;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sk.upjs.ics.android.beachcoachapp.ExampleAdapter;
import sk.upjs.ics.android.beachcoachapp.ExampleItem;
import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.Exercice;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;
import sk.upjs.ics.android.beachcoachapp.objects.Training;


public class DochadzkaNovyTreningFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImageButton buttNewTg;
    private TextView textDatum;
    private EditText editNewTg;
    private ImageButton buttTime;
    private TextView txtOch;

    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    final ArrayList<ExampleItem> exampleItemArrayList = new ArrayList<>();
    private ArrayList<Player> playersListLocal = new ArrayList<>();
    private ArrayList<Player> playersAdded = new ArrayList<>();
    private ArrayList<UcastnikTr> ret = new ArrayList<>();
    private int hour;
    private int minute;
    private int day;
    private int month;
    private int year;


    public DochadzkaNovyTreningFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dochadzka_novy_trening, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recViewHraciNewTg);
        buttNewTg = (ImageButton) view.findViewById(R.id.buttNewTg);
        textDatum = (TextView) view.findViewById(R.id.textDatumNewTg);
        buttTime = (ImageButton) view.findViewById(R.id.buttTimePickerNewTg);
        editNewTg = (EditText) view.findViewById(R.id.editTextNazovNewTg);
        txtOch = (TextView) view.findViewById(R.id.txtOch);

        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new ExampleAdapter(exampleItemArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        saveBunndle();
        fillRecView();

        textDatum.setText(day+"."+month+"."+year);

        adapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                addPlayerToList(playersListLocal.get(position), position);
            }

            @Override
            public void onDeleteClick(int position) {
            }
        });

        buttTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hour = i;
                        minute = i1;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0, 0, 0, hour, minute);
                        if(minute==0){
                            txtOch.setText(hour + ":" + minute+"0");
                        }
                        else {
                            txtOch.setText(hour + ":" + minute);
                        }

                    }
                }, 12, 0, true);

                timePickerDialog.updateTime(hour, minute);
                timePickerDialog.show();
            }
        });

        buttNewTg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MakeNewTraining();
            }
        });


    }

    public void fillRecView() {
        for (Player player : MainUser.getInstance().getData().getPlayersList()) {
            System.out.println(player);
            playersListLocal.add(player);
            if (player.getPohlaviePl().equals("muz")) {
                exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_boy, player.getMenoPl(), player.getPriezviskoPl(), R.drawable.ic_baseline_add));
            } else {
                exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_girl, player.getMenoPl(), player.getPriezviskoPl(), R.drawable.ic_baseline_add));
            }
        }
    }

    public void MakeNewTraining() {
        String nazov = editNewTg.getText().toString();


        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        final DocumentReference ref = firebaseFirestore.collection("Exercices").document();
        final Training training;
        if (minute==0){
            training = new Training(FirebaseAuth.getInstance().getCurrentUser().getUid(),day+"."+month+"."+year,hour+":"+minute+"0",makeUcastniciArrayToFirebase(),nazov, false);
        }
        else {
            training = new Training(FirebaseAuth.getInstance().getCurrentUser().getUid(),day+"."+month+"."+year,hour+":"+minute,makeUcastniciArrayToFirebase(),nazov, false);
        }

        firebaseFirestore.collection("Trainings").add(training)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(requireContext(), "Tréning úspešne vytvorený", Toast.LENGTH_SHORT).show();
                            training.setStringUID(task.getResult().getId());
                            MainUser.getInstance().getData().addTraining(training);
                            goBack();
                        } else {
                            Toast.makeText(requireContext(), "Niečo sa pokazilo, skús ešte raz", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    public ArrayList<UcastnikTr> makeUcastniciArrayToFirebase(){
        for (Player p: playersAdded) {
            ret.add(new UcastnikTr(p.getStringUID(),false,p.getMenoPl(),p.getPriezviskoPl()));
        }
        return ret;
    }

    public void goBack(){
        Intent i = new Intent(getContext(),MainActivity.class);
        startActivity(i);
    }

    public void addPlayerToList(Player p, int pos){
        if (playersAdded.contains(p)){
            if (p.getPohlaviePl().equals("zena")) {
                changeImage(pos, R.drawable.ic_baseline_girl);
            } else {
                changeImage(pos, R.drawable.ic_baseline_boy);
            }
            playersAdded.remove(p);
        }
        else {
            changeImage(pos,R.drawable.plus_button);
            playersAdded.add(p);
        }
    }

    public void saveBunndle(){
        if (getArguments()!=null){
            day = getArguments().getInt("year");
            month = getArguments().getInt("month");
            year = getArguments().getInt("day");
        }
        else {
            System.out.println("nebavi :(");
        }
    }
    public void changeImage(int pos, int imgX){
        exampleItemArrayList.get(pos).changeImage(imgX);
        adapter.notifyItemChanged(pos);
    }

}