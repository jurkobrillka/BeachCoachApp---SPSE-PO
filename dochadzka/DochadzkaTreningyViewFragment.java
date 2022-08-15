package sk.upjs.ics.android.beachcoachapp.dochadzka;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sk.upjs.ics.android.beachcoachapp.ExampleAdapter;
import sk.upjs.ics.android.beachcoachapp.ExampleItem;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Training;


public class DochadzkaTreningyViewFragment extends Fragment {



    private TextView textDatum, ninjaText;
    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    NavController navController;
    final ArrayList<ExampleItem> exampleItemArrayList = new ArrayList<>();
    private ArrayList<Training> trainingsLocalList = new ArrayList<>();
    private int hour;
    private int minute;
    private int day;
    private int month;
    private int year;
    private Bundle args;


    public DochadzkaTreningyViewFragment() {
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
        return inflater.inflate(R.layout.fragment_dochadzka_treningy_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView)view.findViewById(R.id.recTreningyView);
        recyclerView.setHasFixedSize(true);
        navController = Navigation.findNavController(view);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new ExampleAdapter(exampleItemArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        ninjaText = (TextView)view.findViewById(R.id.ninjaText);
        textDatum = (TextView)view.findViewById(R.id.textTreningyView);
        getBundleData();

        textDatum.setText(day+"."+month+"."+year);
        if (fillRecView()==0){
            ninjaText.setVisibility(View.VISIBLE);
        }
        adapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                setBundle(position);
                navController.navigate(R.id.action_dochadzkaTreningyViewFragment_to_dochadzkaShowTrening,args);
            }

            @Override
            public void onDeleteClick(int position) {

            }
        });







    }

    public void getBundleData(){
        if(getArguments()!=null){
            day = getArguments().getInt("year");
            month = getArguments().getInt("month");
            year = getArguments().getInt("day");
        }
        else {
            System.out.println("nebavi");
        }
    }

    public int fillRecView(){
        exampleItemArrayList.clear();
        for (Training t: MainUser.getInstance().getData().getTrainingsList()) {
            if (t.getDatum().equals(day+"."+month+"."+year)){
            exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_event,nazovFun(t),t.getDatum()+(" / ")+t.getCas(),R.drawable.ic_baseline_clear));
            trainingsLocalList.add(t);
            }
        }
        return trainingsLocalList.size();
    }

    public void setBundle(int pos){
        args = new Bundle();
        args.putInt("day",day);
        args.putInt("month",month+1);
        args.putInt("year",year);
        args.putString("UID",trainingsLocalList.get(pos).getStringUID());
    }

    public String nazovFun(Training t){
        return t.getNazov().length()==0?"Bez názvu":t.getNazov();
    }
}