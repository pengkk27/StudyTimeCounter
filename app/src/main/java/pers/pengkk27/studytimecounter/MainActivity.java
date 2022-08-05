package pers.pengkk27.studytimecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView getup_time_yesterday;
    private  TextView sleep_time_yesterday;
    private TextView learning_time_yesterday;
    private TextView active_time_yesterday;
    private TextView percentage_time_yesterday;

    private TextView getup_time_today;
    private  TextView sleep_time_today;
    private TextView learning_time_today;
    private TextView active_time_today;
    private TextView percentage_time_today;

    private Button getUp;
    private Button sleep;
    private Button learning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LitePal.initialize(this);
        setContentView(R.layout.activity_main);

        getup_time_yesterday = findViewById(R.id.getup_time_yesterday);
        sleep_time_yesterday = findViewById(R.id.sleep_time_yesterday);
        learning_time_yesterday = findViewById(R.id.learning_time_yesterday);
        active_time_yesterday = findViewById(R.id.active_time_yesterday);
        percentage_time_yesterday = findViewById(R.id.percentage_time_yesterday);

        getup_time_today = findViewById(R.id.getup_time_today);
        sleep_time_today = findViewById(R.id.sleep_time_today);
        learning_time_today = findViewById(R.id.learning_time_today);
        active_time_today = findViewById(R.id.active_time_today);
        percentage_time_today = findViewById(R.id.percentage_time_today);

        getUp = findViewById(R.id.getup);
        sleep = findViewById(R.id.sleep);
        learning = findViewById(R.id.learning);

    }

    private void init() {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

}