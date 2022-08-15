package sk.upjs.ics.android.beachcoachapp.login;

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
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.MainUserData;
import sk.upjs.ics.android.beachcoachapp.R;

public class registerDataFragment extends Fragment {

    EditText textMeno;
    EditText textPriezvisko;
    EditText textKlub;
    Button   goOnButton;
    CardView registerDataCard;
    FirebaseFirestore db = FirebaseFirestore.getInstance();





    public registerDataFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_data, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textKlub = (EditText)getActivity().findViewById(R.id.textKlub);
        textMeno = (EditText)getActivity().findViewById(R.id.textMeno);
        textPriezvisko = (EditText)getActivity().findViewById(R.id.textPriezvisko);
        goOnButton = (Button)getActivity().findViewById(R.id.goOnButton);
        registerDataCard = (CardView)getActivity().findViewById(R.id.registerDataCard);
        final FirebaseAuth auth = FirebaseAuth.getInstance();

        YoYo.with(Techniques.FadeInLeft)
                .duration(1000)
                .repeat(0)
                .playOn(getView().findViewById(R.id.registerDataCard));


        goOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String klubS = textKlub.getText().toString();
                boolean cont = true;

                if (textMeno.getText().toString().isEmpty()) {
                    textMeno.setError("Zadaj meno");
                    textMeno.requestFocus();
                    cont = false;
                    return;
                }
                if (textPriezvisko.getText().toString().isEmpty()) {
                    textPriezvisko.setError("Zadaj priezvisko");
                    textPriezvisko.requestFocus();
                    cont = false;
                    return;
                }
                if (textMeno.getText().toString().isEmpty()) {
                    klubS = "X";
                }


                if (cont){
                    Map<String, Object> data = new HashMap<>();
                //data.put("username", textUsername.getText().toString());
                data.put("meno", textMeno.getText().toString());
                data.put("priezvisko", textPriezvisko.getText().toString());
                data.put("klub", klubS);
                db.collection("Trainers").document(auth.getCurrentUser().getUid()).set(data, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(requireContext(), "Úspešne si sa zaregistroval!", Toast.LENGTH_SHORT).show();

                            db.collection("Trainers").document(auth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        MainUserData user = task.getResult().toObject(MainUserData.class);
                                        MainUser.getInstance().setData(user);
                                        //user.addUserToList(user);
                                        //tu pridavam
                                        Toast.makeText(requireContext(), "Ahoj " + user.getMeno() + "!", Toast.LENGTH_SHORT).show();
                                        goOn();
                                    } else {
                                        Toast.makeText(requireContext(), "Niečo sa pokazilo, skús prosím znovu", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(requireContext(), "Niečo sa pokazilo, skús prosím znovu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
            }
        });
    }

    public void goOn(){
        Intent intent= new Intent(this.getActivity(), MainActivity.class);
        startActivity(intent);
    }
}


