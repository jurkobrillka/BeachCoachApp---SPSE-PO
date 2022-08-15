package sk.upjs.ics.android.beachcoachapp.cvicenia;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sk.upjs.ics.android.beachcoachapp.ExampleAdapter;
import sk.upjs.ics.android.beachcoachapp.ExampleItem;
import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.Exercice;
import sk.upjs.ics.android.beachcoachapp.objects.Fine;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class CviceniaStartFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    NavController navController;
    private CardView alreadyDeletedE;
    private Button anoButtE;
    private Button nieButtE;
    FloatingActionButton fabHome;
    final ArrayList<ExampleItem> exampleItemArrayList = new ArrayList<>();
    final ArrayList<Exercice> exercicesListLocal = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cvicenia_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fabHome = (FloatingActionButton)getActivity().findViewById(R.id.fabHomeC);
        navController = Navigation.findNavController(view);
        recyclerView = getView().findViewById(R.id.recViewC);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new ExampleAdapter(exampleItemArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        alreadyDeletedE = (CardView)getActivity().findViewById(R.id.deleteAlreadyE);
        anoButtE = (Button)getActivity().findViewById(R.id.anoButtE);
        nieButtE = (Button)getActivity().findViewById(R.id.nieButtE);

        exercicesListLocal.clear();
        exampleItemArrayList.clear();
        for (Exercice exercice: MainUser.getInstance().getData().getExercicesList()) {
            System.out.println(exercice);
            exercicesListLocal.add(exercice);
            System.out.println(exercice.getStringUID()+" ******************************************************************");
            exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_cviko,exercice.getCasEx()+" min.",exercice.getNazovEx(),R.drawable.ic_baseline_delete));
        }
        exampleItemArrayList.add(new ExampleItem(R.drawable.plus_button,"","Nové cvičenie",R.drawable.ic_baseline_check));


        adapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position==exampleItemArrayList.size()-1){
                    navController.navigate(R.id.action_cviceniaStartFragment_to_cvicenieNewFragment);
                }
                else{
                    Bundle args = new Bundle();
                    args.putString("idDoc",exercicesListLocal.get(position).getStringUID());
                    navController.navigate(R.id.action_cviceniaStartFragment_to_cviceniaInfoFragment,args);
                }
            }

            @Override
            public void onDeleteClick(int position) {
                final int ppXD = position;
                YoYo.with(Techniques.SlideInUp)
                        .duration(500)
                        .repeat(0)
                        .playOn(getView().findViewById(R.id.deleteAlreadyE));
                alreadyDeletedE.setVisibility(View.VISIBLE);
                anoButtE.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        YoYo.with(Techniques.SlideOutDown)
                                .duration(500)
                                .repeat(0)
                                .playOn(getView().findViewById(R.id.deleteAlreadyE));
                        removeItem(ppXD);
                    }
                });
                nieButtE.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        YoYo.with(Techniques.SlideOutDown)
                                .duration(500)
                                .repeat(0)
                                .playOn(getView().findViewById(R.id.deleteAlreadyE));
                    }
                });
            }
        });

        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });

    }

    public void removeItem(int position){
        exercicesListLocal.get(position);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Exercices").document(exercicesListLocal.get(position).getStringUID()).delete();
        exampleItemArrayList.remove(position);
        MainUser.getInstance().getData().getExercicesList().remove(exercicesListLocal.get(position));
        adapter.notifyItemRemoved(position);
    }
}