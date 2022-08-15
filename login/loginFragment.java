package sk.upjs.ics.android.beachcoachapp.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Html;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.MainUserData;
import sk.upjs.ics.android.beachcoachapp.R;


public class loginFragment extends Fragment {

    private Button buttonLogin;
    private EditText editTextPasswd;
    private EditText editTextUsername;
    private CardView loginCard;
    private TextView register_here;
    private NavController navController;




    public loginFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainUserData user = new MainUserData();





        buttonLogin = (Button)getActivity().findViewById(R.id.loginButton);
        editTextPasswd = (EditText)getActivity().findViewById(R.id.editTextPasswd);
        editTextUsername = (EditText)getActivity().findViewById(R.id.editTextUsername);
        loginCard = (CardView)getActivity().findViewById(R.id.loginCard);
        register_here = (TextView)getActivity().findViewById(R.id.register_here);
        navController= Navigation.findNavController(view);

        String htmlString="Ešte nemáš účet?<u> Zaregistruj sa tu!</u>";
        register_here.setText(Html.fromHtml(htmlString));

        register_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickToLogin();

            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean continueReg = true;

                if (editTextUsername.getText().toString().isEmpty()) {
                    editTextUsername.setError("Zadaj meno");
                    editTextUsername.requestFocus();
                    continueReg = false;
                    return;
                }
                if (editTextPasswd.getText().toString().isEmpty()) {
                    editTextPasswd.setError("Zadaj email");
                    editTextPasswd.requestFocus();
                    continueReg = false;
                    return;
                }

                if (continueReg) {
                    final FirebaseAuth auth = FirebaseAuth.getInstance();
                    auth.signInWithEmailAndPassword(editTextUsername.getText().toString(), editTextPasswd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("Trainers").document(auth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            MainUserData user = task.getResult().toObject(MainUserData.class);
                                            MainUser.getInstance().setData(user);
                                            //user.addUserToList(user);
                                            //tu pridavam
                                            Toast.makeText(requireContext(), "Ahoj "+MainUser.getInstance().getData().getMeno()+"!", Toast.LENGTH_SHORT).show();
                                            goOn();
                                        } else {
                                            Toast.makeText(requireContext(), "Zlý email alebo heslo, skús prosím znovu", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(requireContext(), "Zlý email alebo heslo, skús prosím znovu", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else {
                    System.out.println("daco fakt chujovo");
                }
            }
        });

        /*YoYo.with(Techniques.FadeInLeft)
                .duration(1000)
                .repeat(0)
                .playOn(getView().findViewById(R.id.registerCard));*/


    }

    public void goOn(){
        Intent intent= new Intent(this.getActivity(), MainActivity.class);
        startActivity(intent);
    }

    public void onClickToLogin(){
        navController.navigate(R.id.action_loginFragment_to_registerFragment);
    }

    public void startAnim(){
        YoYo.with(Techniques.Tada)
                .duration(700)
                .repeat(5)
                .playOn(getView().findViewById(R.id.editTextUsername));
        YoYo.with(Techniques.Tada)
                .duration(700)
                .repeat(5)
                .playOn(getView().findViewById(R.id.editTextPasswd));
        YoYo.with(Techniques.Tada)
                .duration(700)
                .repeat(5)
                .playOn(getView().findViewById(R.id.loginButton));
    }
}