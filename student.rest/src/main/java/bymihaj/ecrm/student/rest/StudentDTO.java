package bymihaj.ecrm.student.rest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "student")
public class StudentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Positive
    private long lessonId;
    
    @Pattern(regexp = "(\\+)[0-9]{12}")
    private String phone;
    
    public StudentDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
