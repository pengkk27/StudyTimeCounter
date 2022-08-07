package pers.pengkk27.studytimecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.LitePal;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import pers.pengkk27.studytimecounter.entity.ActiveTime;
import pers.pengkk27.studytimecounter.entity.LearningTime;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView getup_time_yesterday;
    private TextView sleep_time_yesterday;
    private TextView learning_time_yesterday;
    private TextView active_time_yesterday;
    private TextView percentage_time_yesterday;

    private TextView getup_time_today;
    private TextView sleep_time_today;
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

    /**
     * 初始化数值界面。
     * 查询数据库中的信息，时候存在数据。若存在，则显示计算出的数据；若不存在，则显示“未记录”
     */
    private void init() {
        // 查询昨天的起床时间，若查找不到，则后面的数据全部为“未记录”
        ActiveTime yesterdayGetUp = LitePal.find(ActiveTime.class, 100);
        if (yesterdayGetUp == null) {
            getup_time_yesterday.setText("未记录");
            sleep_time_yesterday.setText("未记录");
            learning_time_yesterday.setText("未记录");
            active_time_yesterday.setText("未记录");
            percentage_time_yesterday.setText("未记录");
        }

        LearningTime learningTimeYest = LitePal.where("activeId = ?", "100").find(LearningTime.class).get(0);

        getup_time_yesterday.setText(timeChange(yesterdayGetUp.getGetUpTime()));
        sleep_time_yesterday.setText(timeChange(yesterdayGetUp.getSleepTime()));
        learning_time_yesterday.setText(countTime(learningTimeYest.getLearningTime()));
        active_time_today.setText(countTime(yesterdayGetUp.getGetUpTime(), yesterdayGetUp.getSleepTime()));
        percentage_time_yesterday.setText(countPercentage(yesterdayGetUp.getActiveTime(), learningTimeYest.getLearningTime()));

        /*------------------------------------------------------------------------------------------------*/

        ActiveTime todayGetUp = LitePal.find(ActiveTime.class, 200);
        if (todayGetUp == null) {
            getup_time_today.setText("未记录");
            sleep_time_today.setText("未记录");
            learning_time_today.setText("未记录");
            active_time_today.setText("未记录");
            percentage_time_today.setText("未记录");
        }


        LearningTime learningTimeToDay = LitePal.where("activeId = ?", "200").find(LearningTime.class).get(0);

        getup_time_today.setText(timeChange(todayGetUp.getGetUpTime()));
        sleep_time_today.setText(timeChange(todayGetUp.getSleepTime()));
        learning_time_today.setText(countTime(learningTimeToDay.getLearningTime()));
        active_time_today.setText(countTime(todayGetUp.getGetUpTime(), todayGetUp.getSleepTime()));
        percentage_time_today.setText(countPercentage(todayGetUp.getActiveTime(), learningTimeToDay.getLearningTime()));
    }

    /**
     * 将时刻转换为时间点数
     *
     * @return
     */
    public String timeChange(long currentTime) {
        String time = new Date(currentTime).toString();
        String[] times = time.split(" ");
        String hour = times[3].split(":")[0] + ":" + times[3].split(":")[1];
        return hour;
    }

    public String countTime(long time) {
        long totalSeconds = time / 1000;
        long totalMinutes = totalSeconds / 60;
        long totalHour = totalMinutes / 60;
        Long result = new Long(totalHour);
        return result.toString();
    }

    public String countTime(long time1, long time2) {
        long time = time2 - time1;
        return countTime(time);
    }

    public String countPercentage(long activeTime, long learningTime) {
        double percentage = (double) (Math.round(learningTime * 100 / activeTime) / 100.0);
        return (new Double(percentage * 100).toString()) + "%";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

}