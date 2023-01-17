package bymihaj.ecrm.erm.ui;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UtilService {

    private final static Logger log = LoggerFactory.getLogger(UtilService.class);
    
    public boolean badRequest(Model model,HttpClientErrorException ex) {
        if(HttpStatus.BAD_REQUEST.equals(ex.getStatusCode())) {
            TypeReference<HashMap<String, String>> typeReference = new TypeReference<HashMap<String,String>>() {};
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                int index = ex.getMessage().indexOf("\"");
                String json = ex.getMessage().substring(index+1,ex.getMessage().length()-1);
                Map<String, String> errorMap = objectMapper.readValue(json, typeReference);
                errorMap.entrySet().forEach(e -> model.addAttribute("error_"+e.getKey(), e.getValue()));
            } catch (JsonProcessingException e) {
                log.error("Impossible to parse json", e);
            }
            return true;
        } else { 
            return false;
        }
    
    }
}
