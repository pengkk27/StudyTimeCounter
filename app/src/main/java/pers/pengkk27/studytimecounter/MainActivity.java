package pers.pengkk27.studytimecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button getUp;
    private Button sleep;
    private Button learning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUp = findViewById(R.id.getup);
        sleep = findViewById(R.id.sleep);
        learning = findViewById(R.id.learning);



    }
}