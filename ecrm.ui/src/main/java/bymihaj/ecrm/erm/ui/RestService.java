package bymihaj.ecrm.erm.ui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import bymihaj.ecrm.model.Schedule;
import bymihaj.ecrm.model.Student;

@Component
public class RestService {
    
    @Value("${rest.schedule.path}")
    private String schedulePath;
    
    @Value("${rest.student.path}")
    private String studentPath;
    
    private RestTemplate rest;
    
    public RestService() {
        rest = new RestTemplate();
    }
    
    // teacher
    
    public Schedule[] getAllSchedule() {
        return rest.getForEntity(schedulePath, Schedule[].class).getBody();
    }
    
    public Schedule getSchedule(Long id) {
        return rest.getForObject(schedulePath+id, Schedule.class);
    }
    
    public void postSchedule(Schedule schedule) {
        rest.postForObject(schedulePath, schedule, Schedule.class);
    }
    
    public void deleteSchedule(Long id) {
        rest.delete(schedulePath+id);;
    }
    
    // student
    
    public void studentApply(Student student) {
        rest.postForObject(studentPath+"studentApply", student, Student.class);
    }
    
    public Long getStudentCountInRoom(Long lessonId) {
        return rest.getForEntity(studentPath+"studentCount/"+lessonId, Long.class).getBody();
    }
    
    public Student[] getStudentInRoom(Long id) {
        return rest.getForEntity(studentPath + "studentList/"+id, Student[].class).getBody();
    }
}
