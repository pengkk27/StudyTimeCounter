package pers.pengkk27.studytimecounter.entity;

import org.litepal.crud.LitePalSupport;

public class Percentage extends LitePalSupport {
    private int id;
    private long date;
    private double percentage;
    private int activeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getActiveId() {
        return activeId;
    }

    public void setActiveId(int activeId) {
        this.activeId = activeId;
    }

    @Override
    public String toString() {
        return "Percentage{" +
                "id=" + id +
                ", date=" + date +
                ", percentage=" + percentage +
                ", activeId=" + activeId +
                '}';
    }
}
