package sk.upjs.ics.android.beachcoachapp.pomocky.Timer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import sk.upjs.ics.android.beachcoachapp.R;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import com.shawnlin.numberpicker.NumberPicker;
import android.widget.TextView;

import java.util.Locale;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class ToolsTimerFragment extends Fragment {

    private EditText mEditTextInput;
    private TextView mTextViewCountDown;
    private ImageButton mButtonSet;
    private ImageButton mButtonStartPause;
    private ImageButton mButtonReset;
    private EditText medit_text_inputSecond;
    private NumberPicker numPick;
    private NumberPicker numPickMin;


    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;
    private boolean isStopped=false;
    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;
    private int FinnalyTime;
    private int PickerMinNum;
    private int PickerSekNum;


    public ToolsTimerFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tools_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextViewCountDown = (TextView)view.findViewById(R.id.text_view_countdown);
        numPick =(NumberPicker)view.findViewById(R.id.numPick);
        numPickMin = (NumberPicker)view.findViewById(R.id.numPickMin);
        mButtonSet = (ImageButton)view.findViewById(R.id.button_set);
        mButtonStartPause = view.findViewById(R.id.button_start_pause);
        mButtonReset = view.findViewById(R.id.button_reset);
        numPick.setMinValue(0);
        numPick.setMaxValue(60);
        numPickMin.setMaxValue(60);
        numPickMin.setMinValue(0);
        setTimeFromNumPick();


        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeFromNumPick();
                setTime(FinnalyTime);

            }
        });
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    if(isStopped){
                        startTimer();
                    }
                    else{
                        setTimeFromNumPick();
                        setTime(FinnalyTime);
                        startTimer();
                    }
                }
            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

    }
    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }
    private void setTimeFromNumPick(){
        numPick.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                PickerSekNum = i1;
            }
        });
        numPickMin.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                PickerMinNum = i1;
            }
        });
        FinnalyTime = (PickerSekNum*1000)+(PickerMinNum*60000);
    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis+1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                isStopped=false;
                mTimerRunning = false;
                updateWatchInterface();
            }
        }.start();

        mTimerRunning = true;
        updateWatchInterface();
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        //mButtonStartPause.setBackgroundResource(R.drawable.ic_pause);
        isStopped=true;
        mTimerRunning = false;
        updateWatchInterface();
    }
    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
        isStopped=false;
        //mButtonStartPause.setBackgroundResource(R.drawable.ic_pause);
        mButtonSet.setVisibility(View.INVISIBLE);
    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }

        mTextViewCountDown.setText(timeLeftFormatted);
    }
    private void updateWatchInterface() {
        if (mTimerRunning) {
            mButtonReset.setEnabled(false);
            //mEditTextInput.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);
            // mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setImageResource(R.drawable.ic_pause);
        } else {
            mButtonReset.setEnabled(true);
            //mEditTextInput.setVisibility(View.VISIBLE);
            mButtonSet.setVisibility(View.VISIBLE);
            mButtonStartPause.setImageResource(R.drawable.ic_baseline_start);

            if (mTimeLeftInMillis < 1000) {
                mButtonStartPause.setVisibility(View.INVISIBLE);
            } else {
                mButtonStartPause.setVisibility(View.VISIBLE);
            }

            if (mTimeLeftInMillis < mStartTimeInMillis) {
                mButtonReset.setVisibility(View.VISIBLE);
            } else {
                mButtonReset.setVisibility(View.
                        VISIBLE);
            }
        }
    }
    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus(); //Tu mozno chyba pozor
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);  //i tu mozno pozor pozor
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }




}