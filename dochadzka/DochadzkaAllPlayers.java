package sk.upjs.ics.android.beachcoachapp.dochadzka;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sk.upjs.ics.android.beachcoachapp.ExampleAdapter;
import sk.upjs.ics.android.beachcoachapp.ExampleItem;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;


public class DochadzkaAllPlayers extends Fragment {


    private RecyclerView recyclerView;
    private TextView textAll;
    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    NavController navController;


    public DochadzkaAllPlayers() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dochadzka_all_players, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textAll = (TextView)getActivity().findViewById(R.id.textAll);
        recyclerView = (RecyclerView)getActivity().findViewById(R.id.recAll);
        navController = Navigation.findNavController(view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        final ArrayList<ExampleItem> exampleItemArrayList = new ArrayList<>();
        adapter = new ExampleAdapter(exampleItemArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        for (Player p: MainUser.getInstance().getData().getPlayersList()) {
            exampleItemArrayList.add(new ExampleItem(p.getPohlaviePl().equals("zena")?R.drawable.ic_baseline_girl:R.drawable.ic_baseline_boy,p.getMenoPl()+" "+p.getPriezviskoPl(),p.getTreningyTrue()+"/"+p.getTreningyAll()+" ("+p.getTreningyPer()+"%)"));
        }
    }

}