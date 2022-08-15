package sk.upjs.ics.android.beachcoachapp.hraci;

import androidx.appcompat.app.AppCompatActivity;
import sk.upjs.ics.android.beachcoachapp.R;

import android.os.Bundle;
import android.widget.TextView;

public class PlayerActivity extends AppCompatActivity {

    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

    }
}