package sk.upjs.ics.android.beachcoachapp.pomocky.MojUcet;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.auth.FirebaseAuth;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.loginActivity;


public class mojUcetFragment extends Fragment {


    private TextView textMe, textPri, textMa;
    NavController navController;
    private CardView settButton;
    private CardView logButton;




    public mojUcetFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moj_ucet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textMa = (TextView)getActivity().findViewById(R.id.textMa);
        textMe = (TextView)getActivity().findViewById(R.id.textMe);
        textPri = (TextView)getActivity().findViewById(R.id.textPri);
        logButton = (CardView) getActivity().findViewById(R.id.logoutButton);
        settButton = (CardView)getActivity().findViewById(R.id.settButton);
        navController = Navigation.findNavController(view);
        MainUser user = MainUser.getInstance();

        textMe.setText(user.getData().getMeno());
        textPri.setText(user.getData().getPriezvisko());
        textMa.setText(user.getData().getEmail());
        animCard();


        logButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(requireContext(), "Odhlásenie", Toast.LENGTH_LONG).show();
                return false;
            }
        });
        settButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(requireContext(), "Nastavenia", Toast.LENGTH_LONG).show();

                return false;
            }
        });

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(requireContext(), "Úspešne odhlásený", Toast.LENGTH_LONG).show();
                logOutAct();
            }
        });
        settButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_mojUcetFragment_to_mojUcetSettFragment);
            }
        });



    }

    public void logOutAct(){
        Intent intent= new Intent(this.getActivity(), loginActivity.class);
        startActivity(intent);
    }

    public void animCard(){
        YoYo.with(Techniques.FadeInLeft)
                .duration(500)
                .repeat(0)
                .playOn(getView().findViewById(R.id.mojUcetCard));
    }
}