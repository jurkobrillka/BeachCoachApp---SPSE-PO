package sk.upjs.ics.android.beachcoachapp.testovania;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.shawnlin.numberpicker.NumberPicker;

import java.util.Arrays;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;

import static android.content.ContentValues.TAG;


public class TestUdajeFragment extends Fragment {


    private NumberPicker numPick;
    private SwitchCompat switchNum;
    private AutoCompleteTextView menoTest;
    private SwitchCompat switchEdit;
    private CardView start;
    private TestHrac t;
    private TextView popisText;
    private TextView gigiText;
    private static TestStart testStart;
    private NavController navController;
    private int coDoTestHrac=0;
    private final String[] plNames = new String[MainUser.getInstance().getData().getPlayersList().size()];



    public TestUdajeFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_prsty, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gigiText = (TextView)view.findViewById(R.id.gigiText);
        navController = Navigation.findNavController(view);
        numPick = (NumberPicker)view.findViewById(R.id.number_picker);
        switchNum = (SwitchCompat)view.findViewById(R.id.switchNum);
        switchEdit = (SwitchCompat)view.findViewById(R.id.switchEdit);
        menoTest = (AutoCompleteTextView) view.findViewById(R.id.menoTest);
        start = (CardView)view.findViewById(R.id.startCV);
        popisText = (TextView)view.findViewById(R.id.popis);
        fillPlNames();
        t = new TestHrac();
        testStart = new TestStart();
        setTitles(); //dal som tie vsetky ifi do metody set Titles

        ArrayAdapter<String>StAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1,plNames);
        menoTest.setAdapter(StAdapter);




        numPick.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                if (numPick.getValue()==0){
                    switchNum.setChecked(false);
                }
                else switchNum.setChecked(true);
            }
        });
        menoTest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (containName(menoTest.getText().toString())){
                    if (menoTest.getText().length()==0){
                        switchEdit.setChecked(false);
                    }
                    else switchEdit.setChecked(true);
                }
                else {
                    switchEdit.setChecked(false);
                }

                return false;
            }
        });
        menoTest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (containName(menoTest.getText().toString())){
                    if (menoTest.getText().length()==0){
                        switchEdit.setChecked(false);
                    }
                    else switchEdit.setChecked(true);
                }
                else {
                    switchEdit.setChecked(false);
                }
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchEdit.isChecked() &&  switchNum.isChecked()){
                    TestHrac t = new TestHrac(menoTest.getText().toString(),numPick.getValue(),coDoTestHrac);
                    t.addToList(t);
                    goOn();

                }
                else {
                    System.out.println("ta so----------------------------------------");
                }
            }
        });


    }

    public boolean containName(String sX){
        boolean ret = false;
        for (int i = 0; i <plNames.length ; i++) {
            if (sX.equals(plNames[i])){
                ret = true;
                break;
            }
        }
        return ret;
    }

    public void fillPlNames(){
        int forEachIndex = 0;
        for (Player playerX: MainUser.getInstance().getData().getPlayersList()) {
            plNames[forEachIndex]=playerX.getMenoPl()+" "+playerX.getPriezviskoPl();
            forEachIndex++;
        }
        for (int i = 0; i <plNames.length ; i++) {
            Log.i(TAG, "fillPlNames: "+plNames[i]);
        }
    }

    public void setTitles(){
        if (testStart.getCo()==1){
            gigiText.setText("Útočný úder");
            popisText.setText("smeč/cut");
            coDoTestHrac = 1;
        }
        else if (testStart.getCo()==2){
            gigiText.setText("Prsty");
            popisText.setText("nahrávka");
            coDoTestHrac = 2;
        }
        else if (testStart.getCo()==3){
            gigiText.setText("Bager");
            popisText.setText("prihrávka");
            coDoTestHrac = 3;
        }
        else if (testStart.getCo()==4){
            gigiText.setText("Servis");
            popisText.setText("skákaný/plachta");
            coDoTestHrac = 4;
        }
        else if (testStart.getCo()==5){
            gigiText.setText("Blok");
            popisText.setText("čínsky múr");
            coDoTestHrac = 5;
        }
        else System.out.println("ta more ta soooo");

    }
    public void goOn(){
        //testStart = new TestStart();
        TestHrac testHrac = new TestHrac();
        if (testHrac.getTestovanyHrac().get(0).getCoTestujem()==2){
            navController.navigate(R.id.action_testPrstyFragment_to_testPrstyTestovanieFragment);
        }
        else if (testHrac.getTestovanyHrac().get(0).getCoTestujem()==1){
            navController.navigate(R.id.action_testPrstyFragment_to_testUtokRozdelenieFragment);
        }
        else if (testHrac.getTestovanyHrac().get(0).getCoTestujem()==3){
            navController.navigate(R.id.action_testPrstyFragment_to_testPrstyTestovanieFragment);
        }
        else if (testHrac.getTestovanyHrac().get(0).getCoTestujem()==4){
            navController.navigate(R.id.action_testPrstyFragment_to_testUtokRozdelenieFragment);
        }
        else if (testHrac.getTestovanyHrac().get(0).getCoTestujem()==5){
            System.out.println("daco vymysli");
            navController.navigate(R.id.action_testPrstyFragment_to_testPrstyTestovanieFragment);
        }

        else System.out.println("cijebeee -----------------------------------------");
    }





}