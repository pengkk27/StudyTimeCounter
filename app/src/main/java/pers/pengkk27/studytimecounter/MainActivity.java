package pers.pengkk27.studytimecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import pers.pengkk27.studytimecounter.pop.GetUp;
import pers.pengkk27.studytimecounter.pop.Learning;
import pers.pengkk27.studytimecounter.pop.Sleep;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences share;
    private SharedPreferences.Editor editor;

    private TextView getup_time;
    private TextView sleep_time;
    private TextView learning_time;
    private TextView active_time;
    private TextView percentage_time;

    private Button getUp;
    private Button sleep;
    private Button learning;
    private Button clear;

    GetUp getupClick;
    Sleep sleepClick;
    Learning learningClick;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        share = getSharedPreferences("time", MODE_PRIVATE);
        editor = share.edit();

        getup_time = findViewById(R.id.getup_time);
        sleep_time = findViewById(R.id.sleep_time);
        learning_time = findViewById(R.id.learning_time);
        active_time = findViewById(R.id.active_time);
        percentage_time = findViewById(R.id.percentage_time);

        getUp = findViewById(R.id.getup);
        sleep = findViewById(R.id.sleep);
        learning = findViewById(R.id.learning);
        clear = findViewById(R.id.clear);

        initData();

        getUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGetUpInput(v);
            }
        });
        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSleepInput(v);
            }
        });
        learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLearnInput(v);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();
                initData();
            }
        });
    }

    private void initData() {

        String getUp = share.getString("getUp", "未记录");
        String sleep = share.getString("sleep", "未记录");
        int learning = share.getInt("learning_time", 0);

        getup_time.setText(getUp);
        sleep_time.setText(sleep);
        learning_time.setText(learning + "分钟");

        int activeTime = 0;

        if (getUp == null || getUp.length() == 0 || getUp.equals("未记录")) {
            active_time.setText("未记录");
        } else {
            if (sleep != null && sleep.length() != 0 && !"未记录".equals(sleep)) {
                activeTime = countTimeBetween(getUp, sleep);
            } else {
                String currentTime = getCurrentTime(System.currentTimeMillis());
                activeTime = countTimeBetween(getUp, currentTime);
            }
            active_time.setText(activeTime + "分钟");
        }

        if (activeTime == 0) {
            percentage_time.setText("0%");
        } else {
            int percentage = (int) ((learning * 1.0 / activeTime) * 100);
            percentage_time.setText(percentage + "%");
        }
    }

    public void showGetUpInput(View view) {
        getupClick = new GetUp(this, onClickListener);
        getupClick.show();
    }

    public void showSleepInput(View view) {
        sleepClick = new Sleep(this, onClickListener);
        sleepClick.show();
    }

    public void showLearnInput(View view) {
        learningClick = new Learning(this, onClickListener);
        learningClick.show();
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.getup_btn_save_pop:
                    String getUpTime = getupClick.getup_time_pop.getText().toString().trim();
                    editor.putString("getUp", getUpTime);
                    editor.apply();
                    getupClick.hide();
                    initData();
                    break;
                case R.id.sleep_btn_save_pop:
                    String sleepTime = sleepClick.sleep_time_pop.getText().toString().trim();
                    editor.putString("sleep", sleepTime);
                    editor.apply();
                    sleepClick.hide();
                    initData();
                    break;
                case R.id.learning_btn_save_pop:
                    String startLearningTime = learningClick.start_learning_time_pop.getText().toString().trim();
                    String endLearningTime = learningClick.end_learning_time_pop.getText().toString().trim();

                    int totalTime = share.getInt("learning_time", 0) + countTimeBetween(startLearningTime, endLearningTime);

                    editor.putInt("learning_time", totalTime);
                    editor.apply();
                    learningClick.hide();
                    initData();
                    break;
            }
        }
    };

    public int countTimeBetween(String time1, String time2) {
        String[] time1List = time1.split("[:|：]");
        String[] time2List = time2.split("[:|：]");
        int start = Integer.parseInt(time1List[0]) * 60 + Integer.parseInt(time1List[1]);
        int end = Integer.parseInt(time2List[0]) * 60 + Integer.parseInt(time2List[1]);
        return end - start;
    }

    public String getCurrentTime(long currentTime) {
        String time = new Date(currentTime).toString();
        String[] times = time.split(" ");
        String hour = times[3].split(":")[0] + ":" + times[3].split(":")[1];
        return hour;
    }

}