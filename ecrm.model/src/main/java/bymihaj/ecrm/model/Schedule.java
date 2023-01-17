package bymihaj.ecrm.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Schedule {
    
    private long id;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start;
    
    private String stringStart;
    
    private int minutDuration;
    private String groupLevel;
    private int groupSize;
    
    public Schedule() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getMinutDuration() {
        return minutDuration;
    }

    public void setMinutDuration(int minutDuration) {
        this.minutDuration = minutDuration;
    }

    public String getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(String groupLevel) {
        this.groupLevel = groupLevel;
    }

    public String getStringStart() {
        return stringStart;
    }

    public void setStringStart(String stringStart) {
        this.stringStart = stringStart;
    }

}
