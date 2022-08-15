package sk.upjs.ics.android.beachcoachapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener;




    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView logo;
        public TextView viewMeno;
        public TextView viewPriezvisko;
        private ImageButton buttonDelete;


        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            logo = itemView.findViewById(R.id.image);
            viewMeno = itemView.findViewById(R.id.titleMeno);
            viewPriezvisko = itemView.findViewById(R.id.titlePriez);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }



    }



    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(v, mListener);
        return exampleViewHolder;
    }
    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);
        int cfx = mExampleList.get(position).getImageDelete();
        holder.logo.setImageResource(currentItem.getImageResource());
        holder.viewMeno.setText(currentItem.getTitleMeno());
        holder.viewPriezvisko.setText(currentItem.getTitlePriezvisko());
        holder.buttonDelete.setImageResource(cfx);

    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
