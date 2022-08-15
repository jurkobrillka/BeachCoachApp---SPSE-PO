package sk.upjs.ics.android.beachcoachapp.testovania.predXpoTestovanie;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sk.upjs.ics.android.beachcoachapp.ExampleAdapter;
import sk.upjs.ics.android.beachcoachapp.ExampleItem;
import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;

import static android.content.ContentValues.TAG;


public class TestPredViewFragment extends Fragment {

    AutoCompleteTextView actMenoPl;
    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageButton atcSend;
    private String predXpo;
    NavController navController;
    final ArrayList<ExampleItem> exampleItemArrayList = new ArrayList<>();
    Player playerXD = new Player();
    private final String[] plNames = new String[MainUser.getInstance().getData().getPlayersList().size()];


    public TestPredViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_pred_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        actMenoPl = (AutoCompleteTextView) view.findViewById(R.id.actMenoPl);
        atcSend = (ImageButton) view.findViewById(R.id.atcSend);
        ArrayAdapter<String> StAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, plNames);
        actMenoPl.setAdapter(StAdapter);
        recyclerView = getView().findViewById(R.id.recViewTestPred);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new ExampleAdapter(exampleItemArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getArgsBundle();
        fillPlNames();



        atcSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightName(actMenoPl.getText().toString())) {
                    if (exampleItemArrayList.size() == 0) {
                        closeKeyBoard();
                        if (predXpo.equals("pred")){
                            //predsey=zonne testovanie
                            fillRecView();
                        }
                        else {
                            //posezonne testovanie
                            fillPORecView();
                        }
                    } else {}
                } else {
                    exampleItemArrayList.clear();
                    Toast toast = Toast.makeText(requireContext(), "Takého hráča nemáš v zozname", Toast.LENGTH_SHORT);
                    toast.show();
                    YoYo.with(Techniques.Shake)
                            .repeat(1)
                            .duration(200)
                            .playOn(getView().findViewById(R.id.actMenoPl));
                }
            }
        });



        adapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position==0 && whoa(position)){
                    sendInfo("clnkovyBeh","cas", "Beh",predXpo);
                }
                else if (position==1 && whoa(position)){
                    sendInfo("dosahStoj","meranie", "Dosah v stoji",predXpo);
                }
                else if (position==2 && whoa(position)){
                    sendInfo("dosahVyskok","meranie", "Dosah vo výskoku",predXpo);
                }
                else if (position==3 && whoa(position)){
                    sendInfo("kilometerBeh","cas", "Beh - kilometer",predXpo);
                }
                else if (position==4 && whoa(position)){
                    sendInfo("plankVydrz","cas", "Výdrž v planku",predXpo);
                }
                else if (position==5 && whoa(position)){
                    sendInfo("sedLahMinuta","cas", "Sed-ľah minúta ",predXpo);
                }
                else if (position==6 && whoa(position)){
                    sendInfo("skokDoDlzky","meranie", "Skok do dĺžky",predXpo);
                }
                else if (position==7 && whoa(position)){
                    Toast toast = Toast.makeText(requireContext(),"Táto hodnota sa automaticky vypočíta z dosahu zo stoja a z výskoku.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (position==8 && whoa(position)){
                    sendInfo("bagertPer","sport", "3",predXpo);
                }
                else if (position==9 && whoa(position)){
                    sendInfo("prstyPer","sport", "2",predXpo);
                }
                else if (position==10 && whoa(position)){
                    sendInfo("blokPer","sport", "5",predXpo);
                }
                else if (position==11 && whoa(position)){
                    sendInfo("lobCiarPer","sport", "1",predXpo);
                }
                else if (position==12 && whoa(position)){
                    sendInfo("lobDigPer","sport", "1",predXpo);
                }
                else if (position==13 && whoa(position)){
                    sendInfo("smecCiarPer","sport", "1",predXpo);
                }
                else if (position==14 && whoa(position)){
                    sendInfo("smecDigaPer","sport", "1",predXpo);
                }
                else if (position==15 && whoa(position)){
                    sendInfo("plachtaCiarPer","sport", "4",predXpo);
                }
                else if (position==16 && whoa(position)){
                    sendInfo("plachtaDigPer","sport", "4",predXpo);
                }
                else if (position==17 && whoa(position)){
                    sendInfo("skakanyCiaraPer","sport", "4",predXpo);
                }
                else if (position==18 && whoa(position)){
                    sendInfo("skakanyDigaPer","sport", "4",predXpo);
                }
                else {
                    Toast toast = Toast.makeText(requireContext(),"Toto si už testoval", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onDeleteClick(int position) {
                Toast toast = Toast.makeText(requireContext(),"Stav vykonania", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    public void sendInfo(String sX, String ifX, String whatTitle, String predXpoX){        //cas/meranie
        Bundle args = new Bundle();
        args.putString("idDoc",playerXD.getStringUID());
        args.putString("whatTest", sX);
        args.putString("whatTitle",whatTitle);
        args.putString("predXpo",predXpoX);
        if (ifX.equals("cas")) {
            System.out.println("CAS ****************************");
            navController.navigate(R.id.action_testPredViewFragment_to_testPredCas, args);
        }
        else if (ifX.equals("meranie")){
            System.out.println("MERANIE -----------------------------------------");
            navController.navigate(R.id.action_testPredViewFragment_to_testPredMeranie, args);
        }
        else if(ifX.equals("sport")){
            navController.navigate(R.id.action_testPredViewFragment_to_testPrstyTestovanieFragment, args);
        }
        else {
            System.out.println("OCH");
        }
    }

    public void fillPlNames() {
        int forEachIndex = 0;
        for (Player playerX : MainUser.getInstance().getData().getPlayersList()) {
            plNames[forEachIndex] = playerX.getMenoPl() + " " + playerX.getPriezviskoPl();
            forEachIndex++;
        }
    }

    public boolean rightName(String XD) {
        boolean ret = false;
        for (int i = 0; i < plNames.length; i++) {
            if (XD.equals(plNames[i])) {
                ret = true;
                for (Player playerX : MainUser.getInstance().getData().getPlayersList()) {
                    String menoPriez = playerX.getMenoPl() + " " + playerX.getPriezviskoPl();
                    if (menoPriez.equals(plNames[i])) {
                        playerXD = playerX;
                    }
                }
                break;
            }
        }
        return ret;
    }

    public void fillRecView() {
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, playerXD.getStarMovementTest().getClnkovyBeh().toString(), "Člnkový beh", playerXD.getStarMovementTest().getClnkovyBeh().equals("0") ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, playerXD.getStarMovementTest().getDosahStoj().toString()+" cm", "Dosah v stoji", playerXD.getStarMovementTest().getDosahStoj() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, playerXD.getStarMovementTest().getDosahVyskok().toString()+" cm", "Dosah vo výskoku", playerXD.getStarMovementTest().getDosahVyskok() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, playerXD.getStarMovementTest().getKilometerBeh().toString(), "Beh - kilometer", playerXD.getStarMovementTest().getKilometerBeh().equals("0") ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, playerXD.getStarMovementTest().getPlankVydrz().toString(), "Výdrž v planku", playerXD.getStarMovementTest().getPlankVydrz().equals("0") ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, String.valueOf(playerXD.getStarMovementTest().getSedLahMinuta()), "Sed-ľah minúta", playerXD.getStarMovementTest().getSedLahMinuta() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, playerXD.getStarMovementTest().getSkokDoDlzky().toString()+" cm", "Skok do dĺžky", playerXD.getStarMovementTest().getSkokDoDlzky() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, playerXD.getStarMovementTest().getVyskok().toString()+" cm", "Výskok", playerXD.getStarMovementTest().getVyskok() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));

        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getStartSpecificTest().getBagertPer())+" %", "Bager", playerXD.getStartSpecificTest().getBagertPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getStartSpecificTest().getPrstyPer())+" %", "Prsty", playerXD.getStartSpecificTest().getPrstyPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getStartSpecificTest().getBlokPer())+" %", "Blok", playerXD.getStartSpecificTest().getBlokPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getStartSpecificTest().getLobCiarPer())+" %", "Lob - čiara", playerXD.getStartSpecificTest().getLobCiarPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getStartSpecificTest().getLobDigPer())+" %", "Lob - diagonála", playerXD.getStartSpecificTest().getLobDigPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getStartSpecificTest().getSmecCiarPer())+" %", "Smeč - čiara", playerXD.getStartSpecificTest().getSmecCiarPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getStartSpecificTest().getSmecDigPer())+" %", "Smeč - diagonála", playerXD.getStartSpecificTest().getSmecDigPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getStartSpecificTest().getPlachtaCiarPer())+" %", "Plachta - čiara", playerXD.getStartSpecificTest().getPlachtaCiarPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getStartSpecificTest().getPlachtaDigPer())+" %", "Plachta - diagonála", playerXD.getStartSpecificTest().getPlachtaDigPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getStartSpecificTest().getSkakanyCiaraPer())+" %", "Skákaný servis - čiara", playerXD.getStartSpecificTest().getSkakanyCiaraPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getStartSpecificTest().getSkakanyDigaPer())+" %", "Skákaný servis - diagonála", playerXD.getStartSpecificTest().getSkakanyDigaPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
    }

    public void fillPORecView(){
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, playerXD.getFinalMovementTest().getClnkovyBeh().toString(), "Člnkový beh", playerXD.getFinalMovementTest().getClnkovyBeh().equals("0") ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, playerXD.getFinalMovementTest().getDosahStoj().toString()+" cm", "Dosah v stoji", playerXD.getFinalMovementTest().getDosahStoj() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, playerXD.getFinalMovementTest().getDosahVyskok().toString()+" cm", "Dosah vo výskoku", playerXD.getFinalMovementTest().getDosahVyskok() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, playerXD.getFinalMovementTest().getKilometerBeh().toString(), "Beh - kilometer", playerXD.getFinalMovementTest().getKilometerBeh().equals("0") ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, playerXD.getFinalMovementTest().getPlankVydrz().toString(), "Výdrž v planku", playerXD.getFinalMovementTest().getPlankVydrz().equals("0") ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, String.valueOf(playerXD.getFinalMovementTest().getSedLahMinuta()), "Sed-ľah minúta", playerXD.getFinalMovementTest().getSedLahMinuta() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, playerXD.getFinalMovementTest().getSkokDoDlzky().toString()+" cm", "Skok do dĺžky", playerXD.getFinalMovementTest().getSkokDoDlzky() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_blok, playerXD.getFinalMovementTest().getVyskok().toString()+" cm", "Výskok", playerXD.getFinalMovementTest().getVyskok() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));

        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getFinalSpecificTest().getBagertPer())+" %", "Bager", playerXD.getFinalSpecificTest().getBagertPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getFinalSpecificTest().getPrstyPer())+" %", "Prsty", playerXD.getFinalSpecificTest().getPrstyPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getFinalSpecificTest().getBlokPer())+" %", "Blok", playerXD.getFinalSpecificTest().getBlokPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getFinalSpecificTest().getLobCiarPer())+" %", "Lob - čiara", playerXD.getFinalSpecificTest().getLobCiarPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getFinalSpecificTest().getLobDigPer())+" %", "Lob - diagonála", playerXD.getFinalSpecificTest().getLobDigPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getFinalSpecificTest().getSmecCiarPer())+" %", "Smeč - čiara", playerXD.getFinalSpecificTest().getSmecCiarPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getFinalSpecificTest().getSmecDigPer())+" %", "Smeč - diagonála", playerXD.getFinalSpecificTest().getSmecDigPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getFinalSpecificTest().getPlachtaCiarPer())+" %", "Plachta - čiara", playerXD.getFinalSpecificTest().getPlachtaCiarPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getFinalSpecificTest().getPlachtaDigPer())+" %", "Plachta - diagonála", playerXD.getFinalSpecificTest().getPlachtaDigPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getFinalSpecificTest().getSkakanyCiaraPer())+" %", "Skákaný servis - čiara", playerXD.getFinalSpecificTest().getSkakanyCiaraPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_test, String.valueOf(playerXD.getFinalSpecificTest().getSkakanyDigaPer())+" %", "Skákaný servis - diagonála", playerXD.getFinalSpecificTest().getSkakanyDigaPer() == 0 ? R.drawable.thumbfalse : R.drawable.thumbtrue));

    }

    public void closeKeyBoard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public boolean whoa(int pos){
        boolean x = true;
        if (exampleItemArrayList.get(pos).getImageDelete()==R.drawable.thumbfalse){
            x = true;
        }
        else {
           x = false;
        }
        return x;
    }

    public void getArgsBundle(){
        if (getArguments()!=null){
            predXpo = getArguments().getString("predXpo");
        }
        else {
            System.out.println("nebavi :(");
        }
    }

}