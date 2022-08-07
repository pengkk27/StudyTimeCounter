package pers.pengkk27.studytimecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView getup_time;
    private TextView sleep_time;
    private TextView learning_time;
    private TextView active_time;
    private TextView percentage_time;

    /****************记录按钮****************/
    private Button getUp;
    private Button sleep;
    private Button learning;
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}