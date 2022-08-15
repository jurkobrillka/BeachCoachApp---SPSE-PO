package sk.upjs.ics.android.beachcoachapp.pokuty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sk.upjs.ics.android.beachcoachapp.ExampleAdapter;
import sk.upjs.ics.android.beachcoachapp.ExampleItem;
import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.cvicenia.CviceniaStartFragment;
import sk.upjs.ics.android.beachcoachapp.objects.Fine;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;


public class FineStartFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    NavController navController;
    FloatingActionButton fabHome;
    private CardView alreadyDeletedF;
    private Button anoButtF;
    private Button nieButtF;
    final ArrayList<ExampleItem> exampleItemArrayList = new ArrayList<>();
    private ArrayList<Fine> finesListLocal = new ArrayList<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fine_start, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        fabHome = (FloatingActionButton)getActivity().findViewById(R.id.fabHome);
        finesListLocal.clear();
        exampleItemArrayList.clear();

        for (Fine fine: MainUser.getInstance().getData().getFinesList()) {
            System.out.println(fine);
            finesListLocal.add(fine);
            exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_monetization_on_24,fine.getCenaFi()+" EUR",fine.getPopisFi(),R.drawable.ic_baseline_delete));

        }
       exampleItemArrayList.add(new ExampleItem(R.drawable.plus_button,"\uD83E\uDD11","Nová pokuta",R.drawable.ic_baseline_check));



        recyclerView = getView().findViewById(R.id.recViewPokuty);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new ExampleAdapter(exampleItemArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        alreadyDeletedF = (CardView)getActivity().findViewById(R.id.deleteAlreadyF);
        anoButtF = (Button)getActivity().findViewById(R.id.anoButtF);
        nieButtF = (Button)getActivity().findViewById(R.id.nieButtF);

        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });


        adapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position==exampleItemArrayList.size()-1){
                    navController.navigate(R.id.action_fineStartFragment_to_newFineFragment);
                }
                else{
                    //bavi
                    Bundle args = new Bundle();
                    System.out.println("+++++++++++++++++++++++++++++++++++++robim+++++++++++++++++++++++++++++++"+finesListLocal.get(position).getStringUID());
                    args.putString("idDoc",finesListLocal.get(position).getStringUID());
                    navController.navigate(R.id.action_fineStartFragment_to_fineChooseFragment,args);
                }
            }

            @Override
            public void onDeleteClick(int position) {
                final int ppXD = position;
                YoYo.with(Techniques.SlideInUp)
                        .duration(500)
                        .repeat(0)
                        .playOn(getView().findViewById(R.id.deleteAlreadyF));
                alreadyDeletedF.setVisibility(View.VISIBLE);
                anoButtF.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        YoYo.with(Techniques.SlideOutDown)
                                .duration(500)
                                .repeat(0)
                                .playOn(getView().findViewById(R.id.deleteAlreadyF));
                        removeItem(ppXD);
                    }
                });
                nieButtF.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        YoYo.with(Techniques.SlideOutDown)
                                .duration(500)
                                .repeat(0)
                                .playOn(getView().findViewById(R.id.deleteAlreadyF));
                    }
                });
            }
        });

    }

    public void removeItem(int position){
        finesListLocal.get(position);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Fines").document(finesListLocal.get(position).getStringUID()).delete();
        exampleItemArrayList.remove(position);
        MainUser.getInstance().getData().getFinesList().remove(finesListLocal.get(position));
        adapter.notifyItemRemoved(position);
    }
}