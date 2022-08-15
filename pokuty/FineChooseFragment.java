package sk.upjs.ics.android.beachcoachapp.pokuty;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import sk.upjs.ics.android.beachcoachapp.Adapter;
import sk.upjs.ics.android.beachcoachapp.ExampleAdapter;
import sk.upjs.ics.android.beachcoachapp.ExampleItem;
import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.Fine;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;


public class FineChooseFragment extends Fragment {

    private NavController navController;
    private String idDocX;
    private Fine fineInfo;
    private ViewPager2 viewPager2Fine;
    private MainAdapter adapter;
    final ArrayList<Model> modelsList = new ArrayList<>();
    private FloatingActionButton fabChoose;

    public Fine getFineInfo() {
        return fineInfo;
    }

    public FineChooseFragment() {
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
        return inflater.inflate(R.layout.fragment_fine_choose, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        viewPager2Fine = (ViewPager2)getActivity().findViewById(R.id.viewPagerFine);
        fabChoose = (FloatingActionButton)getActivity().findViewById(R.id.fabChoose);
        findIdSetObj();
        adapter = new MainAdapter(modelsList);

        viewPager2Fine.setClipToPadding(false);
        viewPager2Fine.setClipChildren(false);
        viewPager2Fine.setOffscreenPageLimit(3);
        viewPager2Fine.getChildAt(0);
        viewPager2Fine.setAdapter(adapter);



        for (Player player: MainUser.getInstance().getData().getPlayersList()) {
            System.out.println(player);

            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++pridalo "+player.getStringUID());
            if (player.getPohlaviePl().equals("muz")){
                modelsList.add(new Model(R.drawable.ic_baseline_boy,player.getMenoPl()+" "+player.getPriezviskoPl(),String.valueOf(player.getPokuty())));
            }
            else {
                modelsList.add(new Model(R.drawable.ic_baseline_girl,player.getMenoPl()+" "+player.getPriezviskoPl(),String.valueOf(player.getPokuty())));
            }
        }

        fabChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });


        adapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


                int first = Integer.parseInt(modelsList.get(position).getDesc());
                System.out.println((Integer.parseInt(modelsList.get(position).getDesc()))+fineInfo.getCenaFi()+"           **********************************************");
            }

            @Override
            public void onDeleteClick(int position) {
                System.out.println("funguje!");
            }
        });
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(8));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1-Math.abs(position);
                page.setScaleY(0.8f + v * 0.2f);
            }
        });
        viewPager2Fine.setPageTransformer(compositePageTransformer);






    }


    public void findIdSetObj(){
        if (getArguments() != null) {
            idDocX = getArguments().getString("idDoc");
            System.out.println(idDocX + " --------------------------------------------------");
            //lebo getArguments je null takze sa nikdynedostane ku davaniu idDocX
            //getArguments je null lebo neslo z view_fragment ale new_fragment
        } else {
            System.out.println("nebavi :(");
        }


        for (Fine fine : MainUser.getInstance().getData().getFinesList()) {
            if (fine.getStringUID().equals(idDocX)) {
                fineInfo = fine;
                FineXHelp fX = new FineXHelp(fine.getCenaFi());
                fX.clearList();
                fX.addList(fX);


                System.out.println("nasiel som hihihi");
            } else {
                System.out.println("nenasiel som hladam dalej");
            }
        }
    }
}