package bymihaj.ecrm.student.rest;

import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.validation.ConstraintViolationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@SpringBootApplication
@ControllerAdvice
public class StudentApplication implements ServletContextListener {

    @Autowired
    private JdbcTemplate jdbc;
    
    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handle(ResponseStatusException ex) {
        return new ResponseEntity<String>(ex.getMessage(), ex.getStatus());
    }
    
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = ex.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(v -> v.getPropertyPath().toString(), v -> v.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
    //shutdown
    @Bean
    ServletListenerRegistrationBean<ServletContextListener> servletListener() {
        ServletListenerRegistrationBean<ServletContextListener> srb
          = new ServletListenerRegistrationBean<>();
        srb.setListener(this);
        return srb;
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        jdbc.execute("SHUTDOWN");
    }
}
