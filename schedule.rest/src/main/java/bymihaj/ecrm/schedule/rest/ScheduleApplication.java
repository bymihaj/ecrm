package bymihaj.ecrm.schedule.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

// TODO stop correct with db shutdown

@SpringBootApplication
@ControllerAdvice
public class ScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleApplication.class, args);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<String> handle(ResponseStatusException ex) {
	    return new ResponseEntity<String>(ex.getMessage(), ex.getStatus());
	}

}
