package pers.pengkk27.studytimecounter.entity;

import org.litepal.crud.LitePalSupport;

public class LearningTime extends LitePalSupport {
    private int id;
    private long startTime;
    private long endTime;
    private int isStart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getIsStart() {
        return isStart;
    }

    public void setIsStart(int isStart) {
        this.isStart = isStart;
    }

    @Override
    public String toString() {
        return "LearningTime{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isStart=" + isStart +
                '}';
    }
}
