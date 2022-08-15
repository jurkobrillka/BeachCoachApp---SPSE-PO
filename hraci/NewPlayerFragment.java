package sk.upjs.ics.android.beachcoachapp.hraci;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shawnlin.numberpicker.NumberPicker;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.FinalMovementTest;
import sk.upjs.ics.android.beachcoachapp.objects.FinalSpecificTest;
import sk.upjs.ics.android.beachcoachapp.objects.LastPerformance;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.MainUserData;
import sk.upjs.ics.android.beachcoachapp.objects.Player;

import static android.content.ContentValues.TAG;


public class NewPlayerFragment extends Fragment {


    private EditText editNewMeno, editNewPriezvisko, editNewNarodenie, editNewVyska, editNewVaha, editNewKlub, editNewMobile, editNewPlaMail;
    private Switch swPohlavie;
    private TextView textNewMuz;
    private TextView textNewZena;
    private ImageButton saveDiskette;
    private NavController navController;
    private NumberPicker numberPicker;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_player, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        editNewMeno = (EditText) getActivity().findViewById(R.id.editNewMeno);
        editNewPriezvisko = (EditText) getActivity().findViewById(R.id.editNewPriezvisko);
        editNewNarodenie = (EditText) getActivity().findViewById(R.id.editNewDatumNar);
        editNewVaha = (EditText) getActivity().findViewById(R.id.editNewVaha);
        editNewVyska = (EditText) getActivity().findViewById(R.id.editNewVyska);
        swPohlavie = (Switch) getActivity().findViewById(R.id.swNewPohlvie);
        textNewMuz = (TextView) getActivity().findViewById(R.id.textColMuz);
        textNewZena = (TextView) getActivity().findViewById(R.id.textColZen);
        saveDiskette = (ImageButton) getActivity().findViewById(R.id.saveDiskette);
        editNewKlub = (EditText) getActivity().findViewById(R.id.editNewKlub);
        numberPicker = (NumberPicker) getActivity().findViewById(R.id.number_pickerSize);
        editNewMobile = (EditText) getActivity().findViewById(R.id.editNewMobile);
        editNewPlaMail = (EditText) getActivity().findViewById(R.id.editNewPlayerMail);


        navController = Navigation.findNavController(view);

        String[] velkosti = {"XS", "S", "M", "L", "XL", "XXL"};
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(velkosti.length);
        numberPicker.setDisplayedValues(velkosti);
        numberPicker.setValue(4);


