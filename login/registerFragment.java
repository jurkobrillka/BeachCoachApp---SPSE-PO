package sk.upjs.ics.android.beachcoachapp.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.R;


public class registerFragment extends Fragment {

    private EditText textUsername;
    private EditText textEmail;
    private EditText textPassword;
    private Button registerButton;
    private CardView registerCard;
    private TextView login_here;
    NavController navController;
    private String StrUsername;
    private String StrEmail;
    private String StrPassword;



    public registerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textEmail = (EditText) getActivity().findViewById(R.id.textEmail);
        textPassword = (EditText) getActivity().findViewById(R.id.textPasswd);
        //textUsername = (EditText) getActivity().findViewById(R.id.textUserName);
        registerButton = (Button) getActivity().findViewById(R.id.registerButton);
        registerCard = (CardView) getActivity().findViewById(R.id.registerCard);
        login_here = (TextView)getActivity().findViewById(R.id.login_here);
        navController = Navigation.findNavController(view);

        String htmlString="Už máš účet? <u>Prihlás sa tu!</u>";
        login_here.setText(Html.fromHtml(htmlString));

        login_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickToLogin();
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //idem cekovat ci uzivatel neni dilino
                boolean continueReg = true;
                setStrings();
                /*if (StrUsername.isEmpty()) {
                    textUsername.setError("Zadaj meno");
                    textUsername.requestFocus();
                    continueReg = false;
                    return;
                }*/
                if (StrEmail.isEmpty()) {
                    textEmail.setError("Zadaj email");
                    textEmail.requestFocus();
                    continueReg = false;
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(StrEmail).matches()){
                    textEmail.setError("Zadaj správny formát emailu");
                    textEmail.requestFocus();
                    continueReg = false;
                    return;
                }
                if (StrPassword.isEmpty()){
                    textPassword.setError("Nezadal si heslo");
                    textPassword.requestFocus();
                    continueReg = false;
                    return;
                }
                if (StrPassword.length() < 6){
                    textPassword.setError("Zadal si príliš slabé heslo");
                    textPassword.requestFocus();
                    continueReg = false;
                    return;
                }


                if (continueReg) {
                    final FirebaseAuth auth = FirebaseAuth.getInstance();
                    auth.createUserWithEmailAndPassword(textEmail.getText().toString(), textPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        Map<String, Object> data = new HashMap<>();
                                        //data.put("username", textUsername.getText().toString());
                                        data.put("email", textEmail.getText().toString());
                                        db.collection("Trainers").document(auth.getCurrentUser().getUid()).set(data, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(requireContext(), "Registrácia prebehla úspešne\nVítame ťa "+/*textUsername.getText().toString()*/"!", Toast.LENGTH_SHORT).show();
                                                    goOn();
                                                } else {
                                                    Toast.makeText(requireContext(), "Bohužiaľ, niečo sa pokazilo\nSkús ešte raz prosím", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });


                                    } else {
                                        Toast.makeText(requireContext(), "Bohužiaľ, niečo sa pokazilo. Skús ešte raz prosím", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                }
                else {
                    System.out.println("Bohužiaľ, niečo sa pokazilo. Skús ešte raz prosím");
                }
            }
        });


        YoYo.with(Techniques.FadeInLeft)
                .duration(1000)
                .repeat(0)
                .playOn(getView().findViewById(R.id.registerCard));


    }

    public void onClickToLogin(){
        navController.navigate(R.id.action_registerFragment_to_loginFragment);
    }

    public void startAnim() {

        YoYo.with(Techniques.FadeInLeft)
                .duration(700)
                .repeat(5)
                .playOn(getView().findViewById(R.id.textEmail));
        YoYo.with(Techniques.FadeInLeft)
                .duration(700)
                .repeat(5)
                .playOn(getView().findViewById(R.id.textPasswd));
        YoYo.with(Techniques.FadeInLeft)
                .duration(700)
                .repeat(5)
                .playOn(getView().findViewById(R.id.registerButton));
    }

    public void setStrings(){
        //StrUsername = textUsername.getText().toString();
        StrEmail = textEmail.getText().toString();
        StrPassword = textPassword.getText().toString();
    }

    public void goOn(){
        navController.navigate(R.id.action_registerFragment_to_registerDataFragment);
    }


}