package bymihaj.ecrm.schedule.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bymihaj.ecrm.model.Schedule;

@RestController
public class ScheduleController {
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping({"/schedule"})
    public Object getScheduleList() {
        List<Schedule> list = new ArrayList<>();
        scheduleRepository.findAll().forEach(s -> list.add(modelMapper.map(s, Schedule.class)));
        return list;
    }
    
    @GetMapping({"/schedule/{id}"})
    public Object getSchedule(@PathVariable("id") Long id) {
        Optional<ScheduleDTO> dto = scheduleRepository.findById(id);
        if(dto.isPresent()) {
            return modelMapper.map(dto, Schedule.class);
        } else {
            return null;
        }
    }
    
    @PostMapping("/schedule")
    public void postSchedule(@RequestBody Schedule schedule) {
        ScheduleDTO dto = modelMapper.map(schedule, ScheduleDTO.class);
        scheduleRepository.save(dto);
    }
    
    @DeleteMapping("/schedule/{id}")
    public void deleteSchedule(@PathVariable("id") Long id) {
        scheduleRepository.deleteById(id);
    }

}
