package sk.upjs.ics.android.beachcoachapp.pokuty;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import sk.upjs.ics.android.beachcoachapp.ExampleAdapter;
import sk.upjs.ics.android.beachcoachapp.ExampleItem;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<Model> modelsList;
    private MainAdapter.OnItemClickListener mListener;


    public MainAdapter(ArrayList<Model> modelsList) {
        this.modelsList = modelsList;
    }




    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);

    }

    public void setOnItemClickListener(MainAdapter.OnItemClickListener listener) {
        mListener = listener;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.example_fine_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model currentItem = modelsList.get(position);

        holder.imageViewMod.setImageResource(currentItem.getImage());
        holder.txtFineChooseMeno.setText(currentItem.getTitle());
        holder.txtFineChooseCena.setText(currentItem.getDesc());

    }

    @Override
    public int getItemCount() {
        return modelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewMod;
        TextView txtFineChooseMeno,txtFineChooseCena;
        ImageButton pokutuj;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewMod = itemView.findViewById(R.id.imgFineChoose);
            txtFineChooseMeno = itemView.findViewById(R.id.txtFineChooseMeno);
            txtFineChooseCena = itemView.findViewById(R.id.txtFineChooseCena);
            pokutuj = itemView.findViewById(R.id.buttFineChoosePokutuj);




            pokutuj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    FineChooseFragment f = new FineChooseFragment();
                    FineXHelp fx = new FineXHelp();
                    double stavChudaka = MainUser.getInstance().getData().getPlayersList().get(getAdapterPosition()).getPokuty();
                    double cenaPokuty = fx.getxList().get(0).getCenaXHelp();
                    System.out.println(cenaPokuty+" ***************************");
                    double huraKonecDufam = stavChudaka+cenaPokuty;
                    MainUser.getInstance().getData().getPlayersList().get(getAdapterPosition()).setPokuty(huraKonecDufam);
                    txtFineChooseCena.setText(String.valueOf((stavChudaka+cenaPokuty)));
                    System.out.println("POKUTUJEE BOHAAAAAAAA ....................................................................................."+getAdapterPosition()+",, "+ MainUser.getInstance().getData().getPlayersList().get(getAdapterPosition()).getPokuty());

                    DocumentReference documentReference = FirebaseFirestore.getInstance()
                            .collection("Players")
                            .document(MainUser.getInstance().getData().getPlayersList().get(getAdapterPosition()).getStringUID());
                    documentReference.update(
                            "pokuty",huraKonecDufam
                    ).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(view.getContext(), "Hráč bol pokutovaný!", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(view.getContext(),"Hráč nebol pokutovaný", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            });
        }




    }
}
