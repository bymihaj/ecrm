package bymihaj.ecrm.erm.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bymihaj.ecrm.model.Student;

@Controller
public class StudentController {

    @Autowired
    private UtilService utilService;
    
    @Autowired
    private RestService restService;
    
    @GetMapping("/scheduleBoard")
    public String scheduleBoard(Model model) {
        model.addAttribute("array", restService.getAllSchedule());
        return "scheduleBoard";
    }
    
    @GetMapping("/registration/{lessonId}")
    public String registration(Model model, @PathVariable("lessonId") Long lessonId) {
        Student student = new Student();
        student.setLessonId(lessonId);
        model.addAttribute("schedule", restService.getSchedule(lessonId));
        model.addAttribute("student", student);
        return "registrationForm";
    }
    
    @PostMapping("/registrationConfirm")
    public String registrationConfirm(Model model, @ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        try {
            restService.studentApply(student);
        } catch (HttpClientErrorException ex) {
            if(utilService.badRequest(model, ex) || HttpStatus.NOT_ACCEPTABLE.equals(ex.getStatusCode())) {
                model.addAttribute("schedule", restService.getSchedule(student.getLessonId()));
                model.addAttribute("student", student);
                return "registrationForm";
            }
        }
        
        model.addAttribute("schedule", restService.getSchedule(student.getLessonId()));
        return "registrationConfirm";
    }
}
