package sk.upjs.ics.android.beachcoachapp.pomocky.Stopky;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import sk.upjs.ics.android.beachcoachapp.R;

import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Locale;


public class ToolsStopkyFragment extends Fragment {

    TextView timeChrono;
    ImageButton stopButtonVector,pauseButtonVector,startButtonVector;
    /*private boolean isResume;
    Handler handler;
    long tMilliSec,tBuff,tStart,tUpdate = 0L;
    int sec,min,milliSec;*/

    private int seconds = 0;

    // Is the stopwatch running?
    private boolean running;

    private boolean wasRunning;




    public ToolsStopkyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {

            // Get the previous state of the stopwatch
            //            // if the activity has been
            //            // destroyed and recreated.
            seconds
                    = savedInstanceState
                    .getInt("seconds");
            running
                    = savedInstanceState
                    .getBoolean("running");
            wasRunning
                    = savedInstanceState
                    .getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tools_stopky, container, false);



    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*if (savedInstanceState != null) {

            // Get the previous state of the stopwatch
            //            // if the activity has been
            //            // destroyed and recreated.
            seconds
                    = savedInstanceState
                    .getInt("seconds");
            running
                    = savedInstanceState
                    .getBoolean("running");
            wasRunning
                    = savedInstanceState
                    .getBoolean("wasRunning");
        }
        runTimer();*/


        timeChrono = (TextView) view.findViewById(R.id.timeChrono);
        stopButtonVector = (ImageButton)view.findViewById(R.id.stopButtonVector);
        pauseButtonVector = (ImageButton)view.findViewById(R.id.pauseButtonVector);
        startButtonVector = (ImageButton)view.findViewById(R.id.startButtonVector);
        timeChrono.setText("00:00");

    pauseButtonVector.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onClickStop();
            YoYo.with(Techniques.Flash)
                    .duration(700)
                    .repeat(0)
                    .playOn(getView().findViewById(R.id.pauseButtonVector));
        }
    });

    startButtonVector.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onClickStart();
            YoYo.with(Techniques.Flash)
                    .duration(700)
                    .repeat(0)
                    .playOn(getView().findViewById(R.id.startButtonVector));


        }
    });
    stopButtonVector.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onClickReset();
            YoYo.with(Techniques.Flash)
                    .duration(700)
                    .repeat(0)
                    .playOn(getView().findViewById(R.id.stopButtonVector));

        }
    });
    }


    @Override
    public void onSaveInstanceState(
            Bundle savedInstanceState)
    {
        savedInstanceState
                .putInt("seconds", seconds);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    public void onClickStart() {
        running = true;
    }

    public void onClickStop() {
        running = false;
    }

    public void onClickReset() {
        running = false;
        seconds = 0;
    }

    private void runTimer() {

        // Get the text view.

        // Creates a new Handler
        final Handler handler
                = new Handler();

        // Call the post() method,
        // passing in a new Runnable.
        // The post() method processes
        // code without a delay,
        // so the code in the Runnable
        // will run almost immediately.
        handler.post(new Runnable() {
            @Override

            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // Format the seconds into hours, minutes,
                // and seconds.
                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d",hours, minutes, secs);

                // Set the text view text.
                timeChrono.setText(time);

                // If running is true, increment the
                // seconds variable.
                if (running) {
                    seconds++;
                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 0000);
            }
        });
    }
}



