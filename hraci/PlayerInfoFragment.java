package sk.upjs.ics.android.beachcoachapp.hraci;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import sk.upjs.ics.android.beachcoachapp.MainActivity;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;
import sk.upjs.ics.android.beachcoachapp.objects.Player;


public class PlayerInfoFragment extends Fragment {

    private NavController navController;
    private ImageView imageLogoInfo;
    private TextView textMenoInfo, textMobileInfo, textMailInfo, textPrieInfo, textNarInfo, textKlubInfo, textVahaInfo, textVyskaInfo, textVelkostOblInfo, textPokutyInfo;
    CardView settCard, deleteCard;
    Player playerInfo = new Player();
    private String idDocX;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player_info, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        textMenoInfo = (TextView)getActivity().findViewById(R.id.textMeInfo);
        textPrieInfo  = (TextView)getActivity().findViewById(R.id.textPriInfo);
        textKlubInfo = (TextView)getActivity().findViewById(R.id.textKlubInfo);
        textNarInfo  = (TextView)getActivity().findViewById(R.id.textNarInfo);
        textVahaInfo = (TextView)getActivity().findViewById(R.id.textVahaInfo);
        textVyskaInfo  = (TextView)getActivity().findViewById(R.id.textVyskaInfo);
        textVelkostOblInfo  = (TextView)getActivity().findViewById(R.id.textVelkostOblInfo);
        textPokutyInfo  = (TextView)getActivity().findViewById(R.id.textPokutyInfo);
        deleteCard = (CardView)getActivity().findViewById(R.id.buttonDeleteInfo);
        settCard = (CardView)getActivity().findViewById(R.id.buttonSettInfo);
        imageLogoInfo = (ImageView)getActivity().findViewById(R.id.imageLogoInfo);
        textMailInfo = (TextView)getActivity().findViewById(R.id.textMailInfo);
        textMobileInfo = (TextView)getActivity().findViewById(R.id.textMobileInfo);



        if (getArguments()!=null){
            idDocX = getArguments().getString("idDoc");
            System.out.println(idDocX+" --------------------------------------------------");
            //lebo getArguments je null takze sa nikdynedostane ku davaniu idDocX
            //getArguments je null lebo neslo z view_fragment ale new_fragment
        }
        else {
            System.out.println("nebavi :(");
        }


        for (Player player:MainUser.getInstance().getData().getPlayersList()) {

            //error je ze nie je nic v idDocX lebo line 73
            if (player.getStringUID().equals(idDocX)){
                playerInfo = player;
                System.out.println("nasiel som hihihi");
            }
            else {
                System.out.println("nenasiel som hladam dalej");
            }
        }




       textMenoInfo.setText(playerInfo.getMenoPl());
       textPrieInfo.setText(playerInfo.getPriezviskoPl());
       textNarInfo.setText(playerInfo.getDatumNarPl());
       textVyskaInfo.setText(String.valueOf(playerInfo.getVyskaPl())+" cm");
       textVahaInfo.setText(String.valueOf(playerInfo.getVahaPl())+" kg");
       textVelkostOblInfo.setText(String.valueOf(playerInfo.getVelkostObleceniaPl()));
       textPokutyInfo.setText(String.valueOf(playerInfo.getPokuty())+" EUR");
       textKlubInfo.setText(playerInfo.getKlub());
       textMailInfo.setText(playerInfo.getMail());
       textMobileInfo.setText(playerInfo.getTelC());


        if (playerInfo.getPohlaviePl().equals("muz")){
            imageLogoInfo.setBackgroundResource(R.drawable.ic_baseline_boy);
        }
        else {
            System.out.println("nezmeni ti obrayok )))))))))))))))))))))))))))))))))))))))))");
        }


       settCard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Bundle args = new Bundle();
               args.putString("idDoc",playerInfo.getStringUID());
               navController.navigate(R.id.action_playerInfoFragment_to_playerSettingsFragment,args);
           }
       });
       deleteCard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
               firebaseFirestore.collection("Players").document(playerInfo.getStringUID()).delete();
               Toast.makeText(requireContext(), "Hráč úspešne odstránený! ", Toast.LENGTH_SHORT).show();
               Intent i = new Intent(getActivity(), MainActivity.class);
               startActivity(i);
           }
       });

    }
}