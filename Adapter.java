package sk.upjs.ics.android.beachcoachapp;

import android.content.Context;
import android.icu.util.ValueIterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import sk.upjs.ics.android.beachcoachapp.pokuty.Model;

public class Adapter extends PagerAdapter {

    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;
    @Override
    public int getCount() {
        return models.size();
    }

    public Adapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item,container,false);
        ImageView imageView;
        TextView txFineMeno, txFinePr;

        imageView = view.findViewById(R.id.ivFineImg);
        txFineMeno = view.findViewById(R.id.txFineMeno);
        txFinePr = view.findViewById(R.id.txFinePri);
        imageView.setImageResource(models.get(position).getImage());
        txFineMeno.setText(models.get(position).getTitle());
        txFinePr.setText(models.get(position).getDesc());
        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);

    }
}
