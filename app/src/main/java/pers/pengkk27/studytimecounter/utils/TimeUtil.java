package pers.pengkk27.studytimecounter.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author pengkk27
 * <p>
 * 时间计算工具类
 */
public class TimeUtil {


    /**
     * 判断输入的文字时间是否合理
     */
    public boolean adjustTime(String timeString) {
        // 先判断是否为空值
        if (StringUtils.isBlank(timeString)) {
            return false;
        }

        // 判断是否为一个时刻
        if (!timeString.contains(":") || !timeString.contains("：")) {
            return false;
        }

        // 拆分分号左右两边的数字
        String[] timeList = timeString.split("[:|：]");
        if (ObjectUtils.isEmpty(timeList) || timeList.length < 2) {
            return false;
        }

        int hour;
        int minute;
        try {
            hour = Integer.parseInt(timeList[0]);
            minute = Integer.parseInt(timeList[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        if (hour < 0 || hour > 24) {
            return false;
        }
        if (minute < 0 || minute > 60) {
            return false;
        }

        return true;
    }

    /**
     * 判断两个时间段是否合理
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    public boolean adjustTime(String startTime, String endTime) {
        if (!adjustTime(startTime) || !adjustTime(endTime)) {
            return false;
        }

        if (countTimeBetween(startTime, endTime) < 0) {
            return false;
        }

        return true;
    }

    /**
     * 计算两个时刻之间的时间（分钟）
     * @param time1 开始时间
     * @param time2 结束时间
     * @return 时刻之间的时间
     */
    public int countTimeBetween(String time1, String time2) {
        String[] time1List = time1.split("[:|：]");
        String[] time2List = time2.split("[:|：]");
        int start = Integer.parseInt(time1List[0]) * 60 + Integer.parseInt(time1List[1]);
        int end = Integer.parseInt(time2List[0]) * 60 + Integer.parseInt(time2List[1]);
        return end - start;
    }
}
