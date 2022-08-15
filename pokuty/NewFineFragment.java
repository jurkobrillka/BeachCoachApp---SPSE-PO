package sk.upjs.ics.android.beachcoachapp.pokuty;

import android.content.Intent;
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
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.Fine;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;


public class NewFineFragment extends Fragment {


    EditText editNazov, editCena;
    ImageButton addButton;
    NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_fine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editCena = (EditText) getActivity().findViewById(R.id.editCena);
        editNazov = (EditText) getActivity().findViewById(R.id.editNazot);
        addButton = (ImageButton) getActivity().findViewById(R.id.addButton);
        navController = Navigation.findNavController(view);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean cont = true;

                if (editNazov.getText().toString().isEmpty()) {
                    editNazov.setError("Zadaj názov");
                    editNazov.requestFocus();
                    cont = false;
                    errorShake();
                    return;
                }
                if (editCena.getText().toString().isEmpty()) {
                    editCena.setError("Zadaj cenu");
                    editCena.requestFocus();
                    errorShake();
                    cont = false;
                    return;
                }
                if (cont) {

                    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                    final Fine fine = new Fine(FirebaseAuth.getInstance().getCurrentUser().getUid(),
                            Double.parseDouble(editCena.getText().toString()),
                            editNazov.getText().toString());

                    firebaseFirestore.collection("Fines").document().set(fine)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(requireContext(), "Pokuta úspešne vytvorená", Toast.LENGTH_SHORT).show();
                                        MainUser.getInstance().getData().addFine(fine);
                                        Intent i = new Intent(getActivity(), MainActivity.class);
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(requireContext(), "Niečo sa pokazilo, skús ešte raz", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(requireContext(), "Niečo sa pokazilo, skús ešte raz", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    public void errorShake() {
        YoYo.with(Techniques.Shake)
                .duration(500)
                .repeat(0)
                .playOn(getView().findViewById(R.id.addButton));
    }
}