        swPohlavie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (swPohlavie.isChecked()) {
                    textNewZena.setTextColor(Color.GREEN);
                    textNewMuz.setTextColor(Color.GRAY);
                } else {
                    textNewZena.setTextColor(Color.GRAY);
                    textNewMuz.setTextColor(Color.GREEN);
                }
            }
        });

        saveDiskette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean cont = true;


                if (editNewMeno.getText().toString().isEmpty()) {
                    editNewMeno.setError("Zadaj meno");
                    editNewMeno.requestFocus();
                    cont = false;
                    errorShake();
                    System.out.println("zly vstup alebo ziadny");
                    return;
                }
                if (editNewPriezvisko.getText().toString().isEmpty()) {
                    editNewPriezvisko.setError("Zadaj priezvisko");
                    editNewPriezvisko.requestFocus();
                    cont = false;
                    errorShake();
                    System.out.println("zly vstup alebo ziadny");
                    return;
                }
                if (editNewNarodenie.getText().toString().isEmpty()) {
                    editNewNarodenie.setError("Zadaj dátum narodenia");
                    editNewNarodenie.requestFocus();
                    cont = false;
                    errorShake();
                    System.out.println("zly vstup alebo ziadny");
                    return;
                }
                if (editNewPlaMail.getText().toString().isEmpty()) {
                    editNewNarodenie.setError("Zadaj mail");
                    editNewNarodenie.requestFocus();
                    cont = false;
                    errorShake();
                    System.out.println("zly vstup alebo ziadny");
                    return;
                }
                if (editNewMobile.getText().toString().isEmpty()) {
                    editNewNarodenie.setError("Zadaj tel. číslo");
                    editNewNarodenie.requestFocus();
                    cont = false;
                    errorShake();
                    System.out.println("zly vstup alebo ziadny");
                    return;
                }
                if (editNewKlub.getText().toString().isEmpty()) {
                    editNewKlub.setError("Zadaj klub");
                    editNewKlub.requestFocus();
                    cont = false;
                    errorShake();
                    System.out.println("zly vstup alebo ziadny");
                    return;
                }
                if (editNewVaha.getText().toString().isEmpty()) {
                    editNewVaha.setError("Zadaj váhu");
                    editNewVaha.requestFocus();
                    cont = false;
                    errorShake();
                    System.out.println("zly vstup alebo ziadny");
                    return;
                }
                if (editNewVyska.getText().toString().isEmpty()) {
                    editNewVyska.setError("Zadaj výšku");
                    editNewVyska.requestFocus();
                    cont = false;
                    errorShake();
                    System.out.println("zly vstup alebo ziadny");
                    return;
                }
                if (cont) {


                    //TODO uloz noveho hraca!!!!!!
                    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                    final FinalMovementTest finalMovementTestZ = new FinalMovementTest("0","0",0.0,0.0,0.0,0.0,0,"0");
                    final FinalSpecificTest finalSpecificTestZ = new FinalSpecificTest(0,0,0,0,0,0,0,0,0,0,0);
                    final FinalMovementTest StartMovementTestZ = new FinalMovementTest("0","0",0.0,0.0,0.0,0.0,0,"0");
                    final FinalSpecificTest StartSpecificTestZ = new FinalSpecificTest(0,0,0,0,0,0,0,0,0,0,0);

                    final LastPerformance lastPerformanceZ = new LastPerformance(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                    final Player player = new Player(
                            FirebaseAuth.getInstance().getCurrentUser().getUid(),
                            editNewMeno.getText().toString(),
                            editNewPriezvisko.getText().toString(),
                            swPohlavie.isChecked() ? "zena" : "muz",
                            editNewNarodenie.getText().toString(),
                            Double.parseDouble(editNewVyska.getText().toString()),
                            Double.parseDouble(editNewVaha.getText().toString()),
                            editNewKlub.getText().toString(),
                            setSize(),
                            0,
                            editNewPlaMail.getText().toString(),
                            editNewMobile.getText().toString(),
                            lastPerformanceZ,
                            finalMovementTestZ,
                            finalSpecificTestZ,
                            StartSpecificTestZ,
                            StartMovementTestZ);



                    firebaseFirestore.collection("Players").document().set(player).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(requireContext(), "Hráč úspešne vytvorený", Toast.LENGTH_SHORT).show();
                                System.out.println(player.toString()+" ********************************");
                                MainUser.getInstance().getData().addPlayer(player);
                                Intent i = new Intent(getActivity(), MainActivity.class);
                                startActivity(i);
                                //TODO daj toto ked dokoncis lastPerformances  navController.navigate(R.id.action_newPlayerFragment_to_playerViewFragment);
                            } else {
                                Toast.makeText(requireContext(), "Niečo sa pokazilo, skús ešte raz", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                } else {
                    Toast.makeText(requireContext(), "Niečo sa muselo pokaziť, skús prosím znovu", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public String setSize() {

        switch (String.valueOf(numberPicker.getValue())) {
            case "1":
                return "XS";
            case "2":
                return "S";
            case "3":
                return "M";
            case "4":
                return "L";
            case "5":
                return "XL";
            case "6":
                return "XXL";
            default:
                return "L";
        }

    }




    public void errorShake() {
        YoYo.with(Techniques.Shake)
                .duration(500)
                .repeat(0)
                .playOn(getView().findViewById(R.id.saveDiskette));
    }
}