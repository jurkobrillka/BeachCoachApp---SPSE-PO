package sk.upjs.ics.android.beachcoachapp.hraci;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
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
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;

import static android.content.ContentValues.TAG;


public class PlayerViewFragment extends Fragment{

    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    //TODO karta already deleted
    private CardView alreadyDeletedP;
    private Button anoButtP;
    private Button nieButtP;
    NavController navController;
    FloatingActionButton fabHome;
    final ArrayList<ExampleItem> exampleItemArrayList = new ArrayList<>();
    private ArrayList<Player> playersListLocal = new ArrayList<>();





    public PlayerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_player_view, container, false);


    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        fabHome = (FloatingActionButton)getActivity().findViewById(R.id.fabHomeP);
        navController = Navigation.findNavController(view);


        //exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_person,"Jozo","Grmbla"));
        //add dajak dynamicky


        recyclerView = getView().findViewById(R.id.recViewHraci);
        recyclerView.setHasFixedSize(true);
        //TODO karta already deleted
        alreadyDeletedP = (CardView)getActivity().findViewById(R.id.deleteAlreadyP);
        anoButtP = (Button)getActivity().findViewById(R.id.anoButtP);
        nieButtP = (Button)getActivity().findViewById(R.id.nieButtP);

        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new ExampleAdapter(exampleItemArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



        playersListLocal.clear();
        exampleItemArrayList.clear();
        for (Player player: MainUser.getInstance().getData().getPlayersList()) {
            System.out.println(player);
            playersListLocal.add(player);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++pridalo "+player.getStringUID());
            if (player.getPohlaviePl().equals("muz")){
                exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_boy,player.getMenoPl(),player.getPriezviskoPl(),R.drawable.ic_baseline_delete));
            }
            else {
                exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_girl,player.getMenoPl(),player.getPriezviskoPl(),R.drawable.ic_baseline_delete));
            }
        }
        exampleItemArrayList.add(new ExampleItem(R.drawable.plus_button,"","Nový hráč",R.drawable.ic_baseline_clear));

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
                    navController.navigate(R.id.action_playerViewFragment_to_newPlayerFragment);
                }
                else{
                    Bundle args = new Bundle();
                    System.out.println("+++++++++++++++++++++++++++++++++++++robim+++++++++++++++++++++++++++++++"+playersListLocal.get(position).getStringUID());
                    args.putString("idDoc",playersListLocal.get(position).getStringUID());
                    navController.navigate(R.id.action_playerViewFragment_to_playerInfoFragment,args);
                }
            }

            @Override
            public void onDeleteClick(int position) {


                final int ppXD = position;
                YoYo.with(Techniques.SlideInUp)
                        .duration(500)
                        .repeat(0)
                        .playOn(getView().findViewById(R.id.deleteAlreadyP));
                alreadyDeletedP.setVisibility(View.VISIBLE);
                anoButtP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        YoYo.with(Techniques.SlideOutDown)
                                .duration(500)
                                .repeat(0)
                                .playOn(getView().findViewById(R.id.deleteAlreadyP));
                        removeItem(ppXD);
                    }
                });
                nieButtP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        YoYo.with(Techniques.SlideOutDown)
                                .duration(500)
                                .repeat(0)
                                .playOn(getView().findViewById(R.id.deleteAlreadyP));
                    }
                });



            }
        });
    }
    public void removeItem(int positionX){
        playersListLocal.get(positionX);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Players").document(playersListLocal.get(positionX).getStringUID()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    System.out.println("Vymazal som hraca /////////////////////////////////////////////////////");
                }

            }
        });
        exampleItemArrayList.remove(positionX);
        adapter.notifyItemRemoved(positionX);
        MainUser.getInstance().getData().getPlayersList().remove(playersListLocal.get(positionX));
    }




}