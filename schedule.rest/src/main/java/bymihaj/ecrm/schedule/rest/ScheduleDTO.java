package bymihaj.ecrm.schedule.rest;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name="schedule")
public class ScheduleDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Future(message="Should be in a future")
    private Date start;
    
    @Positive(message="What is lesson duratin?")
    private int minutDuration;
    
    @NotBlank(message="What is group level?")
    private String groupLevel;
    
    @Positive(message="What is group size?")
    private int groupSize;
    
    public ScheduleDTO() {}

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

}
