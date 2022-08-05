package pers.pengkk27.studytimecounter.entity;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class ActiveTime extends LitePalSupport {

    private int id;
    private long getUpTime;
    private long sleepTime;
    private long activeTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getGetUpTime() {
        return getUpTime;
    }

    public void setGetUpTime(long getUpTime) {
        this.getUpTime = getUpTime;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }

    @Override
    public String toString() {
        return "ActiveTime{" +
                "id=" + id +
                ", getUpTime=" + getUpTime +
                ", sleepTime=" + sleepTime +
                ", activeTime=" + activeTime +
                '}';
    }
}
