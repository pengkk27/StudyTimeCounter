package pers.pengkk27.studytimecounter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pers.pengkk27.studytimecounter.pop.GetUp;
import pers.pengkk27.studytimecounter.pop.Learning;
import pers.pengkk27.studytimecounter.pop.Sleep;
import pers.pengkk27.studytimecounter.utils.TimeUtil;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences share;
    private SharedPreferences.Editor editor;

    private TextView getup_time;
    private TextView sleep_time;
    private TextView learning_time;
    private TextView active_time;
    private TextView percentage_time;
    private TextView start_learning_time;
    private TextView end_learning_time;

    private Button getUp;
    private Button sleep;
    private Button learning;
    private Button clear;
    private Button start_learning_time_button;
    private Button end_learning_time_button;
    private Button clear_time_record;


    GetUp getupClick;
    Sleep sleepClick;
    Learning learningClick;

    private TimeUtil timeUtil;


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
        start_learning_time = findViewById(R.id.start_learning_time);
        end_learning_time = findViewById(R.id.end_learning_time);

        getUp = findViewById(R.id.getup);
        sleep = findViewById(R.id.sleep);
        learning = findViewById(R.id.learning);
        clear = findViewById(R.id.clear);
        start_learning_time_button = findViewById(R.id.start_learning_time_button);
        end_learning_time_button = findViewById(R.id.end_learning_time_button);
        clear_time_record = findViewById(R.id.clear_time_record);



        timeUtil = new TimeUtil();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initData();
        initTimeRecord();

        start_learning_time_button.setOnClickListener(v -> {
            String currentTime = timeUtil.getCurrentTime(System.currentTimeMillis());
            editor.putString("start_learning_time", currentTime);
            editor.apply();
            start_learning_time.setText(currentTime);
        });
        end_learning_time_button.setOnClickListener(v -> {
            String currentTime = timeUtil.getCurrentTime(System.currentTimeMillis());
            editor.putString("end_learning_time", currentTime);
            editor.apply();
            end_learning_time.setText(currentTime);
        });

        clear_time_record.setOnClickListener(v -> {
            editor.putString("start_learning_time", "未记录");
            editor.putString("end_learning_time", "未记录");
            editor.apply();
            initTimeRecord();
        });
        
        getUp.setOnClickListener(this::showGetUpInput);
        sleep.setOnClickListener(this::showSleepInput);
        learning.setOnClickListener(this::showLearnInput);

        clear.setOnClickListener(v -> {
            editor.clear();
            editor.apply();
            initData();
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
                activeTime = timeUtil.countTimeBetween(getUp, sleep);
            } else {
                String currentTime = timeUtil.getCurrentTime(System.currentTimeMillis());
                activeTime = timeUtil.countTimeBetween(getUp, currentTime);
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

    private void initTimeRecord() {
        String startLearningTime = share.getString("start_learning_time", "未记录");
        String endLearningTime = share.getString("end_learning_time", "未记录");
        start_learning_time.setText(startLearningTime);
        end_learning_time.setText(endLearningTime);
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
                    if (!timeUtil.adjustTime(getUpTime)) {
                        getupClick.hide();
                        Toast.makeText(MainActivity.this, "输入时间有问题，请重新输入", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    editor.putString("getUp", getUpTime);
                    editor.apply();
                    getupClick.hide();
                    initData();
                    break;
                case R.id.sleep_btn_save_pop:
                    String sleepTime = sleepClick.sleep_time_pop.getText().toString().trim();
                    if (!timeUtil.adjustTime(sleepTime)) {
                        sleepClick.hide();
                        Toast.makeText(MainActivity.this, "输入时间有问题，请重新输入", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    editor.putString("sleep", sleepTime);
                    editor.apply();
                    sleepClick.hide();
                    initData();
                    break;
                case R.id.learning_btn_save_pop:
                    String startLearningTime = learningClick.start_learning_time_pop.getText().toString().trim();
                    String endLearningTime = learningClick.end_learning_time_pop.getText().toString().trim();
                    if (!timeUtil.adjustTime(startLearningTime, endLearningTime)) {
                        learningClick.hide();
                        Toast.makeText(MainActivity.this, "输入时间有问题，请重新输入", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    int totalTime = share.getInt("learning_time", 0) + timeUtil.countTimeBetween(startLearningTime, endLearningTime);

                    editor.putInt("learning_time", totalTime);
                    editor.apply();
                    learningClick.hide();
                    initData();
                    break;
            }
        }
    };



}