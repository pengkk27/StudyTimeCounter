package pers.pengkk27.studytimecounter.entity;

import org.litepal.crud.LitePalSupport;

public class LearningTime extends LitePalSupport {
    private int id;
    private long learningTime;
    private int isStart;
    private int activeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLearningTime() {
        return learningTime;
    }

    public void setLearningTime(long learningTime) {
        this.learningTime = learningTime;
    }

    public int getIsStart() {
        return isStart;
    }

    public void setIsStart(int isStart) {
        this.isStart = isStart;
    }

    public int getActiveId() {
        return activeId;
    }

    public void setActiveId(int activeId) {
        this.activeId = activeId;
    }

    @Override
    public String toString() {
        return "LearningTime{" +
                "id=" + id +
                ", learningTime=" + learningTime +
                ", isStart=" + isStart +
                ", activeId=" + activeId +
                '}';
    }
}
