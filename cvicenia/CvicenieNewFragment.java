package sk.upjs.ics.android.beachcoachapp.cvicenia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.Exercice;
import sk.upjs.ics.android.beachcoachapp.objects.Fine;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;


public class CvicenieNewFragment extends Fragment {


    private EditText editNazovE, editPopisE, editCasE;
    private ImageButton addButtonE;
    private NavController navController;


    public CvicenieNewFragment() {
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
        return inflater.inflate(R.layout.fragment_cvicenie_new, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editCasE = (EditText) getActivity().findViewById(R.id.editCasE);
        editNazovE = (EditText) getActivity().findViewById(R.id.editNazovE);
        editPopisE = (EditText) getActivity().findViewById(R.id.editPopisE);
        addButtonE = (ImageButton) getActivity().findViewById(R.id.addButtonE);
        navController = Navigation.findNavController(view);

        addButtonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean cont = true;

                if (editNazovE.getText().toString().isEmpty()) {
                    editNazovE.setError("Zadaj názov");
                    editNazovE.requestFocus();
                    cont = false;
                    errorShake();
                    return;
                }
                if (editPopisE.getText().toString().isEmpty()) {
                    editPopisE.setError("Zadaj popis");
                    editPopisE.requestFocus();
                    cont = false;
                    errorShake();
                    return;
                }
                if (editCasE.getText().toString().isEmpty()) {
                    editCasE.setError("Zadaj čas");
                    editCasE.requestFocus();
                    cont = false;
                    errorShake();
                    return;
                }

                if (cont) {

                    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                    final DocumentReference ref = firebaseFirestore.collection("Exercices").document();


                    final Exercice exercice = new Exercice(FirebaseAuth.getInstance().getCurrentUser().getUid(),
                            Double.parseDouble(editCasE.getText().toString()),
                            editPopisE.getText().toString(),
                            editNazovE.getText().toString()
                    );

                    firebaseFirestore.collection("Exercices").add(exercice)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(requireContext(), "Pokuta úspešne vytvorená", Toast.LENGTH_SHORT).show();
                                        exercice.setStringUID(task.getResult().getId());
                                        MainUser.getInstance().getData().addExercise(exercice);
                                        goBack();
                                    } else {
                                        Toast.makeText(requireContext(), "Niečo sa pokazilo, skús ešte raz", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(requireContext(), "Niečo sa pokazilo, skús ešte raz", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }

    public void goBack() {
        navController.navigate(R.id.action_cvicenieNewFragment_to_cviceniaStartFragment);
    }

    public void errorShake() {
        YoYo.with(Techniques.Shake)
                .duration(500)
                .repeat(0)
                .playOn(getView().findViewById(R.id.addButtonE));
    }
}