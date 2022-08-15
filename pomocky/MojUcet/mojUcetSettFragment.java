package sk.upjs.ics.android.beachcoachapp.pomocky.MojUcet;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.loginActivity;


public class mojUcetSettFragment extends Fragment {


    private TextView menoSett;
    private CardView zmenaMeno, zmenaHeslo, deleteAcc, deleteAlready, zmenaCard;
    private Button anoButt,  nieButt;
    private EditText editTextChameleon, editTextPass;
    private ImageView diskette;


    MainUser user = MainUser.getInstance();


    public mojUcetSettFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moj_ucet_sett, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menoSett = (TextView)getActivity().findViewById(R.id.menoSett);
        zmenaHeslo = (CardView)getActivity().findViewById(R.id.zmenaHeslo);
        zmenaMeno = (CardView)getActivity().findViewById(R.id.zmenaMeno);
        deleteAcc = (CardView)getActivity().findViewById(R.id.deleteAcc);
        deleteAlready = (CardView)getActivity().findViewById(R.id.deleteAlready);
        zmenaCard = (CardView)getActivity().findViewById(R.id.zmenaCard);
        editTextChameleon = (EditText)getActivity().findViewById(R.id.editTextChameleon);
        diskette = (ImageView)getActivity().findViewById(R.id.diskette);

        anoButt = (Button)getActivity().findViewById(R.id.anoButt);
        nieButt = (Button)getActivity().findViewById(R.id.nieButt);
        FirebaseAuth  auth = FirebaseAuth.getInstance();
        menoSett.setText(user.getData().getMeno());


        zmenaMeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editTextChameleon.setInputType(InputType.TYPE_CLASS_TEXT);
                YoYo.with(Techniques.SlideInDown)
                        .duration(500)
                        .repeat(0)
                        .playOn(getView().findViewById(R.id.zmenaCard));
                zmenaCard.setVisibility(View.VISIBLE);
                //chameleon ostava nemenim
                diskette.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                         // InputType.TYPE_CLASS_TEXT
                        String newMeno = editTextChameleon.getText().toString();
                        String[] datas = new String[2];
                        datas=newMeno.split(" ");
                        boolean continueReg = true;
                        if (newMeno.isEmpty()) {
                            editTextChameleon.setError("Zadaj meno");
                            editTextChameleon.requestFocus();
                            continueReg = false;
                            return;
                        }
                        if (!newMeno.contains(" ")){
                            editTextChameleon.setError("Zadaj meno a priezvisko!");
                            //editTextChameleon.requestFocus();
                            continueReg = false;
                            return;
                        }
                        if (continueReg){

                            FirebaseAuth  authX = FirebaseAuth.getInstance();
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            MainUser user = MainUser.getInstance();
                            user.getData().setMeno(datas[0]);
                            user.getData().setPriezvisko(datas[1]);

                            Map<String, Object> data = new HashMap<>();
                            data.put("meno",datas[0]);
                            db.collection("Trainers").document(authX.getCurrentUser().getUid()).set(data, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(requireContext(), "Tvoje meno bolo zmenené", Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(requireContext(), "Niečo sa pokazilo :(", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                            Map<String, Object> dataX = new HashMap<>();
                            dataX.put("priezvisko",datas[1]);
                            db.collection("Trainers").document(authX.getCurrentUser().getUid()).set(data, SetOptions.merge());
                            menoSett.setText(datas[0]);

                        }
                        else{
                            Toast.makeText(requireContext(), "daco chujovo", Toast.LENGTH_LONG).show();

                        }
                        YoYo.with(Techniques.SlideOutUp)
                                .duration(500)
                                .repeat(0)
                                .playOn(getView().findViewById(R.id.zmenaCard));



                    }
                });


            }
        });



        zmenaHeslo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                YoYo.with(Techniques.SlideInDown)
                        .duration(500)
                        .repeat(0)
                        .playOn(getView().findViewById(R.id.zmenaCard));
                zmenaCard.setVisibility(View.VISIBLE);
                editTextChameleon.setHint("Zadaj heslo");
                editTextChameleon.setTransformationMethod(PasswordTransformationMethod.getInstance());;

                diskette.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String newPass = editTextChameleon.getText().toString();
                        boolean contReg = true;
                        if (newPass.isEmpty()) {
                            editTextChameleon.setError("Zadaj heslo");
                            editTextChameleon.requestFocus();
                            contReg = false;
                            return;
                        }
                        if (newPass.length()<6) {
                            editTextChameleon.setError("Zadaj silnejšie heslo");
                            editTextChameleon.requestFocus();
                            contReg = false;
                            return;
                        }
                        if (contReg){
                            FirebaseAuth.getInstance().getCurrentUser().updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(requireContext(), "tvoje heslo je úspešne zmenené", Toast.LENGTH_LONG).show();
                                        YoYo.with(Techniques.SlideOutUp)
                                                .duration(500)
                                                .repeat(0)
                                                .playOn(getView().findViewById(R.id.zmenaCard));

                                    }
                                    else {
                                        Toast.makeText(requireContext(), "Niekde sa stala chyba, skús ešte raz", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }



                    }
                });


            }
        });


        deleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAlready.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInUp)
                        .duration(500)
                        .repeat(0)
                        .playOn(getView().findViewById(R.id.deleteAlready));

                anoButt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //delete acc
                        FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(requireContext(), "Tvoj účet bol úspešne deaktivovaný", Toast.LENGTH_LONG).show();
                                    goOnLogin();
                                }
                                else Toast.makeText(requireContext(), "Niečo sa pokazilo, skús ešte raz", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                nieButt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        YoYo.with(Techniques.SlideOutDown)
                                .duration(500)
                                .repeat(0)
                                .playOn(getView().findViewById(R.id.deleteAlready));
                        //deleteAlready.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });

     }

    public void goOnLogin(){
        Intent intent= new Intent(this.getActivity(), loginActivity.class);
        startActivity(intent);
    }


}