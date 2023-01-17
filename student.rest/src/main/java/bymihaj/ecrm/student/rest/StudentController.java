package bymihaj.ecrm.student.rest;

import java.util.Collection;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import bymihaj.ecrm.model.Schedule;
import bymihaj.ecrm.model.Student;

@RestController
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @PostMapping("/studentApply")
    public void studentApply(@RequestBody Student student) {
        // TODO check phone number is registered
        // TODO check that student has available lessons
        
        
        RestTemplate rest = new RestTemplate();
        Schedule schedule = rest.getForObject("http://localhost:8081/schedule/"+student.getLessonId(), Schedule.class);
        
        if(Objects.isNull(schedule)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson ID "+ student.getLessonId() + " not found");
        }
        
        Collection<StudentDTO> dublicateSearch = studentRepository.findPhoneLesson(student.getPhone(), student.getLessonId());
        if(!dublicateSearch.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Already registered");
        }
        
        
        StudentDTO dto = modelMapper.map(student, StudentDTO.class);
        studentRepository.save(dto);
    }
    
    @GetMapping("/studentCount/{lessonId}")
    public Long getStudentCount(@PathVariable Long lessonId) {
        return studentRepository.countInLesson(lessonId);
    }
    
    @GetMapping("/studentList/{lessonId}")
    public Collection<Student> getStudentListForLesson(@PathVariable Long lessonId) {
        Collection<StudentDTO> dtoList = studentRepository.getRegisteredStudentForLesson(lessonId);
        return modelMapper.map(dtoList,  new TypeToken<Collection<Student>>() {}.getType());
    }

}
