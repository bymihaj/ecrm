package bymihaj.ecrm.model;

public class ClassRoom extends Schedule {
    
    private Long studentCount;
    
    public ClassRoom(Schedule schedule) {
        this.setId(schedule.getId());
        this.setStart(schedule.getStart());
        this.setMinutDuration(schedule.getMinutDuration());
        this.setGroupLevel(schedule.getGroupLevel());
        this.setGroupSize(schedule.getGroupSize());
    }

    public Long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
    }
}
