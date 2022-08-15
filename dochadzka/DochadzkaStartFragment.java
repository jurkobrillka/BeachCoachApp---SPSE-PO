package sk.upjs.ics.android.beachcoachapp.dochadzka;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.type.DateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sk.upjs.ics.android.beachcoachapp.ExampleAdapter;
import sk.upjs.ics.android.beachcoachapp.ExampleItem;
import sk.upjs.ics.android.beachcoachapp.R;
import sk.upjs.ics.android.beachcoachapp.objects.MainUser;

public class DochadzkaStartFragment extends Fragment {

    private CalendarView calView;
    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    NavController navController;
    final ArrayList<ExampleItem> exampleItemArrayList = new ArrayList<>();
    private int day;
    private int month;
    private Bundle args;
    private int year;




    public DochadzkaStartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dochadzka_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calView = (CalendarView)view.findViewById(R.id.calView);
        recyclerView = (RecyclerView)view.findViewById(R.id.recViewDochadzka);
        navController = Navigation.findNavController(view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new ExampleAdapter(exampleItemArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        exampleItemArrayList.clear();
        exampleItemArrayList.add(new ExampleItem(R.drawable.plus_button,"tréning","Nový",R.drawable.ic_baseline_clear));
        exampleItemArrayList.add(new ExampleItem(R.drawable.thumbtrue,"v danom dni","Tréningy",R.drawable.ic_baseline_clear));
        exampleItemArrayList.add(new ExampleItem(R.drawable.ic_baseline_ciernypetro,"hráčov","Dochádzka",R.drawable.ic_baseline_clear));

        setRightDate();



        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                day = i;
                month = i1;
                year = i2;
            }
        });

         //TODO toto tu dorob

        adapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position==0){
                   //novy trening
                    setBundle();
                    navController.navigate(R.id.action_dochadzkaStartFragment_to_dochadzkaNovyTreningFragment,args);
                }
                else if(position==1){
                    setBundle();
                    navController.navigate(R.id.action_dochadzkaStartFragment_to_dochadzkaTreningyViewFragment,args);
                    //pozti den
                }
                else {
                    navController.navigate(R.id.action_dochadzkaStartFragment_to_dochadzkaAllPlayers);
                }
            }

            @Override
            public void onDeleteClick(int position) {


            }
        });
    }

    public void setBundle(){
        args = new Bundle();
        System.out.println(day+" : "+month+" : "+year+ " **************args*********************************************");

        args.putInt("day",day);
        args.putInt("month",month+1);
        args.putInt("year",year);
    }

    public void setRightDate(){
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String dayX = currentDate.charAt(0)+""+currentDate.charAt(1);
        String monthX = currentDate.charAt(3)+""+currentDate.charAt(4);
        String yearX = currentDate.charAt(6)+""+currentDate.charAt(7)+""+currentDate.charAt(8)+""+currentDate.charAt(9);
        day = Integer.parseInt(dayX);
        month = Integer.parseInt(monthX)-1;
        year = Integer.parseInt(yearX);

        System.out.println(currentDate+ " *****************************************");
        System.out.println(day+" : "+month+" : "+year+ " ***********************************************************");


        int y, d, m;
        y = year;
        d = day;
        m = month;
        day = y;
        year = d;
    }

}