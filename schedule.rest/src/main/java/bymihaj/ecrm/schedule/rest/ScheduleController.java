package bymihaj.ecrm.schedule.rest;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

// TODO add validation prevent duplication with the same time
// JavaEE standart? jsv-3xx ?/

@RestController
public class ScheduleController {
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @GetMapping({"/schedule","/schedule/{id}"})
    public Object getSchedule(@PathVariable("id") Optional<Long> id) {
        if(id.isPresent()) {
            return scheduleRepository.findById(id.get());
        } else {
            return scheduleRepository.findAll();
        }
    }
    
    @PostMapping("/schedule/{start}/{minuteDuration}/{groupLevel}/{groupSize}")
    public void postSchedule(
            @PathVariable("start") @DateTimeFormat(iso=ISO.DATE_TIME) Date start,
            @PathVariable("minuteDuration") int minuteDuration,
            @PathVariable("groupLevel") String groupLevel,
            @PathVariable("groupSize") int groupSize) {
        Schedule schedule = new Schedule();
        schedule.setStart(start);
        schedule.setMinutDuration(minuteDuration);
        schedule.setGroupLevel(groupLevel);
        schedule.setGroupSize(groupSize);
        scheduleRepository.save(schedule);
    }
    
    @PutMapping("/schedule/{id}/{start}/{minuteDuration}/{groupLevel}/{groupSize}")
    public void putSchedule(
            @PathVariable("id") Long id,
            @PathVariable("start") @DateTimeFormat(iso=ISO.DATE_TIME) Date start,
            @PathVariable("minuteDuration") int minuteDuration,
            @PathVariable("groupLevel") String groupLevel,
            @PathVariable("groupSize") int groupSize) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
        if(scheduleOptional.isPresent()) {
            Schedule schedule = scheduleOptional.get();
            schedule.setStart(start);
            schedule.setMinutDuration(minuteDuration);
            schedule.setGroupLevel(groupLevel);
            schedule.setGroupSize(groupSize);
            scheduleRepository.save(schedule);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID " + id + " not found");
        }
        
    }

}
