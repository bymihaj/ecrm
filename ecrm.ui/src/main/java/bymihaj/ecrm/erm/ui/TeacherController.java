package bymihaj.ecrm.erm.ui;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;

import bymihaj.ecrm.model.ClassRoom;
import bymihaj.ecrm.model.Schedule;

@Controller
public class TeacherController {
    
    private final static Logger log = LoggerFactory.getLogger(TeacherController.class);
    
    @PreAuthorize("hasRole('TEACHER')")
    @Retention(RetentionPolicy.RUNTIME)
    private @interface TeacherRole {}
    
    @Autowired
    private RestService restService;
    
    @Autowired
    private UtilService utilService;
    
    @TeacherRole
    @GetMapping("/scheduleNew") 
    public String getScheduleForm(Model model) {
        Schedule schedule = new Schedule();
        model.addAttribute("schedule", schedule);
        return "scheduleForm";
    }
    
    @TeacherRole
    @GetMapping("/scheduleList")
    public String getScheduleList(Model model) {
        List<ClassRoom> classRoomList = new ArrayList<>();
        for(Schedule schedule :restService.getAllSchedule()) {
            ClassRoom classRoom = new ClassRoom(schedule);
            classRoom.setStudentCount(restService.getStudentCountInRoom(schedule.getId()));
            classRoomList.add(classRoom);
        }
        
        model.addAttribute("array", classRoomList);
        return "scheduleList";
    }
        
    @TeacherRole
    @PostMapping("/schedule")
    public String postScheduleForm(Model model, @ModelAttribute Schedule schedule) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            schedule.setStart(sdf.parse(schedule.getStringStart()));
        } catch (ParseException e) {
            log.error("Unable to parse date-time", e);
        }
        
        try {
            restService.postSchedule(schedule);
        } catch (HttpClientErrorException ex) {
            if(utilService.badRequest(model, ex)) {
                return "scheduleForm"; 
            }
        }
        
        return "scheduleForm";
    }
    
    @TeacherRole
    @GetMapping("/scheduleRemove/{id}")
    public String scheduleRemove(Model model, @PathVariable("id") Long id) {
        model.addAttribute("schedule", restService.getSchedule(id));
        return "scheduleRemoveDialog";
    }
    
    @TeacherRole
    @GetMapping("/scheduleRemoveConfirm/{id}")
    public String scheduleRemoveConfirmed(@PathVariable("id") Long id) {
        restService.deleteSchedule(id);
        return "redirect:/scheduleList";
    }
    
    @TeacherRole
    @GetMapping("/classRoom/{id}")
    public String getStudentInRoom(Model model, @PathVariable("id") Long id) {
        model.addAttribute("array", restService.getStudentInRoom(id));
        return "classRoom";
    }
    
}
