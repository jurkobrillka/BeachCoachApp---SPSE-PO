package sk.upjs.ics.android.beachcoachapp.testovania.prsty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;
import sk.upjs.ics.android.beachcoachapp.testovania.TestHrac;


public class TestPrstyTestovanieFragment extends Fragment {

    private ImageButton thumbUp, thumbDown, clearOK, clearX;
    private TextView counter, counterClear, menoTest, counterDone, counterAll, firstText, secondText, text1;
    private TestHrac testHrac;
    private int counterThumbInt;
    private int counterClearInt;
    private int counterX;
    private int counterDoneInt;
    private CardView goOn, counterView;
    private Player playerInfo;
    private String idDocXX;
    private String whatTitle;
    private String sX;
    private NavController navController;
    private String predXpo;





    public TestPrstyTestovanieFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_prsty_testovanie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        thumbUp = (ImageButton)view.findViewById(R.id.thumbUp);
        thumbDown = (ImageButton)view.findViewById(R.id.thumbDown);
        clearOK = (ImageButton)view.findViewById(R.id.clearOK);
        clearX = (ImageButton)view.findViewById(R.id.clearX);
        counter = (TextView)view.findViewById(R.id.counter);
        counterClear = (TextView)view.findViewById(R.id.clearCounter);
        menoTest = (TextView)view.findViewById(R.id.menoTest);
        counterAll = (TextView)view.findViewById(R.id.counterAll);
        counterDone = (TextView)view.findViewById(R.id.counterDone);
        counterView = (CardView) view.findViewById(R.id.counterView);
        firstText = (TextView)view.findViewById(R.id.firstText);
        secondText = (TextView)view.findViewById(R.id.secondText);
        goOn = (CardView)view.findViewById(R.id.goOn);
        text1 = (TextView)view.findViewById(R.id.text1);
        navController = Navigation.findNavController(view);
        testHrac = new TestHrac();
        counterClearInt=0;
        counterThumbInt=0;
        counterX = 0;
        counterDoneInt=0;
        getArgsBundle();

        //todo - zmen triedu TestHrac aby si mal uz vsetko co ti treba, pomen to string a dorob ostatne 2, zajtra to mas vsetko hotove. Inac pokuta. Dnes si navrhni navrhy cmuq


        if (testHrac.getTestovanyHrac().isEmpty()){
            setFoundPlayer();
        }
        else {
            setTitles();
        }

        counterDone.setText(Integer.toString(counterDoneInt));


        clearOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterClearInt++;
                if(counterClearInt>counterDoneInt){
                counterClearInt--;
                }
                else {
                    System.out.println("OKIII ");
                }
                checkEnd();
            }
        });
        clearX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEnd();
            }
        });


        thumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            counterX++;
            counterDoneInt++;
            checkEnd();
            }
        });
        thumbDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterDoneInt++;
                checkEnd();
            }
        });


        goOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (testHrac.getTestovanyHrac().isEmpty()){
                    if (predXpo.equals("pred")) {
                        writeFireBaseTest("startSpecificTest");
                    }
                    else if (predXpo.equals("po")){
                        writeFireBaseTest("finalSpecificTest");
                    }
                    else {
                        System.out.println("lol");
                    }
                }
                else {
                    testHrac.getTestovanyHrac().get(0).setOkX(counterClearInt);
                    testHrac.getTestovanyHrac().get(0).setLikeDislike(counterX);
                    navController.navigate(R.id.action_testPrstyTestovanieFragment_to_testVyhodnotenie);
                }


            }
        });
    }
        public void setNumbers(){
            counter.setText(Integer.toString(counterX));
            counterClear.setText(Integer.toString(counterClearInt));
            counterDone.setText(Integer.toString(counterDoneInt));

        }

        public void animErrorStart(){
            YoYo.with(Techniques.Shake)
                    .duration(300)
                    .playOn(counterView);
        }

        public void checkEnd(){
        if (testHrac.getTestovanyHrac().isEmpty()){
            if (counterDoneInt>20){
                animErrorStart();
            }
            else {
                setNumbers();
            }
        }
        else {
            if (counterDoneInt>testHrac.getTestovanyHrac().get(0).getPocetOpakovani()){
                System.out.println("sprav anim");
                animErrorStart();
            }
            else {
                setNumbers();
            }
        }

        }

        public void setTitles(){
            counterAll.setText(Integer.toString(testHrac.getTestovanyHrac().get(0).getPocetOpakovani()));
            menoTest.setText(testHrac.getTestovanyHrac().get(0).getMenoTestovaneho());
            counterDone.setText(Integer.toString(counterDoneInt));
            if (testHrac.getTestovanyHrac().get(0).getCoTestujem()==1){
                firstText.setText("Smer úderu");
                secondText.setText("In/Out");
                text1.setText("Útočný úder");
                //uder
            }
            else if (testHrac.getTestovanyHrac().get(0).getCoTestujem()==2){
                firstText.setText("Presnosť nahrávky");
                secondText.setText("Čistota nahrávky");
                text1.setText("Prsty");
                //prsty
            }
            else if (testHrac.getTestovanyHrac().get(0).getCoTestujem()==3){
                firstText.setText("Presnosť príjmu");
                secondText.setText("Náhra prstami");
                text1.setText("Bager");
            }
            else if (testHrac.getTestovanyHrac().get(0).getCoTestujem()==4){
                firstText.setText("Správny smer");
                secondText.setText("Eso/Chyba");
                text1.setText("Servis");
            }
            else if (testHrac.getTestovanyHrac().get(0).getCoTestujem()==5){
                firstText.setText("Úspešný blok");
                secondText.setText("Bod/Chyba");
                text1.setText("Blok");
            }
        }

        public void setFoundPlayer(){
        idDocXX = "";
        whatTitle = "";
            if (getArguments()!=null){
                idDocXX = getArguments().getString("idDoc");
                whatTitle = getArguments().getString("whatTitle");
                sX = getArguments().getString("whatTest");
            }
            else {
                System.out.println("nebavi :(");
            }
            for (Player player: MainUser.getInstance().getData().getPlayersList()) {

                if (player.getStringUID().equals(idDocXX)){
                    playerInfo = player;
                }
                else {}
            }

            menoTest.setText(playerInfo.getMenoPl()+" "+playerInfo.getPriezviskoPl());
            counterAll.setText("20");

            if (whatTitle.equals("1")){
                firstText.setText("Smer úderu");
                secondText.setText("In/Out");
                text1.setText("Útočný úder");
                //uder
            }
            else if (whatTitle.equals("2")){
                firstText.setText("Presnosť nahrávky");
                secondText.setText("Čistota nahrávky");
                text1.setText("Prsty");
                //prsty
            }
            else if (whatTitle.equals("3")){
                firstText.setText("Presnosť príjmu");
                secondText.setText("Náhra prstami");
                text1.setText("Bager");
            }
            else if (whatTitle.equals("4")){
                firstText.setText("Správny smer");
                secondText.setText("Eso/Chyba");
                text1.setText("Servis");
            }
            else if (whatTitle.equals("5")){
                firstText.setText("Úspešný blok");
                secondText.setText("Bod/Chyba");
                text1.setText("Blok");
            }
        }

        //startSpecificTest
        public void writeFireBaseTest(String field){
        double finalUsp = ((double) counterX/20.0)*100.0;
            DocumentReference documentReference = FirebaseFirestore.getInstance()
                    .collection("Players")
                    .document(idDocXX);
            documentReference.update(field+"."+sX, finalUsp)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(requireContext(), "Údaje úspešne zapísané!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(requireContext(), "Nepodarilo sa :(", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
             Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
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